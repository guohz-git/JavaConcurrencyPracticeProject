package practice5_2.FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 提前加载耗时的任务
 */
public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });

    public Preloader() {
        start();
    }

    private ProductInfo loadProductInfo() throws InterruptedException {
        Thread.sleep(3000);
        ProductInfo info = new ProductInfo();
        info.setProductName("小米手机");
        return info;
    }

    private final Thread thread = new Thread(future);

    private void start(){
        thread.start();
    }

    public ProductInfo get() throws Exception {
        try {
            //获取数据结果，如果没有结果将阻塞等待
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            //先处理已知可以发生的异常
            if(cause instanceof DataLoadException){
                throw  (DataLoadException)cause;
            }else{
                //处理常见可能发生的异常
                throw launderException(cause);
            }
        }
        return null;
    }

    /**
     * 处理一些其他的异常
     * @param cause
     * @return
     */
    private Exception launderException(Throwable cause) {
        if(cause instanceof  RuntimeException){
            return (Exception) cause;
        }else if(cause instanceof Error){
            throw (Error)cause;
        }else{
            throw new IllegalStateException("Not unchecked",cause);
        }
    }
}
