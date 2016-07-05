package com.github.watchmaker.io.trackme.service.phones.api;

import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocation;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationFactory;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping(PhoneLocationEndpoint.PATH)
public class PhoneLocationEndpoint extends AbstractPhoneEndpoint{
    public static final String PATH = "/phone";

    private PhoneLocationService phoneLocationService;


    @Autowired
    public PhoneLocationEndpoint(PhoneLocationService phoneLocationService,
                                 @Value("${access.token}") String requiredAccessToken) {
        super(requiredAccessToken);
        this.phoneLocationService = phoneLocationService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    @SendTo("/topic/phone-location-change")
    public PhoneLocation addPhoneLocation(@PathVariable("userId") String userId,
                                          @RequestParam("accessToken") String accessToken,
                                          @RequestBody @NotNull @Valid PhoneLocationRequest request) {
        checkAccessToken(accessToken);

        PhoneLocation phoneLocation = PhoneLocationFactory.create(userId, request);
        phoneLocationService.insert(phoneLocation);

        return phoneLocation;
    }

    @RequestMapping("/{userId}/last")
    public PhoneLocation list(@PathVariable("userId") String userIdParam,
                              @RequestParam("accessToken") String accessToken) {
        checkAccessToken(accessToken);

        UUID userId = UUID.fromString(userIdParam);
        PhoneLocation phoneLocation = phoneLocationService.findUserPhoneLastLocation(userId);

        return phoneLocation;
    }
}
