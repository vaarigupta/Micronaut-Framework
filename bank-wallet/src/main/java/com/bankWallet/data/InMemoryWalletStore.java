package com.bankWallet.data;

import com.bankWallet.model.Wallet;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
public class InMemoryWalletStore {

    public static final UUID ACCOUNT_ID = UUID.fromString("ca9b5e76-518e-457e-995d-7891364c16a9");

    private final Map<UUID,List<Wallet>> walletStore = new HashMap<>();

    public List<Wallet> GetWalletByAccount(UUID accountID)
    {
        return walletStore.getOrDefault(accountID,new ArrayList<>());
    }

    public List<Wallet> PutWalletInAccount(UUID accountID, List<Wallet> wallets)
    {
        walletStore.put(accountID,wallets);
        return GetWalletByAccount(accountID);
    }
}
