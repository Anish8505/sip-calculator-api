package com.sip.controller;

import com.sip.model.SipResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lumpsum")
@CrossOrigin("*")
public class LumpsumController {

    @GetMapping
    public SipResponse calculateLumpsum(
            @RequestParam double principal,
            @RequestParam double rate,
            @RequestParam int years
    ) {
        // years → time in years
        // rate → annual % return
        double annualRate = rate / 100.0;

        double maturity = principal * Math.pow(1 + annualRate, years);
        double invested = principal;
        double profit = maturity - invested;

        // round to 2 decimals
        maturity = Math.round(maturity * 100.0) / 100.0;
        invested = Math.round(invested * 100.0) / 100.0;
        profit   = Math.round(profit * 100.0) / 100.0;

        return new SipResponse(invested, maturity, profit);
    }
}
