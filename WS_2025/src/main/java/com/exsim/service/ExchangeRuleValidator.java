package com.exsim.service;

import com.exsim.domain.Market;
import com.exsim.domain.Order;

public class ExchangeRuleValidator {
    private final MatchingService orderMatcher = MatchingService.getInstance();

    public boolean validateOrder(String exchange, Order order){
        System.out.println("In validateOrder exchange="+exchange);
         if("HK".equals(exchange)) {
             try {
                 //Validate limit Price
                 String symbol = order.getSymbol();
                 char side = order.getSide();
                 double price = order.getPrice();
                 char type = order.getType();
                 Market market = orderMatcher.getMarkets().get(symbol);
                 if(null == market){
                     return true;
                 }
                 System.out.println("In validateOrder type="+type+" side="+side);
                 if (('2' == type) && ('1' == side)) {
                     //should be lower than best ask
                     if (market.getAskOrders().size() > 0) {
                         double bestAsk = market.getAskOrders().get(0).getPrice();
                         System.out.println("In validateOrder price="+price+" bestAsk="+bestAsk);
                         if (price > bestAsk) {
                             return false;
                         }
                     }

                 }
                 if (('2' == type) && ('2' == side)) {
                     //should be higher than best bid
                     if (market.getBidOrders().size() > 0) {
                         double bestBid = market.getBidOrders().get(0).getPrice();
                         System.out.println("In validateOrder price="+price+" bestBid="+bestBid);
                         if (price < bestBid) {
                             return false;
                         }
                     }
                 }

         }catch(Exception ex){
             ex.printStackTrace();
             }

        }
         return true;
    }
}
