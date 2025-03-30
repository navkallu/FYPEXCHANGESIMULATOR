package com.exsim.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketData {
    private String symbol;
    private double bidprice;
    private double askprice;
    private double highprice;
    private double lowprice;
    private double lastprice;

    @JsonCreator
    public MarketData(@JsonProperty("symbol") String symbol,
                      @JsonProperty("bidprice") double bidprice,
                      @JsonProperty("askprice") double askprice,
                      @JsonProperty("highprice") double highprice,
                      @JsonProperty("lowprice") double lowprice,
                      @JsonProperty("lastprice") double lastprice
    ) {
        this.symbol = symbol;
        this.bidprice = bidprice;
        this.askprice = askprice;
        this.highprice = highprice;
        this.lowprice = lowprice;
        this.lastprice = lastprice;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getBidprice() {
        return bidprice;
    }

    public void setBidprice(double bidprice) {
        this.bidprice = bidprice;
    }

    public double getAskprice() {
        return askprice;
    }

    public void setAskprice(double askprice) {
        this.askprice = askprice;
    }

    public double getHighprice() {
        return highprice;
    }

    public void setHighprice(double highprice) {
        this.highprice = highprice;
    }

    public double getLowprice() {
        return lowprice;
    }

    public void setLowprice(double lowprice) {
        this.lowprice = lowprice;
    }

    public double getLastprice() {
        return lastprice;
    }

    public void setLastprice(double lastprice) {
        this.lastprice = lastprice;
    }


}
