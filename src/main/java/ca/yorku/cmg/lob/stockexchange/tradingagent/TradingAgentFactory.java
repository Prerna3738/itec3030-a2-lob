package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

public class TradingAgentFactory extends AbstractTradingAgentFactory {

    @Override
    public TradingAgent createAgent(String type, String style,
                                    Trader t, StockExchange e, NewsBoard n) {
        ITradingStrategy s;
        if (style.equalsIgnoreCase("Aggressive")) {
            s = new AggressiveStrategy();
        } else {
            s = new ConservativeStrategy();
        }
        if (type.equalsIgnoreCase("Institutional")) {
            return new TradingAgentInstitutional(t, e, n, s);
        } else {
            return new TradingAgentRetail(t, e, n, s);
        }
    }
}
