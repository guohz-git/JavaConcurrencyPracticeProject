package chapter6.CompletionRenderPage;

import chapter6.SimpleRenderPage.ImageInfo;
import com.sun.scenario.effect.ImageData;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Render {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private  BlockingQueue<QueueFuture<QueueingFutureTask<ImageData>>> blockingDeque = new PriorityBlockingQueue<>();


    void renderPage(CharSequence sequence){
        List<ImageInfo> imageInfos = scanForImageInfo(sequence);

        //循环创建提交任务
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
        readerText(sequence);
        for(int i = 0; i < imageInfos.size();i++){
            try {
                QueueFuture<QueueingFutureTask<ImageData>> take = blockingDeque.take();
                if(take.getFuture().isDone()){
                    System.out.println("图片已经显示:"+take.getFuture().getIndex());
                    renderImage(take.getFuture().getIndex(),null);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
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
