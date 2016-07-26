/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author herbert
 */
public class TransactionRequest {

	public String batchId;
	public String requestId;
	public String paymentMethodId;
	public String productId;
	public String amount;
	public String currencyCode;
	public String countryCode;
	public String memo;
	public String channelId;
	public String customerId;
	
	public HashMap<String, String> requestReference;
	public HashMap<String, String> paymentMethodReference;
	public HashMap<String, String> metaData;
	public HashMap<String, String> productReference;
	public HashMap<String, String> channelReference;
	
	
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public HashMap<String, String> getRequestReference() {
		return requestReference;
	}
	public void setRequestReference(HashMap<String, String> requestReference) {
		this.requestReference = requestReference;
	}
	public HashMap<String, String> getPaymentMethodReference() {
		return paymentMethodReference;
	}
	public void setPaymentMethodReference(
			HashMap<String, String> paymentMethodReference) {
		this.paymentMethodReference = paymentMethodReference;
	}
	public HashMap<String, String> getMetaData() {
		return metaData;
	}
	public void setMetaData(HashMap<String, String> metaData) {
		this.metaData = metaData;
	}
	public HashMap<String, String> getProductReference() {
		return productReference;
	}
	public void setProductReference(HashMap<String, String> productReference) {
		this.productReference = productReference;
	}
	public HashMap<String, String> getChannelReference() {
		return channelReference;
	}
	public void setChannelReference(HashMap<String, String> channelReference) {
		this.channelReference = channelReference;
	}
	
    
}
