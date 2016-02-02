    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.co.intelworld.services;

import intelworld.hmacauth.exception.EmptyOrNullException;
import intelworld.hmacauth.impl.Constants;
import intelworld.hmacauth.impl.HmacAuthHeadersImpl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import org.json.JSONObject;
//import org.apache.commons.lang3.StringUtils;
import ug.co.intelworld.constants.IP2Codes;
import ug.co.intelworld.exceptions.IP2GatewayException;

/**
 *
 * @author herbert
 */
public class IP2Gateway implements ug.co.intelworld.contracts.IP2Gateway {

    private final String SANDBOX_IP = "https://gemini-api-hmactest.azurewebsites.net";
    private final String PRODUCTION_IP = "https://gemini-api-hmactest.azurewebsites.net";

    private static final String HTTP_POST = "POST";
    private static final String HTTP_GET = "GET";

    private final IP2Codes.Environment environment;
    private final String product_id;

    private final String api_username;

    private final String api_password;

    private final String merchant_account_id;

    private static int requestTimeOut = 5000;

    private static int connectTimeout = 6000;

    /**
     * IP2Gateway is the main class from which calls to remote IP2 service is
     * made.
     *
     * @param merchant_account_id Account id of the merchant on IP2 platform
     * @param api_username Username that has been assigned to you by the IP2
     * team for Hmac Auth
     * @param api_password Password that has been assigned to you by the IP2
     * team for Hmac Auth
     * @param environment Target environment for this request
     * {@link ug.co.intelworld.constants.IP2Codes.Environment}
     * @param product_id Product identification on IP2 platform
     */
    private IP2Gateway(
            String merchant_account_id,
            String api_username,
            String api_password,
            IP2Codes.Environment environment,
            String product_id
    ) {

        this.api_username = api_username;
        this.api_password = api_password;
        this.merchant_account_id = merchant_account_id;
        this.environment = environment;
        this.product_id = product_id;
    }

    /**
     * Helper method to create instances of IP2Gateway
     *
     * @param merchant_account_id Account id of the merchant on IP2 platform
     * @param api_username Username that has been assigned to you by the IP2
     * team for Hmac Auth
     * @param api_password Password that has been assigned to you by the IP2
     * team for Hmac Auth
     * @param enviroment Target environment for this request
     * {@link ug.co.intelworld.constants.IP2Codes.Environment}
     * @param product_id Product identification on IP2 platform
     * @return instance of IP2GateWay
     * @throws ug.co.intelworld.exceptions.IP2GatewayException if any of the
     * required parameters are null or empty
     */
    public static IP2Gateway getInstance(
            String merchant_account_id,
            String api_username,
            String api_password,
            IP2Codes.Environment enviroment,
            String product_id
    ) throws IP2GatewayException {

//        if(StringUtils.isEmpty(api_password)||StringUtils.isEmpty(api_username)||StringUtils.isEmpty(api_password)){
//            throw new IP2GatewayException("Parameters cannot be empty or null");
//        }        
        return new IP2Gateway(merchant_account_id, api_username, api_password, enviroment, product_id);
    }

    @Override
    public String topUpAirtime(IP2Codes.ServiceID service, String refId, String batchId, String subscriptionId, String amount, IP2Codes.CountryCodes countryCode, IP2Codes.CurrencyCodes currencyCode, String memo, IP2Codes.Channels channelId, String accountId) throws IP2GatewayException {
        return topUpAirtime(service, refId, batchId, subscriptionId, amount, countryCode, currencyCode, memo, channelId, accountId, false, "", false, "");
    }

    @Override
    public String topUpAirtime(IP2Codes.ServiceID service, String refId, String batchId, String subscriptionId, String amount, IP2Codes.CountryCodes countryCode, IP2Codes.CurrencyCodes currencyCode, String memo, IP2Codes.Channels channelId, String accountId, boolean sendRecipientMessage, String recipientMessage) throws IP2GatewayException {

        return topUpAirtime(service, refId, batchId, subscriptionId, amount, countryCode, currencyCode, memo,channelId, accountId, sendRecipientMessage, recipientMessage, false, "");
    }

    @Override
    public String topUpAirtime(IP2Codes.ServiceID service, String refId, String batchId, String subscriptionId, String amount, IP2Codes.CountryCodes countryCode, IP2Codes.CurrencyCodes currencyCode, String memo, IP2Codes.Channels channelId, String accountId, String confirmationMessage, boolean sendConfirmationMessage) throws IP2GatewayException {
        return topUpAirtime(service, refId, batchId, subscriptionId, amount, countryCode, currencyCode, memo, channelId, accountId, false, "", sendConfirmationMessage, confirmationMessage);
    }

