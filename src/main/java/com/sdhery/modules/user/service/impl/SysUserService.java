package com.sdhery.modules.user.service.impl;

import com.sdhery.core.dao.EntityDao;
import com.sdhery.core.service.ISysObjectKeyService;
import com.sdhery.core.service.impl.BaseService;
import com.sdhery.modules.user.dao.ISysUserDao;
import com.sdhery.modules.user.dao.impl.SysUserDao;
import com.sdhery.modules.user.domain.SysUser;
import com.sdhery.modules.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-15
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
@Service("sysUserService")
public class SysUserService extends BaseService<SysUser, Integer> implements ISysUserService {
    @Autowired
    ISysUserDao sysUserDao;

    @Autowired
    @Qualifier("sysObjectKeyService")
    ISysObjectKeyService sysObjectKeyService;

    protected EntityDao<SysUser, Integer> getEntityDao() {
        return sysUserDao;
    }

    public SysUser getSysUserByLoginId(String loginId) {
        return sysUserDao.getSysUserByLoginId(loginId);
    }

    public String getRealLoginKey(String fieldValue) {
        return "sysUserLoginKey_" + fieldValue;
    }

    public SysUser getSysUserByKey(String key) {
        SysUser sysUser = null;
        try {
            String sysObjectKey = getRealLoginKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUser;
    }

    public SysUser getSysUserBySysUserId(int sysUserId) {
        return sysUserDao.getById(sysUserId);
    }
}
