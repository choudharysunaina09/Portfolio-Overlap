package com.sunaina.portfolio.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunaina.portfolio.model.StockData;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileParser {
    private String inputFilePath;
    private String stockDataURL;

    public FileParser(String inputFilePath, String stockDataURL) {
        this.inputFilePath = inputFilePath;
        this.stockDataURL = stockDataURL;
    }

    public FileParser(String stockDataURL){
        this.stockDataURL = stockDataURL;
    }
    public StockData getStockData() {
        ObjectMapper objectMapper = new ObjectMapper();
        StockData stockData = null;
        try {
            stockData = objectMapper.readValue(new URL(stockDataURL), StockData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stockData;
    }

    public List<String[]> readInputFromFile() {
        Map<String, String> map = new HashMap<>();
        List<String[]> commandLines = new ArrayList<>();
        try{
            List<String> allLines = Files.readAllLines(Paths.get(inputFilePath));
            for (String line:allLines) {
                commandLines.add(getFunctionArguments(line));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return commandLines;
    }
    private String[] getFunctionArguments(String command) {
        var args = command.split(" ");
        return Arrays.copyOfRange(args, 0, args.length);
    }
}
