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

    private String batchId;
    private String refId;
    private String productId;
    private String amount;
    private String currencyCode;
    private String countryCode;
    private String memo;
    private String channelId;
    private String providerId;
    private String userId;
    private HashMap<String, Object> thirdPartyReference;
    private HashMap<String, Object> paymentReference;
    private HashMap<String, Object> metaData;
    private HashMap<String, Object> productReference;
    
    
	public TransactionRequest(String batchId, String refId, String productId,
			String amount, String currencyCode, String countryCode,
			String memo, String channelId, String providerId, String userId,
			HashMap<String, Object> thirdPartyReference,
			HashMap<String, Object> paymentReference,
			HashMap<String, Object> metaData,
			HashMap<String, Object> productReference) {
		
		this.batchId = batchId;
		this.refId = refId;
		this.productId = productId;
		this.amount = amount;
		this.currencyCode = currencyCode;
		this.countryCode = countryCode;
		this.memo = memo;
		this.channelId = channelId;
		this.providerId = providerId;
		this.userId = userId;
		this.thirdPartyReference = thirdPartyReference;
		this.paymentReference = paymentReference;
		this.metaData = metaData;
		this.productReference = productReference;
	}


	public String getBatchId() {
		return batchId;
	}


	public String getRefId() {
		return refId;
	}


	public String getProductId() {
		return productId;
	}


	public String getAmount() {
		return amount;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public String getMemo() {
		return memo;
	}


	public String getChannelId() {
		return channelId;
	}


	public String getProviderId() {
		return providerId;
	}


	public String getUserId() {
		return userId;
	}


	public HashMap<String, Object> getThirdPartyReference() {
		return thirdPartyReference;
	}


	public HashMap<String, Object> getPaymentReference() {
		return paymentReference;
	}


	public HashMap<String, Object> getMetaData() {
		return metaData;
	}


	public HashMap<String, Object> getProductReference() {
		return productReference;
	}
	
	
	
    
    
    
    
}
