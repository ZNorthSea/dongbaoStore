package com.ztj.dongbao.portal.custom;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_DATE;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaRenderException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztj.dongbao.portal.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * 谷歌默认验证码整合redis组件
 *
 * @author zhaotj
 */
@Slf4j
@Component
public class MyGoogleKaptcha implements Kaptcha {

  private DefaultKaptcha kaptcha;

  @Autowired
  private HttpServletResponse response;

  @Autowired
  private RedisUtil redisUtil;

  public MyGoogleKaptcha(DefaultKaptcha kaptcha) {
    this.kaptcha = kaptcha;
  }

  @Override
  public String render() {
    response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store, no-cache, must-revalidate");
    response.addHeader(HttpHeaders.CACHE_CONTROL, "post-check=0, pre-check=0");
    response.setHeader(HttpHeaders.PRAGMA, "no-cache");
    response.setContentType("image/jpeg");
    String text = kaptcha.createText();
    try (ServletOutputStream out = response.getOutputStream()) {
      redisUtil.set("captcha",text);
      ImageIO.write(kaptcha.createImage(text), "jpg", out);
      return text;
    } catch (IOException e) {
      throw new KaptchaRenderException(e);
    }
  }

  @Override
  public boolean validate(String code) {
    return validate(code, 900);
  }

  @Override
  public boolean validate(String code, long second) {
    String captcha = (String)redisUtil.get("captcha");
    if (captcha.equals(code)){
      redisUtil.del("captcha");
      return true;
    }else {
      return false;
    }

  }

}