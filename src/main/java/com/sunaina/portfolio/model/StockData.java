package com.sunaina.portfolio.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StockData {
    private Set<Funds> funds;

    public Set<String> getStocksInFund(String fundName) throws RuntimeException{
        Set<String> stocks = new HashSet<>();
        for (Funds fund: funds) {
            if (fund.getName().equals(fundName))
                return fund.getStocks();
        }
        if (stocks.isEmpty())
            throw new RuntimeException("FUND NOT FOUND");
        return null;
    }

    public Set<String> addStockToFund(String fundName, String stockToBeAdded) throws RuntimeException{
        Set<String> stockList = getStocksInFund(fundName);
        stockList.add(stockToBeAdded);
        return stockList;
    }

    public void updateFund(String fundName, String stockToBeAdded) throws RuntimeException{
        for (Funds fund: funds) {
            if (fund.getName().equals(fundName))
                fund.setStocks(addStockToFund(fund.getName(), stockToBeAdded));
        }
    }
}
