/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.services;

import intelworld.hmacauth.exception.EmptyOrNullException;
import intelworld.hmacauth.impl.Constants;
import intelworld.hmacauth.impl.HmacAuthHeadersImpl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ip2.constants.Environment;
import ip2.exceptions.IP2GatewayException;

/**
 *
 * @author herbert
 */
abstract class ProviderImpl {

    public static final String DEV_ENVIRONMENT = "SANDBOX";
    public static final String PROD_ENVIRONMENT = "PRODUCTION";
    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";

    private final String SANDBOX_IP = "http://ip2-sandbox.intelworld.international:84";
    private final String PRODUCTION_IP = "https://gemini-api.azurewebsites.net";

    private final Environment environment;
    private final String subscriptionId;
    private final String accountId;
    private final String username;
    private final String password;

    private static int connectTimeout;
    private static int requestTimeout;
    private static boolean setTimeout = false;

    public ProviderImpl(Environment environment, String subscriptionId, String accountId, String username, String password) {
        this.environment = environment;
        this.subscriptionId = subscriptionId;
        this.accountId = accountId;
        this.username = username;
        this.password = password;
    }
    
    

    protected IP2Response makePayment(PaymentRequest request,int type) throws IP2GatewayException {

        //type 0 - requestpayment /1 -depositpayments
        JSONObject object = new JSONObject();

        object.put("RequestId", request.getRequestId());
        object.put("BatchId", request.getBatchId());
        object.put("Amount", request.getAmount());
        object.put("Memo", request.getMemo());
        object.put("Payment", request.getPayment());
        object.put("Channel", request.getChannel());
        

        JSONObject paymentRef = new JSONObject();
        Map<String, Object> pr = request.getPaymentReference();

        for (Map.Entry<String, Object> entry : pr.entrySet()) {
            paymentRef.put(entry.getKey(), entry.getValue());

        }

        object.put("PaymentReference", paymentRef);

        JSONObject channelRef = new JSONObject();
        Map<String, Object> cr = request.getChannelReference();

        for (Map.Entry<String, Object> entry : cr.entrySet()) {
            channelRef.put(entry.getKey(), entry.getValue());

        }

        object.put("ChannelReference", channelRef);

        JSONObject clientRef = new JSONObject();

        Map<String, Object> clr = request.getChannelReference();

        if (clr != null && clr.size() > 0) {
            for (Map.Entry<String, Object> entry : cr.entrySet()) {
                clientRef.put(entry.getKey(), entry.getValue());

            }

            object.put("ClientReference", clientRef);
        } else {
            object.put("ClientReference", "{}");
        }

        JSONObject metaRef = new JSONObject();

        Map<String, Object> meta = request.getChannelReference();

        if (meta != null && meta.size() > 0) {
            for (Map.Entry<String, Object> entry : cr.entrySet()) {
                metaRef.put(entry.getKey(), entry.getValue());

            }

            object.put("MetaData", metaRef);
        } else {
            object.put("MetaData", "{}");
        }
        final String requestUri;
        if(type == 0){
            requestUri = "/api/creditPayments?accountId=".concat(accountId).concat("&subscriptionId=").concat(subscriptionId);
        }else{
            requestUri = "/api/debitPayments?accountId=".concat(accountId).concat("&subscriptionId=").concat(subscriptionId);
        }
        
        return makePCRequest(object.toString(), requestUri);

    }
    
    
    
