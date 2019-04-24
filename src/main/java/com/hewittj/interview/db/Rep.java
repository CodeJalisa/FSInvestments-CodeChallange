package com.hewittj.interview.db;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jalisa on 4/23/19.
 */
public class Rep {

    String name;
    HashMap<String,Investor> investors;
    List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String rep) {
        this.name = rep;
    }

    public HashMap<String,Investor> getInvestors() {
        return investors;
    }

    public void setInvestors(HashMap<String,Investor> investors) {
        this.investors = investors;
    }


    public boolean contains(String name){
        return name==name;
    }


}
