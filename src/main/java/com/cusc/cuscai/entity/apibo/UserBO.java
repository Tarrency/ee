package com.cusc.cuscai.entity.apibo;

import com.cusc.cuscai.entity.bo.UserInfoBO;

import java.util.Date;


public class UserBO {
    Integer userId;
    String userAccount;
    Date userCreateDate;
    String userName;
    String userPassword;
    String userPhone;
    String userMail;
    String userImage;

    //默认构造函数
    public UserBO(){}
    public UserBO(UserInfoBO userInfoBO){
        this.userId = userInfoBO.getUserId();
        this.userAccount = userInfoBO.getUserAccount();
        this.userCreateDate = userInfoBO.getUserCreatedate();
        this.userName = userInfoBO.getUserName();
        this.userPassword = userInfoBO.getUserPassword();
        this.userPhone = userInfoBO.getUserPhone();
        this.userMail = userInfoBO.getUserMail();
        this.userImage = userInfoBO.getUserImage();
    }

    public String getUserImage() { return userImage; }

    public void setUserImage(String userImage) { this.userImage = userImage; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUserAccount() { return userAccount; }

    public void setUserAccount(String userAccount) { this.userAccount = userAccount; }

    public Date getUserCreateDate() { return userCreateDate; }

    public void setUserCreateDate(Date userCreateDate) { this.userCreateDate = userCreateDate; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public String getUserMail() { return userMail; }

    public void setUserMail(String userMail) { this.userMail = userMail; }
}
