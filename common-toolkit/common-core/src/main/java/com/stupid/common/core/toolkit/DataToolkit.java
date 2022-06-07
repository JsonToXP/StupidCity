package com.stupid.common.core.toolkit;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据转换工具包
 */
public class DataToolkit {

    /**
     * Object 2 Map
     **/
    public static Map object2Map(Object o){
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = new HashMap<>();
        try{
            map = mapper.readValue(mapper.writeValueAsString(o), Map.class);
        }catch (Exception e){
            e.getMessage();
        }
        return map;
    }

    /**
     * Map 2 Object
     **/
    public static <T> T map2Object(Map map,Class<T> c){
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try{
            t = mapper.readValue(mapper.writeValueAsString(map), c);
        }catch (Exception e){
            e.getMessage();
        }
        return t;
    }

    /**
     * Object 2 Class
     **/
    public static <T> T object2Class(Object o,Class<T> clazz){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(o,clazz);
    }
}
