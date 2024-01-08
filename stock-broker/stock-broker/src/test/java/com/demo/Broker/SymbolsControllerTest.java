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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class SymbolsControllerTest {

    @Inject
    @Client("/symbols")
    HttpClient client;

    @Inject
    InMemoryStore inMemoryStore;

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore.class);

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


    @Test
    void SymbolsEndPointReturnsResponseBasedOnQueryParameters()
    {
        var max3Response = client.toBlocking().exchange("/filter?max=3",JsonNode.class);
        assertEquals(HttpStatus.OK,max3Response.getStatus());
        LOG.info("max3Response returns 3 entries : {}",max3Response.getBody().get());
      //  LOG.debug("max3Response returns 3 entries : {}",max3Response.getBody().get());
        assertEquals(3,max3Response.getBody().get().size());

        var offset2Response = client.toBlocking().exchange("/filter?offset=2",JsonNode.class);
        assertEquals(HttpStatus.OK, offset2Response.getStatus());
        LOG.info( "offset2Response returns 2 entries : {}", offset2Response.getBody().get());
      //  LOG.debug( "offset2Response returns 5 entries : {}", offset2Response.getBody().get().get(1));
        assertEquals(8, offset2Response.getBody().get().size());

        var max6offset4Response = client.toBlocking().exchange("/filter?max6&offset=4",JsonNode.class);
        assertEquals(HttpStatus.OK, max6offset4Response.getStatus());
        LOG.info( "max6offset4Response returns 2 entries : {}", max6offset4Response.getBody().get());
        assertEquals(6, max6offset4Response.getBody().get().size());
    }
}
