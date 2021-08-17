package com.wjzn.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BarVO {
    private List<String> names;
    private List<DataVo> values;
}
