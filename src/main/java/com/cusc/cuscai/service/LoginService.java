package com.cusc.cuscai.service;
import com.cusc.cuscai.dao.LoginDao;
import com.cusc.cuscai.entity.apibo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LoginService {

    @Autowired
    LoginDao logindao;

    public boolean isExist(String username) {
        Login login = getByName(username);
        return null!=login;
    }

    public Login getByName(String username) {
        return logindao.findByUsername(username);
    }

    public Login get(String username, String password){
        return logindao.getByUsernameAndPassword(username, password);
    }

    public void add(Login login) {
        logindao.save(login);
    }


}