package com.yulan.service;

import com.yulan.dao.WebUserDao;
import com.yulan.pojo.WebUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebUserService {
    @Autowired
    private WebUserDao webUserDao;

    public WebUser getUser(@Param("cid") String cid) {
        return webUserDao.getUser(cid);
    }

    public List<WebUser> getWebUsersByCompanyId(String companyId) {
        return webUserDao.getWebUsersByCompanyId(companyId);
    }
}
