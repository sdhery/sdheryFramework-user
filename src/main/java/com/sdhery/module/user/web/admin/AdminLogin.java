package com.sdhery.module.user.web.admin;

import com.sdhery.module.core.web.BaseController;
import com.sdhery.module.helper.MessageSourceManager;
import com.sdhery.module.helper.ServiceManager;
import com.sdhery.module.user.domain.SysUser;
import com.sdhery.module.user.service.ISysUserService;
import com.sdhery.module.user.util.CookieUtil;
import com.sdhery.module.user.vo.SysUserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-6
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AdminLogin extends BaseController {

    @RequestMapping(value = "/admin/loginOK")
    public @ResponseBody ModelMap loginOk(SysUserVo sysUserVo,HttpServletRequest request,HttpServletResponse response) {
        ModelMap modelMap = new ModelMap();
        try {
            int result = ServiceManager.sysUserService.loginResult(sysUserVo.getLoginId(), sysUserVo.getPassword(), ISysUserService.TARGET_SYSTEM);
            if(result==ISysUserService.LOGIN_SUCCESSFUL){
                setSuccess(modelMap);
            }else{
                modelMap.put("result", MessageSourceManager.getMessage("login.error." + result, request));
                setFailure(modelMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMap;
    }
}
