package vn.hcmute.web24162095.util;

import jakarta.servlet.http.HttpServletRequest;

public final class RequestUtil_24162095 {
    private RequestUtil_24162095() { }
    public static int intParam(HttpServletRequest req,String name,int fallback){try{return Integer.parseInt(req.getParameter(name));}catch(Exception e){return fallback;}}
    public static Integer nullableInt(String value){try{return value==null||value.isBlank()?null:Integer.valueOf(value);}catch(NumberFormatException e){return null;}}
    public static String trim(String value){return value==null?null:value.trim();}
}
