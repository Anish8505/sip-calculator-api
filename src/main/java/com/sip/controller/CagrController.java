package com.sip.controller;

import com.sip.model.CagrResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cagr")
@CrossOrigin("*")
public class CagrController {

    /**
     * CAGR calculator
     * initial = starting amount
     * fin = final amount
     * years = number of years
     */
    @GetMapping
    public CagrResponse calculateCagr(
            @RequestParam("initial") double initialAmount,
            @RequestParam("final") double finalAmount,
            @RequestParam("years") double years
    ) {
        if (initialAmount <= 0 || finalAmount <= 0 || years <= 0) {
            throw new IllegalArgumentException("Initial, final and years must be greater than zero.");
        }

        // CAGR = (Final / Initial)^(1/n) - 1
        double ratio = finalAmount / initialAmount;
        double cagr = Math.pow(ratio, 1.0 / years) - 1.0;

        double totalGain = finalAmount - initialAmount;
        double totalReturnPercent = (ratio - 1.0) * 100.0;
        double cagrPercent = cagr * 100.0;

        // rounding
        initialAmount = Math.round(initialAmount * 100.0) / 100.0;
        finalAmount = Math.round(finalAmount * 100.0) / 100.0;
        years = Math.round(years * 100.0) / 100.0;

        cagrPercent = Math.round(cagrPercent * 100.0) / 100.0;
        totalReturnPercent = Math.round(totalReturnPercent * 100.0) / 100.0;
        totalGain = Math.round(totalGain * 100.0) / 100.0;

        return new CagrResponse(initialAmount, finalAmount, years, cagrPercent, totalReturnPercent, totalGain);
    }
}
