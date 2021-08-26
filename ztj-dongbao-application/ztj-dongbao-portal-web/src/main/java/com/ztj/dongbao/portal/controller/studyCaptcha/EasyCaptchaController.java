package com.ztj.dongbao.portal.controller.studyCaptcha;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import com.ztj.dongbao.base.annotations.TokenCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/easyCaptcha")
@RestController
public class EasyCaptchaController {

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCodeCheck(HttpServletResponse response, HttpServletRequest request){
        try {
            // 算术验证码
            ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(200,50);
            arithmeticCaptcha.setLen(3);
            String text = arithmeticCaptcha.text();
            System.out.println("text = " + text);
            CaptchaUtil.out(arithmeticCaptcha,request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verifyCodeCheck(String verifyCode, HttpServletRequest request) {
        boolean ver = CaptchaUtil.ver(verifyCode, request);
        if (ver){
            CaptchaUtil.clear(request);
            return "通过";
        }

        return "不通过";
    }
}
