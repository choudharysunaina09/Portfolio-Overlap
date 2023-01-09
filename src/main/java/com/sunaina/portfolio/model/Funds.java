package com.sunaina.portfolio.model;

import lombok.Data;

import java.util.Set;


@Data
public class Funds {
    private String name;
    private Set<String> stocks;
}
