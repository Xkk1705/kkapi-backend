package com.xukang.kkapiinterface;


import com.xukang.kkapiclientsdk.clinet.KkApiClient;
import com.xukang.kkapiclientsdk.moodel.User;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setName("restful");
        KkApiClient kkApiClient = new KkApiClient("xukang","xukang");
        String result1 = kkApiClient.getNameByGet("get");
        String result2 = kkApiClient.getNameByPost("post");
        String result3 = kkApiClient.getNameByRest(user);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
