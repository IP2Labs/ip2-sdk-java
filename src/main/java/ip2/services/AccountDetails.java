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

        
    
    protected String accountId;
    protected String accountType;
    protected String currencyCode;
    protected String countryCode;
    protected BigDecimal totalAmount;
    protected String accountStatus;
    protected BigDecimal balance;
    protected String createdOn;

    public String getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCreatedOn() {
        return createdOn;
    }

}
