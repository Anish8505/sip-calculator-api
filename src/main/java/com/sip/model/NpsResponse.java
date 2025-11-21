package com.sip.model;

public class NpsResponse {
    private double monthlyContribution;
    private int years;
    private double rate;
    private double totalInvestment;
    private double maturityAmount;
    private double totalGain;

    public NpsResponse(double monthlyContribution, int years, double rate,
                       double totalInvestment, double maturityAmount, double totalGain) {
        this.monthlyContribution = monthlyContribution;
        this.years = years;
        this.rate = rate;
        this.totalInvestment = totalInvestment;
        this.maturityAmount = maturityAmount;
        this.totalGain = totalGain;
    }

    public double getMonthlyContribution() {
        return monthlyContribution;
    }

    public int getYears() {
        return years;
    }

    public double getRate() {
        return rate;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public double getMaturityAmount() {
        return maturityAmount;
    }

    public double getTotalGain() {
        return totalGain;
    }
}
