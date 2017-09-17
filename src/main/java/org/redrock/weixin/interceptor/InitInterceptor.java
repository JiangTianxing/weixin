package org.redrock.weixin.interceptor;

import org.redrock.weixin.annotation.Weixin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class InitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler != null) {
            if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                Annotation[] methodAnnotations = ((HandlerMethod)handler).getMethod().getAnnotations();
                for (int i = 0; i < methodAnnotations.length; i++) {
                    Annotation annotation = methodAnnotations[i];
                    try {
                        Weixin weixin = (Weixin) annotation;
                        Class<? extends BaseInterceptor> interceptor = weixin.value();
                        Object object = Class.forName(interceptor.getCanonicalName()).newInstance();
                        Class[] clazzes = new Class[]{HttpServletRequest.class, HttpServletResponse.class, Object.class};
                        Method method = object.getClass().getMethod("interceptor", clazzes);
                        Object[] params = new Object[]{httpServletRequest, httpServletResponse, handler};
                        return (boolean) method.invoke(object, params);
                    } catch (Exception e) {
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        httpServletResponse.getWriter().println("end");
    }
}
