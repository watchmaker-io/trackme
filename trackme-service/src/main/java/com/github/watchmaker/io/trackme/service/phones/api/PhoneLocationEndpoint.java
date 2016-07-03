package com.github.watchmaker.io.trackme.service.phones.api;

import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocation;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationFactory;
import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
public class PhoneLocationEndpoint extends AbstractPhoneEndpoint{
    public static final String PATH = "/phone";

    private PhoneLocationService phoneLocationService;


    @Autowired
    public PhoneLocationEndpoint(PhoneLocationService phoneLocationService,
                                 @Value("${access.token}") String requiredAccessToken) {
        super(requiredAccessToken);
        this.phoneLocationService = phoneLocationService;
    }

    @Path("/{userId}")
    @POST
    public Response addPhoneLocation(@PathParam("userId") String userId,
                                     @QueryParam("accessToken") String accessToken,
                                     @NotNull @Valid PhoneLocationRequest request) {
        checkAccessToken(accessToken);

        PhoneLocation phoneLocation = PhoneLocationFactory.create(userId, request);
        phoneLocationService.insert(phoneLocation);

        return Response.ok().build();
    }

    @Path("/{userId}/last")
    @GET
    public Response list(@NotNull @PathParam("userId") String userIdParam,
                         @QueryParam("accessToken") String accessToken) {
        checkAccessToken(accessToken);

        UUID userId = UUID.fromString(userIdParam);
        PhoneLocation phoneLocation = phoneLocationService.findUserPhoneLastLocation(userId);

        return Response.ok(phoneLocation).build();
    }
}
