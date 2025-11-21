package com.sip.controller;

import com.sip.model.SipResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rd")
@CrossOrigin("*")
public class RdController {

    /**
     * Recurring Deposit calculator (treated similar to SIP with monthly compounding).
     */
    @GetMapping
    public SipResponse calculateRd(
            @RequestParam double monthly,
            @RequestParam double rate,
            @RequestParam int years
    ) {
        if (monthly <= 0 || rate <= 0 || years <= 0) {
            throw new IllegalArgumentException("Monthly amount, rate and years must be greater than zero.");
        }

        int months = years * 12;
        double monthlyRate = rate / (12 * 100.0);

        double maturity = monthly * ((Math.pow(1 + monthlyRate, months) - 1) / monthlyRate) * (1 + monthlyRate);
        double invested = monthly * months;
        double profit = maturity - invested;

        maturity = Math.round(maturity * 100.0) / 100.0;
        invested = Math.round(invested * 100.0) / 100.0;
        profit   = Math.round(profit * 100.0) / 100.0;

        return new SipResponse(invested, maturity, profit);
    }
}
