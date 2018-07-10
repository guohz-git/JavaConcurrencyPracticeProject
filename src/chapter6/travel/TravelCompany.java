package chapter6.travel;

public class TravelCompany {

    private String companyName;
    private String tel;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 旅游公司报价旅游价格
     * @param travelInfo 旅游信息
     * @return
     */
    public TravelQuote solicitQuote(TravelInfo travelInfo) throws InterruptedException {
        //模拟耗时
        Thread.sleep(4000);
        if("南京".equalsIgnoreCase(travelInfo.getDestination())){
            return new TravelQuote(travelInfo.getDays(),travelInfo.getDays()*travelInfo.getNums()*200);
        }else if("上海".equalsIgnoreCase(travelInfo.getDestination())){
            return new TravelQuote(travelInfo.getDays(),travelInfo.getDays()*travelInfo.getNums()*400);
        }else{
            return new TravelQuote(travelInfo.getDays(),travelInfo.getDays()*travelInfo.getNums()*100);
        }
    }

    public TravelCompany(String companyName, String tel) {
        this.companyName = companyName;
        this.tel = tel;
    }
}
