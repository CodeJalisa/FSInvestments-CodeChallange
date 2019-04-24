package com.hewittj.interview.db;

import java.util.Date;

/**
 * Created by Jalisa on 4/23/19.
 */
public class Transaction {

    Date date;
    Double price;
    Double share;
    String type;
    String investorName;


    public Transaction(Date date, String type, Double share, Double price, String investorName){
        this.date=date;
        this.type=type;
        this.share=share;
        this.price=price;
        this.investorName = investorName;


    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInvestor() {
        return investorName;
    }

    public void setInvestor(String investor) {
        this.investorName = investor;
    }
}
