package com.demo.broker.model;

import java.util.ArrayList;
import java.util.List;

//parameterized constructor
public record Watchlist(List<Symbol> symbols) {

    //default constructor
    public Watchlist()
    {
        this(new ArrayList<>());
    }
}
