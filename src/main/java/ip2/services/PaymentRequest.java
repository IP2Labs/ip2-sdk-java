/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.services;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author herbert
 */
public class PaymentRequest {

    private final String requestId;
    private final String batchId;
    private final BigDecimal amount;
    private final String channel;
    private final String payment;
    private final String memo;
    private final Map<String, Object> channelReference;
    private final Map<String, Object> paymentReference; //Message comes here
    private final Map<String, Object> clientReference;
    private final Map<String,Object> meta;

    public PaymentRequest(String requestId, String batchId, BigDecimal amount, String channel, String payment, String memo, Map<String, Object> channelReference, Map<String, Object> paymentReference, Map<String, Object> clientReference, Map<String, Object> meta) {
        this.requestId = requestId;
        this.batchId = batchId;
        this.amount = amount;
        this.channel = channel;
        this.payment = payment;
        this.memo = memo;
        this.channelReference = channelReference;
        this.paymentReference = paymentReference;
        this.clientReference = clientReference;
        this.meta = meta;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getBatchId() {
        return batchId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getChannel() {
        return channel;
    }

    public String getPayment() {
        return payment;
    }

    public String getMemo() {
        return memo;
    }

    public Map<String, Object> getChannelReference() {
        return channelReference;
    }

    public Map<String, Object> getPaymentReference() {
        return paymentReference;
    }

    public Map<String, Object> getClientReference() {
        return clientReference;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }
    
    
    
}
