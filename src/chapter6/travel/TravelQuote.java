package chapter6.travel;

/**
 * 公司返回报价信息
 */
public class TravelQuote {

    private int days;
    private double price;

    public TravelQuote(int days, double price) {
        this.days = days;
        this.price = price;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TravelQuote{" +
                "days=" + days +
                ", price=" + price +
                '}';
    }
}
