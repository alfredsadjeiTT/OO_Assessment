public class Stock extends Product  {

    private String ticker;

    public Stock(String productID, String exchange, String ticker, ProductPricingService pps) {
        super(productID,exchange);
        this.ticker = ticker;
        this.pps = pps;
        this.price = this.pps.price(this.exchange,this.ticker);
    }


    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }


}
