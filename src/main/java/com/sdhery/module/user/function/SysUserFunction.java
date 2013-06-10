package com.sdhery.module.user.function;

import com.sdhery.module.helper.ServiceManager;
import com.sdhery.module.user.domain.SysUser;
import com.sdhery.module.user.service.ISysUserService;
import com.sdhery.module.user.util.SysUserCookieUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-10
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class SysUserFunction {
    public static SysUser getAdminSysUser(HttpServletRequest request){
        SysUser sysUser = null;
        try{
            int sysUserId = SysUserCookieUtil.getAdminLoginUserIdFromCookie(request);
            if(sysUserId!= ISysUserService.NULL_SYSUSERID){
                sysUser = ServiceManager.sysUserService.getById(sysUserId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sysUser;
    }
}
