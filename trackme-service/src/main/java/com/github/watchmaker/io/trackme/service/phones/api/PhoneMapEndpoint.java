package com.github.watchmaker.io.trackme.service.phones.api;

import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocation;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping(PhoneMapEndpoint.PATH)
public class PhoneMapEndpoint extends AbstractPhoneEndpoint{
    public static final String PATH = "/phone-last-location";

    private PhoneLocationService phoneLocationService;
    private String googleMapsApiKey;


    @Autowired
    public PhoneMapEndpoint(PhoneLocationService phoneLocationService,
                            @Value("${googleMaps.apiKey}") String googleMapsApiKey,
                            @Value("${access.token}") String requiredAccessToken) {
        super(requiredAccessToken);
        this.phoneLocationService = phoneLocationService;
        this.googleMapsApiKey = googleMapsApiKey;
    }

    @RequestMapping(value = "/{userId}")
    public String showPhoneLastLocation(@PathVariable("userId") String userIdParam,
                                    @RequestParam("accessToken") String accessToken,
                                    Model model) {
        checkAccessToken(accessToken);

        model.addAttribute("apiKey", googleMapsApiKey);
        UUID userId = UUID.fromString(userIdParam);
        PhoneLocation userPhoneLocation = phoneLocationService.findUserPhoneLastLocation(userId);
        // FIXME dchojnacki ustawianie propertisow szczegolow
        model.addAttribute("location", userPhoneLocation);

        return "phoneLocation";
    }
}
