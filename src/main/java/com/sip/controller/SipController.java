package com.sip.controller;

import com.sip.model.SipResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sip")
@CrossOrigin("*")
public class SipController {

    @GetMapping
    public SipResponse calculateSip(
            @RequestParam double monthly,
            @RequestParam double rate,
            @RequestParam int years
    ) {
        int months = years * 12;
        double monthlyRate = rate / (12 * 100);

        double maturity = monthly * ((Math.pow(1 + monthlyRate, months) - 1) / monthlyRate) * (1 + monthlyRate);
        double invested = monthly * months;
        double profit = maturity - invested;

        // round to 2 decimal places
        maturity = Math.round(maturity * 100.0) / 100.0;
        invested = Math.round(invested * 100.0) / 100.0;
        profit   = Math.round(profit * 100.0) / 100.0;

        return new SipResponse(invested, maturity, profit);
    }
}
