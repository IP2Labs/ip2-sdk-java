/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.exceptions;

/**
 *
 * @author herbert
 */
public class IP2GatewayException extends Exception{

    public IP2GatewayException(String message) {
        super(message);
    }

    public IP2GatewayException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
