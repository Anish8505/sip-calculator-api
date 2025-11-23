package com.sip.controller;

import com.sip.model.SwpResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/swp")
@CrossOrigin("*")
public class SwpController {

    /**
     * Simple SWP calculator.
     * corpus  = starting corpus (₹)
     * rate    = expected annual return (%)
     * monthly = fixed monthly withdrawal (₹)
     *
     * We simulate month-by-month:
     * 1. Add interest on current corpus
     * 2. Subtract monthly withdrawal
     */
    @GetMapping
    public SwpResponse calculateSwp(
            @RequestParam double corpus,
            @RequestParam double rate,
            @RequestParam double monthly
    ) {
        if (corpus <= 0 || rate < 0 || monthly <= 0) {
            throw new IllegalArgumentException("Inputs must be valid and greater than zero.");
        }

        double startingCorpus = corpus;
        double rMonthly = rate / (12 * 100.0);

        int months = 0;
        double totalWithdrawn = 0.0;
        double totalInterest = 0.0;

        // Hard cap of 100 years of simulation (1200 months)
        int maxMonths = 1200;

        while (corpus > 0 && months < maxMonths) {
            // Interest for the month
            double interest = corpus * rMonthly;
            corpus += interest;
            totalInterest += interest;

            // If remaining corpus is less than monthly withdrawal, withdraw whatever is left
            if (corpus <= monthly) {
                totalWithdrawn += corpus;
                corpus = 0;
                months++;
                break;
            } else {
                corpus -= monthly;
                totalWithdrawn += monthly;
            }

            months++;
        }

        double yearsLasted = months / 12.0;

        // Round some values
        totalWithdrawn = Math.round(totalWithdrawn);
        totalInterest = Math.round(totalInterest);
        startingCorpus = Math.round(startingCorpus);

        return new SwpResponse(
                startingCorpus,
                monthly,
                rate,
                months,
                Math.round(yearsLasted * 100.0) / 100.0,
                totalWithdrawn,
                totalInterest
        );
    }
}
