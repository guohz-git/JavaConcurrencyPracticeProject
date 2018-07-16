package chapter7.log;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogWriter {
    private static final int CAPACITY = 3000;
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;


    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        logger = new LoggerThread(writer);
    }

    private class LoggerThread extends Thread{
        private final PrintWriter writer;

        public LoggerThread(Writer writer) {
            this.writer = (PrintWriter) writer;
        }
        @Override
        public void run() {
            try {
                while (true){
                    writer.println(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                writer.close();
            }
        }
    }
}
