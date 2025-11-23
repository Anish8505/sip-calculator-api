package com.sip.controller;

import com.sip.model.TaxResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tax")
@CrossOrigin("*")
public class TaxController {

    @GetMapping
    public TaxResponse calculateTax(
            @RequestParam double income,
            @RequestParam(defaultValue = "new") String regime
    ) {
        if (income <= 0) {
            throw new IllegalArgumentException("Income must be greater than zero.");
        }

        String normalizedRegime = regime.equalsIgnoreCase("old") ? "old" : "new";

        double baseTax = normalizedRegime.equals("old")
                ? calculateOldRegimeTax(income)
                : calculateNewRegimeTax(income);

        // 4% cess
        double cess = baseTax * 0.04;
        double totalTax = baseTax + cess;
        double netIncome = income - totalTax;
        double effectiveRate = (totalTax / income) * 100.0;

        // round to 2 decimals
        totalTax = Math.round(totalTax * 100.0) / 100.0;
        netIncome = Math.round(netIncome * 100.0) / 100.0;
        effectiveRate = Math.round(effectiveRate * 100.0) / 100.0;
        income = Math.round(income * 100.0) / 100.0;

        return new TaxResponse(income, totalTax, netIncome, effectiveRate, normalizedRegime);
    }

    // Simple Indian slab demo - OLD regime
    private double calculateOldRegimeTax(double income) {
        double tax = 0.0;
        double remaining = income;

        // 0 – 2.5L : 0%
        if (remaining <= 250000) return 0.0;
        remaining -= 250000;

        // 2.5L – 5L : 5%
        if (remaining <= 250000) {
            tax += remaining * 0.05;
            return tax;
        } else {
            tax += 250000 * 0.05;
            remaining -= 250000;
        }

        // 5L – 10L : 20%
        if (remaining <= 500000) {
            tax += remaining * 0.20;
            return tax;
        } else {
            tax += 500000 * 0.20;
            remaining -= 500000;
        }

        // > 10L : 30%
        if (remaining > 0) {
            tax += remaining * 0.30;
        }

        return tax;
    }

    // Simple Indian slab demo - NEW regime
    private double calculateNewRegimeTax(double income) {
        double tax = 0.0;
        double remaining = income;

        // 0 – 3L : 0%
        if (remaining <= 300000) return 0.0;
        remaining -= 300000;

        // 3L – 7L : 5%
        if (remaining <= 400000) {
            tax += remaining * 0.05;
            return tax;
        } else {
            tax += 400000 * 0.05;
            remaining -= 400000;
        }

        // 7L – 10L : 10%
        if (remaining <= 300000) {
            tax += remaining * 0.10;
            return tax;
        } else {
            tax += 300000 * 0.10;
            remaining -= 300000;
        }

        // 10L – 12L : 15%
        if (remaining <= 200000) {
            tax += remaining * 0.15;
            return tax;
        } else {
            tax += 200000 * 0.15;
            remaining -= 200000;
        }

        // 12L – 15L : 20%
        if (remaining <= 300000) {
            tax += remaining * 0.20;
            return tax;
        } else {
            tax += 300000 * 0.20;
            remaining -= 300000;
        }

        // > 15L : 30%
        if (remaining > 0) {
            tax += remaining * 0.30;
        }

        return tax;
    }
}
