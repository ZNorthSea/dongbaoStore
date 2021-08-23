package com.ztj.dongbao.portal.interceptor;

import com.ztj.dongbao.base.annotations.TokenCheck;
import com.ztj.dongbao.base.exception.TokenException;
import com.ztj.dongbao.common.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器 验证token
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response
            , Object handler) throws Exception {

        System.out.println("进入拦截器");

        String token = request.getHeader("token");
        if ("".equals(token)){
            throw new TokenException("token不存在");
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(TokenCheck.class)){
            TokenCheck tokenCheck = method.getAnnotation(TokenCheck.class);
            if (tokenCheck.required()){
                // 校验token
                try{
                    JwtUtil.parseToken(token);
                    return true;
                }catch (Exception e){
                    throw new TokenException("token异常");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
