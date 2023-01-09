package com.sunaina.portfolio.service;

import com.sunaina.portfolio.model.StockData;
import com.sunaina.portfolio.util.FileParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class FundManagerTest {
    StockData stockData = new FileParser("https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json").getStockData();
    @Test
    public void testCalculateOverlapPercentageForFundNotFound() {
        FundManager fundManager = new FundManager(stockData);
        String fundToCompare = "MIRAE_ASSET_LARGE_CAP";
        List<String> fundToCompareWith = new ArrayList<>();
        fundToCompareWith.add("BLUECHIP");
        fundToCompareWith.add("ICICI_PRU_BLUECHIP");
        fundToCompareWith.add("UTI_NIFTY_INDEX");
        String output = fundManager.calculateOverlap(fundToCompare, fundToCompareWith);
        assertEquals("MIRAE_ASSET_LARGE_CAP AXIS_BLUECHIP 43.75%\n" +
                "MIRAE_ASSET_LARGE_CAP ICICI_PRU_BLUECHIP 44.62%\n" +
                "MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 95.00%\n", output);

    }
    @Test
    public void testPositiveCalculateOverlapPercentage() {
        FundManager fundManager = new FundManager(stockData);
        String fundToCompare = "MIRAE_ASSET_LARGE_CAP";
        List<String> fundToCompareWith = new ArrayList<>();
        fundToCompareWith.add("AXIS_BLUECHIP");
        fundToCompareWith.add("ICICI_PRU_BLUECHIP");
        fundToCompareWith.add("UTI_NIFTY_INDEX");
        String output = fundManager.calculateOverlap(fundToCompare, fundToCompareWith);
        assertEquals("MIRAE_ASSET_LARGE_CAP AXIS_BLUECHIP 43.75%\n" +
                "MIRAE_ASSET_LARGE_CAP ICICI_PRU_BLUECHIP 44.62%\n" +
                "MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 95.00%\n", output);

    }
}