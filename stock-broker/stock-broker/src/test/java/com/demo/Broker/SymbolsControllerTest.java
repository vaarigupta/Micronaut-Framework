package com.demo.Broker;

import com.demo.broker.Symbol;
import com.demo.broker.data.InMemoryStore;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class SymbolsControllerTest {

    @Inject
    @Client("/symbols")
    HttpClient client;

    @Inject
    InMemoryStore inMemoryStore;


    @BeforeEach
    void initialize()
    {
        inMemoryStore.initializeWith(10);
    }
    @Test
    void SymbolsEndPointReturnListOfSymbols()
    {
        var response = client.toBlocking().exchange("/", JsonNode.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(10,response.getBody().get().size());
    }


    @Test
    void SymbolsEndPointReturnCorrectSymbol()
    {
        var testSymbol = new Symbol("TEST");

        inMemoryStore.getSymbols().put(testSymbol.value(),testSymbol);
        var response = client.toBlocking().exchange("/" + testSymbol.value(), Symbol.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(testSymbol, response.getBody().get());
    }

}
