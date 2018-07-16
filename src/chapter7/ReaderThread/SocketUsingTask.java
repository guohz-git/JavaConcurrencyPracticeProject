package chapter7.ReaderThread;

import chapter7.newTaskFor.CancellableTask;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class SocketUsingTask<T> implements CancellableTask {

    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public synchronized  void cancel(){
        if(socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public RunnableFuture<T> newTask(){
        return new FutureTask<T>(this){
            @Override
            public boolean cancel(boolean mayInterruptIfRunning){
                SocketUsingTask.this.cancel();
                return super.cancel(mayInterruptIfRunning);
            }
        };
    }
}
