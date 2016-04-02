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
import org.junit.Assert;

import org.junit.Test;
import ug.co.intelworld.constants.Environment;
import ug.co.intelworld.services.IP2Gateway;
import ug.co.intelworld.exceptions.IP2GatewayException;
import ug.co.intelworld.helpers.IP2GatewayUtils;
import ug.co.intelworld.services.AccountDetails;
import ug.co.intelworld.services.CommerceRequest;
import ug.co.intelworld.services.IP2Response;
import ug.co.intelworld.services.PaymentRequest;
import ug.co.intelworld.services.Transactions;

/**
 *
 * @author herbert
 */
public class AccountDetailsTests {
    
    public AccountDetailsTests() {
    }
    
 
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testThatCanGetAccountBalance() {
         
        try {
            final String subscriptionId = "092316011101C489965F8449899C7D171CC8CAF4E6";
            final String accountId = "256758649804";
            final String username = "";
            final String password = "";
            
           IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, subscriptionId, accountId, username, password);
           
            AccountDetails details =  gateway.getAccountDetails();
            
            Assert.assertEquals(accountId, details.getAccountId());
                        
        } catch (IP2GatewayException ex) {
            Assert.fail("Exception not expected");
            Logger.getLogger(AccountDetailsTests.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     

     
    
     

}
