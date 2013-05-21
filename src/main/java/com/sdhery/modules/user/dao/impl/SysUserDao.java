package com.sdhery.modules.user.dao.impl;

import com.sdhery.core.dao.impl.BaseMybatisDao;
import com.sdhery.modules.user.dao.ISysUserDao;
import com.sdhery.modules.user.domain.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-15
 * Time: 下午5:20
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class SysUserDao  extends BaseMybatisDao<SysUser, Integer> implements ISysUserDao{
    public SysUser getSysUserByLoginId(String loginId){
        return getSqlSession().selectOne(SysUserDao.class.getName() + ".getSysUserByLoginId", loginId);
    }
}
