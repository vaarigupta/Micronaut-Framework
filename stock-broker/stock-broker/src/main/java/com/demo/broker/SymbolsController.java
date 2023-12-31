package com.demo.broker;

import com.demo.broker.data.InMemoryStore;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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
}
