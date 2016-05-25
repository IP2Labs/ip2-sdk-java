/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.services;

import java.math.BigDecimal;

/**
 *
 * @author herbert
 */
public class AccountDetails {

        private String accountId;
        private String subscriptionId;
        private String accountType;
        private String currencyCode;
        private String countryCode;
        private String accountStatus;
        
		public String getAccountId()
		{
			return accountId;
		}
		
		public void setAccountId(String accountId)
		{
			this.accountId = accountId;
		}
		public String getSubscriptionId() 
		{
			return subscriptionId;
		}
		public void setSubscriptionId(String subscriptionId) 
		{
			this.subscriptionId = subscriptionId;
		}
		public String getAccountType() 
		{
			return accountType;
		}
		public void setAccountType(String accountType) 
		{
			this.accountType = accountType;
		}
		public String getCurrencyCode() 
		{
			return currencyCode;
		}
		public void setCurrencyCode(String currencyCode) 
		{
			this.currencyCode = currencyCode;
		}
		public String getCountryCode() 
		{
			return countryCode;
		}
		public void setCountryCode(String countryCode) 
		{
			this.countryCode = countryCode;
		}
		public String getAccountStatus() 
		{
			return accountStatus;
		}
		public void setAccountStatus(String accountStatus) 
		{
			this.accountStatus = accountStatus;
		}
        
        

}