    @Override
    public String topUpAirtime(IP2Codes.ServiceID service, String refId, String batchId, String subscriptionId, String amount, IP2Codes.CountryCodes countryCode, IP2Codes.CurrencyCodes currencyCode, String memo, IP2Codes.Channels channelId, String accountId, boolean sendRecipientMessage, String recipientMessage, boolean sendConfirmationMessage, String confirmationMessage) throws IP2GatewayException {
        //Generate topup request here
        final String serviceCode;
        switch(service){
            case SELL_AIRTIME:
                serviceCode = "AIRTIMESEND";
                break;
            case BUY_AIRTIME:
                serviceCode = "TRANSFERTO";
                break;
            default:
                throw new IP2GatewayException("Service is not supported for this product");
        }

        final String reference = generateServiceReference(service, amount, accountId, sendRecipientMessage, recipientMessage, sendConfirmationMessage, confirmationMessage);
        //    final String referenceHmac = generateAirtimeReferenceHmac(amount, accountId, sendRecipientMessage, recipientMessage, sendConfirmationMessage, confirmationMessage);

        final String hmacUri = buildHmacUri(serviceCode,refId, batchId, accountId, subscriptionId, reference, amount, IP2Codes.CountryCodes.getCountryCode(countryCode), currencyCode.name(), memo,"INTELIPAY", channelId.name());
        final String requestUri = buildUri(serviceCode,refId, batchId, accountId, subscriptionId, reference, amount, IP2Codes.CountryCodes.getCountryCode(countryCode), currencyCode.name(), memo, "INTELIPAY",channelId.name());

        return makeHttpRequest(requestUri, hmacUri, HTTP_POST, "");

    }

    @Override
    public String transactMobileMoney( IP2Codes.TransactionType transactionType, String refId, String batchId, String subscriptionId, String amount, IP2Codes.CountryCodes countryCode, IP2Codes.CurrencyCodes currencyCode, String memo, String accountId, IP2Codes.Providers providerId, IP2Codes.Channels channelId) throws IP2GatewayException {

        final String reference = generateProviderReference(amount, accountId, providerId);
        //Generate transactMobileMoney here
        final String hmacUri = buildHmacUri("SOMEVALUE",refId, batchId, accountId, subscriptionId, reference, amount, IP2Codes.CountryCodes.getCountryCode(countryCode), currencyCode.name(), memo,"INTELIPAY", channelId.name());
        final String requestUri = buildUri("SOMEVALUE",refId, batchId, accountId, subscriptionId, reference, amount, IP2Codes.CountryCodes.getCountryCode(countryCode), currencyCode.name(), memo, "INTELIPAY",channelId.name());

        return makeHttpRequest(requestUri, hmacUri, HTTP_POST, "");
        
    }

    /**
     *
     * Gets value of HTTP request timeout
     *
     * @return vale of HTTP request timeout
     */
    public static int getRequestTimeOut() {
        return requestTimeOut;
    }

    /**
     *
     * Sets value of HTTP request timeout
     *
     * @param requestTimeOut value of HTTP request timeout that is desired
     */
    public static void setRequestTimeOut(int requestTimeOut) {
        IP2Gateway.requestTimeOut = requestTimeOut;
    }

    /**
     *
     * Gets value of HTTP connection timeout
     *
     * @return value of HTTP connection timeout
     */
    public static int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     *
     * Sets the value of HTTP connection timeout
     *
     * @param connectTimeout sets value of HTTP connection timeout
     */
    public static void setConnectTimeout(int connectTimeout) {
        IP2Gateway.connectTimeout = connectTimeout;
    }

 

    private String generateProviderReference(          
            String amount,
            String accountId,
            IP2Codes.Providers providerId) throws IP2GatewayException{
        
        JSONObject momo_object = new JSONObject();
        momo_object.append("AccountId",accountId)
                .append("Amount", amount);
        switch(providerId){
            case AIRTELMONEY:
                JSONObject main_object1 = new JSONObject();
                main_object1.append("AIRTELMONEYUGPvdReference", momo_object);
                return main_object1.toString();
            case MTNMOBILEMONEY:
                JSONObject main_object2 = new JSONObject();
                main_object2.append("MTNMOMOUGPvdReference", momo_object);
                return main_object2.toString();
            case MICROPAY:
                JSONObject main_object3 = new JSONObject();
                main_object3.append("MICROPAYPvdReference", momo_object);
                return main_object3.toString();
            default:
                throw new IP2GatewayException("The given provider ID "+providerId.name()+" is not support by this product");
        }
        
    }
    
