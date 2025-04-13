package com.exsim.service;

import com.exsim.db.MarketDataDAO;
import com.exsim.db.OrderBookDAO;
import com.exsim.domain.Market;
import com.exsim.domain.Order;
import com.exsim.domain.PriceData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistenceService {

    public static String displayOrderBook(HashMap<String, Market> markets){
       String jsonString = "";
       try{
           // Convert to JSON
           ObjectMapper objectMapper = new ObjectMapper();
           jsonString = objectMapper.writeValueAsString(markets);
           objectMapper.writeValue(new File("..\\orderbook\\orderbook.json"), markets);
       }catch(Exception ex){
            ex.printStackTrace();
       }
        updateOrderBookDB(markets);
        return jsonString;

    }

    public static void updateOrderBookDB(HashMap<String, Market> markets){

        OrderBookDAO obDao = new OrderBookDAO();

        for(Map.Entry<String,Market> entry:markets.entrySet()){
            String symbol = entry.getKey();
            Market mkt = entry.getValue();
            List<Order> bidOrders = mkt.getBidOrders();
            List<Order> askOrders = mkt.getAskOrders();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String bidOrderJsonString = objectMapper.writeValueAsString(bidOrders);
                String askOrderJsonString = objectMapper.writeValueAsString(askOrders);
                updateMarketDataPersistence(symbol,bidOrders,askOrders);
                obDao.insertOrderBook(symbol, bidOrderJsonString, askOrderJsonString);
            }catch(Exception ex){
                ex.printStackTrace();
            }


        }


    }

    public static void updateMarketDataPersistence(String symbol,List<Order> bidOrders , List<Order> askOrders){
        System.out.println("updateMarketDataPersistence called");

        PriceUpdateService priceUpdateService = PriceUpdateService.getInstance();
        PriceData pd  = priceUpdateService.getPriceData(symbol);
        double lowprice = pd.getLowPrice();
        double highprice = pd.getHighPrice();
        double lastprice = pd.getLastPrice();
        long executedQty = pd.getLastExecutedQty();
        long totalExecutedQty = pd.getTotalExecutedQty();
        double avgPrice = pd.getAvgPrice();
        String isOpen = pd.getOpenOrders();

        if(!priceUpdateService.isDataMatched(symbol)){
            System.out.println("updateMarketDataPersistence returned");
            return;
        }
        double bidprice = 0.0;
        double askprice = 0.0;
        if(bidOrders.size()>0){
            bidprice = bidOrders.get(0).getPrice();
        }
        if(askOrders.size()>0){
            askprice = askOrders.get(0).getPrice();
        }
        //Update Open Status
        if (bidOrders.size() == 0 && askOrders.size() == 0) {
            isOpen = "N";
        }


        MarketDataDAO mktDao = new MarketDataDAO();
        try {
            System.out.println("Calling insertMarketData");
            mktDao.insertMarketData(symbol, bidprice, askprice, highprice, lowprice,lastprice,executedQty,totalExecutedQty,avgPrice, isOpen);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    }

