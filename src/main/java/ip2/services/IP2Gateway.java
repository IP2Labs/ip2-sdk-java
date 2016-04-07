    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.services;

//import org.apache.commons.lang3.StringUtils;

import ip2.constants.Environment;
import ip2.exceptions.IP2GatewayException;


/**
 *
 * @author herbert
 */
public class IP2Gateway extends ProviderImpl implements ip2.contracts.IP2Gateway {

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

	@Override
	public ProductItems[] getProductItems(String productId)
			throws IP2GatewayException {
		// TODO Auto-generated method stub
		return super.getProductItems(productId);
	}

	@Override
	public Products[] getProducts() throws IP2GatewayException {
		// TODO Auto-generated method stub
		return super.getProducts();
	}
}