package com.sdhery.module.user.service;


import com.sdhery.module.core.service.IBaseService;
import com.sdhery.module.user.domain.SysUser;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-15
 * Time: 下午7:09
 * To change this template use File | Settings | File Templates.
 */
public interface ISysUserService extends IBaseService<SysUser, Integer> {
    /**
     * 用户错误代码标记
     */
    public static final int LOGIN_SUCCESSFUL = 200;//用户填写密码验证成功
    public static final int NOT_USER_RANDOM = 201;//用户随机数为空
    public static final int NOT_USER_PASSWORDHASH = 202;//用户加密密码为空
    public static final int INPUT_USER_PASSWORD_ERROR = 203;//用户填写密码错误
    public static final int NOT_USER = 204;//用户不存在
    public static final int DISABLE_NOT_USER = 205;//用户未激活
    public static final int NOT_SYSTEM_ADMIN = 206;//非系统管理员
    public static final int NOT_MEMBER = 207;//非会员，系统管理员定义为非会员
    public static final int NOT_ADMIN_LOGIN = 301;//没有登录后台的权限
    public static final long ADMIN_ROOT_ID = 1;//root管理员ID
    /**
     * 用户登录的目的地
     */
    public static final int TARGET_SYSTEM = 0; //系统管理后台
    public static final int TARGET_MEMBER = 1; //会员专区

    /**
     * 用户激活标记
     */
    public static final int USER_ENABLE_NO = 0; //未激活
    public static final int USER_ENABLE_YES = 1; //激活
    /**
     * 用户类型标记
     */
    public static final int USERTYPE_ISADMIN_NO = 0; //普通会员；
    public static final int USERTYPE_ISADMIN_YES = 1; //系统管理员；

    SysUser getSysUserByLoginId(String loginId);

    String getRealLoginKey(String fieldValue);

    SysUser getSysUserByKey(String key);

    SysUser getSysUserBySysUserId(int sysUserId);
}
