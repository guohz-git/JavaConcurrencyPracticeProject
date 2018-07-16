package chapter7.ReaderThread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 关闭Socket通信线程简单版
 */
public class ReaderThread extends Thread {
    private static final int BUFSZ = 1024;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }


    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = 0;
                count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processBuffer(byte[] buf, int count) {
    }
}