    protected IP2Response makePCRequest(String entity,String requestUri) throws IP2GatewayException{
        
    	 TransportImpl response = null;
         try {
            response = makeHttpRequest(requestUri, requestUri, HTTP_POST, entity, setTimeout);
            JSONObject object = new JSONObject(response.getMessage());

            IP2Response details = new IP2Response();
            details.correlationId = object.getString("CorrelationId");
            details.createdOn = object.getString("CreatedOn");
            details.helpLink = object.getString("HelpLink");
            details.requestId = object.getString("RequestId");
            details.responseCode = object.getInt("HttpStatusCode");
            details.responseMessage = object.getString("ResponseMessage");
            details.transactionId = object.getString("TransactionId");

            return details;

        } catch (IP2GatewayException | JSONException | NullPointerException ex)  {
        	  if(response.getLineStatus() == 508)
    		  {
    			  throw new IP2GatewayException("Server timeout", ex);
    		  }
    		  else if(response.getLineStatus() == 408)
    		  {
    			  throw new IP2GatewayException("Connection timeout", ex);
    		  }
    		  else
    		  {
    			  throw new IP2GatewayException("Failed to process returned IP2 response", ex);
    		  }
        }
    }

    protected IP2Response makeCommerce(CommerceRequest request) throws IP2GatewayException{
        
         //type 0 - requestpayment /1 -depositpayments
        JSONObject object = new JSONObject();

        object.put("RequestId", request.getRequestId());
        object.put("BatchId", request.getBatchId());
        object.put("Amount", request.getAmount());
        object.put("Memo", request.getMemo());
        object.put("Product", request.getProduct());
        object.put("Channel", request.getChannel());

        JSONObject paymentRef = new JSONObject();
        Map<String, Object> pr = request.getProductReference();

        for (Map.Entry<String, Object> entry : pr.entrySet()) {
            paymentRef.put(entry.getKey(), entry.getValue());

        }

        object.put("ProductReference", paymentRef);

        JSONObject channelRef = new JSONObject();
        Map<String, Object> cr = request.getChannelReference();

        for (Map.Entry<String, Object> entry : cr.entrySet()) {
            channelRef.put(entry.getKey(), entry.getValue());

        }

        object.put("ChannelReference", channelRef);

        JSONObject clientRef = new JSONObject();

        Map<String, Object> clr = request.getClientReference();

        if (clr != null && clr.size() > 0) {
            for (Map.Entry<String, Object> entry : cr.entrySet()) {
                clientRef.put(entry.getKey(), entry.getValue());

            }

            object.put("ClientReference", clientRef);
        } else {
            object.put("ClientReference", "{}");
        }

        JSONObject metaRef = new JSONObject();

        Map<String, Object> meta = request.getMeta();

        if (meta != null && meta.size() > 0) {
            for (Map.Entry<String, Object> entry : cr.entrySet()) {
                metaRef.put(entry.getKey(), entry.getValue());

            }

            object.put("MetaData", metaRef);
        } else {
            object.put("MetaData", "{}");
        }
        final String requestUri = "/api/debitWallets?accountId=".concat(accountId).concat("&subscriptionId=").concat(subscriptionId);
        
         return makePCRequest(object.toString(), requestUri);
    }
    
    protected Transactions[] getTransactions() throws IP2GatewayException {

         final String requestUri = "/api/transactions/".concat(accountId).concat("?subscriptionId=").concat(subscriptionId);
         TransportImpl response = null;
        try {
            response = makeHttpRequest(requestUri, requestUri, HTTP_GET, null, setTimeout);
            JSONArray array = new JSONArray(response);
            int length = array.length();
            
            Transactions[] tr_list= new Transactions[length];
            for(int i = 0;i<length;i++){
                JSONObject object = (JSONObject)array.get(i);
                
                Transactions tr = new Transactions();                
                tr.accountId = object.getString("AccountId");
                tr.amount = object.getBigDecimal("Amount");
                tr.channel = object.getString("Channel");
                tr.country = object.getString("Country");
                tr.creationDate = object.getString("CreationDate");
                tr.currency = object.getString("Currency");
                tr.memo = object.getString("Memo");
                tr.paymentMethod = object.getString("PaymentMethod");
                tr.service = object.getString("Service");
                tr.status = object.getString("Status");
                tr.statusMessage = object.getString("StatusMessage");
                
                tr_list[i] = tr;
                
            }
            return tr_list;

        } catch (IP2GatewayException | JSONException | NullPointerException ex)  {
        	  if(response.getLineStatus() == 508)
    		  {
    			  throw new IP2GatewayException("Server timeout", ex);
    		  }
    		  else if(response.getLineStatus() == 408)
    		  {
    			  throw new IP2GatewayException("Connection timeout", ex);
    		  }
    		  else
    		  {
    			  throw new IP2GatewayException("Failed to process returned IP2 response", ex);
    		  }
        }
    }

