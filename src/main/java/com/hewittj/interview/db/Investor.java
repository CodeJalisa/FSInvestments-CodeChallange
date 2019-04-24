package com.hewittj.interview.db;

import java.util.HashMap;

/**
 * Created by Jalisa on 4/23/19.
 */
public class Investor {

    String investorName;
    Double cashBalance;
    Double shareBalance;
    HashMap<String, Fund> funds;

    public Double getShareBalance() {
        return shareBalance;
    }

    public void setShareBalance(Double shareBalance) {
        this.shareBalance = shareBalance;
    }

    public Double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(Double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public HashMap<String, Fund> getFunds() {
        return funds;
    }

    public void setFunds(HashMap<String, Fund> funds) {
        this.funds = funds;
    }
}
