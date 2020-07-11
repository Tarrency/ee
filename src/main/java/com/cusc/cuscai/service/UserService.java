package com.cusc.cuscai.service;


import com.cusc.cuscai.dao.UserInfoDao;
import com.cusc.cuscai.entity.apibo.UserBO;
import com.cusc.cuscai.entity.bo.UserInfoBO;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserInfoDao userInfoDao;


    public UserBO getUserInfo(String userAccount){
        UserInfoBO resdata = new UserInfoBO();
        try{
            resdata = userInfoDao.getUserInfo(userAccount);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        UserBO userinfo = new UserBO(resdata);
        return userinfo;
    }

    public void modifyUser(String userAccount,String userName,String userPassword,
                           String userPhone,String userMail)
    {
        try{
            userInfoDao.modifyUser(userAccount,userName,userPassword,userPhone,userMail);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
