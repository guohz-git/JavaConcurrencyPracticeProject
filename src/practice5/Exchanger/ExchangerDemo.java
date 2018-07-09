package practice5.Exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, "A");
        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, "B");

        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
    }


    static class ExchangerRunnable extends  Thread{

        Exchanger exchanger;
        Object object;

        public ExchangerRunnable(Exchanger exchanger, Object object) {
            this.exchanger = exchanger;
            this.object = object;
        }

        @Override
        public void run() {

            Object previous = this.object;
            try {
                this.object = this.exchanger.exchange(previous);
                System.out.println(
                        Thread.currentThread().getName() +
                                " exchanged " + previous + " for " + this.object
                );

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
