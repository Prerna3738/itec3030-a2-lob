package ca.yorku.cmg.lob.stockexchange.strategy;

import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.*;
import ca.yorku.cmg.lob.tradestandards.IOrder;
import ca.yorku.cmg.lob.trader.Trader;

public class AggressiveStrategy implements ITradingStrategy {
    @Override
    public void actOnEvent(Event e, int pos, int price, Trader t, StockExchange exc) {
        IOrder newOrder = null;
        if (e instanceof GoodNews) {
            newOrder = new Bid(t, e.getSecrity(), (int)Math.round(price * 1.05), (int)Math.round(pos * 0.5), e.getTime());
        } else if (e instanceof BadNews) {
            newOrder = new Ask(t, e.getSecrity(), (int)Math.round(price * 0.90), (int)Math.round(pos * 0.8), e.getTime());
        }
        if (newOrder != null) exc.submitOrder(newOrder, e.getTime());
    }
}