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
    public class FutureTest {

        @Mock
        ProductPricingService PPService;

        @Before
        public void setup() {
            when(PPService.price(Mockito.anyString(), Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(330.00);

        }

        @After
        public void tearDown() {

        }

        @Test
        public void priceServiceTest() {

            Future MicroSoft = new Future("445D", "NASDAQ","33445NSDQ",2 ,2021 , PPService);

            verify(PPService).price("NASDAQ","33445NSDQ",2,2021);
        }

        @Test
        public void futureCreated() {

            Future MicroSoft = new Future("445D", "NASDAQ","33445NSDQ",2 ,2021 , PPService);


            Assert.assertEquals(MicroSoft.getPrice(), 330.00, 0.001);
        }
    }
