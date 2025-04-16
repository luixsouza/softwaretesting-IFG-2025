package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionPersistenceTest {

    private static final AuctionPersistence subject = new AuctionPersistence();
    @Test
    public void shouldCreateAnAuction() {
        //test setup
        Auction auction = new Auction.Builder()
                .withInicialDate(LocalDate.now().plusDays(1))
                .withName("PS 5")
                .withDescription("My little PS 5")
                .withMinimumBidValue(BigDecimal.valueOf(2000))
                .build();
        //goal
        Auction result = subject.create(auction);
        //assertion
        assertEquals(auction.getName(), result.getName());
        assertEquals(auction.getDescription(), result.getDescription());
        assertEquals(auction.getInitialDate(), result.getInitialDate());
        assertEquals(auction.getMinimumBidValue(), result.getMinimumBidValue());
    }

    @Test
    public void shouldThrowExceptionWhenNullInput() {
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(null));
        assertTrue(result.getMessage().contains("Null values are not allowed"));
    }

    @Test
    public void shouldThrowExceptionWhenInitialDateNotProvided() {
        Auction auction = new Auction.Builder()
                .withName("PS 5")
                .withDescription("My little PS 5")
                .withMinimumBidValue(BigDecimal.valueOf(2000))
                .build();
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(auction));
        assertTrue(result.getMessage().contains("Initial data can not be null"));
    }

    @Test
    public void shouldThrowExceptionWhenNameNotProvided() {
        Auction auction = new Auction.Builder()
                .withInicialDate(LocalDate.now())
                .withDescription("My little PS 5")
                .withMinimumBidValue(BigDecimal.valueOf(2000))
                .build();
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(auction));
        assertTrue(result.getMessage().contains("Name can not be null"));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        Auction auction = new Auction.Builder()
                .withName("")
                .withInicialDate(LocalDate.now())
                .withDescription("My little PS 5")
                .withMinimumBidValue(BigDecimal.valueOf(2000))
                .build();
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(auction));
        assertTrue(result.getMessage().contains("Name can not be null"));
    }

    @Test
    public void shouldThrowExceptionWhenMinimumBidValueNotProvided() {
        Auction auction = new Auction.Builder()
                .withInicialDate(LocalDate.now())
                .withName("PS 5")
                .withDescription("My little PS 5")
                .build();
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(auction));
        assertTrue(result.getMessage().contains("Minimum bid value can not be null"));
    }

    @Test
    public void shouldThrowExceptionWhenInitialDateIsToday() {
        Auction auction = new Auction.Builder()
                .withInicialDate(LocalDate.now())
                .withName("PS 5")
                .withDescription("My little PS 5")
                .withMinimumBidValue(BigDecimal.valueOf(2000))
                .build();

        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(auction));
        assertTrue(result.getMessage().contains("Initial date must be in the future"));
    }

    @Test
    public void shouldThrowExceptionWhenInitialDateIsInThePast() {
        Auction auction = new Auction.Builder()
                .withInicialDate(LocalDate.now().minusDays(1))
                .withName("PS 5")
                .withDescription("My little PS 5")
                .withMinimumBidValue(BigDecimal.valueOf(2000))
                .build();

        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(auction));
        assertTrue(result.getMessage().contains("Initial date must be in the future"));
    }

    @Test
    public void shouldThrowExceptionWhenMinimumBidValueIsNegative() {
        Auction auction = new Auction.Builder()
                .withInicialDate(LocalDate.now().plusDays(1))
                .withName("PS 5")
                .withDescription("My little PS 5")
                .withMinimumBidValue(BigDecimal.valueOf(-100))
                .build();

        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(auction));
        assertTrue(result.getMessage().contains("Minimum bid value must be greater than zero"));
    }

    @Test
    public void shouldThrowExceptionWhenMinimumBidValueIsZero() {
        Auction auction = new Auction.Builder()
                .withInicialDate(LocalDate.now().plusDays(1))
                .withName("PS 5")
                .withDescription("My little PS 5")
                .withMinimumBidValue(BigDecimal.ZERO)
                .build();

        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class, () -> subject.create(auction));
        assertTrue(result.getMessage().contains("Minimum bid value must be greater than zero"));
    }
}