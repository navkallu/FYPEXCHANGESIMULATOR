package com.exsim.service;

import com.exsim.db.OrderBookDAO;
import com.exsim.domain.Market;
import com.exsim.domain.Order;
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

                obDao.insertOrderBook(symbol, bidOrderJsonString, askOrderJsonString);
            }catch(Exception ex){
                ex.printStackTrace();
            }


        }


    }
}
