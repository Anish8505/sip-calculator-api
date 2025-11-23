package com.sip.controller;

import com.sip.model.PpfResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ppf")
@CrossOrigin("*")
public class PpfController {

    @GetMapping
    public PpfResponse calculatePpf(
            @RequestParam double yearly,
            @RequestParam double rate,
            @RequestParam int years
    ) {
        if (yearly <= 0 || rate <= 0 || years <= 0) {
            throw new IllegalArgumentException("Inputs must be greater than zero.");
        }

        double r = rate / 100.0;
        double maturity = yearly * ((Math.pow(1 + r, years + 1) - (1 + r)) / r);
        double invested = yearly * years;
        double interest = maturity - invested;

        maturity = Math.round(maturity);
        invested = Math.round(invested);
        interest = Math.round(interest);

        return new PpfResponse(yearly, years, maturity, invested, interest);
    }
}
