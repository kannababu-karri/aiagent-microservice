package com.restful.aiagent.entities;

import java.util.Objects;

public class FraudTransaction {
	
    private String transactionDate;
    private Double amount;
    private String merchantID;
    private String transactionType;
    private String location;
    
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
    public String toString() {
        return "FraudTransaction{" +
                "transactionDate='" + transactionDate + '\'' +
                ", amount=" + amount +
                ", merchantID='" + merchantID + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, amount, merchantID, transactionType, location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FraudTransaction)) return false;
        FraudTransaction that = (FraudTransaction) o;
        return Objects.equals(transactionDate, that.transactionDate) &&
               Objects.equals(amount, that.amount) &&
               Objects.equals(merchantID, that.merchantID) &&
               Objects.equals(transactionType, that.transactionType) &&
               Objects.equals(location, that.location);
    }
}
