package practice6.SimpleExecutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static practice6.SingleThread.SingleThreadWebServer.handleRequest;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice6.SimpleExecutor
 * @Date 2018/7/10 上午12:34
 */
public class TaskExecutionWebServer {

    private static final int NUMTHREADS = 100;
    /**
     * 初始化为100个线程执行任务
     */
    private static final Executor exec =
            Executors.newFixedThreadPool(NUMTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);

        while (true) {
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
}
