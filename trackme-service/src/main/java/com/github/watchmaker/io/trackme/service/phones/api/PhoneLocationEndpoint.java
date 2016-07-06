package com.github.watchmaker.io.trackme.service.phones.api;

import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocation;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationFactory;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.core.MessageSendingOperations;
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
public class PhoneLocationEndpoint extends AbstractAccessTokenVerifier {
    public static final String PATH = "/phone";

    private PhoneLocationService phoneLocationService;
    private MessageSendingOperations<String> messagingTemplate;


    @Autowired
    public PhoneLocationEndpoint(PhoneLocationService phoneLocationService,
                                 MessageSendingOperations<String> messagingTemplate,
                                 @Value("${access.token}") String requiredAccessToken) {
        super(requiredAccessToken);
        this.phoneLocationService = phoneLocationService;
        this.messagingTemplate = messagingTemplate;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public PhoneLocationDTO addPhoneLocation(@PathVariable("userId") String userId,
                                             @RequestParam("accessToken") String accessToken,
                                             @RequestBody @NotNull @Valid PhoneLocationRequest request) {
        verifyAccessToken(accessToken);

        PhoneLocation phoneLocation = PhoneLocationFactory.create(userId, request);
        phoneLocationService.insert(phoneLocation);

        PhoneLocationDTO phoneLocationDTO = PhoneLocationFactoryDTO.create(phoneLocation);
        messagingTemplate.convertAndSend("/topic/phoneLocationChange", phoneLocationDTO);

        return phoneLocationDTO;
    }

    @RequestMapping("/{userId}/last")
    public PhoneLocationDTO list(@PathVariable("userId") String userIdParam,
                                 @RequestParam("accessToken") String accessToken) {
        verifyAccessToken(accessToken);

        UUID userId = UUID.fromString(userIdParam);
        PhoneLocation phoneLocation = phoneLocationService.findUserPhoneLastLocation(userId);

        return PhoneLocationFactoryDTO.create(phoneLocation);
    }
}
