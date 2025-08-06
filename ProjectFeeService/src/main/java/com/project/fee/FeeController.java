package com.project.fee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fee")
public class FeeController {
    @Autowired
    private FeeService feeService;

    @PostMapping("/create")
    public ResponseEntity<?> createFee(@RequestBody Fee fee) {
        Fee created = feeService.createFee(fee);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/getByCode/{code}")
    public ResponseEntity<?> getByCode(@PathVariable String code) {
        Fee fee = feeService.getFeeByCode(code);
        if (fee != null) return ResponseEntity.ok(fee);
        else return ResponseEntity.notFound().build();
    }
}
