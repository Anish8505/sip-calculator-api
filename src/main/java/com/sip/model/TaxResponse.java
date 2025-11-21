package com.sip.model;

public class TaxResponse {
    private double income;
    private double tax;
    private double netIncome;
    private double effectiveRate;
    private String regime;

    public TaxResponse(double income, double tax, double netIncome, double effectiveRate, String regime) {
        this.income = income;
        this.tax = tax;
        this.netIncome = netIncome;
        this.effectiveRate = effectiveRate;
        this.regime = regime;
    }

    public double getIncome() {
        return income;
    }

    public double getTax() {
        return tax;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public double getEffectiveRate() {
        return effectiveRate;
    }

    public String getRegime() {
        return regime;
    }
}
