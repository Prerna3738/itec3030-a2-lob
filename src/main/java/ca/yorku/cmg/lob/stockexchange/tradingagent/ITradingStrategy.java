package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.events.Event;

public interface ITradingStrategy {
    void actOnEvent(Event e, int pos, int price);
}
