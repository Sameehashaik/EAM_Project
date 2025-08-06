package com.project.fee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeService {
    @Autowired
    private FeeRepository repo;

    public Fee createFee(Fee fee) {
        return repo.save(fee);
    }

    public Fee getFeeByCode(String code) {
        return repo.findByFeeCode(code);
    }
}
