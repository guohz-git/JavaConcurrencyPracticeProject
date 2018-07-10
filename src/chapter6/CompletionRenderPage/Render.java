package chapter6.CompletionRenderPage;

import chapter6.SimpleRenderPage.ImageInfo;
import com.sun.scenario.effect.ImageData;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Render {

    /**
     * 初始化线程执行服务
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    /**
     * 使用优先级阻塞队列
     */
    private  BlockingQueue<QueueFuture<QueueingFutureTask<ImageData>>> blockingDeque = new PriorityBlockingQueue<>();


    void renderPage(CharSequence sequence){

        List<ImageInfo> imageInfos = scanForImageInfo(sequence);

        //循环创建提交任务，并提交执行任务
        for(ImageInfo info : imageInfos){
            QueueingFutureTask<ImageData> task = new QueueingFutureTask<ImageData>(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    ImageData imageData = info.downloadImage(info);
                    return imageData;
                }
            },blockingDeque);
            //提交任务
            executorService.submit(task);
        }
        //首先渲染文本，提高响应速度
        readerText(sequence);

        for(int i = 0; i < imageInfos.size();i++){
            try {
                //从队列里面获取，已完成的异步任务渲染图片
                QueueFuture<QueueingFutureTask<ImageData>> take = blockingDeque.take();
                if(take.getFuture().isDone()){
                    System.out.println("图片已经显示:"+take.getFuture().getIndex());
                    renderImage(take.getFuture().getIndex(),null);
                }
            } catch (InterruptedException e) {
                //中断阻塞线程
                Thread.currentThread().interrupt();
                e.printStackTrace();
                renderDefaultImage(i,null);
            } catch (CancellationException ex){
                renderDefaultImage(i,null);
            }
        }
        executorService.shutdown();
    }

    /**
     * 如果图片下载失败，渲染默认图片
     * @param i
     * @param o
     */
    private void renderDefaultImage(int i, Object o) {
        System.out.println("渲染默认图片: "+i);
    }

    private void renderImage(Long index,ImageData imageData) {
        System.out.println("图片渲染完成: " + index +"!");
    }

    private void readerText(CharSequence source) {
        System.out.println("文本渲染完成！" );
    }


    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        List<ImageInfo> list = new ArrayList<>();
        list.add(new ImageInfo("测试文件1","test.png"));
        list.add(new ImageInfo("测试文件2","test.png"));
        list.add(new ImageInfo("测试文件3","test.png"));
        return list;
    }
}
