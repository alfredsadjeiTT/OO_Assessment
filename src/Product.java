import java.util.Objects;

public abstract class Product {
    private String productID;
    protected String exchange;
    protected double price;
    protected ProductPricingService pps;


    public Product(String productID, String exchange) {
        this.productID = productID;
        this.exchange = exchange;


    }



    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                productID.equals(product.productID) &&
                exchange.equals(product.exchange) &&
                pps.equals(product.pps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, exchange, price, pps);
    }
}
