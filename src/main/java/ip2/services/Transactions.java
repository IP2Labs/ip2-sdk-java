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
public class Transactions {

    private String transactionId;
    protected String accountId;
    protected String currency;
    protected String country;
    protected String channel;
    protected String paymentMethod;
    protected String service;
    protected BigDecimal amount;
    protected String memo;
    protected String status;
    protected String creationDate;
    protected String statusMessage;

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCountry() {
        return country;
    }

    public String getChannel() {
        return channel;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getService() {
        return service;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMemo() {
        return memo;
    }

    public String getStatus() {
        return status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

}
