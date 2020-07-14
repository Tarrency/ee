package com.cusc.cuscai.controller;

import com.cusc.cuscai.entity.apibo.UserBO;
import com.cusc.cuscai.service.UserService;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@Api(tags = "User相关接口")
public class UserController {

    @Autowired
    UserService userService = new UserService();

    @PostMapping("/userinfo")
    @ResponseBody
    @ApiOperation("得到用户信息")
    public Result getUserInfo(
            @RequestParam("userAccount") @ApiParam(value = "用户账号", required = true) String userAccount
    ){
        UserBO userinfo = new UserBO();
        try{
            userinfo = userService.getUserInfo(userAccount);
            if(userinfo.getUserId()==-1)
                throw new Exception("未找到该用户");
        }catch (Exception e) {
            return Result.fail(40110, e.getMessage());
        }
        return Result.success(20110, "查询User信息成功", userinfo);
    }

    @PutMapping("/change")
    @ResponseBody
    @ApiOperation("修改用户信息")
    public Result modifyUser(
            @RequestParam("userAccount") @ApiParam(value = "用户账号", required = true) String userAccount,
            @RequestParam("userName") @ApiParam(value = "用户名称", defaultValue = "") String userName,
            @RequestParam("userPassword") @ApiParam(value = "用户密码", defaultValue = "") String userPassword,
            @RequestParam("userPhone") @ApiParam(value = "用户手机", defaultValue = "") String userPhone,
            @RequestParam("userMail") @ApiParam(value = "用户邮箱", defaultValue = "") String userMail
    ){
        if(userPhone == ""){

        }else {
            String s = "^1[0-9]{10}$";
            Pattern p = Pattern.compile(s);
            Matcher m = p.matcher(userPhone);
            if(!m.matches()){
                return Result.fail(40100, "手机号码格式不正确");
            }
        }
        if(userMail == ""){

        }else {
            String s = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p = Pattern.compile(s);
            Matcher m = p.matcher(userMail);
            if(!m.matches()){
                return Result.fail(40100, "邮箱格式不正确");
            }
        }

        try {
            userService.modifyUser(userAccount,userName,userPassword,userPhone,userMail);
        } catch (Exception e) {
            return Result.fail(40100, e.getMessage());
        }
        return Result.success(20100, "修改user信息成功", userAccount);
    }
}
