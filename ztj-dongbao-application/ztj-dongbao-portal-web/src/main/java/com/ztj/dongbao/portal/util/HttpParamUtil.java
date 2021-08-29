package com.ztj.dongbao.portal.util;

import cn.hutool.core.net.URLDecoder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 统一获得URL和body中的参数，适应于所有的请求
 */
public class HttpParamUtil {
    /**
     * 获取URL和Body中的所有参数
     *
     * @param request
     * @return 有序map
     * @throws IOException
     */
    public static SortedMap<String, String> getAllParams(HttpServletRequest request) throws IOException {
        // 获取URL中的参数
        Map<String, String> urlParam = getURLParam(request);

        // 获取Body中的参数
        Map<String, String> bodyParam = getBodyParam(request);

        SortedMap<String, String> allMap = new TreeMap<>();

        Set<Map.Entry<String, String>> entries = urlParam.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            allMap.put(entry.getKey(), entry.getValue());
        }

        Set<Map.Entry<String, String>> entries1 = bodyParam.entrySet();
        for (Map.Entry<String, String> stringEntry : entries1) {
            allMap.put(stringEntry.getKey(), stringEntry.getValue());
        }

        return allMap;
    }

    private static Map<String, String> getBodyParam(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        while (null != (line = reader.readLine())) {
            sb.append(line);
        }
        if ("".equals(sb.toString())){
            return new HashMap<>();
        }
        Map map = JSONObject.parseObject(sb.toString(), Map.class);
        return map;
    }

    /**
     * 获得URL中的参数
     *
     * @param request
     * @return Map中放入 URL参数
     */
    private static Map<String, String> getURLParam(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        String queryParam = "";
        if (!StringUtils.isEmpty(request.getQueryString())) {
            // 获取URL中经过UTF-8解码之后的参数
            queryParam = URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8);
        } else {
            return map;
        }

        String[] param = queryParam.trim().split("&");
        for (String s : param) {
            int i = s.indexOf("=");
            map.put(s.substring(0, i), s.substring(i + 1));
        }

        return map;
    }
}
