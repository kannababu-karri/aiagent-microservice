package com.restful.aiagent.entities;

import java.util.List;
import java.util.Objects;

public class FraudDetectionResponseDto {
	 private Double fraud_probability;
	 private Boolean is_fraud;
	 private List<String> explanation;
	 
	 public Double getFraud_probability() {
		 return fraud_probability;
	 }
	 public void setFraud_probability(Double fraud_probability) {
		 this.fraud_probability = fraud_probability;
	 }
	 public Boolean getIs_fraud() {
		 return is_fraud;
	 }
	 public void setIs_fraud(Boolean is_fraud) {
		 this.is_fraud = is_fraud;
	 }
	 public List<String> getExplanation() {
		 return explanation;
	 }
	 public void setExplanation(List<String> explanation) {
		 this.explanation = explanation;
	 }
	 
	//toString()
    @Override
    public String toString() {
        return "FraudDetectionResponseDto{" +
                "fraud_probability=" + fraud_probability +
                ", is_fraud=" + is_fraud +
                ", explanation=" + explanation +
                '}';
    }

    //equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogsDto)) return false;
        FraudDetectionResponseDto that = (FraudDetectionResponseDto) o;
        return Objects.equals(fraud_probability, that.fraud_probability) &&
               Objects.equals(is_fraud, that.is_fraud) &&
               Objects.equals(explanation, that.explanation);
    }

    //hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(fraud_probability, is_fraud, explanation);
    }
}
