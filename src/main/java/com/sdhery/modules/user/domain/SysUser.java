package com.sdhery.modules.user.domain;

import com.sdhery.core.domain.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-15
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
public class SysUser extends BaseEntity {
    Integer sysUserId;
    String loginId;

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
