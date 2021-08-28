package com.ztj.dongbao.portal.controller.api;

import cn.hutool.core.convert.Convert;
import com.ztj.dongbao.portal.controller.api.posttest.SignDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-safe")
public class ApiSafeController {

    @GetMapping("/hello")
    public String hi(){
        return "hello";
    }

    @GetMapping("/testGet")
    public String getTest(String appid, String name, String sign, HttpServletRequest request){
        // 参数排序
        HashMap<String,String> map = new HashMap<>();

        // 参数先写死
//        map.put("appid",appid);
//        map.put("name",name);

        // 获取get中的参数
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String nextElement = parameterNames.nextElement();
            String parameter = request.getParameter(nextElement);
            map.put(nextElement,parameter);
        }

        // 遍历map，查看参数
       // Set<Map.Entry<String, Object>> entries = map.entrySet();
       // for (Map.Entry<String, Object> entry : entries) {
       //     String value = entry.getKey() +":"+ entry.getValue();
       //     System.out.println("value = " + value);
       // }

        String sign1 = CheckUtil.generatorSign(map);
        // 判断检验
        if (sign1.equals(sign)){
            return "校验通过";
        }
        return "校验不通过";
    }

    @PostMapping("/testPost")
    public String postTest(@RequestBody SignDTO signDTO,HttpServletRequest request){
        System.out.println("signDTO = " + signDTO);

        // 参数转map
        Map<String, String> map = Convert.toMap(String.class, String.class, signDTO);
        System.out.println("map = " + map);

        // 使用工具类校验参数
        Boolean checkSign = CheckUtil.checkSign(map);

        // 判断sign
        if (checkSign){
            return "校验通过";
        }
        return "校验 不通过!!!";
    }

}
