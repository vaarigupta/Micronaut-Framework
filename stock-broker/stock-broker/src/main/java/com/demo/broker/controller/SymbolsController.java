package com.demo.broker.controller;

import com.demo.broker.data.InMemoryStore;
import com.demo.broker.model.Symbol;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;

import java.util.List;
import java.util.Optional;

@Controller("/symbols")
public class SymbolsController {

    private final InMemoryStore inMemoryStore;

    public SymbolsController(InMemoryStore inMemoryStore) {
        this.inMemoryStore = inMemoryStore;
    }

    @Get
    public List<Symbol> GetAll()
    {
        return inMemoryStore.getSymbols().values().stream().toList();
    }

    @Get("{value}")
    public Symbol GetSymbolByValue(@PathVariable String value)
    {
        return inMemoryStore.getSymbols().get(value);
    }

    @Get("filter{?max,offset}")
    public List<Symbol> GetSymbolsByQuery(@QueryValue Optional<Integer> max, @QueryValue Optional<Integer> offset)
    {
        return inMemoryStore.getSymbols().values()
                .stream()
                .skip(offset.orElse(0))
                .limit(max.orElse(10))
                .toList();
    }
}
