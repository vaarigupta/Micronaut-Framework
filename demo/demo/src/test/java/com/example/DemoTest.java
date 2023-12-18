package com.example;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HelloWorldControllerTest {

    @Inject
    @Client("/")
    HttpClient client;



    @Test
    void helloWorldEndpointRespond() {

        var response = client.toBlocking().retrieve("/hello");
        assertEquals("Hello World !",response);
    }

    @Test
    void helloWorldEndpointRespondBody() {

        var response = client.toBlocking().exchange("/hello",String.class);
        assertEquals("Hello World !",response.getBody().get());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

}
