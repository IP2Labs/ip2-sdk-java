/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.co.intelworld.helpers;

import java.util.UUID;

/**
 *
 * @author herbert
 */
public class IP2GatewayUtils {
    
    public static String generateUniqueID(){
        return UUID.randomUUID().toString();
    }
           
}
