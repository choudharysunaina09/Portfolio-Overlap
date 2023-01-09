package com.sunaina.portfolio.service;

import com.sunaina.portfolio.InputCommand;
import com.sunaina.portfolio.model.StockData;

import java.text.DecimalFormat;
import java.util.*;

public class FundManager {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private StockData stockData;
    private List<String> userPortfolios;
    public FundManager(StockData stockData) {
        this.stockData = stockData;
        this.userPortfolios = new ArrayList<>();
    }

    public String executeInput(List<String[]> commandLineInput) {
        String output = "";
        for (String[] commandLine: commandLineInput
             ) {
            String command = commandLine[0];
            if(command.equals(InputCommand.CURRENT_PORTFOLIO.name())){
                getUserCurrentFunds(commandLine);
            } else if(command.equals(InputCommand.CALCULATE_OVERLAP.name())){
                output += calculateOverlap(commandLine[1], userPortfolios);
            } else if (command.equals(InputCommand.ADD_STOCK.name())){
                addStocks(commandLine[1], commandLine[2]);
            } else if (command.equals(InputCommand.OVERLAP_PERCENTAGE.name())){
                output += overlapPercentage(commandLine[1], userPortfolios);
            }else {
                output += "INVALID COMMAND" + '\n';
            }
        }
        return output;
    }

    public void getUserCurrentFunds(String[] userFunds){
        for (int i=1; i<userFunds.length;i++)
            userPortfolios.add(userFunds[i]);
    }
    public String calculateOverlap(String fundToCompare, List<String> listToCompareWith) {
        String output = "";
        try {
            Set<String> stocksInFundToCompare = stockData.getStocksInFund(fundToCompare);
            for (String fundToCompareWith : listToCompareWith) {
                try {
                    Set<String> stocksInFundToCompareWith = stockData.getStocksInFund(fundToCompareWith);
                    String overlapPercentage = getOverlapPercentage(stocksInFundToCompare, stocksInFundToCompareWith);
                    output += fundToCompare + " " + fundToCompareWith + " " + overlapPercentage + '%' + '\n';
                } catch (Exception ex) {
                    output += ex.getMessage() + '\n';
                }
            }
        } catch (Exception ex){
            output += ex.getMessage() + '\n';
        }
        return output;
    }

    public void addStocks(String fundName, String stockToBeAdded) {
        if(!stockToBeAdded.isEmpty())
            stockData.updateFund(fundName, stockToBeAdded);
    }

    public String overlapPercentage(String fundToCompare, List<String> listOfFundsToCompareWith){
        return calculateOverlap(fundToCompare, listOfFundsToCompareWith);
    }

    private String getOverlapPercentage(Set<String> stocksInFundToCompare, Set<String> stocksInFundToCompareWith) {
        double commonStockCount = getCommonStockCountBetweenTwoFunds(stocksInFundToCompare, stocksInFundToCompareWith);
        double totalStockCount = stocksInFundToCompare.size() + stocksInFundToCompareWith.size();
        return df.format(((2 * commonStockCount) / totalStockCount) * 100);
    }
    private int getCommonStockCountBetweenTwoFunds(Set<String> stocksInFundA, Set<String> stocksInFundB) {
        Set<String> setTemp = new HashSet<>(stocksInFundA);
        setTemp.retainAll(stocksInFundB);
        return setTemp.size();
    }
}
