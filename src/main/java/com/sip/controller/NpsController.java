package com.sip.controller;

import com.sip.model.NpsResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nps")
@CrossOrigin("*")
public class NpsController {

    /**
     * NPS calculator (simple version).
     * monthly = monthly contribution
     * rate    = expected return % per year
     * years   = years until retirement
     *
     * Treat as monthly SIP:
     * FV = P * [((1 + i)^n - 1) / i] * (1 + i)
     * where i = rate / 12
     */
    @GetMapping
    public NpsResponse calculateNps(
            @RequestParam double monthly,
            @RequestParam double rate,
            @RequestParam int years
    ) {
        if (monthly <= 0 || rate <= 0 || years <= 0) {
            throw new IllegalArgumentException("Inputs must be greater than zero.");
        }

        int months = years * 12;
        double monthlyRate = rate / (12 * 100.0);

        double maturity = monthly *
                ((Math.pow(1 + monthlyRate, months) - 1) / monthlyRate) *
                (1 + monthlyRate);

        double invested = monthly * months;
        double gain = maturity - invested;

        maturity = Math.round(maturity);
        invested = Math.round(invested);
        gain = Math.round(gain);

        return new NpsResponse(monthly, years, rate, invested, maturity, gain);
    }
}
