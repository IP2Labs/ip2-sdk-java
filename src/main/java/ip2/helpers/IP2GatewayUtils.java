/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.helpers;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author herbert
 */
public class IP2GatewayUtils {
    
    public static String generateUniqueID(){
        return UUID.randomUUID().toString();
    }
    
    public static String generateAlphaNumeric()
    {
    	return RandomStringUtils.random(10, true, true).toUpperCase();
    }
           
}
