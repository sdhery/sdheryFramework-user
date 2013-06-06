package com.sdhery.module.user.web.admin;

import com.sdhery.module.user.vo.SysUserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-6
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AdminLogin {
    String result;

    @RequestMapping(value = "/admin/loginOK")
    public String loginOk(ModelMap modelMap, SysUserVo sysUserVo) {
        return result;
    }
}
