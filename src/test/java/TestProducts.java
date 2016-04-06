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
    	 final String subscriptionId = "092316011101C489965F8449899C7D171CC8CAF4E6";
         final String accountId = "256758649804";
         final String username = "username";
         final String password = "password";
         
         IP2Gateway gateway = new IP2Gateway(Environment.PRODUCTION, subscriptionId, accountId, username, password);
         
         try 
         {
			gateway.getProductItems("");
		 }
         catch (IP2GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
