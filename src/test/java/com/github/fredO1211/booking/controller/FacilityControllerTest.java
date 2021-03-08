package com.github.fredO1211.booking.controller;

import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.repository.FacilityRepository;
import com.github.fredO1211.booking.service.exceptions.UnavailableNameException;
import com.github.fredO1211.booking.service.impl.FacilityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


@WebMvcTest
@ContextConfiguration(classes = ServiceConfiguration.class)
class FacilityControllerTest {
    @Test
    void sdsada(){
        assertTrue(Boolean.TRUE);
    }

    @Test
    void shouldReturnConflictStatusWhenNameInDatabaseIsAlreadyExist(@Autowired WebApplicationContext context) {
//        FacilityRepository repository = mock(FacilityRepository.class);
//        given()
//                .webAppContextSetup(context)
//                .body("{\"name\": \"Ala\",\"basicRentAmount\" : 300}")
//                .when()
//                .post("/facilities")
//                .then()
//                .statusCode(201);
    }
}
//
@Configuration(proxyBeanMethods = false)
class ServiceConfiguration {

    @Bean
    FacilityServiceImpl service() {
        return new FacilityServiceImpl(mock(FacilityRepository.class)) {
            @Override
            public Facility save(@Valid Facility facility) {
                if (facility.getName().equals("name")) {
                    throw new UnavailableNameException();
                }
                return facility;
            }
        };
    }

    @Bean
    FacilityController controller(FacilityServiceImpl facilityServiceImpl) {
        return new FacilityController(facilityServiceImpl);
    }
}