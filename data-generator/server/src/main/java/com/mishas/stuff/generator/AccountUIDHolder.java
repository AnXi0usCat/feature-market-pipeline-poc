package com.mishas.stuff.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public enum AccountUIDHolder {

    ACCOUNT_HOLDER(10);

    private List<UUID> accountHolders = new ArrayList<>();

    AccountUIDHolder(int listSize) {
        IntStream.range(0, listSize).forEach(value -> this.accountHolders.add(UUID.randomUUID()));
    }

    public UUID getAccountHolder() {
        Random random = new Random();
        return accountHolders.get(random.nextInt(accountHolders.size()));
    }


}
