import java.net.SocketTimeoutException;
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
		IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, "06f497ccfb6d4bb59fa82947597b5fea-V2", "256776120055", "BLACK", "BLACK");
		
		HashMap<String, Object> tp = new HashMap<String, Object>();
		tp.put("DeviceId", "847383748374");
		
		HashMap<String, Object> paymentf= new HashMap<String, Object>();
		paymentf.put("MSISDN", "256776120055");
		
		HashMap<String, Object> productf= new HashMap<String, Object>();
		productf.put("MSISDN", "256784703425");
		
		HashMap<String, Object> md= new HashMap<String, Object>();
		md.put("Location", "000");
		
		
		
		TransactionRequest request = new TransactionRequest(IP2GatewayUtils.generateUniqueID(), IP2GatewayUtils.generateUniqueID(),
				"MTNUGAIRTIME", "500", "UGX", "256", "Airtime test", "Android_Payapp", "IP2", "", tp, paymentf, md, productf);
		
		IP2Response response;
		try {
			response = gateway.requestDebit(request);
			System.out.println(response.getData());
		} catch (IP2GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
		
	    
	}
	
}
