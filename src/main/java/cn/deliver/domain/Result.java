package cn.deliver.domain;
/**
 * @author ATK
 */
public class Result {
    private String msg;
    private String code;
    private Object data;

    public Result() {
    }

    public Result(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }


    public Result(String msg, String code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
