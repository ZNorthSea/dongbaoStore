package com.ztj.dongbao.portal.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ztj.dongbao.base.annotations.TokenCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/happyCaptcha")
@RestController
public class HappyCaptchaController {

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletResponse response, HttpServletRequest request){
        HappyCaptcha.require(request,response)
                .style(CaptchaStyle.ANIM)
                .build().finish();
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {

        Boolean aBoolean = HappyCaptcha.verification(request,verifyCode,true);
        if (aBoolean){
            HappyCaptcha.remove(request);
            return "通过";
        }

        return "不通过";
    }
}
