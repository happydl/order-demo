package com.ldm.order.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
public class User {

    public Integer id;

    @NotBlank(message="账号长度在3~20个字符")
    @Length(min = 3,max = 20,message="账号长度在3~20个字符")
    public String name;

    @NotBlank(message="用户密码在3~8个字符")
    @Length(min=3,max=8,message="用户密码在6~20个字符")
    public String password;

    public User() {

    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
