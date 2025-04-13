package com.exsim.domain;

public class LastQtyPrice {

    private double lastPrice;
    private long lastExecutedQty;

    public LastQtyPrice(double lastPrice, long lastExecutedQty) {
        this.lastPrice = lastPrice;
        this.lastExecutedQty = lastExecutedQty;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public long getLastExecutedQty() {
        return lastExecutedQty;
    }

    public void setLastExecutedQty(long lastExecutedQty) {
        this.lastExecutedQty = lastExecutedQty;
    }



}
