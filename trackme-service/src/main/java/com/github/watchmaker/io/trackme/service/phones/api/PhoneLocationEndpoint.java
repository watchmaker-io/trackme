package com.github.watchmaker.io.trackme.service.phones.api;

import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocation;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationFactory;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Component
@Path(PhoneLocationEndpoint.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PhoneLocationEndpoint {
    public static final String PATH = "/phone";

    private PhoneLocationService phoneLocationService;


    @Autowired
    public PhoneLocationEndpoint(PhoneLocationService phoneLocationService) {
        this.phoneLocationService = phoneLocationService;
    }

    @Path("/{userId}")
    @POST
    public Response addPhoneLocation(@PathParam("userId") String userId,
                                     @NotNull @Valid PhoneLocationRequest request){
        PhoneLocation phoneLocation = PhoneLocationFactory.create(userId, request);
        phoneLocationService.insert(phoneLocation);

        return Response.ok().build();
    }

    @Path("/{userId}/list")
    @GET
    public Response list(@NotNull @PathParam("userId") String userIdParam) {
        UUID userId = UUID.fromString(userIdParam);
        List<PhoneLocation> phoneLocations = phoneLocationService.findUserPhoneLocation(userId);

        return Response.ok(phoneLocations).build();
    }
}
