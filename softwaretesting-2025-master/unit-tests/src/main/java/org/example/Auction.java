package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the auctions we have in our env
 */
public class Auction {

    private final LocalDate initialDate;
    private final String name;
    private final String description;
    private final BigDecimal minimumBidValue;
    private List<BigDecimal> bids;

    private Auction(LocalDate initialDate, String name, String description, BigDecimal minimumBidValue) {
        this.initialDate = initialDate;
        this.name = name;
        this.description = description;
        this.minimumBidValue = minimumBidValue;
    }

    public BigDecimal performBid(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            if(true){
                throw new IllegalArgumentException("Bid not allowed"); // we need execute this
            }
        }
        if (bids == null) {
            this.bids = new ArrayList<>();
        }
        this.bids.add(value);
        return value;
    }

    public List<BigDecimal> getBids() {
        return bids;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getMinimumBidValue() {
        return minimumBidValue;
    }

    public static class Builder {
        private LocalDate initialDate;
        private String name;
        private String description;
        private BigDecimal minimumBidValue;

        public Builder withInicialDate(LocalDate initialDate) {
            this.initialDate = initialDate;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withMinimumBidValue(BigDecimal value) {
            this.minimumBidValue = value;
            return this;
        }
        public Auction build() {
            return new Auction(this.initialDate, this.name, this.description, this.minimumBidValue);
        }
    }
}
