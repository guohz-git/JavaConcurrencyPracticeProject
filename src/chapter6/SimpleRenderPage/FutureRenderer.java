package chapter6.SimpleRenderPage;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureRenderer {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    void renderPage(CharSequence source){
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for(ImageInfo info : imageInfos){
//                    result.add(info.downloadImage());
                }
                return result;
            }
        };

        //异步下载任务
        Future<List<ImageData>> submit = executorService.submit(task);
        //先渲染内容
        readerText(source);

        try {
            List<ImageData> imageData = submit.get();
            renderImage(imageData);
        } catch (InterruptedException e) {
            System.out.println("任务执行被中断。");
            Thread.currentThread().interrupt();
            submit.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void renderImage(List<ImageData> imageData) {
    }

    private void readerText(CharSequence source) {
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        List<ImageInfo> list = new ArrayList<>();
        list.add(new ImageInfo("测试文件","test.png"));
        return list;
    }
    
    

}
