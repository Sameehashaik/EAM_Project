package com.project.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketService {

    @Autowired
    private MarketRepository marketRepo;

    public Market saveMarket(Market market) {
        return marketRepo.save(market);
    }

    public List<Market> getAllMarkets() {
        return marketRepo.findAll();
    }

    public Optional<Market> getMarketById(String id) {
        return marketRepo.findById(id);
    }

    public Optional<Market> updateMarket(String id, Market updatedMarket) {
        return marketRepo.findById(id).map(existing -> {
            existing.setPrice(updatedMarket.getPrice());
            existing.setQuantity(updatedMarket.getQuantity());
            existing.setStockSymbol(updatedMarket.getStockSymbol());
            return marketRepo.save(existing);
        });
    }

    public boolean deleteMarket(String id) {
        if (marketRepo.existsById(id)) {
            marketRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
