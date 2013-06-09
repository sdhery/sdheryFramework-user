package com.sdhery.module.user.web.admin;

import com.sdhery.module.core.web.BaseController;
import com.sdhery.module.helper.MessageSourceManager;
import com.sdhery.module.helper.ServiceManager;
import com.sdhery.module.user.service.ISysUserService;
import com.sdhery.module.user.vo.SysUserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    public void loginOk(ModelMap modelMap, SysUserVo sysUserVo,HttpServletRequest request) {
        try {
            int result = ServiceManager.sysUserService.loginResult(sysUserVo.getLoginId(), sysUserVo.getPassword(), ISysUserService.TARGET_SYSTEM);
            modelMap.put("result", MessageSourceManager.getMessage("login.error." + result, request));
            if(result==ISysUserService.LOGIN_SUCCESSFUL){
                setSuccess(modelMap);
            }else{
                setFailure(modelMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
