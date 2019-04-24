package com.hewittj.interview.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jalisa on 4/22/19.
 */
public class TransactionDataService {

    public HashMap<String,Rep> getData() {
        HashMap<String,Rep> dataMap = new HashMap<>();
        String file = "src/main/resources/Data.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] strArry = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"); //TODO

                //Storing strings into a variable and transforming it to appropriate type
                Date date = new SimpleDateFormat("M/dd/yy").parse(strArry[0]);
                String type = strArry[1];
                Double share = Double.parseDouble(strArry[2]);
                Double price = Double.parseDouble(strArry[3].replace("$",""));
                String fund = strArry[4];
                String investorName = strArry[5];
                String repName = strArry[6];

                //Setting transaction object
                Transaction transaction = new Transaction(date,type,share,price,investorName);

                //Checking to see if map already contains this sales rep
                if(dataMap.containsKey(repName)){
                    //adding new transaction to sales rep info
                    List<Transaction> transactions = dataMap.get(repName).getTransactions();
                    transactions.add(transaction);
                    if(dataMap.get(repName).getInvestors().containsKey(investorName)){//checking if sales rep has already worked with this investor
                        if(dataMap.get(repName).getInvestors().get(investorName).getFunds().containsKey(fund)){//if so, checking if Investor already has this fund type
                            //if so, adding new fund info to Investors info
                            Double netPrice = dataMap.get(repName).getInvestors().get(investorName).getFunds().get(fund).getNetPrice() + price;
                            Fund currentFund =dataMap.get(repName).getInvestors().get(investorName).getFunds().get(fund);
                            currentFund.setNetPrice(netPrice);
                            Investor currentInvestor = dataMap.get(repName).getInvestors().get(investorName);
                            currentInvestor.setCashBalance(currentInvestor.getCashBalance()+price);
                            currentInvestor.setShareBalance(currentInvestor.getShareBalance()+share);
                            dataMap.get(repName).getInvestors().get(investorName).getFunds().put(fund,currentFund);
                        }else{//if Investor does not have this fund, adding new Fund type to Investor info
                            Fund newFund = new Fund();
                            newFund.setFund(fund);
                            newFund.setNetPrice(price);
                            Investor currentInvestor = dataMap.get(repName).getInvestors().get(investorName);
                            currentInvestor.setCashBalance(currentInvestor.getCashBalance()+price);
                            currentInvestor.setShareBalance(currentInvestor.getShareBalance()+share);
                            dataMap.get(repName).getInvestors().get(investorName).getFunds().put(fund,newFund);
                        }
                    }else{//if sales rep has not worked with this Investor, adding new Investor with fund data to sales rep info
                        Investor newInvestor = new Investor();
                        Fund newFund = new Fund();
                        newFund.setFund(fund);
                        newFund.setNetPrice(price);
                        newInvestor.setInvestorName(investorName);
                        newInvestor.setCashBalance(price);
                        newInvestor.setShareBalance(share);
                        newInvestor.setFunds(new HashMap<String, Fund>(){{put(newFund.getFund(),newFund);}});
                        dataMap.get(repName).getInvestors().put(investorName,newInvestor);
                    }

                }else{//If we do not have this sales rep already, add new Sales rep with transaction and Investor information
                    Rep newRep = new Rep();
                    newRep.setName(investorName);
                    newRep.setTransactions(new ArrayList<Transaction>(){{add(transaction);}});
                    Investor newInvestor = new Investor();
                    Fund newFund = new Fund();
                    newFund.setFund(fund);
                    newFund.setNetPrice(price);
                    newInvestor.setInvestorName(investorName);
                    newInvestor.setCashBalance(price);
                    newInvestor.setShareBalance(share);
                    newInvestor.setFunds(new HashMap<String, Fund>(){{put(newFund.getFund(),newFund);}});
                    newRep.setInvestors(new HashMap<String, Investor>(){{put(investorName,newInvestor);}});
                    dataMap.put(repName,newRep);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
        }
        return dataMap;
    }
}
