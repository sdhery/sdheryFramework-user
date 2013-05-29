package com.sdhery.modules.user.service.impl;

import com.sdhery.core.dao.EntityDao;
import com.sdhery.core.helper.CoreServiceManager;
import com.sdhery.core.service.ISysObjectKeyService;
import com.sdhery.core.service.impl.BaseService;
import com.sdhery.modules.user.dao.ISysUserDao;
import com.sdhery.modules.user.domain.SysUser;
import com.sdhery.modules.user.service.ISysUserService;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-15
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */

public class SysUserService extends BaseService<SysUser, Integer> implements ISysUserService {

    ISysUserDao sysUserDao;

    public void setSysUserDao(ISysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

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
            String value = CoreServiceManager.sysObjectKeyService.getById(sysObjectKey).getSysObjectKeyValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUser;
    }

    public SysUser getSysUserBySysUserId(int sysUserId) {
        return sysUserDao.getById(sysUserId);
    }
}
