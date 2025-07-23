package com.project.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private RestTemplate restTemplate;

    public Order placeOrder(Order order) {
        // Save order locally
        Order savedOrder = orderRepo.save(order);

        // Build market request (optional fields can be copied from order)
        Map<String, Object> marketRequest = new HashMap<>();
        marketRequest.put("stockSymbol", order.getStockSymbol());
        marketRequest.put("quantity", order.getQuantity());
        marketRequest.put("price", order.getPrice());

        // Call Market Service
        try {
            restTemplate.postForEntity(
                "http://localhost:9092/market/place",
                marketRequest,
                String.class
            );
        } catch (Exception e) {
            System.out.println("Failed to contact market service: " + e.getMessage());
        }

        return savedOrder;
    }
}

