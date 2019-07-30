package com.yulan.dao;

import com.yulan.pojo.WebUser;
import com.yulan.pojo.Web_user;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WebUserDao {

    WebUser getUser(@Param("cid") String cid);

    List<WebUser> getWebUsersByCompanyId(String companyId);
}
