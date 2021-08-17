package com.wjzn.vo;

import lombok.Data;

import java.util.Map;

@Data
public class PieVO {
    private  Double value;
    private Map<String,String> itemStyle;
    private String name;
}
