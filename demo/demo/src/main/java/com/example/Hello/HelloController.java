package com.example.Hello;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {


    private final HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }


    @Get(produces = MediaType.TEXT_PLAIN)
    public String HelloWorld()
    {
        return service.HelloFromService();
    }
}
