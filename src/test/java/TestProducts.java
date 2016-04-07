import ip2.constants.Environment;
import ip2.exceptions.IP2GatewayException;
import ip2.services.IP2Gateway;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestProducts 
{
	public TestProducts() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void runTest()
    {
    	 final String subscriptionId = "092315082879B7A85FF2CD436B91713472AE58B380";
         final String accountId = "256776120056";
         final String username = "intelipaymposug";
         final String password = "TZ[8IjcQ5";
         
         IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, subscriptionId, accountId, username, password);
         
         try 
         {
			gateway.getProducts();
		 }
         catch (IP2GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
