import java.util.*;
import java.util.stream.Collectors;


public class Auditor implements MontrealTradingProducts {

    private List<Product> auditList = new ArrayList<>();
    private Map<String,Integer> tradedQuantityTable = new HashMap<>();


    public List<Product> getAuditList() {
        return auditList;
    }

    public Map<String, Integer> getTradedQuantityTable() {
        return tradedQuantityTable;
    }

    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {
        if(this.auditList.contains(product)){
            throw new ProductAlreadyRegisteredException("Product is already registered.");
        }

        auditList.add(product);

        //This ensures that auditList and tradeQuantityTable are of the same length
        tradedQuantityTable.put(product.getProductID(),0);
    }

    @Override
    public void trade(Product product, int quantity) throws IllegalArgumentException {
        if (isRegistered(product)){
            if (!(quantity < 0)){
                tradedQuantityTable.put(product.getProductID(),quantity);
            }else{
                throw new IllegalArgumentException("Negative quantities are not valid.");
            }

        }

    }

    @Override
    public int totalTradeQuantityForDay() {
        Optional<Integer> totalQuantity;

        totalQuantity = this.tradedQuantityTable.values().stream().reduce(Integer::sum);


      return totalQuantity.get();
    }

    @Override
    public double totalValueOfDaysTradedProducts() {

        double sum = 0;
        
        List<Double> productValueList = this.auditList.stream().map(Product::getPrice).collect(Collectors.toList());


        List<Integer> productQuantityList =  this.tradedQuantityTable.values().stream().collect(Collectors.toList());


        for (int i = 0; i < auditList.size() ; i++) {
            sum += productValueList.get(i)*productQuantityList.get(i);
        }

        return sum;
       
    }

    private boolean isRegistered(Product product){
        return this.auditList.contains(product);
    }
}
