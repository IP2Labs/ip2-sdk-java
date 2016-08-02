
import java.util.HashMap;

import ip2.constants.Environment;
import ip2.constants.ServiceKeys;
import ip2.exceptions.IP2GatewayException;
import ip2.helpers.IP2GatewayUtils;
import ip2.services.AccountBalance;
import ip2.services.AccountDetails;
import ip2.services.IP2Gateway;
import ip2.services.IP2Response;
import ip2.services.TransactionRequest;

import org.junit.Test;



public class TestIP2 {

	@Test
	public void runTestIP2()
	{
		IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, "F6E4CA2A3F214BD886D2D896FE81B331V2", "256776120055", "BLACK", "BLACK");
		
		TransactionRequest request = new TransactionRequest();
		request.setBatchId("BATCH-1");
		request.setRequestId("B6E6E123AB4SB6");
		request.setPaymentMethodId("IP2WALLETUG");
		request.setProductId("AIRTELAIRTIMEUG");
		request.setAmount("5000");
		request.setCurrencyCode("UGX");
		request.setCountryCode("256");
		request.setMemo("My airtime");
		request.setChannelId("USSD");
		request.setCustomerId("100008938298");
		
		HashMap<String, String> requestReference = new HashMap<String, String>();
		requestReference.put("PhoneNumber", "256784703425");
		requestReference.put("ReceiptId", "1234");
		
		HashMap<String, String> paymentMethodReference = new HashMap<String, String>();
		paymentMethodReference.put("SrcMsisdn", "256784703425");
		paymentMethodReference.put("DstMsisdn", "256784703425");
		
		HashMap<String, String> metaData = new HashMap<String, String>();
		metaData.put("CreatedOn", "2016-06-12 14:30:23");
		
		HashMap<String, String> productReference = new HashMap<String, String>();
		productReference.put("Msisdn","25677MYPHONE");
		productReference.put("VoucherId", "NYPHO");
		
		HashMap<String, String> channelReference = new HashMap<String, String>();
		channelReference.put("AppId", "28047502834092305");
		
		request.setRequestReference(requestReference);
		request.setPaymentMethodReference(paymentMethodReference);
		request.setMetaData(metaData);
		request.setProductReference(productReference);
		request.setChannelReference(channelReference);
		
		IP2Response response;
		try {
			//gateway.setTimeout(20000, 20);
			response = gateway.requestDebit(request);
			System.out.println(response.getData());
		} catch (IP2GatewayException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		/*try {
			AccountDetails details = gateway.getAccountDetails();
			System.out.println(details.getAccountStatus());
		} catch (IP2GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    
	}
	
}
