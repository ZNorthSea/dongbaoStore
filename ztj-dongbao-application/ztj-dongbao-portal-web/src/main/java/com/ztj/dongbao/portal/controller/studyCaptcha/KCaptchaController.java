package com.ztj.dongbao.portal.controller.studyCaptcha;

import com.baomidou.kaptcha.Kaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import com.ztj.dongbao.base.annotations.TokenCheck;
import com.ztj.dongbao.portal.custom.MyGoogleKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/KCaptcha")
@RestController
public class KCaptchaController {
    @Autowired
    private MyGoogleKaptcha myGoogleKaptcha;

    @GetMapping("/generator")
    public void generatorCodeCheck(HttpServletResponse response, HttpServletRequest request){
        myGoogleKaptcha.render();
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verifyCodeCheck(String verifyCode, HttpServletRequest request) {
        boolean validate = myGoogleKaptcha.validate(verifyCode, 30);
        if (validate) {
            return "通过";
        }
        return "不通过";
    }
}
