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

    private final String SANDBOX_IP = "http://ec2-54-148-117-189.us-west-2.compute.amazonaws.com";
    private final String PRODUCTION_IP = "https://gemini-api.azurewebsites.net";

    private final Environment environment;
    private final String subscriptionId;
    private final String accountId;
    private final String username;
    private final String password;

    private static int connectTimeout;
    private static int requestTimeout;

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
        
         try {
            final String response = makeHttpRequest(requestUri, requestUri, HTTP_POST, entity);
            JSONObject object = new JSONObject(response);

            IP2Response details = new IP2Response();
            details.correlationId = object.getString("CorrelationId");
            details.createdOn = object.getString("CreatedOn");
            details.helpLink = object.getString("HelpLink");
            details.requestId = object.getString("RequestId");
            details.responseCode = object.getInt("ResponseCode");
            details.responseMessage = object.getString("ResponseMessage");
            details.transactionId = object.getString("TransactionId");

            return details;

        } catch (IP2GatewayException | JSONException | NullPointerException ex)  {
            throw new IP2GatewayException("Failed to process returned IP2 response", ex);
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
        try {
            final String response = makeHttpRequest(requestUri, requestUri, HTTP_GET, null);
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
            throw new IP2GatewayException("Failed to process returned IP2 response", ex);
        }
    }

    protected AccountDetails getAccountDetails() throws IP2GatewayException {

        final String requestUri = "/api/accounts/".concat(accountId).concat("?subscriptionId=").concat(subscriptionId);
        try {
            final String response = makeHttpRequest(requestUri, requestUri, HTTP_GET, null);
            JSONObject object = new JSONObject(response);

            AccountDetails details = new AccountDetails();
            details.accountId = object.getString("AccountId");
            details.accountStatus = object.getString("AccountStatus");
            details.accountType = object.getString("AccountType");
            details.balance = object.getBigDecimal("Balance");
            details.countryCode = object.getString("CountryCode");
            details.createdOn =  object.getString("CreatedOn");
            details.currencyCode = object.getString("CurrencyCode");
            details.totalAmount = object.getBigDecimal("TotalAmount");

            return details;

        } catch (IP2GatewayException | JSONException | NullPointerException ex) {
            throw new IP2GatewayException("Failed to process returned IP2 response", ex);
        }
    }

    public static void setConnectTimeout(int connectTimeout) {
        ProviderImpl.connectTimeout = connectTimeout;
    }

    public static void setRequestTimeout(int requestTimeout) {
        ProviderImpl.requestTimeout = requestTimeout;
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

    protected String makeHttpRequest(String requestUri, String hmacUri, String httpMethod, String entity) throws IP2GatewayException {

        String url_;
        switch (environment) {
            case PRODUCTION:
                url_ = PRODUCTION_IP;
                try {

                    return NetworkHelpers.productionHttpRequest(
                            httpMethod,
                            url_.concat(requestUri),
                            generateHmacHeaders(httpMethod, hmacUri),
                            entity,
                            requestTimeout,
                            connectTimeout);
                } catch (IP2GatewayException | IOException ex) {
                    throw new IP2GatewayException("Exception occured while making network requests", ex);
                }
            case SANDBOX:
                url_ = SANDBOX_IP;
                try {

                    return NetworkHelpers.sandboxHttpRequest(
                            httpMethod,
                            url_.concat(requestUri),
                            generateHmacHeaders(httpMethod, hmacUri),
                            entity,
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
    	String resourceUri = "/api/services?productId=".concat(productId);
    	
    	String getResponse = makeHttpRequest(resourceUri, resourceUri, HTTP_GET, null);
    	
    	JSONObject object = new JSONObject(getResponse);
    	
    	ProductItems[] productItemsResponse = new ProductItems[object.length()];
    	
    	for(int i=0; i<object.length(); i++)
    	{
    		ProductItems productItems  = new ProductItems();
    		productItems.setProductId(object.getString("ProductId"));
    		productItems.setCategory1(object.getString("Category1"));
    		productItems.setCategory2(object.getString("Category2"));
    		productItems.setCategory3(object.getString("Category3"));
    		productItems.setCategory4(object.getString("Category4"));
    		productItems.setName(object.getString("Name"));
    		productItems.setDescription(object.getString("Description"));
    		productItems.setMinimumPrice(object.getBigDecimal("MininumPrice"));
    		productItems.setMaximumPrice(object.getBigDecimal("MaximumPrice"));
    		productItems.setWholeSalePrice(object.getBigDecimal("WholeSalePrice"));
    		productItems.setCurrencyCode(object.getString("CurrencyCode"));
    		productItems.setProductFee(object.getBigDecimal("ProductFee"));
    		productItems.setDiscount(object.getBigDecimal("Discount"));
    		productItems.setTax(object.getBigDecimal("Tax"));
    		
    		productItemsResponse[i] = productItems;
    	}
    	
    	
    	
    	return productItemsResponse;
    }
    
    
    public Products[] getProducts() throws IP2GatewayException
    {
    	String resourceUri = "/api/services";
    	
        String getResponse = makeHttpRequest(resourceUri, resourceUri, HTTP_GET, null);
    	
    	JSONObject object = new JSONObject(getResponse);
    	
    	Products[] productResponse = new Products[object.length()];
    	
    	for(int i=0; i<object.length(); i++)
    	{
    		Products products = new Products();
    		products.setProductId(object.getString("ServiceId"));
    		products.setName(object.getString("Name"));
    		products.setDescription(object.getString("Description"));
    		products.setWebsite(object.getString("Website"));
    		products.setContactEmail(object.getString("ContactEmail"));
    		products.setServiceUri(object.getString("ServiceUri"));
    		products.setCurrencyCode(object.getString("CurrencyCode"));
    		products.setCountryCode(object.getString("CountryCode"));
    		products.setThumbNail(object.getString("ThumbNail"));
    		products.setLargeImage(object.getString("LargeImage"));
    		
    		productResponse[i] = products;
    	}
    	
    	return productResponse;
    }
    

}
