package com.project.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    private final String MARKET_SERVICE_URL = "http://localhost:9092/market/place";

    // 1. Place order and call Market service
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody Order order) {
        Order savedOrder = orderService.placeOrder(order);

        // Send the order as JSON to Market Service
        Market marketRequest = new Market();
        marketRequest.setStockSymbol(order.getStockSymbol());
        marketRequest.setPrice(order.getPrice());
        marketRequest.setQuantity(order.getQuantity());

        restTemplate.postForObject(MARKET_SERVICE_URL, marketRequest, Market.class);

        return ResponseEntity.ok(savedOrder);
    }

    // 2. Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // 3. Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Get orders by username
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Order>> getOrdersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(orderService.getOrdersByUsername(username));
    }

    // 5. Delete order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        if (orderService.deleteOrderById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
