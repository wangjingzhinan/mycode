package com.wjzn.util;

import java.util.HashMap;
import java.util.Map;

public class DataUtil {

    public static Map<String,String> createItemStyle(Integer type){
        String color = "227700";
        if(type==0){
            color = "#FFB7DD";
        }else if(type==1){
            color = "#00DD77";
        }else if(type==2){
            color = "#D28EFF";
        }else if(type==3){
            color = "#DD3377";
        }else if(type==4){
            color = "#C10066";
        }else {
            color = "#007799";
        }
        Map<String,String> map = new HashMap<>();
        map.put("color", color);
        return map;
    }

}
