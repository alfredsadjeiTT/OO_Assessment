import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class AuditorTests {

    @Mock
    ProductPricingService PPService;

    @Before
    public void setup(){
        when(PPService.price(Mockito.anyString(),Mockito.anyString())).thenReturn(330.00);
        when(PPService.price(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(330.00);

    }

    @After
    public void tearDown(){

    }

    @Test
    public void newProductAdded() throws ProductAlreadyRegisteredException{
        Stock Apple = new Stock("3D","NSDQ","APPL",PPService);

        Future Tesla = new Future("4P","NSDQ","44D",2,2021,PPService);

        Auditor auditor = new Auditor();

        auditor.addNewProduct(Apple);
        auditor.addNewProduct(Tesla);

        Assert.assertTrue(auditor.getAuditList().contains(Apple));
        Assert.assertTrue(auditor.getAuditList().contains(Tesla));
    }

    @Test(expected = ProductAlreadyRegisteredException.class)
    public void alreadyRegistered() throws ProductAlreadyRegisteredException{
        Auditor auditor = new Auditor();

        auditor.addNewProduct(new Stock("3D","NSDQ","APPL",PPService));
        auditor.addNewProduct(new Stock("3D","NSDQ","APPL",PPService));

    }

    @Test
    public void quantityBooked() throws ProductAlreadyRegisteredException{
        Stock Apple = new Stock("3D","NSDQ","APPL",PPService);


        Auditor auditor = new Auditor();

        auditor.addNewProduct(Apple);


        auditor.trade(Apple,12);

        Assert.assertEquals(12, (int) auditor.getTradedQuantityTable().get(Apple.getProductID()));
    }


    @Test(expected = IllegalArgumentException.class)
    public void negativeQuantity() throws ProductAlreadyRegisteredException,IllegalArgumentException{
        Stock Apple = new Stock("3D","NSDQ","APPL",PPService);

        Auditor auditor = new Auditor();

        auditor.addNewProduct(Apple);

        auditor.trade(Apple,-3);

    }


    @Test
    public void totalTradeQuantity() throws ProductAlreadyRegisteredException{

        Stock Apple = new Stock("3D","NSDQ","APPL",PPService);

        Future Tesla = new Future("4P","NSDQ","44D",2,2021,PPService);

        Auditor auditor = new Auditor();

        auditor.addNewProduct(Apple);
        auditor.addNewProduct(Tesla);

        auditor.trade(Apple,10);
        auditor.trade(Tesla,10);

        int total = auditor.totalTradeQuantityForDay();

        Assert.assertEquals(total,20);
    }

    @Test
    public void totalValueOfTradedProducts() throws ProductAlreadyRegisteredException{

        //price is always 330.00
        //total val = price * quantity
        //total val per product with quantity 10 = 3300.00
        //total overall for two products of quantity 10 = 6600.00

        Stock Apple = new Stock("3D","NSDQ","APPL",PPService);

        Future Tesla = new Future("4P","NSDQ","44D",2,2021,PPService);

        Auditor auditor = new Auditor();

        auditor.addNewProduct(Apple);
        auditor.addNewProduct(Tesla);



        auditor.trade(Apple,10);
        auditor.trade(Tesla,10);


        double total = auditor.totalValueOfDaysTradedProducts();

        Assert.assertEquals(6600.0, total, 0.0);
    }




}
