/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip2.services;

/**
 *
 * @author herbert
 */
public class IP2Response {
    
    protected String correlationId;
    protected String requestId;
    protected String transactionId;
    protected int responseCode;
    protected String responseMessage;
    protected String helpLink;
    protected String createdOn;

    public String getCorrelationId() {
        return correlationId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getHelpLink() {
        return helpLink;
    }

    public String getCreatedOn() {
        return createdOn;
    }
    
    
}
