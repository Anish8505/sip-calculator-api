package com.sip.controller;

import com.sip.model.SipResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fd")
@CrossOrigin("*")
public class FdController {

    /**
     * Simple Fixed Deposit calculator.
     * Assumes quarterly compounding by default (n = 4).
     */
    @GetMapping
    public SipResponse calculateFd(
            @RequestParam double principal,
            @RequestParam double rate,
            @RequestParam int years
    ) {
        if (principal <= 0 || rate <= 0 || years <= 0) {
            throw new IllegalArgumentException("Principal, rate and years must be greater than zero.");
        }

        // Quarterly compounding: n = 4
        int n = 4;
        double r = rate / 100.0;
        double maturity = principal * Math.pow(1 + r / n, n * years);
        double invested = principal;
        double profit = maturity - invested;

        // round to 2 decimals
        maturity = Math.round(maturity * 100.0) / 100.0;
        invested = Math.round(invested * 100.0) / 100.0;
        profit   = Math.round(profit * 100.0) / 100.0;

        return new SipResponse(invested, maturity, profit);
    }
}
