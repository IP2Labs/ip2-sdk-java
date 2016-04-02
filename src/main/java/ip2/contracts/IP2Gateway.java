/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.contracts;


import ip2.exceptions.IP2GatewayException;
import ip2.services.AccountDetails;
import ip2.services.PaymentRequest;
import ip2.services.CommerceRequest;
import ip2.services.IP2Response;
import ip2.services.Transactions;

/**
 *
 * @author Naphlin Peter Akena 01/20/2016
 * @version 0.1
 * 
 */
public interface IP2Gateway {
    
    public IP2Response requestPayment(PaymentRequest request) throws IP2GatewayException;
    public IP2Response deposit(PaymentRequest request) throws IP2GatewayException;
    public IP2Response purchase(CommerceRequest request) throws IP2GatewayException;
    public AccountDetails getAccountDetails() throws IP2GatewayException;
    public Transactions[] getTransactions() throws IP2GatewayException;
   
}