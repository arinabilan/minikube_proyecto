package com.project.simulate.packages.controllers;

import com.project.simulate.packages.services.SimulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulate")
public class SimulateController {

    @Autowired
    private SimulateService simulateService;

    @GetMapping("/{amount}/{interestRate}/{years}")
    public ResponseEntity<Double> simulateAmount(
            @PathVariable double amount,
            @PathVariable double interestRate,
            @PathVariable double years) {

        double result = simulateService.simulateAmount(amount, interestRate, years);
        return ResponseEntity.ok(result);
    }
}
