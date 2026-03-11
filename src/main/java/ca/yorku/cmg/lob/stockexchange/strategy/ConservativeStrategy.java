package ca.yorku.cmg.lob.stockexchange.strategy;

import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.*;
import ca.yorku.cmg.lob.tradestandards.IOrder;
import ca.yorku.cmg.lob.trader.Trader;

public class ConservativeStrategy implements ITradingStrategy {
    @Override
    public void actOnEvent(Event e, int pos, int price, Trader t, StockExchange exc) {
        IOrder newOrder = null;
        if (e instanceof GoodNews) {
            newOrder = new Bid(t, e.getSecrity(), (int)Math.round(price * 1.01), (int)Math.round(pos * 0.1), e.getTime());
        } else if (e instanceof BadNews) {
            newOrder = new Ask(t, e.getSecrity(), (int)Math.round(price * 0.99), (int)Math.round(pos * 0.2), e.getTime());
        }
        if (newOrder != null) exc.submitOrder(newOrder, e.getTime());
    }
}