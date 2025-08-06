package com.project.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private RestTemplate restTemplate;

    private final String MARKET_SERVICE_URL = "http://localhost:9092/market/place";

    public Order placeOrder(Order order) {
        // Save order locally
        Order savedOrder = orderRepo.save(order);

        // Build Market DTO
        Market marketRequest = new Market();
        marketRequest.setStockSymbol(order.getStockSymbol());
        marketRequest.setQuantity(order.getQuantity());
        marketRequest.setPrice(order.getPrice());

        // Call Market Service
        try {
            restTemplate.postForObject(
                    MARKET_SERVICE_URL,
                    marketRequest,
                    Market.class
            );
        } catch (Exception e) {
            System.out.println("Failed to contact Market Service: " + e.getMessage());
        }

        return savedOrder;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    // Get order by ID
    public Optional<Order> getOrderById(String id) {
        return orderRepo.findById(id);
    }

    // Get orders by username
    public List<Order> getOrdersByUsername(String username) {
        return orderRepo.findByUsername(username);
    }

    //  Delete order by ID
    public boolean deleteOrderById(String id) {
        if (orderRepo.existsById(id)) {
            orderRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
