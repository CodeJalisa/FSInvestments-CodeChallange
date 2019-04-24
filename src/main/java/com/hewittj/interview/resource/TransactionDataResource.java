package com.hewittj.interview.resource;

import com.hewittj.interview.db.Rep;
import com.hewittj.interview.db.TransactionDataService;



import java.util.*;

/**
 * Created by Jalisa on 4/22/19.
 */
public class TransactionDataResource {

    private TransactionDataService service = new TransactionDataService();


    public void printTransactionSummaries(){

        HashMap<String, Rep> dataMap = service.getData();

        printSalesSummary(dataMap);
        printAssetsUnderManagement(dataMap);
        printInvestorProfit(dataMap);
        printBreakReport(dataMap);

    }

    private void printSalesSummary(HashMap<String, Rep> dataMap){
        HashMap<String,Double[]> salesMap = new HashMap<>();
        Date today = new Date();
        int quarter = (int)Math.ceil(today.getMonth()/3);

        System.out.println("\n----------Sales Rep Summary----------");
        dataMap.forEach((name,rep) -> {
            Double[] initialSales = new Double[]{0.0,0.0,0.0,0.0};
            salesMap.put(name,initialSales);

            rep.getTransactions().forEach(sale -> {
                Date date = sale.getDate();
                if(date.getYear() == today.getYear()){
                    Double yearToDateSales = salesMap.get(name)[0] + sale.getPrice();
                    salesMap.put(name,new Double[]{yearToDateSales,salesMap.get(name)[1],salesMap.get(name)[2],salesMap.get(name)[3]});
                    if((int)Math.ceil(date.getMonth()/3)==quarter) {
                        Double quarterToDateSales = salesMap.get(name)[2]+ sale.getPrice();
                        salesMap.put(name, new Double[]{salesMap.get(name)[0], salesMap.get(name)[1], quarterToDateSales, salesMap.get(name)[3]});
                        if(date.getMonth() == today.getMonth()){
                            Double monthToDateSales = salesMap.get(name)[1] + sale.getPrice();
                            salesMap.put(name,new Double[]{salesMap.get(name)[0], monthToDateSales,salesMap.get(name)[2],salesMap.get(name)[3]});
                        }
                    }
                }

                Double inceptionToDate = salesMap.get(name)[3]+ sale.getPrice();
                salesMap.put(name, new Double[]{salesMap.get(name)[0], salesMap.get(name)[1], salesMap.get(name)[2], inceptionToDate});
            });

        });

        salesMap.forEach((s,a) ->{
            System.out.println("\n"+
                    "Rep: "+ s + "\n\tYear to Date: " +a[0] + "\n\tMonth to Date: " +a[1] + "\n\tQuarter to Date: " +a[2]+ "\n\tInception to Date: " +a[3]);
        });
    }

    private void printAssetsUnderManagement(HashMap<String, Rep> dataMap){
        System.out.println("\n----------Asset Under Management Summary----------");

        dataMap.forEach((repName,rep) -> {
            System.out.println("\nRep: "+ repName);
            rep.getInvestors().forEach((investorName,investor) ->{
                System.out.println("\tNet Amount Held by Investor "+ investorName+  ": $"+ investor.getCashBalance() );
            } );
        });
    }

    private void printInvestorProfit(HashMap<String, Rep> dataMap){
        System.out.println("\n----------Investor Profit Summary----------");

        dataMap.forEach((repName,rep) -> {
            rep.getInvestors().forEach((investorName,investor) ->{
                System.out.println("\nInvestor: "+ investorName);
                investor.getFunds().forEach((fundName, fund) ->{
                    System.out.println("\tFund Name: " + fundName+ "\n\t\tNet Amount: " + fund.getNetPrice() );
                });
            });
        });
    }

    private void printBreakReport(HashMap<String, Rep> dataMap){
        System.out.println("\n----------Error Summary----------");

        dataMap.forEach((repName,rep) -> {
            rep.getInvestors().forEach((investorName,investor) ->{
                if(investor.getCashBalance() < 0 ){
                    System.out.println("Error found with Investor: "+ investorName
                            + "\n\tNegative cash balance: " + investor.getCashBalance());
                }
                if(investor.getShareBalance() < 0 ){
                    System.out.println("Error found with Investor: "+ investorName
                            + "\n\tNegative share balance: " + investor.getCashBalance());
                }

            } );
        });

    }

}
