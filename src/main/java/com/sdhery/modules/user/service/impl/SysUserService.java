package com.sdhery.modules.user.service.impl;

import com.sdhery.core.dao.EntityDao;
import com.sdhery.core.service.impl.BaseService;
import com.sdhery.modules.user.dao.ISysUserDao;
import com.sdhery.modules.user.dao.impl.SysUserDao;
import com.sdhery.modules.user.domain.SysUser;
import com.sdhery.modules.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-15
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
@Component("sysUserService")
public class SysUserService extends BaseService<SysUser,Integer> implements ISysUserService {
    @Autowired
    ISysUserDao sysUserDao;


    protected EntityDao<SysUser, Integer> getEntityDao() {
        return sysUserDao;
    }

    public SysUser getSysUserByLoginId(String loginId) {
        return sysUserDao.getSysUserByLoginId(loginId);
    }
}
