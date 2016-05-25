import ip2.constants.Environment;
import ip2.exceptions.IP2GatewayException;
import ip2.services.AccountBalance;
import ip2.services.AccountDetails;
import ip2.services.IP2Gateway;

import org.junit.Test;



public class TestIP2 {

	@Test
	public void runTestIP2()
	{
		IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, "06f497ccfb6d4bb59fa82947597b5fea-V2",
				"256776120055",
                "BLACK", "BLACK");
		
		gateway.setTimeout(true, 15000, 2000);
		
		try {
			AccountDetails details = gateway.getAccountDetails();
			System.out.println(details.getAccountType());
		} catch (IP2GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
