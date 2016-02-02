/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.co.intelworld.contracts;

import ug.co.intelworld.constants.IP2Codes;
import ug.co.intelworld.exceptions.IP2GatewayException;

/**
 *
 * @author Naphlin Peter Akena 01/20/2016
 * @version 0.1
 * 
 */
public interface IP2Gateway {
    
     /**
     *
     * Returns a response from remote IP2 airtime service. Call this method when you want to buy airtime using the IP2 platform
     * This method should be called if you do not want IP2 to send any confirmatory messages to you and recipient of
     * the airtime.
     * 
     * 
     * @param refId Merchant's reference ID on the IP2 app system, this reference can be any alphanumerical of up to 32 characters (UUID length)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * 
     * @param service Type of service that requester wants to do
     * @param batchId Merchant's batch reference ID on the app system, this reference can be any alphanumerical of upto 32 characters (UUID lenth)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * @param subscriptionId Unique User id that identifies a merchant on the IP2 app system, this ID will be given to you when you complete the setup process
     * @param amount The amount in airtime that will be transferred to the receivers account ID
     * @param countryCode The country code for which the airtime will be deposited
     * @param currencyCode The currency code for the number to which the airtime will be sold
     * @param memo Any other message that should accompany this request
     * @param channelId The platform from which this request has been made, the list of channels can be looked up here {@link ug.co.intelworld.constants.IP2Codes.Channels}
     * @param accountId The beneficiary of the this transaction
     * @return a JSON string representation of the response from this transaction
     * @throws IP2GatewayException if any other Web or Runtime exception is raised in the course of execution
     * 
     * @see ug.co.intelworld.helpers.IP2GatewayUtils
     */
    
    public String topUpAirtime(
            IP2Codes.ServiceID service,
            String refId,
            String batchId,
            String subscriptionId,
            String amount,
            IP2Codes.CountryCodes countryCode,
            IP2Codes.CurrencyCodes currencyCode,
            String memo,
            
            IP2Codes.Channels channelId,//ussd etc 
            String accountId
            
    ) throws IP2GatewayException;   
    
    
    
      /**
     *
     * Returns a response from remote IP2 airtime service. Call this method when you want to buy airtime using the IP2 platform
     * This method should be called if you want to pass in any custom confirmatory the recipient of the airtime, otherwise,
     * there are simpler less verbose messages that you can use with the same method names but different parameter
     * 
     * 
     * @param refId Merchant's transaction reference ID on the IP2 app system, this reference can be any alphanumerical of up to 32 characters (UUID length)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * 
     * @param service Type of service that requester wants to do
     * @param batchId Merchant's batch reference ID on the app system, this reference can be any alphanumerical of upto 32 characters (UUID lenth)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * @param subscriptionId Unique User id that identifies a merchant on the IP2 app system, this ID will be given to you when you complete the setup process
     * @param amount The amount in airtime that will be transferred to the receivers account ID
     * @param countryCode The country code for which the airtime will be deposited
     * @param currencyCode The currency code for the number to which the airtime will be sold
     * @param memo Any other message that should accompany this request
     * @param channelId The platform from which this request has been made, the list of channels can be looked up here {@link ug.co.intelworld.constants.IP2Codes.Channels}
     * @param accountId The beneficiary of the this transaction
     * @param sendRecipientMessage Indicator to IP2 platform to send a message to the beneficiary of the transaction
     * @param recipientMessage Contents of the message to be sent to the recipient if sendRecipientMessage is true
     * @return a JSON string representation of the response from this transaction
     * @throws IP2GatewayException if any other Web or Runtime exception is raised in the course of execution
     * 
     * @see ug.co.intelworld.helpers.IP2GatewayUtils
     */
    
    public String topUpAirtime(
            IP2Codes.ServiceID service,
            String refId,
            String batchId,
            String subscriptionId,
            String amount,
            IP2Codes.CountryCodes countryCode,
            IP2Codes.CurrencyCodes currencyCode,
            String memo,
            IP2Codes.Channels channelId,//ussd etc 
            String accountId,
            boolean sendRecipientMessage,
            String recipientMessage
            
    ) throws IP2GatewayException;   
    
    
     /**
     *
     * Returns a response from remote IP2 airtime service. Call this method when you want to buy airtime using the IP2 platform
     * This method should be called if you want to pass in any custom confirmatory messages to yourself and to the recipient of 
     * the airtime, otherwise, there are simpler less verbose messages that you can use with the same method names but different parameter
     * 
     * 
     * @param refId Merchant's transaction reference ID on the IP2 app system, this reference can be any alphanumerical of up to 32 characters (UUID length)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * 
     * @param service Type of service that requester wants to do
     * @param batchId Merchant's batch reference ID on the app system, this reference can be any alphanumerical of upto 32 characters (UUID lenth)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * @param subscriptionId Unique User id that identifies a merchant on the IP2 app system, this ID will be given to you when you complete the setup process
     * @param amount The amount in airtime that will be transferred to the receivers account ID
     * @param countryCode The country code for which the airtime will be deposited
     * @param currencyCode The currency code for the number to which the airtime will be sold
     * @param memo Any other message that should accompany this request
     * @param channelId The platform from which this request has been made, the list of channels can be looked up here {@link ug.co.intelworld.constants.IP2Codes.Channels}
     * @param accountId The beneficiary of the this transaction
     * @param sendConfirmationMessage Indicator to IP2 platform to send a confirmation when the transaction is completed
     * @param confirmationMessage Contents of the message to to be sent as confirmation for this transaction
     * @return a JSON string representation of the response from this transaction
     * @throws IP2GatewayException if any other Web or Runtime exception is raised in the course of execution
     * 
     * @see ug.co.intelworld.helpers.IP2GatewayUtils
     */
    public String topUpAirtime(
            IP2Codes.ServiceID service,
            String refId,
            String batchId,
            String subscriptionId,
            String amount,
            IP2Codes.CountryCodes countryCode,
            IP2Codes.CurrencyCodes currencyCode,
            String memo,
            IP2Codes.Channels channelId,//ussd etc 
            String accountId,            
            String confirmationMessage,
            boolean sendConfirmationMessage
            
    ) throws IP2GatewayException;
    
    
    
