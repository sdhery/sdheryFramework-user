package com.sdhery.module.user.helper;

import com.sdhery.module.user.service.ISysUserService;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-7
 * Time: 上午9:18
 * To change this template use File | Settings | File Templates.
 */
public class SysUserServiceManager {
    public static ISysUserService sysUserService;


    public void setSysUserService(ISysUserService sysUserService) {
        SysUserServiceManager.sysUserService = sysUserService;
    }
}
