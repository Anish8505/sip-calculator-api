package com.sip.controller;

import com.sip.model.RetirementResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/retirement")
@CrossOrigin("*")
public class RetirementController {

    @GetMapping
    public RetirementResponse calculateRetirement(
            @RequestParam double expense,
            @RequestParam double inflation,
            @RequestParam int years
    ) {
        if (expense <= 0 || inflation < 0 || years <= 0) {
            throw new IllegalArgumentException("Inputs must be valid and greater than zero.");
        }

        double r = inflation / 100.0;

        // Monthly expense adjusted for inflation
        double expenseAtRetirement = expense * Math.pow(1 + r, years);

        // Yearly need
        double yearlyNeed = expenseAtRetirement * 12;

        // 4% rule (Safe Withdrawal Rate)
        double requiredCorpus = yearlyNeed / 0.04;

        expenseAtRetirement = Math.round(expenseAtRetirement);
        yearlyNeed = Math.round(yearlyNeed);
        requiredCorpus = Math.round(requiredCorpus);

        return new RetirementResponse(
                expense,
                inflation,
                years,
                expenseAtRetirement,
                yearlyNeed,
                requiredCorpus
        );
    }
}