    private String generateServiceReference(
            IP2Codes.ServiceID service,
            String amount,
            String accountId,
            boolean sendRecipientMessage,
            String recipientMessage,
            boolean sendConfirmationMessage,
            String confirmationMessage) throws IP2GatewayException {

        JSONObject main_Object = new JSONObject();
        main_Object.append("MSISDN", accountId)
                .append("AccountId", accountId)
                .append("Amount", amount)
                .append("SendRecipientMessage", sendRecipientMessage)
                .append("RecipientMessage", recipientMessage)
                .append("SendConfirmation", sendConfirmationMessage)
                .append("ConfirmationText", confirmationMessage)
                .append("ProductId", product_id);

        switch (service) {
            case BUY_AIRTIME:

                JSONObject buy_object = new JSONObject();
                buy_object.append("AccountId", merchant_account_id)
                        .append("Amount", amount)
                        .append("TRANSFERTOSvcReference", main_Object);
                return buy_object.toString();
            case SELL_AIRTIME:

                JSONObject sell_object = new JSONObject();
                sell_object.append("AccountId", merchant_account_id)
                        .append("Amount", amount)
                        .append("TRANSFERTOSvcReference", main_Object);
                return sell_object.toString();

            default:
                throw new IP2GatewayException("Specified Service " + service.name() + " cannot be used for this product");
        }

//        return "{\"AccountId\":\"" + merchant_account_id + "\",\"Amount\":\"" + amount + "\",\"TRANSFERTOSvcReference\":{\"MSISDN\":\""
//                + accountId + "\",\"Amount\":\"" + amount + "\",\"SendRecipientMessage\":\"" + sendRecipientMessage + "\",\"RecipientMessage\":\""
//                + recipientMessage + "\",\"SendConfirmation\":\"" + sendConfirmationMessage + "\",\"ConfirmationText\":\"" + confirmationMessage + "\",\"ProductId\":\"" + product_id + "\"}}";
    }

    private String buildHmacUri(String service,String refId, String batchId, String accountId, String subscriptionId,
            String reference, String amount, String countryCode, String currencyCode, String memo, String providerId, String channelId) {
        StringBuilder builder = new StringBuilder();
        builder.append("/api/DebitPayments?refId=").append(refId)
                .append("&batchId=").append(batchId)
                .append("&accountId=").append(accountId)
                .append("&subscriptionId=").append(subscriptionId)
                .append("&reference=").append(reference)
                .append("&amount=").append(amount)
                .append("&countryCode=").append(countryCode)
                .append("&currencyCode=").append(currencyCode)
                .append("&memo=").append(memo)
                .append("&providerId=").append(providerId)
                .append("&serviceId=").append(service)
                .append("&channelId=").append(channelId);

        return builder.toString();

    }

    private String buildUri(String service,String refId, String batchId, String accountId, String subscriptionId,
            String reference, String amount, String countryCode, String currencyCode, String memo, String providerId, String channelId) throws IP2GatewayException {
        StringBuilder builder = new StringBuilder();
        builder.append("/api/DebitPayments?refId=").append(encodeUrlValue(refId))
                .append("&batchId=").append(encodeUrlValue(batchId))
                .append("&accountId=").append(encodeUrlValue(accountId))
                .append("&subscriptionId=").append(encodeUrlValue(subscriptionId))
                .append("&reference=").append(encodeUrlValue(reference))
                .append("&amount=").append(encodeUrlValue(amount))
                .append("&countryCode=").append(encodeUrlValue(countryCode))
                .append("&currencyCode=").append(encodeUrlValue(currencyCode))
                .append("&memo=").append(encodeUrlValue(memo))
                .append("&providerId=").append(encodeUrlValue(providerId))
                .append("&serviceId=").append(service)
                .append("&channelId=").append(encodeUrlValue(channelId));

        return builder.toString();

    }

    private String encodeUrlValue(String value) throws IP2GatewayException {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new IP2GatewayException("Failed to encode url parameters with UTF-8 encoding", ex);
        }

    }

    private Map<String, String> generateHmacHeaders(String requestMethod, String requestUri) throws IP2GatewayException, EmptyOrNullException {

        HmacAuthHeadersImpl hmac = new HmacAuthHeadersImpl(Constants.MEDIA_JSON, api_username, api_password,
                Constants.HMAC_SHA512, Constants.MD_SHA_512,
                Constants.MD5);

        try {
            return hmac.getHeadersAsMap(requestMethod, requestUri);
            //    return hmac.getHeadersAsMap(requestMethod, requestUri,jsonRequestUri);
        } catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new IP2GatewayException("Exception while generating Hmac Headers", ex);
        }

    }

    private String makeHttpRequest(String requestUri, String hmacUri, String httpMethod, String entity) throws IP2GatewayException {

        String url_;

        switch (environment) {
            case PRODUCTION:
                url_ = PRODUCTION_IP;
                break;
            case SANDBOX:
                url_ = SANDBOX_IP;
                break;
            default:
                url_ = SANDBOX_IP;
                break;
        }

        try {
            return NetworkHelpers.postToRemoteService(
                    url_.concat(requestUri),
                    generateHmacHeaders(httpMethod, hmacUri),
                    entity,
                    requestTimeOut,
                    connectTimeout);
        } catch (IP2GatewayException | EmptyOrNullException | IOException ex) {
            throw new IP2GatewayException("Exception occured while making network requests", ex);
        }

    }
}
