package com.xukang.kkapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 加密工具类
 */
public class SignUtils {

    /**
     * 获取摘要加密
     *
     * @param signStr 加密参数
     * @return
     */
    public static String getSign(String signStr, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA384);
        String sign = md5.digestHex(signStr + "." + secretKey);
        return sign;
    }

}
