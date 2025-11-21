package com.sip.model;

public class CagrResponse {
    private double initialAmount;
    private double finalAmount;
    private double years;
    private double cagrPercent;
    private double totalReturnPercent;
    private double totalGain;

    public CagrResponse(double initialAmount, double finalAmount, double years,
                        double cagrPercent, double totalReturnPercent, double totalGain) {
        this.initialAmount = initialAmount;
        this.finalAmount = finalAmount;
        this.years = years;
        this.cagrPercent = cagrPercent;
        this.totalReturnPercent = totalReturnPercent;
        this.totalGain = totalGain;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public double getYears() {
        return years;
    }

    public double getCagrPercent() {
        return cagrPercent;
    }

    public double getTotalReturnPercent() {
        return totalReturnPercent;
    }

    public double getTotalGain() {
        return totalGain;
    }
}
