    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.co.intelworld.services;

//import org.apache.commons.lang3.StringUtils;

import ug.co.intelworld.constants.Environment;
import ug.co.intelworld.exceptions.IP2GatewayException;


/**
 *
 * @author herbert
 */
public class IP2Gateway extends ProviderImpl implements ug.co.intelworld.contracts.IP2Gateway {

    public IP2Gateway(Environment environment, String subscriptionId, String accountId, String username, String password) {
        super(environment, subscriptionId, accountId, username, password);
    }

    @Override
    public IP2Response requestPayment(PaymentRequest request) throws IP2GatewayException {
        return super.makePayment(request, 0);
    }

    @Override
    public IP2Response deposit(PaymentRequest request) throws IP2GatewayException {
        return super.makePayment(request, 1);
    }

    @Override
    public IP2Response purchase(CommerceRequest request) throws IP2GatewayException {
        return super.makeCommerce(request);
    }

    @Override
    public AccountDetails getAccountDetails() throws IP2GatewayException {
        return super.getAccountDetails();
    }

    @Override
    public Transactions[] getTransactions() throws IP2GatewayException {
       return super.getTransactions();
    }
}