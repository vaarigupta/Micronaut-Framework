package com.example.Hello;

import jakarta.inject.Singleton;

@Singleton
public class HelloService {

    public String HelloFromService() {
        return "Hello from Service!";
    }
}
