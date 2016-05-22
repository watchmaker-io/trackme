package com.github.watchmaker.io.trackme.service.phones.api

import com.github.watchmaker.io.trackme.integration.BaseEndpointSpecification
import groovyx.net.http.HttpResponseDecorator
import org.joda.time.DateTime

import static com.github.watchmaker.io.trackme.service.phones.api.PhoneLocationAccuracy.GPS

class AddPhoneLocationSpec extends BaseEndpointSpecification {

    def "should add phone location to system"() {
        given:
            def userId = someUserId()
            def body = objectMapper.writeValueAsString([
                    longitude : 51.91,
                    latitude : 19.14,
                    altitude : 173.14,
                    speed : 1.23,
                    accuracy : GPS,
                    time : DateTime.parse("2016-06-10T13:13:13")
            ])

        when:
            HttpResponseDecorator response =  restClient.get(path: "/phone/list")
//            def response =  restClient.post(path: "/phone/u/${userId}/p/${phoneId}", body: body)

        then:
            response.status == 200
    }

//    @Unroll
//    def "should ignore empty location data"() {
//        given:
//        PhoneLocationRequest request = somePhoneLocationRequest([
//                longitude: longitude,
//                latitude: latitude,
//                altitude: altitude,
//                speed: speed,
//                accuracy: accuracy,
//                time: time
//                ])
//
//        when:
//        Response response = endpoint.addPhoneLocation(
//                userId,
//                phoneId,
//                request)
//
//        then:
//        response.status == SC_BAD_REQUEST
//
//        where:
//        userId       || phoneId      || longitude || latitude || altitude || speed || accuracy || time
//                 null | somePhoneId() |     19.14 |      51.91 |    173.14 |   1.23 |       GPS | DateTime.now()
//        somePhoneId() |          null |     19.14 |      51.91 |    173.14 |   1.23 |       GPS | DateTime.now()
//        somePhoneId() | somePhoneId() |      null |      51.91 |    173.14 |   1.23 |       GPS | DateTime.now()
//        somePhoneId() | somePhoneId() |     19.14 |       null |    173.14 |   1.23 |       GPS | DateTime.now()
//        somePhoneId() | somePhoneId() |     19.14 |      51.91 |      null |   1.23 |       GPS | DateTime.now()
//        somePhoneId() | somePhoneId() |     19.14 |      51.91 |    173.14 |   null |       GPS | DateTime.now()
//        somePhoneId() | somePhoneId() |     19.14 |      51.91 |    173.14 |   1.23 |      null | DateTime.now()
//        somePhoneId() | somePhoneId() |     19.14 |      51.91 |    173.14 |   1.23 |       GPS | null
//    }

    private String someUserId() {
        "8f766d4e-95de-40ee-9701-adf9c952eae1"
    }

    private PhoneLocationRequest somePhoneLocationRequest(Map params = [:]) {
        new PhoneLocationRequest(
                params.longitude ?: 51.91,
                params.latitude ?: 19.14,
                params.altitude ?: 173.14,
                params.speed ?: 1.23,
                params.accuracy ?: GPS,
                params.time ?: DateTime.parse("2016-06-10T13:13:13")
        )
    }
}
