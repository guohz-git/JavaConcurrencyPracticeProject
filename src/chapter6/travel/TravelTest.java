package chapter6.travel;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.concurrent.*;

public class TravelTest {

    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        TravelTest test = new TravelTest();
        TravelInfo travelInfo = new TravelInfo("南京","南京",1,2);
        Set<TravelCompany> companies = new HashSet<>();
        companies.add(new TravelCompany("驴妈妈","1111"));
        companies.add(new TravelCompany("携程","2222"));
        int time = 3;
        TimeUnit unit = TimeUnit.SECONDS;
        List<TravelQuote> rankedTravelQuotes = test.getRankedTravelQuotes(travelInfo, companies, time, unit);
        System.out.println(rankedTravelQuotes);
        //关闭线程池
        exec.shutdown();
    }

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies, long time, TimeUnit unit){
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company: companies) {
            tasks.add(new QuoteTask(company,travelInfo));
        }
        try {
            //同时调用所有的任务，返回future列表
            List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);

            List<TravelQuote> quotes = new ArrayList<>();
            Iterator<QuoteTask> iterator = tasks.iterator();
            for(Future<TravelQuote> future: futures){
                QuoteTask next = iterator.next();
                try {
                    quotes.add(future.get());
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
