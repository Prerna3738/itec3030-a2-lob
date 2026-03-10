package ca.yorku.cmg.lob.stockexchange.strategy;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.trader.Trader;

public interface ITradingStrategy {
    void actOnEvent(Event e, int pos, int price, Trader t, StockExchange exc);
}