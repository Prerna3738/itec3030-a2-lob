package ca.yorku.cmg.lob.stockexchange.tradingagent;

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
        int quantity = 100;
        if (e instanceof GoodNews) {
            newOrder = new Bid(t, e.getSecrity(), (int)Math.round(price * 1.05), quantity, e.getTime());
        } else if (e instanceof BadNews) {
            newOrder = new Ask(t, e.getSecrity(), (int)Math.round(price * 0.90), quantity, e.getTime());
        }
        if (newOrder != null) exc.submitOrder(newOrder, e.getTime());
    }
}
