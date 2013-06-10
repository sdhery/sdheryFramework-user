package com.sdhery.module.user.vo;

import com.sdhery.module.user.domain.SysUser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-6
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
public class SysUserVo extends SysUser{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
