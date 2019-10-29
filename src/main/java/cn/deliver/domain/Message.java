package cn.deliver.domain;

import java.sql.Timestamp;

/**
 * @author Ming
 * @date 2019/10/16 - 22:46
 */
public class Message {

    /**
     * @param status 1表示已读，0表示未读
     * @param type 表示信息的类型  1表示文本，2表示为图片
     */
    private Integer id;
    private Integer from;
    private String from_name;
    private Integer to;
    private String to_name;
    private String message;
    private int type;
    private Timestamp date;
    private Integer status;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", from=" + from +
                ", from_name='" + from_name + '\'' +
                ", to=" + to +
                ", to_name='" + to_name + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    public Message(Integer id, Integer from, Integer to, String message, int type, Timestamp date, Integer status) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.type = type;
        this.date = date;
        this.status = status;
    }

    public Message(Integer id, Integer from, String from_name, Integer to, String to_name, String message, int type, Timestamp date, Integer status) {
        this.id = id;
        this.from = from;
        this.from_name = from_name;
        this.to = to;
        this.to_name = to_name;
        this.message = message;
        this.type = type;
        this.date = date;
        this.status = status;
    }

    public Message(Integer from, Integer to, String message, Timestamp date) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.date = date;
    }

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
