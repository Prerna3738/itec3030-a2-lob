package ca.yorku.cmg.lob.stockexchange.tradingagent;

import java.util.HashMap;
import java.util.Map;
import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.stockexchange.strategy.ITradingStrategy;

public abstract class TradingAgent {
    protected Trader t;
    protected StockExchange exc;
    protected NewsBoard nb;
    protected ITradingStrategy strategy; 
    protected Map<String, Integer> positions = new HashMap<>();

    public TradingAgent(Trader t, StockExchange e, NewsBoard n, ITradingStrategy strategy) {
        this.t = t;
        this.exc = e;
        this.nb = n;
        this.strategy = strategy;
    }

    public void onEvent(Event e, int price) {
        int currentPos = positions.getOrDefault(e.getSecurity(), 0);
        if (strategy != null) {
            strategy.actOnEvent(e, currentPos, price, this.t, this.exc);
        }
    }

    public void addPosition(String ticker, Integer quantity) {
        this.positions.put(ticker, quantity);
    }
}