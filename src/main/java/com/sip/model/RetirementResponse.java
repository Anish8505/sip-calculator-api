package com.sip.model;

public class RetirementResponse {
    private double monthlyExpenseToday;
    private double inflationRate;
    private int years;
    private double expenseAtRetirement;
    private double yearlyExpenseAtRetirement;
    private double requiredCorpus;

    public RetirementResponse(double monthlyExpenseToday, double inflationRate, int years,
                              double expenseAtRetirement, double yearlyExpenseAtRetirement,
                              double requiredCorpus) {
        this.monthlyExpenseToday = monthlyExpenseToday;
        this.inflationRate = inflationRate;
        this.years = years;
        this.expenseAtRetirement = expenseAtRetirement;
        this.yearlyExpenseAtRetirement = yearlyExpenseAtRetirement;
        this.requiredCorpus = requiredCorpus;
    }

    public double getMonthlyExpenseToday() { return monthlyExpenseToday; }
    public double getInflationRate() { return inflationRate; }
    public int getYears() { return years; }
    public double getExpenseAtRetirement() { return expenseAtRetirement; }
    public double getYearlyExpenseAtRetirement() { return yearlyExpenseAtRetirement; }
    public double getRequiredCorpus() { return requiredCorpus; }
}
