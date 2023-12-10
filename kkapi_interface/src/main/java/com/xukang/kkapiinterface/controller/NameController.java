package com.xukang.kkapiinterface.controller;

import cn.hutool.json.JSONUtil;

import com.xukang.kkapiclientsdk.moodel.User;
import com.xukang.kkapiclientsdk.utils.SignUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getName(String name) {
        return "返回的接口名：" + name;
    }

    @PostMapping("/")
    public String postName(@RequestParam String name) {
        return "返回的接口名：" + name;
    }

    @PostMapping("/user")
    public String getName(@RequestBody User user, HttpServletRequest request) {
        String accessKye = request.getHeader("accessKye");
        // todo 这里accessKye需要从数据库中获取
        if (!"xukang".equals(accessKye)) {
            throw new RuntimeException("无权限");
        }
        String timeStamp = request.getHeader("timeStamp");
        Long requestTime = Long.parseLong(timeStamp);
        Long illTime = Long.parseLong(timeStamp) + 5 * 60;
        // 超过五分钟 直接拦截
        if (requestTime > illTime) {
            throw new RuntimeException("无权限");
        }
        // todo 这里每次调用的时候存放到redis中每次请求都不同 防止重放
        String nonce = request.getHeader("nonce");
        if (nonce.length() > 7) {
            throw new RuntimeException("无权限");
        }
        // todo 这里的secretKey 应该从数据库中获取
        String sign = request.getHeader("sign");
        String userJson = JSONUtil.toJsonStr(user);
        String json = userJson;
        String xukang = SignUtils.getSign(json, "xukang");
        if (!xukang.equals(sign)) {
            throw new RuntimeException("无权限");
        }
        return "返回的接口名：" + user.getName();
    }

}
