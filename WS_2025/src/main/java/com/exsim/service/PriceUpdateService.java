package com.exsim.service;

import com.exsim.domain.PriceData;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PriceUpdateService {

    private static PriceUpdateService instance; // Singleton instance
    private ConcurrentMap<String, PriceData> priceDataMap = new ConcurrentHashMap<>();

    private PriceUpdateService() {
        // Private constructor to prevent instantiation
    }

    // Lazy initialization with thread safety using synchronized
    public static synchronized PriceUpdateService getInstance() {
        if (instance == null) {
            instance = new PriceUpdateService();
        }
        return instance;
    }

    public PriceData getPriceData(String symbol) {
        if (priceDataMap.containsKey(symbol)) {
            return priceDataMap.get(symbol);
        }
        PriceData pd = new PriceData();
        pd.setHighPrice(0.0);
        pd.setLowPrice(0.0);
        pd.setLastPrice(0.0);
        pd.setLastExecutedQty(0);
        pd.setTotalExecutedQty(0);
        pd.setAvgPrice(0.0);
        pd.setOpenOrders("Y");

        priceDataMap.put(symbol, pd);
        return pd;
    }

    public void updatePriceData(String symbol, PriceData pd) {
        priceDataMap.put(symbol, pd);
    }

    public boolean isDataMatched(String symbol) {
        return priceDataMap.containsKey(symbol);
    }
}

