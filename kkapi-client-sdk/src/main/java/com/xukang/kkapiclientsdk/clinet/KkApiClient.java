package com.xukang.kkapiclientsdk.clinet;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xukang.kkapiclientsdk.moodel.User;

import java.util.HashMap;
import java.util.Random;

import static com.xukang.kkapiclientsdk.utils.SignUtils.getSign;


public class KkApiClient {
    private String accessKye;
    private String secretKey;

    public KkApiClient(String accessKye, String secretKey) {
        this.accessKye = accessKye;
        this.secretKey = secretKey;
    }


    /**
     * 请求头的设置
     *
     * @return
     */
    public HashMap<String, String> getHeader(String signStr) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKye", accessKye);
        // 4位随机数
        Random random = new Random();
        // todo 这里还需要调用者的用户信息 存入到redis中用于方式重放
        int nonce = random.nextInt(9000) + 1000;
        hashMap.put("nonce", String.valueOf(nonce));
        // 时间戳
        long timeStamp = System.currentTimeMillis() / 1000;
        hashMap.put("timeStamp", String.valueOf(timeStamp));
        hashMap.put("sign", getSign(signStr, secretKey));
        return hashMap;
    }


    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get("http://localhost:8200/api/name/", paramMap);
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post("http://localhost:8200/api/name/", paramMap);
        return result;
    }

    public String getNameByRest(User user) {
        String userJson = JSONUtil.toJsonStr(user);
        String json = userJson;
        String url = "http://localhost:8200/api/name/user";
        HashMap<String, String> header = getHeader(json);
        String result = HttpRequest.post(url)
                .addHeaders(header)
                .body(json)
                .execute().body();
        return result;
    }
}
