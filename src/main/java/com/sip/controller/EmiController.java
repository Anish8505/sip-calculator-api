package com.sip.controller;

import com.sip.model.SipResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emi")
@CrossOrigin("*")
public class EmiController {

    /**
     * EMI calculator.
     * principal = loan amount
     * rate = annual interest rate in %
     * years = tenure in years
     *
     * Returns:
     * - investedAmount  -> principal (loan amount)
     * - maturityAmount  -> total amount paid (EMI * months)
     * - profit          -> total interest paid
     */
    @GetMapping
    public SipResponse calculateEmi(
            @RequestParam double principal,
            @RequestParam double rate,
            @RequestParam int years
    ) {
        if (principal <= 0 || rate <= 0 || years <= 0) {
            throw new IllegalArgumentException("Principal, rate and years must be greater than zero.");
        }

        int months = years * 12;
        double monthlyRate = rate / (12 * 100.0);

        // EMI formula: E = P * r * (1+r)^n / ((1+r)^n - 1)
        double factor = Math.pow(1 + monthlyRate, months);
        double emi = principal * monthlyRate * factor / (factor - 1);

        double totalPaid = emi * months;
        double interest = totalPaid - principal;

        // Round everything to 2 decimals
        emi       = Math.round(emi * 100.0) / 100.0;
        totalPaid = Math.round(totalPaid * 100.0) / 100.0;
        interest  = Math.round(interest * 100.0) / 100.0;
        principal = Math.round(principal * 100.0) / 100.0;

        // Using SipResponse as a generic response:
        // investedAmount = principal
        // maturityAmount = totalPaid
        // profit         = interest
        return new SipResponse(principal, totalPaid, interest);
    }
}

