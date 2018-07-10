package chapter6.travel;

import java.util.*;
import java.util.concurrent.*;

/**
 * 测试主程序代码
 */
public class TravelTest {

    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        TravelTest test = new TravelTest();
        TravelInfo travelInfo = new TravelInfo("南京","南京",1,2);
        Set<TravelCompany> companies = new HashSet<>();
        companies.add(new TravelCompany("驴妈妈","1111"));
        companies.add(new TravelCompany("携程","2222"));
        //超时时间3秒
        int time = 3;
        TimeUnit unit = TimeUnit.SECONDS;
        List<TravelQuote> rankedTravelQuotes = test.getRankedTravelQuotes(travelInfo, companies, time, unit);
        System.out.println(rankedTravelQuotes);
        //关闭线程池
        exec.shutdown();
    }

    /**
     * 并发执行任务方法
     * @param travelInfo 客户旅游信息
     * @param companies 查询公司列表
     * @param time 时间
     * @param unit 时间单位
     * @return
     */
    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies, long time, TimeUnit unit){
        List<QuoteTask> tasks = new ArrayList<>();
        //实例化查询任务
        for (TravelCompany company: companies) {
            tasks.add(new QuoteTask(company,travelInfo));
        }
        try {
            //同时调用所有的任务，返回future列表,传入指定超时时间
            List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);
            List<TravelQuote> quotes = new ArrayList<>();
            Iterator<QuoteTask> iterator = tasks.iterator();
            //遍历查询异步执行结果
            for(Future<TravelQuote> future: futures){
                QuoteTask next = iterator.next();
                try {
                    //将返回信息添加进入询价列表
                    quotes.add(future.get());
                    //以下超时或者返回异常都将返回默认空的报价信息
                } catch (ExecutionException e) {
                    quotes.add(next.getFailureQuote(e.getCause()));
                    e.printStackTrace();
                } catch (CancellationException e){
                    quotes.add(next.getTimeoutQuote(e));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            return quotes;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
