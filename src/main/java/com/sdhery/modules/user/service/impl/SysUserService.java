package com.sdhery.modules.user.service.impl;

import com.sdhery.core.dao.EntityDao;
import com.sdhery.core.domain.SysObjectKey;
import com.sdhery.core.helper.CoreServiceManager;
import com.sdhery.core.service.ISysObjectKeyService;
import com.sdhery.core.service.impl.BaseService;
import com.sdhery.core.util.DigestUtil;
import com.sdhery.modules.user.dao.ISysUserDao;
import com.sdhery.modules.user.domain.SysUser;
import com.sdhery.modules.user.service.ISysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.security.NoSuchAlgorithmException;

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
            key = getRealLoginKey(key);
            SysObjectKey sysObjectKey = CoreServiceManager.sysObjectKeyService.getById(key);
            if (sysObjectKey != null) {
                int sysUserId = NumberUtils.toInt(sysObjectKey.getSysObjectKeyValue());
                sysUser = getSysUserBySysUserId(sysUserId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUser;
    }

    public SysUser getSysUserBySysUserId(int sysUserId) {
        return sysUserDao.getById(sysUserId);
    }

    protected int findUser(SysUser sysUser, String password, int target) throws NoSuchAlgorithmException {
        int isAdmin = sysUser.getIsAdmin();
        if (target == ISysUserService.TARGET_SYSTEM) {
            if (isAdmin != ISysUserService.USERTYPE_ISADMIN_YES) {
                return ISysUserService.NOT_SYSTEM_ADMIN;
            }
        }
        if (target == ISysUserService.TARGET_MEMBER) {
            if (isAdmin != ISysUserService.USERTYPE_ISADMIN_NO) {
                return ISysUserService.NOT_MEMBER;
            }
        }
        int isEnable = sysUser.getState();
        if (isEnable == ISysUserService.USER_ENABLE_NO) {
            return ISysUserService.DISABLE_NOT_USER;
        }
        String random = sysUser.getRandom();

        if (StringUtils.isBlank(random)) {
            return ISysUserService.NOT_USER_RANDOM;
        }
        String passwordHash = sysUser.getPasswordHash();
        if (StringUtils.isBlank(passwordHash)) {
            return ISysUserService.NOT_USER_PASSWORDHASH;
        }
        //验证密码
        String passRan = password + random;
        String passwordSha = DigestUtil.digestString(passRan, "SHA");
        if (!passwordSha.equals(passwordHash)) {
            return ISysUserService.INPUT_USER_PASSWORD_ERROR;
        }
        return ISysUserService.LOGIN_SUCCESSFUL;
    }
}