    /**
     *
     * Returns a response from remote IP2 airtime service. Call this method when you want to buy airtime using the IP2 platform
     * This method should be called if you want to pass in any custom confirmatory messages to yourself and to the recipient of 
     * the airtime, otherwise, there are simpler less verbose messages that you can use with the same method names but different parameter
     * 
     * 
     * @param refId Merchant's transaction reference ID on the IP2 app system, this reference can be any alphanumerical of up to 32 characters (UUID length)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * 
     * @param service Type of service that requester wants to do
     * @param batchId Merchant's batch reference ID on the app system, this reference can be any alphanumerical of upto 32 characters (UUID lenth)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * @param subscriptionId Unique User id that identifies a merchant on the IP2 app system, this ID will be given to you when you complete the setup process
     * @param amount The amount in airtime that will be transferred to the receivers account ID
     * @param countryCode The country code for which the airtime will be deposited
     * @param currencyCode The currency code for the number to which the airtime will be sold
     * @param memo Any other message that should accompany this request
     * @param channelId The platform from which this request has been made, the list of channels can be looked up here {@link ug.co.intelworld.constants.IP2Codes.Channels}
     * @param accountId The beneficiary of the this transaction
     * @param sendRecipientMessage Indicator to IP2 platform to send a message to the beneficiary of the transaction
     * @param recipientMessage Contents of the message to be sent to the recipient if sendRecipientMessage is true
     * @param sendConfirmationMessage Indicator to IP2 platform to send a confirmation when the transaction is completed
     * @param confirmationMessage Contents of the message to to be sent as confirmation for this transaction
     * @return a JSON string representation of the response from this transaction
     * @throws IP2GatewayException if any other Web or Runtime exception is raised in the course of execution
     * 
     * @see ug.co.intelworld.helpers.IP2GatewayUtils
     */
    public String topUpAirtime(
            IP2Codes.ServiceID service,
            String refId,
            String batchId,
            String subscriptionId,
            String amount,
            IP2Codes.CountryCodes countryCode,
            IP2Codes.CurrencyCodes currencyCode,
            String memo,
            IP2Codes.Channels channelId,//ussd etc  
            String accountId,
            boolean sendRecipientMessage,
            String recipientMessage,
            boolean sendConfirmationMessage,
            String confirmationMessage
            
    ) throws IP2GatewayException;

    /**
     *
     * Returns a response from remote IP2 mobile money service. Call this method when you want to debit or credit mobile money accounts through IP2 platform
     * This method should be called if you want to pass in any custom confirmatory messages to yourself and to the recipient of 
     * the airtime, otherwise, there are simpler less verbose messages that you can use with the same method names but different parameter
     * 
     * 
     * @param transactionType the type of mobile money transaction to be performed, this can be credit or debit {@link ug.co.intelworld.constants.IP2Codes.TransactionType}
     * @param refId Merchant's transaction reference ID on the IP2 app system, this reference can be any alphanumerical of up to 32 characters (UUID length)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * 
     * @param batchId Merchant's batch reference ID on the app system, this reference can be any alphanumerical of upto 32 characters (UUID lenth)
     * There are helper methods that you can use to generate this reference in {@link ug.co.intelworld.helpers.IP2GatewayUtils}
     * @param subscriptionId Unique User id that identifies a merchant on the IP2 app system, this ID will be given to you when you complete the setup process
     * @param amount The amount in airtime that will be transferred to the receivers account ID
     * @param countryCode The country code for which the airtime will be deposited
     * @param currencyCode The currency code for the number to which the airtime will be sold
     * @param memo Any other message that should accompany this request
     * @param accountId The beneficiary of the this transaction
     * @param providerId The provider for which this request should be directed, the list of providers can be found at {@link ug.co.intelworld.constants.IP2Codes.Providers}
     * @param channelId The platform from which this request has been made, the list of channels can be looked up here {@link ug.co.intelworld.constants.IP2Codes.Channels}
     * @return JSON Object representation of a transaction
     * @throws ug.co.intelworld.exceptions.IP2GatewayException if any other Web or Runtime exception is raised in the course of execution
     */
    public String transactMobileMoney(
            IP2Codes.TransactionType transactionType,
            String refId,
            String batchId,
            String subscriptionId,
            String amount,
            IP2Codes.CountryCodes countryCode,
            IP2Codes.CurrencyCodes currencyCode,
            String memo,
            String accountId,
            IP2Codes.Providers providerId,//MTN momo
            IP2Codes.Channels channelId//ussd etc            
    ) throws IP2GatewayException;

   
}