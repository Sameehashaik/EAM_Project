package com.project.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService service;

    @PostMapping("/place")
    public ResponseEntity<?> place(@Valid @RequestBody Market marketOrder, BindingResult br) {
        if (br.hasErrors()) {
            List<String> errors = br.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        Market saved = service.saveMarketOrder(marketOrder);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<?> confirm(@PathVariable String id, @RequestParam String status) {
        Market updated = service.confirmOrderTransaction(id, status);
        if (updated != null) return ResponseEntity.ok(updated);
        else return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/processJson/{orderId}")
    public ResponseEntity<?> processJson(@PathVariable String orderId) {
        try {
            Market created = service.processOrderViaJson(orderId);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Failed to process JSON: " + e.getMessage());
        }
    }
    
}
