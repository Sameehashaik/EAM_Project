package com.project.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {
    
    @Autowired
    private MarketRepository marketRepo;

    @PostMapping("/place")
    public ResponseEntity<Market> placeStock(@RequestBody Market market) {
        return ResponseEntity.ok(marketRepo.save(market));
    }
}

