package com.ztj.dongbao.portal.controller.api;

import com.ztj.dongbao.common.util.MD5Util;

import java.util.*;

public class CheckUtil {
    // 盐
    private static final String SECRET = "zhao";

    // 校验 sign 签名
    public static Boolean checkSign(Map<String,String> map){
        String sign = map.get("sign");
        map.remove("sign"); // 防干扰
        String s = generatorSign(map);
        if (s.equals(sign)){
            return true;
        }else {
            return false;
        }
    }

    // 根据map生成签名
    public static String generatorSign(Map<String,String> map){
        // 从map中移除sign 避免干扰
        map.remove("sign");

        // 对map中的参数进行排序
        Map<String,String> sortMap = SortGenerator(map);

        StringBuilder sb = new StringBuilder();
        // 遍历map中的值
        Set<Map.Entry<String, String>> entries = sortMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            sb.append(entry.getKey()+","+entry.getValue());
        }
        sb.append(SECRET);
        return MD5Util.md5(sb.toString());
    }

    private static Map<String, String> SortGenerator(Map<String, String> map) {
        // 判断map是否为空
        if (null == map){
            throw new RuntimeException("map为空");
        }

        Map<String,String> sortMap = new TreeMap<>(new MyMapComparator());
        sortMap.putAll(map);

        return sortMap;
    }

    static class MyMapComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap();
        map.put("appId","123");
        map.put("name","zhangsan");

        String s = generatorSign(map);
        System.out.println("s = " + s);
    }

}
