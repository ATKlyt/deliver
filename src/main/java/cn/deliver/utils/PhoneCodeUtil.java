package cn.deliver.utils;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 93231
 */
public class PhoneCodeUtil {

    private static PhoneCodeUtil phoneCodeUtil = new PhoneCodeUtil();

    /**
     * 短信API调用结果
     */
    private static final int SUCCESS = 200;
    private static final int REQUESTFORBIDDEN = 403;

    /**
     * 设置为private避免外界直接调用其构造器
     */
    private PhoneCodeUtil(){}

    /**
     * 单例模式
     * @return 自身实例化
     */
    public static PhoneCodeUtil getPhoneCodeUtil(){
        return phoneCodeUtil;
    }

    /**
     * 发送手机验证码
     * @param phoneNumber 手机号码
     * @param code 随机生成的6位数验证码
     */
    public boolean sendPhoneCode(String phoneNumber,String code){
        String host = "https://chanyoo.market.alicloudapi.com";
        String path = "/sendsms";
        String method = "GET";
        String appcode = "6a978b7e00904b9aa29412e1916c0399";
        Map<String, String> headers = new HashMap();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap();
        querys.put("mobile", phoneNumber);
        querys.put("content", "您的手机号："+ phoneNumber +"，验证码："+ code +"，请及时完成验证，如不是本人操作请忽略。【阿里云市场】");
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            if(response.getStatusLine().getStatusCode() == SUCCESS){
                return true;
            }else if(response.getStatusLine().getStatusCode() == REQUESTFORBIDDEN){
                //远程服务器拒绝处理请求(可能是短信使用条数已为零)
                return false;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取随机的n位数字组合
     * @param length 数字组合长度
     * @return
     */
    public String getRandomNum(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
