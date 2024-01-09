package com.demo.broker.data;

import com.demo.broker.model.Watchlist;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class InMemoryAccountStore {

    private final Map<UUID, Watchlist> watchlistMap = new HashMap<>();

    public Watchlist GetWatchlistByAccount(UUID AccountId)
    {
      return  watchlistMap.getOrDefault(AccountId, new Watchlist());
    }

    public Watchlist UpdateWatchlistByAccount(UUID AccountId, Watchlist watchlist)
    {
        watchlistMap.put(AccountId,watchlist);
        return GetWatchlistByAccount(AccountId);
    }

    public void DeleteWatchlistByAccount(UUID AccountId)
    {
        if(watchlistMap.containsKey(AccountId))
            watchlistMap.remove(AccountId);

    }

    public UUID GetAccountId(UUID AccountId)
    {
        return AccountId;
    }
}
