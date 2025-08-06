package com.project.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepo;
    @Autowired private ObjectMapper objectMapper;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(
        @Valid @RequestBody Order order,
        BindingResult br
    ) {
        if (br.hasErrors()) {
            var errors = br.getFieldErrors()
                          .stream()
                          .map(DefaultMessageSourceResolvable::getDefaultMessage)
                          .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        Order saved = orderService.placeOrder(order);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/json/{id}")
    public ResponseEntity<String> getOrderAsJson(@PathVariable String id) {
        return orderRepo.findById(id)
            .map(o -> {
                try {
                    return ResponseEntity.ok(objectMapper.writeValueAsString(o));
                } catch (JsonProcessingException e) {
                    return ResponseEntity
                      .status(HttpStatus.INTERNAL_SERVER_ERROR)
                      .body("{\"error\":\"Could not serialize order\"}");
                }
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
