import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class StockTest {

    @Mock
    ProductPricingService PPService;

    @Before
    public void setup(){
        when(PPService.price(Mockito.anyString(),Mockito.anyString())).thenReturn(330.00);

    }

    @After
    public void tearDown(){

    }

    @Test
    public void priceServiceTest(){

        Stock MicroSoft = new Stock("445D","NASDAQ","MSFT",PPService);

        verify(PPService).price("NASDAQ","MSFT");
    }

    @Test
    public void stockCreated(){

        Stock MicroSoft = new Stock("445D","NASDAQ","MSFT",PPService);

        Assert.assertEquals(MicroSoft.getPrice(),330.00,0.001);
    }
}
