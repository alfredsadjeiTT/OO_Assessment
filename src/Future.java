public class Future extends Product {

    private String contractCode;
    private int month;
    private int year;

    public Future(String productID, String exchange,String contractCode, int month, int year,ProductPricingService pps) {
        super(productID, exchange);
        this.contractCode = contractCode;
        this.month = month;
        this.pps = pps;
        this.price = this.pps.price(this.exchange,contractCode,month,year);
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
