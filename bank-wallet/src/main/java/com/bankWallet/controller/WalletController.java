package com.bankWallet.controller;


import com.bankWallet.data.InMemoryWalletStore;
import com.bankWallet.model.Wallet;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/wallet")
public class WalletController {

    private final InMemoryWalletStore store;


    public WalletController(InMemoryWalletStore inMemoryWalletStore) {
        this.store = inMemoryWalletStore;
    }


    @Get(
            produces = MediaType.APPLICATION_JSON
    )
    public List<Wallet> GetWalletByAccount()
    {
        return store.GetWalletByAccount(InMemoryWalletStore.ACCOUNT_ID);
    }

    @Post(
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    public List<Wallet> PostWalletInAccount(@Body List<Wallet> wallets)
    {
        return store.PutWalletInAccount(InMemoryWalletStore.ACCOUNT_ID,wallets);
    }
}
