package com.demo.broker.data;

import com.demo.broker.model.Symbol;
import jakarta.inject.Singleton;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton
public class InMemoryStore {

    private final Map<String, Symbol> symbols = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore.class);
    private final Faker faker = new Faker();

    @PostConstruct
    public  void initialize()
    {
        initializeWith(10);
    }

    public void initializeWith(int totalEntries)
    {
        symbols.clear();
        IntStream.range(0,totalEntries).forEach(i ->
                addNewSymbol());
    }
    private void addNewSymbol()
    {
        var symbol = new Symbol(faker.stock().nsdqSymbol());
        symbols.put(symbol.value(),symbol);
        LOG.debug("Added Symbol: {}",symbol);
    }


    public Map<String, Symbol> getSymbols() {
        return symbols;
    }
}
