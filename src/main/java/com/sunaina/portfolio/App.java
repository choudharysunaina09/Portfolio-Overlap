package com.sunaina.portfolio;

import com.sunaina.portfolio.model.StockData;
import com.sunaina.portfolio.service.FundManager;
import com.sunaina.portfolio.util.FileParser;

import java.util.HashMap;
import java.util.List;

/**
 * Portfolio Overlap
 *
 */
public class App 
{
    private static String stockDataURL = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";
    private static String inputFilePath = "/Users/sunaina.choudhary/eclipse-workspace/demo/src/main/resources/input2.txt";
    public static void main( String[] args )
    {
        if (args.length<=0 || args[0].trim().isEmpty()) {
            System.out.println("You need to specify input file path in main method argument!");
            return;
        }
        FileParser fileParser = new FileParser(args[0], stockDataURL);
        StockData stockData = fileParser.getStockData();
        List<String[]> commandLineInput = fileParser.readInputFromFile();
        FundManager fundManager = new FundManager(stockData);
        String output = fundManager.executeInput(commandLineInput);
        System.out.println(output);
    }
}
