package com.sdhery.modules.user.service;

import com.sdhery.core.service.IBaseService;
import com.sdhery.modules.user.domain.SysUser;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-15
 * Time: 下午7:09
 * To change this template use File | Settings | File Templates.
 */
public interface ISysUserService extends IBaseService<SysUser,Integer>{
    SysUser getSysUserByLoginId(String loginId);
}
