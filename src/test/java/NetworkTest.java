/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;

import org.junit.Test;
import ug.co.intelworld.constants.IP2Codes;
import ug.co.intelworld.contracts.IP2Gateway;
import ug.co.intelworld.exceptions.IP2GatewayException;
import ug.co.intelworld.helpers.IP2GatewayUtils;

/**
 *
 * @author herbert
 */
public class NetworkTest {
    
    public NetworkTest() {
    }
    
  

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testThatCanBuyAirtime() {
         
        try {
            final String ACCOUNT_ID = "256776120056";
            final String USERNAME = "iwadmin";
            final String PASSWORD = "ilov3J3sus";
            
            final String SUBSCRIPTION = "092315082879B7A85FF2CD436B91713472AE58B380";
            
            final String REFERENCE = IP2GatewayUtils.generateUniqueID();
            
            final String PRODUCT_ID = IP2GatewayUtils.generateUniqueID();
            final String RECEIVER_ACCOUNT = "256777110054";
            
            
            IP2Gateway gateway = ug.co.intelworld.services.IP2Gateway.getInstance(
                    ACCOUNT_ID, 
                    USERNAME,
                    PASSWORD, 
                    IP2Codes.Environment.SANDBOX, 
                    PRODUCT_ID);
            
            String response = gateway.topUpAirtime(
                    IP2Codes.ServiceID.SELL_AIRTIME,
                    REFERENCE, //ref id
                    REFERENCE, //batch id
                    SUBSCRIPTION,
                    "500", 
                    IP2Codes.CountryCodes.UGANDA, 
                    IP2Codes.CurrencyCodes.UGX, 
                    "Airtime for Naphlin",
                    IP2Codes.Channels.ANDROIDAPP,
                    RECEIVER_ACCOUNT,
                    true,
                    "Airtime to Naphlin", 
                    true,
                    "Airtime from Herbert");
            
            System.out.println(response);
            
            Assert.assertNotNull(response);
                        
        } catch (IP2GatewayException ex) {
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
