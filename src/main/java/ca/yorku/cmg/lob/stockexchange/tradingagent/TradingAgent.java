package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.tradestandards.IOrder;

public abstract class TradingAgent {

    protected Trader t;
    protected StockExchange exc;
    protected NewsBoard news;
    protected ITradingStrategy strategy;

    public TradingAgent(Trader t, StockExchange e, NewsBoard n, ITradingStrategy s) {
        this.t        = t;
        this.exc      = e;
        this.news     = n;
        this.strategy = s;
        if (s instanceof ConservativeStrategy) {
            ((ConservativeStrategy) s).setTrader(t);
        } else if (s instanceof AggressiveStrategy) {
            ((AggressiveStrategy) s).setTrader(t);
        }
    }

    public void timeAdvancedTo(long time) {
        pollForEvents(time);
    }

    private void pollForEvents(long time) {
        Event e = news.getEventAt(time);
        if (e != null) {
            examineEvent(e);
        }
    }

    private void examineEvent(Event e) {
        int pos = exc.getAccounts()
                     .getTraderAccount(t)
                     .getPosition(e.getSecrity().getTicker());
        if (pos > 0) {
            int price = exc.getPrice(e.getSecrity().getTicker());
            strategy.actOnEvent(e, pos, price);
            IOrder order = null;
            if (strategy instanceof ConservativeStrategy) {
                order = ((ConservativeStrategy) strategy).getLastOrder();
            } else if (strategy instanceof AggressiveStrategy) {
                order = ((AggressiveStrategy) strategy).getLastOrder();
            }
            if (order != null) {
                exc.submitOrder(order, e.getTime());
            }
        }
    }
}
