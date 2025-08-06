package com.project.market;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document(collection = "market")
public class Market {
    @Id
    private String id;

    @NotNull(message = "Order ID is required")
    private String orderId;

	private String transactionId;

    private String feeId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Bid must be positive")
    private double bid;

    @DecimalMin(value = "0.0", inclusive = false, message = "Ask must be positive")
    private double ask;

    @DecimalMin(value = "0.0", inclusive = false, message = "Previous must be positive")
    private double previous;

    @DecimalMin(value = "0.0", inclusive = false, message = "Last must be positive")
    private double last;

    @NotBlank(message = "Exchange type is required")
    private String typeOfExchange; // NYSE, TSE, Montreal

    private String confirmationStatus;

    // Getters and Setters


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public double getPrevious() {
		return previous;
	}

	public void setPrevious(double previous) {
		this.previous = previous;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public String getTypeOfExchange() {
		return typeOfExchange;
	}

	public void setTypeOfExchange(String typeOfExchange) {
		this.typeOfExchange = typeOfExchange;
	}

	public String getConfirmationStatus() {
		return confirmationStatus;
	}

	public void setConfirmationStatus(String confirmationStatus) {
		this.confirmationStatus = confirmationStatus;
	}
}
