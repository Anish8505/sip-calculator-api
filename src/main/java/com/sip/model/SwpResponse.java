package com.sip.model;

public class SwpResponse {
    private double startingCorpus;
    private double monthlyWithdrawal;
    private double rate;
    private int monthsLasted;
    private double yearsLasted;
    private double totalWithdrawn;
    private double totalInterestEarned;

    public SwpResponse(double startingCorpus,
                       double monthlyWithdrawal,
                       double rate,
                       int monthsLasted,
                       double yearsLasted,
                       double totalWithdrawn,
                       double totalInterestEarned) {
        this.startingCorpus = startingCorpus;
        this.monthlyWithdrawal = monthlyWithdrawal;
        this.rate = rate;
        this.monthsLasted = monthsLasted;
        this.yearsLasted = yearsLasted;
        this.totalWithdrawn = totalWithdrawn;
        this.totalInterestEarned = totalInterestEarned;
    }

    public double getStartingCorpus() {
        return startingCorpus;
    }

    public double getMonthlyWithdrawal() {
        return monthlyWithdrawal;
    }

    public double getRate() {
        return rate;
    }

    public int getMonthsLasted() {
        return monthsLasted;
    }

    public double getYearsLasted() {
        return yearsLasted;
    }

    public double getTotalWithdrawn() {
        return totalWithdrawn;
    }

    public double getTotalInterestEarned() {
        return totalInterestEarned;
    }
}
