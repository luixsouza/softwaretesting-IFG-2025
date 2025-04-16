package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionTest {

    private final Auction subject = new Auction.Builder()
            .withInicialDate(LocalDate.now())
            .withName("PS 5")
            .withDescription("My little PS 5")
            .withMinimumBidValue(BigDecimal.valueOf(2000))
            .build();

    @Test
    public void shouldPerformBid() {
        BigDecimal result = subject.performBid(BigDecimal.valueOf(10));
        assertEquals(BigDecimal.valueOf(10), result);
    }

    @Test
    public void shouldThrowExceptionWhenPerformNegativeBid() {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> subject.performBid(BigDecimal.valueOf(-10)));
        assertTrue(result.getMessage().contains("Bid not allowed"));
    }
}
