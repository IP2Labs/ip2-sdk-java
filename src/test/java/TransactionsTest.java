/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ug.co.intelworld.constants.Environment;
import ug.co.intelworld.exceptions.IP2GatewayException;
import ug.co.intelworld.services.IP2Gateway;
import ug.co.intelworld.services.Transactions;

/**
 *
 * @author herbert
 */
public class TransactionsTest {
    
    public TransactionsTest() {
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
     public void testThatCanGettransactions(){
         
         try {
            final String subscriptionId = "092316011101C489965F8449899C7D171CC8CAF4E6";
            final String accountId = "256758649804";
            final String username = "";
            final String password = "";
            
           IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, subscriptionId, accountId, username, password);
           
            
             Transactions[] transactions =  gateway.getTransactions();
            
            Assert.assertTrue(transactions.length > 0);
                        
        } catch (IP2GatewayException ex) {
            Assert.fail("Exception not expected");
            Logger.getLogger(AccountDetailsTests.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
