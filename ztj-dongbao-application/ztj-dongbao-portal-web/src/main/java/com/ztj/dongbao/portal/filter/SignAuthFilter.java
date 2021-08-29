package com.ztj.dongbao.portal.filter;

import com.alibaba.fastjson.JSONObject;
import com.ztj.dongbao.portal.controller.api.CheckUtil;
import com.ztj.dongbao.portal.util.HttpParamUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class SignAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;

        // 获取URL和Body中的值
        Map<String, String> allParams = HttpParamUtil.getAllParams(hRequest);
        System.out.println("allParams = " + allParams);

        Boolean sign = CheckUtil.checkSign(allParams);
        if (sign){
            System.out.println("签名正确------");
            chain.doFilter(hRequest,hResponse);
        }else {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();

            JSONObject param = new JSONObject();
            param.put("code",-1);
            param.put("message", "签名错了");

            writer.write(param.toJSONString());
        }

    }
}
