package com.github.watchmaker.io.trackme.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.github.watchmaker.io.trackme.service.TrackmeServiceApplication
import groovyx.net.http.RESTClient
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.core.MediaType

@ContextConfiguration(classes = TrackmeServiceApplication.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
abstract class BaseEndpointSpecification extends Specification {

    @Shared
    RESTClient restClient

    @Shared
    ObjectMapper objectMapper

    def setupSpec() {
        restClient = new RESTClient("http://localhost:8080", MediaType.APPLICATION_JSON)
        objectMapper = new ObjectMapper()
                    .registerModule(new JodaModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

}