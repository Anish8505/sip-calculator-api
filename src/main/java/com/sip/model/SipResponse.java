package com.sip.model;

public class SipResponse {
    private double investedAmount;
    private double maturityAmount;
    private double profit;

    public SipResponse(double investedAmount, double maturityAmount, double profit) {
        this.investedAmount = investedAmount;
        this.maturityAmount = maturityAmount;
        this.profit = profit;
    }

    public double getInvestedAmount() { return investedAmount; }
    public double getMaturityAmount() { return maturityAmount; }
    public double getProfit() { return profit; }
}

