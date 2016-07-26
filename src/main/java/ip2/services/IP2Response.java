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
   
	private String referenceId;
	private String transactionId;
	private String correlationId;
	private String data;
	private int httpStatus;
	
	public void setHttpStatus(int httpStatus)
	{
		this.httpStatus = httpStatus;
	}
	
	public int getHttpStatus()
	{
		return this.httpStatus;
	}
	
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
    
    
}
