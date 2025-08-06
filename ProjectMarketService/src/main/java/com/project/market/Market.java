package com.project.market;

import jakarta.validation.constraints.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "market")
@Data
public class Market {

	@Id
	private String id;

	@NotBlank(message = "Stock symbol is required")
	private String stockSymbol;

	@Min(value = 1, message = "Quantity must be at least 1")
	private int quantity;

	@DecimalMin(value = "0.01", message = "Price must be at least 0.01")
	private double price;

	// Getters and setters (Lombok already handles it if @Data is used)
}
