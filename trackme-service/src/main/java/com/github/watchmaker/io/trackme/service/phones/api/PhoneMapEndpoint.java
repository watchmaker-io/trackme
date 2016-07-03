package com.github.watchmaker.io.trackme.service.phones.api;

import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PhoneMapEndpoint.PATH)
public class PhoneMapEndpoint {
    public static final String PATH = "/phone-map";

    private PhoneLocationService phoneLocationService;
    private String googleMapsApiKey;


    @Autowired
    public PhoneMapEndpoint(PhoneLocationService phoneLocationService,
                            @Value("${googleMaps.apiKey}") String googleMapsApiKey) {
        this.phoneLocationService = phoneLocationService;
        this.googleMapsApiKey = googleMapsApiKey;
    }

    @RequestMapping(value = "/{userId}")
    public String showPhoneLocation(@PathVariable("userId") String userId,
                                    Model model) {
        model.addAttribute("apiKey", googleMapsApiKey);

        return "phoneLocation";
    }
}
