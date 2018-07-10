package chapter6.travel;

/**
 * 客户旅游信息
 */
public class TravelInfo {

    private String startPlace;
    private String destination;
    private int days;
    private int nums;


    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public TravelInfo(String startPlace, String destination, int days, int nums) {
        this.startPlace = startPlace;
        this.destination = destination;
        this.days = days;
        this.nums = nums;
    }

    public TravelInfo() {
    }


}
