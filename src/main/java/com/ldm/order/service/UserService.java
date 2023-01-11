package com.ldm.order.service;

import com.ldm.order.common.CacheUtils;
import com.ldm.order.common.RespCode;
import com.ldm.order.exception.MyException;
import com.ldm.order.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

@Component
public class UserService {
    Map<String, Integer> tokenToId = new HashMap<>();
    List<User> userList = new ArrayList<>();

    private int tokenCounter = 1;

    private String genToken() {
        return String.valueOf(++tokenCounter) + System.currentTimeMillis();
    }

    private int id = 0;
    private int genId(){
        return ++id;
    }

    UserService() {
        System.out.println("UserService is instantiated");
    }

    public String login(User user) {
        for(User savedUser :userList) {
            if (savedUser.name.equals(user.name) && savedUser.password.equals(user.password)) {
                String token = genToken();
                tokenToId.put(token, savedUser.id);
                return token;
            }
        }
        throw new MyException(RespCode.FAIL, "用户名或密码错误");
    }

    public User findUserById(int id) {
        for (User user: userList) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }

    public User findUserByName(String name) {
        for (User user: userList) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void createUser(String name, String password) {
        int id = genId();
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        userList.add(user);
    }

    public int register(User user) {
        for (User existedUser: userList) {
            if (user.name.equals(existedUser.name)) {
                throw new MyException(RespCode.FAIL, "用户名重复");
            }
        }
        userList.add(user);
        return 0;
    }

    public boolean checkToken(String value) {
        if (tokenToId.containsKey(value)) {
            return true;
        }
        return false;
    }

}
