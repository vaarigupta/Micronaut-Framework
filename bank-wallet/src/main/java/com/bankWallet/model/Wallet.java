package com.bankWallet.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Wallet(UUID accountID, UUID walletID, Currency cur, BigDecimal amount) {
}
