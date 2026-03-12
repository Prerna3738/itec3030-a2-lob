package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.tradestandards.IOrder;

public class AggressiveStrategy implements ITradingStrategy {

    private Trader trader;
    private IOrder lastOrder;

    public void setTrader(Trader t) { this.trader = t; }
    public IOrder getLastOrder()    { return lastOrder; }

    @Override
    public void actOnEvent(Event e, int pos, int price) {
        lastOrder = null;
        if (e instanceof GoodNews) {
            lastOrder = new Bid(trader, e.getSecrity(),
                    (int) Math.round(price * 1.05),
                    (int) Math.round(pos * 0.5),
                    e.getTime());
        } else if (e instanceof BadNews) {
            lastOrder = new Ask(trader, e.getSecrity(),
                    (int) Math.round(price * 0.90),
                    (int) Math.round(pos * 0.8),
                    e.getTime());
        }
    }
}
