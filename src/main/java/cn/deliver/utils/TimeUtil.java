package cn.deliver.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;

public class TimeUtil {

    /**
     * 获取当前时间
     * @return
     */
    public static Timestamp getNowTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    }

}
