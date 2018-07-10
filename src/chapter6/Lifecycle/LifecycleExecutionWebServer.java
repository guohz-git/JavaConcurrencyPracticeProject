package chapter6.Lifecycle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * chapter6.SimpleExecutor
 * @Date 2018/7/10 上午12:34
 */
public class LifecycleExecutionWebServer {

    private static final int NUMTHREADS = 100;
    /**
     * 初始化为100个线程执行任务
     */
    private static final ExecutorService exec =
            Executors.newFixedThreadPool(NUMTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);

        //程序关闭状态不在接受任务，执行完已接收任务
        while (!exec.isShutdown()) {
            Socket accept = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(accept);
                }
            };

            exec.execute(task);
        }
    }

    static void handleRequest(Socket accept) {
        System.out.println("处理任务");
    }

    public void stop() {
        exec.shutdown();
    }


}
