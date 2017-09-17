package org.redrock.weixin.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static boolean hasParameter(HttpServletRequest request, String parameterName) {
        return !StringUtil.isBlank(getParameter(request, parameterName));
    }

    public static String getParameter(HttpServletRequest request, String parameter) {
        return request.getParameter(parameter);
    }

    public static String getUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    public static Object getSessionAttribute(HttpServletRequest request, String parameter) {
        return request.getSession().getAttribute(parameter);
    }

    public static void setSessionAttribute(HttpServletRequest request, String parameterName, Object parameterValue) {
        request.setAttribute(parameterName, parameterValue);
    }
}
