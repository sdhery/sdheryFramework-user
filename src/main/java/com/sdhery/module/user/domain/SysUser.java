package com.sdhery.module.user.domain;


import com.sdhery.module.core.domain.BaseEntity;

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
    String passwordHash;
    String random;
    Integer isAdmin;//0普通会员1管理员
    Integer state;//0激活1未激活

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer admin) {
        isAdmin = admin;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
