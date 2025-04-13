package com.exsim.domain;

public class PriceData {
    private double lastPrice;
    private double lowPrice;
    private double highPrice;
    private double avgPrice;
    private long lastExecutedQty;
    private long totalExecutedQty;
    private String openOrders;
    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }


    public long getLastExecutedQty() {
        return lastExecutedQty;
    }

    public void setLastExecutedQty(long lastExecutedQty) {
        this.lastExecutedQty = lastExecutedQty;
    }

    public long getTotalExecutedQty() {
        return totalExecutedQty;
    }

    public void setTotalExecutedQty(long totalExecutedQty) {
        this.totalExecutedQty = totalExecutedQty;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getOpenOrders() {
        return openOrders;
    }

    public void setOpenOrders(String openOrders) {
        this.openOrders = openOrders;
    }

}
