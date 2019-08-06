package cn.deliver.utils;

import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;

public class SessionUtil {

    private SessionUtil(){}

    public static void removeAttribute(final HttpSession session,final String attrName){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute(attrName);
                timer.cancel();
            }
        },60 * 1000);
    }
}
