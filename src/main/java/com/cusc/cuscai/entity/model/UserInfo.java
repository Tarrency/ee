package com.cusc.cuscai.entity.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import org.noodle.base.BaseModel;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

public class UserInfo extends BaseModel {
    /** 主键 **/
    private Integer userId;

    /** 账号id **/
    private String userAccount;

    /** 创建日期 **/
    @JSONField(format="yyyy-MM-dd")
    private Date userCreatedate;

    /** 用户名称 **/
    private String userName;

    /** 登陆密码 **/
    private String userPassword;

    /** 手机号 **/
    private String userPhone;

    /** 电子邮箱 **/
    private String userMail;

    /** 头像 **/
    private String userImage;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Date getUserCreatedate() {
        return userCreatedate;
    }

    public void setUserCreatedate(Date userCreatedate) {
        this.userCreatedate = userCreatedate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}