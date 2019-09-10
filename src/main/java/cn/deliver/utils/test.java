package cn.deliver.utils;

import org.springframework.util.DigestUtils;

public class test {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("17666502552kang123456".getBytes()));
        System.out.println(DigestUtils.md5DigestAsHex("17666502552kang123456".getBytes()));
    }

}
