package com.github.watchmaker.io.trackme.service.phones.config.jersey;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Logger;

@Component
@ApplicationPath("/")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("com.github.watchmaker.io.trackme.service");

        register(new LoggingFilter(Logger.getLogger("JERSEY_LOGGER"), 4096));
    }
}
