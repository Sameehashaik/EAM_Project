package com.project.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    @NotBlank(message = "Ticker symbol must not be blank")
    private String tickerSymbol;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @DecimalMin(value = "0.01", message = "Order amount must be positive")
    private double orderAmt;

    private boolean addMoreAsRequired;

    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;

    private String feeId;

    private String attribute;

    @NotNull(message = "Order type is required")
    private OrderType orderType;

    // --- getters & setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTickerSymbol() { return tickerSymbol; }
    public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getOrderAmt() { return orderAmt; }
    public void setOrderAmt(double orderAmt) { this.orderAmt = orderAmt; }

    public boolean isAddMoreAsRequired() { return addMoreAsRequired; }
    public void setAddMoreAsRequired(boolean addMoreAsRequired) {
        this.addMoreAsRequired = addMoreAsRequired;
    }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getFeeId() { return feeId; }
    public void setFeeId(String feeId) { this.feeId = feeId; }

    public String getAttribute() { return attribute; }
    public void setAttribute(String attribute) { this.attribute = attribute; }

    public OrderType getOrderType() { return orderType; }
    public void setOrderType(OrderType orderType) { this.orderType = orderType; }

    public enum OrderType {
        BUY, SELL
    }
}
