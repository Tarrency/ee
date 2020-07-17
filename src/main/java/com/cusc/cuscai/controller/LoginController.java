package com.cusc.cuscai.controller;
import com.cusc.cuscai.util.Result;
import com.cusc.cuscai.entity.apibo.Login;
import com.cusc.cuscai.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
@Controller
public class LoginController {
    @Autowired
    LoginService loginservice;

    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public Result loginto(@RequestBody Login requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        Login login = loginservice.get(username, requestUser.getPassword());
        if (null == login) {
            return Result.fail(400,"验证失败",null);
        } else {
            return Result.success(200,"验证成功",null);
        }
    }

}