    protected AccountDetails getAccountDetails() throws IP2GatewayException {

        final String requestUri = "/api/v2/accounts/".concat(subscriptionId).concat("/").concat(accountId);
        TransportImpl response = null;
        try {
            response = makeHttpRequest(requestUri, requestUri, HTTP_GET, null, setTimeout);
            JSONObject object = new JSONObject(response.getMessage());

            AccountDetails details = new AccountDetails();
            details.setAccountId(object.getString("AccountId"));
            details.setSubscriptionId(object.getString("SubscriptionId"));
            details.setAccountType(object.getString("AccountType"));
            details.setCurrencyCode(object.getString("CurrencyCode"));
            details.setCountryCode(object.getString("CountryCode"));
            details.setAccountStatus(object.getString("AccountStatus"));

            return details;

        } catch (IP2GatewayException | JSONException | NullPointerException ex) {
        	if(response != null)
        	{
        		if(response.getLineStatus() == 508)
      		  {
      			  throw new IP2GatewayException("Server timeout", ex);
      		  }
      		  else if(response.getLineStatus() == 408)
      		  {
      			  throw new IP2GatewayException("Connection timeout", ex);
      		  }
        	}  
    		else
    		{
    		   throw new IP2GatewayException("Failed to process returned IP2 response", ex);
    		}
        }
		return null;
    }
    
    
    public AccountBalance getAccountBal() throws IP2GatewayException
    {
    	  final String requestUri = "/api/v2/accounts/balances/".concat(subscriptionId).concat("/").concat(accountId);
    	  TransportImpl response = null;
    	  try
    	  {
    		  response = makeHttpRequest(requestUri, requestUri, HTTP_GET, null, setTimeout);
    		  System.out.println(response);
    		  JSONObject object = new JSONObject(response.getMessage());
    		  
    		  AccountBalance balance = new AccountBalance();
    		  balance.setBalance(object.getLong("Balance"));
    		  
    		  return balance;
    	  }
    	  catch (IP2GatewayException | JSONException | NullPointerException ex) {
    		  if(response.getLineStatus() == 508)
    		  {
    			  throw new IP2GatewayException("Server timeout", ex);
    		  }
    		  else if(response.getLineStatus() == 408)
    		  {
    			  throw new IP2GatewayException("Connection timeout", ex);
    		  }
    		  else
    		  {
    			  throw new IP2GatewayException("Failed to process returned IP2 response", ex);
    		  }
             
          }
    	  
    	
    }

    public void setTimeout(boolean setTimeout, int requestTimeout, int connectionTimeout) {
        ProviderImpl.requestTimeout = requestTimeout;
        ProviderImpl.connectTimeout = connectionTimeout;
        ProviderImpl.setTimeout = setTimeout;
    }

