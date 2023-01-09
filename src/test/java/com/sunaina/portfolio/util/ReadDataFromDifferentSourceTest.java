package com.sunaina.portfolio.util;

import org.junit.jupiter.api.Test;

import java.util.List;


class ReadDataFromDifferentSourceTest {

    private FileParser readDataFromDifferentSource = new FileParser("");
    @Test
    public void readInputFromFile() {
        List<String[]> map = readDataFromDifferentSource.readInputFromFile();
        System.out.println(map);
    }
}