package com.example.Hello;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Controller("/hello")
public class HelloController {


    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
    private final HelloServiceInterface service;

    public HelloController(HelloServiceInterface service) {
        this.service = service;
    }


    @Get(produces = MediaType.TEXT_PLAIN)
    public String HelloWorld()
    {

        LOG.debug("calling from Hello World API");
        return service.HelloFromService();
    }
}
