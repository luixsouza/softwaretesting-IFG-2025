package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuctionPersistence {

    private static final List<Auction> auctionList = new ArrayList<>();

    public Auction create(Auction auction) {
        if (auction == null) {
            throw new IllegalArgumentException("Null values are not allowed");
        }
        if (auction.getInitialDate() == null) {
            throw new IllegalArgumentException("Initial data can not be null");
        }
        if (auction.getName() == null || auction.getName().isEmpty()) {
            throw new IllegalArgumentException("Name can not be null");
        }
        if (auction.getMinimumBidValue() == null) {
            throw new IllegalArgumentException("Minimum bid value can not be null");
        }
        if (auction.getInitialDate().isBefore(LocalDate.now()) || auction.getInitialDate().isEqual(LocalDate.now())) {
        throw new IllegalArgumentException("Initial date must be in the future");
        }
        if (auction.getMinimumBidValue().compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException("Minimum bid value must be greater than zero");
        }

        auctionList.add(auction);
        return auction;
    }
}