package com.example.Hello;

public class HelloService implements  HelloServiceInterface {

    @Override
    public String HelloFromService() {
        return "Hello from Service!";
    }
}
