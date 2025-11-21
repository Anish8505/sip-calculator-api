package com.sip.model;

public class PpfResponse {
    private double yearlyInvestment;
    private int tenureYears;
    private double maturityAmount;
    private double totalInvestment;
    private double totalInterest;

    public PpfResponse(double yearlyInvestment, int tenureYears,
                       double maturityAmount, double totalInvestment, double totalInterest) {
        this.yearlyInvestment = yearlyInvestment;
        this.tenureYears = tenureYears;
        this.maturityAmount = maturityAmount;
        this.totalInvestment = totalInvestment;
        this.totalInterest = totalInterest;
    }

    public double getYearlyInvestment() { return yearlyInvestment; }
    public int getTenureYears() { return tenureYears; }
    public double getMaturityAmount() { return maturityAmount; }
    public double getTotalInvestment() { return totalInvestment; }
    public double getTotalInterest() { return totalInterest; }
}
