/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.co.intelworld.contracts;


import ug.co.intelworld.exceptions.IP2GatewayException;
import ug.co.intelworld.services.AccountDetails;
import ug.co.intelworld.services.PaymentRequest;
import ug.co.intelworld.services.CommerceRequest;
import ug.co.intelworld.services.IP2Response;
import ug.co.intelworld.services.Transactions;

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