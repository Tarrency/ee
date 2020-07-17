package com.cusc.cuscai.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cusc.cuscai.entity.apibo.Login;
public interface LoginDao extends JpaRepository<Login,Integer>{
    Login findByUsername(String username);

    Login getByUsernameAndPassword(String username,String password);

}
