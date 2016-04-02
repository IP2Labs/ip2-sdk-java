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
public class CommerceRequest {
    
    private final String requestId;
    private final String batchId;
    private final BigDecimal amount;
    private final String channel;
    private final String product;
    private final String memo;
    private final Map<String, Object> channelReference;
    private final Map<String, Object> productReference; //Message comes here
    private final Map<String, Object> clientReference;
    private final Map<String,Object> meta;

    public CommerceRequest(String requestId, String batchId, BigDecimal amount, String channel, String product, String memo, Map<String, Object> channelReference, Map<String, Object> productReference, Map<String, Object> clientReference, Map<String, Object> meta) {
        this.requestId = requestId;
        this.batchId = batchId;
        this.amount = amount;
        this.channel = channel;
        this.product = product;
        this.memo = memo;
        this.channelReference = channelReference;
        this.productReference = productReference;
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

    public String getProduct() {
        return product;
    }

    public String getMemo() {
        return memo;
    }

    public Map<String, Object> getChannelReference() {
        return channelReference;
    }

    public Map<String, Object> getProductReference() {
        return productReference;
    }

    public Map<String, Object> getClientReference() {
        return clientReference;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }
    
    
}
