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
import ip2.constants.Environment;
import ip2.exceptions.IP2GatewayException;
import ip2.helpers.IP2GatewayUtils;
import ip2.services.CommerceRequest;
import ip2.services.IP2Gateway;
import ip2.services.IP2Response;

/**
 *
 * @author herbert
 */
public class CommerceTests {
    
    public CommerceTests() {
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
     public void testThatCanMakeCommerceRequest(){
         
           try {
            final String subscriptionId = "092316011101C489965F8449899C7D171CC8CAF4E6";
            final String accountId = "256758649804";
            final String username = "";
            final String password = "";
            
           IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, subscriptionId, accountId, username, password);
           
              Map<String,Object> channelReference = new HashMap<String, Object>();
              channelReference.put("PhoneNumber","256777110054");
              channelReference.put("Message","Airtime for naphlin");
              
              Map<String,Object> productReference = new HashMap<>();
              productReference.put("PhoneNumber","256777110054");
              productReference.put("Name", "AIRTIME");
              
              
               CommerceRequest request = new CommerceRequest(
                       IP2GatewayUtils.generateUniqueID(), 
                       IP2GatewayUtils.generateUniqueID(),
                       new BigDecimal("500"), 
                       "SMS", 
                       "AIRTIMESEND", 
                       "Airtime for Naphlin",
                       channelReference,
                       productReference,
                       null,
                       null);
           
            
               IP2Response response =  gateway.purchase(request);
            
            Assert.assertEquals(0, response.getResponseCode());
                        
        } catch (IP2GatewayException ex) {
            Assert.fail("Exception not expected");
            Logger.getLogger(AccountDetailsTests.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
     }
}
