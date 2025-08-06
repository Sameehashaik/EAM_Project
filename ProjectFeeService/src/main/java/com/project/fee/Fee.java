package com.project.fee;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fees")
public class Fee {
    @Id
    private String id;

    private String feeCode;
    private double amount;
    private String description;

    // Constructors
    public Fee() {}
    public Fee(String feeCode, double amount, String description) {
        this.feeCode = feeCode;
        this.amount = amount;
        this.description = description;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFeeCode() { return feeCode; }
    public void setFeeCode(String feeCode) { this.feeCode = feeCode; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
