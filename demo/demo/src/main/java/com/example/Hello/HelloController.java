package com.example.Hello;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/hello")
public class HelloController {

    @Inject
    private HelloService service;


    @Get(produces = MediaType.TEXT_PLAIN)
    public String HelloWorld()
    {
        return service.HelloFromService();
    }
}
