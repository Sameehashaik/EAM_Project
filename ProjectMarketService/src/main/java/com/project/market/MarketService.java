package com.project.market;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketService {
    @Autowired
    private MarketRepository repo;
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Market saveMarketOrder(Market mo) {
        return repo.save(mo);
    }

    public Market confirmOrderTransaction(String id, String status) {
        var opt = repo.findById(id);
        if (opt.isPresent()) {
            Market mo = opt.get();
            mo.setConfirmationStatus(status);
            return repo.save(mo);
        }
        return null;
    }
    
    public Market processOrderViaJson(String orderId) throws Exception {
        String url = "http://localhost:9091/order/json/" + orderId;
        String orderJson = restTemplate.getForObject(url, String.class);

        Order orderDto = objectMapper.readValue(orderJson, Order.class);

        Market m = new Market();
        m.setOrderId(orderDto.getId());
        m.setBid(orderDto.getOrderAmt());
        m.setAsk(orderDto.getOrderAmt());
        m.setPrevious(orderDto.getOrderAmt());
        m.setLast(orderDto.getOrderAmt());
        m.setTypeOfExchange("NYSE");
        m.setConfirmationStatus("PENDING");

        return repo.save(m);
    }

}
