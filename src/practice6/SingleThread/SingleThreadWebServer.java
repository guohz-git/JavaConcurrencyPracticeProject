package practice6.SingleThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice6.SingleThread
 * @Date 2018/7/10 上午12:22
 */
public class SingleThreadWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket accept = socket.accept();
            handleRequest(accept);
        }
    }

    private static void handleRequest(Socket accept) {
        System.out.println("处理请求。");
    }
}
