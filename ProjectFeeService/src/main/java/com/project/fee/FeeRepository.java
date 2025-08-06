package com.project.fee;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeeRepository extends MongoRepository<Fee, String> {
    Fee findByFeeCode(String feeCode);
}
