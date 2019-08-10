package cn.deliver.utils;

import java.sql.Timestamp;

public class TimeUtil {

    /**
     * 获取当前时间
     * @return
     */
    public static Timestamp getNowTime(){
        return new Timestamp(System.currentTimeMillis());
    }
}
