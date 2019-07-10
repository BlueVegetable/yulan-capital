package com.yulan.dao;

import com.yulan.pojo.Web_user;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Web_userDao {

    //将操作员账号转换成公司ID
    String changeLoginNameToCompanyID(String cid);
}