    protected String encodeUrlValue(String value) throws IP2GatewayException {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new IP2GatewayException("Failed to encode url parameters with UTF-8 encoding", ex);
        }

    }

    protected Map<String, String> generateHmacHeaders(String requestMethod, String requestUri) throws IP2GatewayException {

        HmacAuthHeadersImpl hmac = new HmacAuthHeadersImpl(Constants.MEDIA_JSON, username, password,
                Constants.HMAC_SHA512, Constants.MD_SHA_512,
                Constants.MD5);

        try {
            return hmac.getHeadersAsMap(requestMethod, requestUri);
            //    return hmac.getHeadersAsMap(requestMethod, requestUri,jsonRequestUri);
        } catch (InvalidKeyException | NoSuchAlgorithmException | EmptyOrNullException | UnsupportedEncodingException ex) {
            throw new IP2GatewayException("Exception while generating Hmac Headers", ex);
        }

    }

    protected TransportImpl makeHttpRequest(String requestUri, String hmacUri, String httpMethod, String entity, boolean setTimeout) throws IP2GatewayException {

        String url_;
        switch (environment) {
            case PRODUCTION:
                url_ = PRODUCTION_IP;
                try {

                    return NetworkHelpers.productionHttpRequest(
                            httpMethod,
                            url_.concat(requestUri),
                            generateHmacHeaders(httpMethod, hmacUri),
                            entity, setTimeout,
                            requestTimeout,
                            connectTimeout);
                } catch (IP2GatewayException | IOException ex) {
                    throw new IP2GatewayException("Exception occured while making network requests", ex);
                }
            case SANDBOX:
                url_ = SANDBOX_IP;
                try {

                    return  NetworkHelpers.sandboxHttpRequest(
                            httpMethod,
                            url_.concat(requestUri),
                            generateHmacHeaders(httpMethod, hmacUri),
                            entity, setTimeout,
                            requestTimeout,
                            connectTimeout);
                    
                    
                    
                } catch (IP2GatewayException | IOException ex) {
                    throw new IP2GatewayException("Exception occured while making network requests", ex);
                }
            default:
                throw new IP2GatewayException("Unknown execution environment provided, envirments supported are sandbox and production only. Please check in the Enviroment enum");
        }

    }
    
    
    
    public ProductItems[] getProductItems(String productId) throws IP2GatewayException
    {
    	String resourceUri = "/api/Services/".concat(productId);
    	
    	TransportImpl response = makeHttpRequest(resourceUri, resourceUri, HTTP_GET, null, setTimeout);
    	try
    	{
    		JSONObject object = new JSONObject(response.getMessage());
        	
        	ProductItems[] productItemsResponse = new ProductItems[object.length()];
        	
        	for(int i=0; i<object.length(); i++)
        	{
        		ProductItems productItems  = new ProductItems();
        		
        		productItems.setServiceId(object.getString("ServiceId"));
        		productItems.setUsername(object.getString("Username"));
        		productItems.setWebsite((object.getString("Website")));
        		productItems.setContactPhone(object.getString("ContactPhone"));
        		productItems.setContactEmail(object.getString("ContactEmail"));
        		productItems.setName(object.getString("Name"));
        		productItems.setServiceUri(object.getString("ServiceUri"));
        		productItems.setSummary(object.getString("Summary"));
        		productItems.setDescription(object.getString("Description"));
        		productItems.setCurrencyCode(object.getString("CurrencyCode"));
        		productItems.setCountryCode(object.getString("CountryCode"));
        		productItems.setLargeImage(object.getString("LargeImage"));
        		productItems.setThumbNail(object.getString("ThumbNail"));
        		
        		if(object.has("IsActive"))
        		{
        			productItems.setIsActive("Active");
        		}
        		
        		if(object.has("IsPublic"))
        		{
        			productItems.setIsPublic("Public");
        		}
        		
        		productItems.setCategory(object.getString("Category"));
        		
        		productItemsResponse[i] = productItems;
        	}
        		
        	return productItemsResponse;
    	}
    	catch (JSONException | NullPointerException ex)
    	{
   		  if(response.getLineStatus() == 508)
   		  {
   			  throw new IP2GatewayException("Server timeout", ex);
   		  }
   		  else if(response.getLineStatus() == 408)
   		  {
   			  throw new IP2GatewayException("Connection timeout", ex);
   		  }
   		  else
   		  {
   			  throw new IP2GatewayException("Failed to process returned IP2 response", ex);
   		  }
            
        }
    	
    }
    
    
    public Products[] getProducts() throws IP2GatewayException
    {
    	final String requestUri = "/api/services";
    	
        final TransportImpl response = makeHttpRequest(requestUri, requestUri, "GET", null, setTimeout);
    	try
    	{
    		JSONArray array = new JSONArray(response.getMessage());   	
        	Products[] productResponse = new Products[array.length()];
        	
        	for(int i=0; i<array.length(); i++)
        	{
        		JSONObject object = array.getJSONObject(i);
        		Products products = new Products();
        		products.setProductId(object.getString("ServiceId"));
        		products.setName(object.getString("Name"));
        		products.setUsername(object.getString("Username"));
        		products.setDescription(object.getString("Description"));
        		products.setWebsite(object.getString("Website"));
        		products.setContactPhone(object.getString("ContactPhone"));
        		
        		products.setContactEmail(object.getString("ContactEmail"));
        		products.setServiceUri(object.getString("ServiceUri"));
        		products.setCurrencyCode(object.getString("CurrencyCode"));
        		products.setCountryCode(object.getString("CountryCode"));
        		products.setThumbNail(object.getString("ThumbNail"));
        		products.setLargeImage(object.getString("LargeImage"));
        		products.setIsActive(object.getBoolean("IsActive"));
        		
        		if(object.has("IsPrivate"))
        		{
        			products.setIsPrivate(object.getBoolean("IsPrivate"));
        		}
        			
        		productResponse[i] = products;
        	}
        	
        	return productResponse;
    		
    	}
    	catch (JSONException | NullPointerException ex)
    	{
   		  if(response.getLineStatus() == 508)
   		  {
   			  throw new IP2GatewayException("Server timeout", ex);
   		  }
   		  else if(response.getLineStatus() == 408)
   		  {
   			  throw new IP2GatewayException("Connection timeout", ex);
   		  }
   		  else
   		  {
   			  throw new IP2GatewayException("Failed to process returned IP2 response", ex);
   		  }
            
        }
    	
    }
    
    
    public ServiceProducts[] getServiceProducts() throws IP2GatewayException
    {
    	final String requestUri = "/api/ServiceProducts";
    	
    	final TransportImpl response = makeHttpRequest(requestUri, requestUri, HTTP_GET, null, setTimeout);
    	try
    	{
    		JSONArray array = new JSONArray(response.getMessage());
        	ServiceProducts[] service = new ServiceProducts[array.length()];
        	
        	for(int i=0; i<array.length(); i++)
        	{
        		JSONObject object = array.getJSONObject(i);
        		ServiceProducts products = new ServiceProducts();
        		
        		products.setProductId(object.getString("ProductId"));
        		products.setName(object.getString("Name"));
        		products.setDescription(object.getString("Description"));
        		products.setMinimumPrice(object.getLong("MinimumPrice"));
        		products.setCategory1(object.getString("Category1"));
        		products.setCategory2(object.getString("Category2"));
        		products.setCategory3(object.getString("Category3"));
        		products.setCategory4(object.getString("Category4"));
        		products.setMaximumPrice(object.getLong("MaximumPrice"));
        		products.setWholeSalePrice(object.getLong("WholesalePrice"));
        		products.setCurrencyCode(object.getString("CurrencyCode"));
        		products.setProductFee(object.getLong("ProductFee"));
        		products.setTax(object.getLong("Tax"));
        		products.setLargeImage(object.getString("LargeImage"));
        		products.setThumbNail(object.getString("ThumbNail"));
        		
        		service[i] = products;
        	}
        	
        	return service;
    	}
    	catch (JSONException | NullPointerException ex)
    	{
   		  if(response.getLineStatus() == 508)
   		  {
   			  throw new IP2GatewayException("Server timeout", ex);
   		  }
   		  else if(response.getLineStatus() == 408)
   		  {
   			  throw new IP2GatewayException("Connection timeout", ex);
   		  }
   		  else
   		  {
   			  throw new IP2GatewayException("Failed to process returned IP2 response", ex);
   		  }
            
        }
    	
    }
    

}
