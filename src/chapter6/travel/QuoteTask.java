package chapter6.travel;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

/**
 * 询价任务Callable对象
 */
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

    /**
     * 获取询价代码异常 显示默认报价信息
     * @param cause
     * @return
     */
    public TravelQuote getFailureQuote(Throwable cause) {
        return new TravelQuote(0,-1);
    }

    /**
     * 获取询价超时， 显示默认报价信息
     * @param e
     * @return
     */
    public TravelQuote getTimeoutQuote(CancellationException e) {
        return new TravelQuote(0,-2);
    }
}
