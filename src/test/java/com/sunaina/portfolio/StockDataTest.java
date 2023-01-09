package com.sunaina.portfolio;

import com.sunaina.portfolio.model.StockData;
import com.sunaina.portfolio.util.FileParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StockDataTest {
    StockData stockData = new FileParser("https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json").getStockData();
    @Test
    public void testStocksData() throws IOException {
        assertEquals(10, stockData.getFunds().size());
        assertTrue(stockData.getStocksInFund("MIRAE_ASSET_LARGE_CAP")
                .contains("PETRONET LNG LIMITED"));
        assertEquals(63, stockData.getStocksInFund("MIRAE_ASSET_LARGE_CAP").size());
    }
}

