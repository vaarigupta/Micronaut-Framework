package com.demo.broker.controller;


import com.demo.broker.data.InMemoryAccountStore;
import com.demo.broker.model.Symbol;
import com.demo.broker.model.Watchlist;
import com.demo.main.Application;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller("/watchlist")
public class WatchlistController {

    private  final InMemoryAccountStore store;
    static  final UUID accountId = UUID.randomUUID();
    public  WatchlistController(InMemoryAccountStore inMemoryAccountStore)
    {
        this.store = inMemoryAccountStore;
    }


    @Get( produces = MediaType.APPLICATION_JSON)
    public Watchlist get()
    {
        return store.GetWatchlistByAccount(accountId);
    }

    @Get("/id")
    public UUID getAccountId()
    {
        return store.GetAccountId(accountId);
    }


    @Get("filter{?max}")
    public List<Symbol> getByFilter(@QueryValue Optional<Integer> max)
    {
        return store.GetWatchlistByAccount(accountId)
                .symbols()
                .stream()
                .limit(max.orElse(2))
                .toList();
    }



    @Status(HttpStatus.CREATED)
    @Put (
        produces =  MediaType.APPLICATION_JSON,
        consumes =  MediaType.APPLICATION_JSON
    )
    public Watchlist update(@Body Watchlist watchlist)
    {
        return store.UpdateWatchlistByAccount(accountId,watchlist);
    }

    @Status(HttpStatus.NO_CONTENT)
    @Delete
    public void delete()
    {
        store.DeleteWatchlistByAccount(accountId);
        //return store.GetAccountId(accountId);
    }
}
