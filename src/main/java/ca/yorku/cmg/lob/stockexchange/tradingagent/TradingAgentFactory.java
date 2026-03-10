package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.stockexchange.strategy.*;

public class TradingAgentFactory extends AbstractTradingAgentFactory {

    @Override
    public TradingAgent createAgent(String type, String style, Trader t, StockExchange e, NewsBoard n) {
        
        // 1. Pick the strategy based on the 'style' string
        ITradingStrategy s;
        if (style.equalsIgnoreCase("Aggressive")) {
            s = new AggressiveStrategy();
        } else {
            s = new ConservativeStrategy();
        }

        // 2. Pick the agent type based on the 'type' string
        if (type.equalsIgnoreCase("Institutional")) {
            return new TradingAgentInstitutional(t, e, n, s);
        } else {
            return new TradingAgentRetail(t, e, n, s);
        }
    }
}