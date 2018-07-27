package com.zx.shark.controller;

import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;
import sun.security.rsa.RSASignature;

public class Main {
    public static void main(String[] args) {
        String pass = "101544";
        String md5Password = DigestUtils.md5DigestAsHex(pass.getBytes());
        System.out.println(pass+"经过md5加密之后:"+md5Password);
    }
}
