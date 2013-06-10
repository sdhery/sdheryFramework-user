package com.sdhery.module.user.util;

import com.sdhery.module.core.security.Md5Service;
import com.sdhery.module.user.domain.SysUser;
import com.sdhery.module.user.service.ISysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-6
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public class SysUserCookieUtil {
    public static final String memberLoginKey = "memberInfo";
    public static final String adminLoginKey = "adminInfo";

    /**
     * 后台管理员使用的addCookie
     */
    public static void addAdminCookie(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
        addCookie(sysUser, adminLoginKey, request, response);
    }

    /**
     * 前台会员使用的addCookie
     */
    public static void addMemberCookie(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
        addCookie(sysUser, memberLoginKey, request, response);
    }

    public static void addMemberCookie(SysUser sysUser, HttpServletRequest request, HttpServletResponse response,Integer maxAge) throws Exception {
        addCookie(sysUser, memberLoginKey, request, response,maxAge);
    }

    private static void addCookie(SysUser sysUser, String cookieName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        addCookie(sysUser,cookieName,request,response,null);
    }
    private static void addCookie(SysUser sysUser, String cookieName, HttpServletRequest request, HttpServletResponse response,Integer maxAge) throws Exception {
        if (sysUser == null) return;

        int sysUserId = sysUser.getSysUserId();
        StringBuffer userInfo = new StringBuffer();
        userInfo.append(sysUserId).append(",");
        userInfo.append(sysUser.getLoginId()).append(",");
        userInfo.append(request.getRemoteAddr() == null ? "" : request.getRemoteAddr()).append(",");
        userInfo.append(new Date().getTime());
        String encodeUserInfo = Md5Service.encString(userInfo.toString(), getKey());

        encodeUserInfo = URLEncoder.encode(encodeUserInfo, "UTF-8");
        addCookie(cookieName, encodeUserInfo, response,maxAge);
    }

    private static String getKey() {
        String key = "This is a Md5KEY #$$FFJGVVbk*(&^&*((^";
        return key;
    }

    public static int getAdminLoginUserIdFromCookie(HttpServletRequest request) throws Exception {
        return getLoginUserId(adminLoginKey, request);
    }

    public static int getMemberLoginUserIdFromCookie(HttpServletRequest request) throws Exception {
        return getLoginUserId(memberLoginKey, request);
    }

    public static int getLoginUserId(String cookieName,HttpServletRequest request) throws Exception {
        return NumberUtils.toInt(getLoginSysUserIdFromCookie(cookieName, request), ISysUserService.NULL_SYSUSERID);
    }


    /**
     * 此方法只获得当前登录的sysUserId，并没有验证权限
     * 如果需要验证权限，应该用下面一个方法
     */
    private static String getLoginSysUserIdFromCookie(String cookieName, HttpServletRequest request) throws Exception {
        String decodeUserInfo = getDecodeUserInfo(cookieName, request);
        if (decodeUserInfo == null) {
            return null;
        }
        String[] uInfo = decodeUserInfo.split(",");
        if (uInfo == null || uInfo.length < 4) {
            return null;
        }

        String sysUserId = uInfo[0];
        return sysUserId;
    }

    /**
     * 获得解密后的userInfo
     */
    private static String getDecodeUserInfo(String cookieName, HttpServletRequest request) throws Exception {
        String userInfo = getCookieValueByName(request, cookieName);
        if (userInfo == null || userInfo.trim().equals("")) {
            return null;
        }

        String decodeUserInfo = URLDecoder.decode(userInfo, "UTF-8");
        decodeUserInfo = Md5Service.decString(decodeUserInfo, getKey());
        if (decodeUserInfo == null || decodeUserInfo.trim().equals("")) {
            return null;
        }
        return decodeUserInfo;
    }

    /**
     * 根据名字获得cookie值
     */
    public static String getCookieValueByName(HttpServletRequest request, String cookieName) {
        String cookieValue = "";
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals(cookieName)) {
                        cookieValue = cookie.getValue();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookieValue;
    }

    public static void addCookie(String cookieName, String cookieValue, HttpServletResponse response) throws Exception {
        addCookie(cookieName,cookieValue,response);

    }

    public static void addCookie(String cookieName, String cookieValue, HttpServletResponse response,Integer maxAge) throws Exception {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        if(maxAge!=null){
            cookie.setMaxAge(maxAge);//如果设置为负值的话，则为浏览器进程Cookie(内存中保存)，关闭浏览器就失效。
        }else{
            cookie.setMaxAge(-1);
        }
        String webDomain = "";//todo:未有设置域名功能？
        if (StringUtils.isNotBlank(webDomain)) {
            cookie.setDomain("." + webDomain);
        }
        response.addCookie(cookie);
    }

    /**
     * 根据名字注销cookie
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String webDomain = "";//todo:未有设置域名功能？
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(cookieName)) {
                    cookie.setMaxAge(-1);
                    cookie.setValue("");
                    cookie.setPath("/");
                    if (StringUtils.isNotBlank(webDomain)) {
                        cookie.setDomain("." + webDomain);
                    }
                    response.addCookie(cookie);
                }
            }
        }
    }
}
