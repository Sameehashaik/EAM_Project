package com.project.order;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private RestTemplate restTemplate;

    public Order placeOrder(Order order) {
        // ensure orderDate is set if not provided
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }

        Order saved = orderRepo.save(order);

        // Build a MarketRequest DTO including all relevant fields
        var req = new HashMap<String, Object>();
        req.put("orderId", saved.getId());
        req.put("tickerSymbol", saved.getTickerSymbol());
        req.put("quantity", saved.getQuantity());
        req.put("orderAmt", saved.getOrderAmt());
        req.put("orderDate", saved.getOrderDate().toString());
        req.put("orderType", saved.getOrderType().name());
        // ...add others as needed...

        // call Market service via Eureka
        try {
            restTemplate.postForEntity(
              "http://project-market-service/market/place",
              req,
              Void.class
            );
        } catch (Exception ex) {
            // log & continue
            System.err.println("Could not notify Market service: " + ex.getMessage());
        }

        return saved;
    }
}
