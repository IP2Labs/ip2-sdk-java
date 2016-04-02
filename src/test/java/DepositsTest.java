/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ug.co.intelworld.constants.Environment;
import ug.co.intelworld.exceptions.IP2GatewayException;
import ug.co.intelworld.helpers.IP2GatewayUtils;
import ug.co.intelworld.services.IP2Gateway;
import ug.co.intelworld.services.IP2Response;
import ug.co.intelworld.services.PaymentRequest;

/**
 *
 * @author herbert
 */
public class DepositsTest {
    
    public DepositsTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
     public void testThatCanMakePaymentRequest(){
         
           try {
            final String subscriptionId = "09231504302F2456B4B6AA46DAA815B2D9C15EAA4E";
            final String accountId = "256758649804";
            final String username = "";
            final String password = "";
            
           IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, subscriptionId, accountId, username, password);
           
              Map<String,Object> channelReference = new HashMap<String, Object>();
              channelReference.put("PhoneNumber","256777110054");
              channelReference.put("Message","Deposit for Naphlin");
              
              Map<String,Object> paymentReference = new HashMap<>();
              paymentReference.put("PhoneNumber","256777110054");
              paymentReference.put("Name", "YOUG");
              
              
               PaymentRequest request = new PaymentRequest(
                       IP2GatewayUtils.generateUniqueID(), 
                       IP2GatewayUtils.generateUniqueID(),
                       new BigDecimal("500"), 
                       "SMS", 
                       "YOUG", 
                       "Airtime for Naphlin",
                       channelReference,
                       paymentReference,
                       null,
                       null);
           
            
               IP2Response response =  gateway.deposit(request);
            
            Assert.assertEquals(response.getResponseCode(),0);
                        
        } catch (IP2GatewayException ex) {
            Assert.fail("Exception not expected");
            Logger.getLogger(AccountDetailsTests.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
     }
}
