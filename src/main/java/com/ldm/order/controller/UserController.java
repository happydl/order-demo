package com.ldm.order.controller;

import com.ldm.order.common.RespCode;
import com.ldm.order.common.ResultJSONObject;
import com.ldm.order.domain.User;
import com.ldm.order.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/greeting")
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }

    @PostMapping("/greeting")
    public String greeting(String name, HttpServletResponse response) {
//        ResponseCookie springCookie = ResponseCookie.from("user-id", "abcd")
//                .httpOnly(true)
//                .secure(true)
//                .path("/")
//                .maxAge(60)
//                .domain("localhost")
//                .build();
//        ResponseEntity.ok()
//                .header(HttpHeaders.SET_COOKIE, springCookie.toString())
//                .build();
        Cookie cookie = new Cookie("username", "Jovan");
        response.addCookie(cookie);
        return "12345";
    }

    @RequestMapping("/forward")
    public String forward() {
        return "login.html";
    }


//    public ResponseData login(@RequestBody String name, @RequestBody @RequestParam String password) {

    //    }
    @RequestMapping("/login")
    public ResultJSONObject login(@RequestBody @Validated User user,
//                                  @CookieValue(name = "token", defaultValue = "empty") String token,
                                  HttpServletResponse response) {
//        System.out.printf("name: %s, password: %s, cookie: %s ", user.name, user.password, token);

        // 尝试登录, res 为登录成功token
        String res = userService.login(user);

        Cookie cookie = new Cookie("token", res);
        response.addCookie(cookie);
        return ResultJSONObject.success(userService.findUserByName(user.name));

    }

    @RequestMapping("/register")
    public ResultJSONObject register(@RequestBody @Validated User user, HttpServletResponse response) {
        System.out.printf("name: %s, password: %s \\n", user.name, user.password);

        // 尝试登录
        int res = userService.register(user);

        if (res == -1) {
            return new ResultJSONObject<>(RespCode.FAIL, "用户名已存在");
        } else {
            // 设置cookie
            Cookie cookie = new Cookie("token", String.valueOf(res));
            response.addCookie(cookie);
            return ResultJSONObject.success(userService.findUserByName(user.name));
        }

//        ResultJsonObject result = null;

    }
}

