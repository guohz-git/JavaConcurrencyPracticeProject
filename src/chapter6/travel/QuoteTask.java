package chapter6.travel;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

public class QuoteTask implements Callable<TravelQuote> {

    private final TravelCompany company;
    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }

    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }

    public TravelQuote getFailureQuote(Throwable cause) {
        return new TravelQuote(0,-1);
    }

    public TravelQuote getTimeoutQuote(CancellationException e) {
        return new TravelQuote(0,-2);
    }
}
