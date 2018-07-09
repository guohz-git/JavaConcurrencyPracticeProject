package practice5_2.FutureTask;

public class FutureTaskDemo {

    public static void main(String[] args) {

        Preloader preloader = new Preloader();



        try {
            System.out.println("preloading");
            Thread.sleep(5000);
            System.out.println("preload comleted");

            System.out.println("need use data.");
            ProductInfo productInfo = preloader.get();
            System.out.println(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
