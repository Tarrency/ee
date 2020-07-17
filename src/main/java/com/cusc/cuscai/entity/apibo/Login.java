package com.cusc.cuscai.entity.apibo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name = "login_info")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Login
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    String username;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    String password;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
};