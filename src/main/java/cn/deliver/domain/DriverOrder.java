package cn.deliver.domain;

import java.sql.Timestamp;

public class DriverOrder {
    private Integer id;

    private Integer uid;

    private Integer originalId;

    private Integer destinationId;

    private Integer carry;

    private Timestamp goOff;

    private Timestamp deadline;

    /**
     * (0过期,1未过期,2取消状态,3删除状态)
     * 过期状态：当前时间超过出发时间
     */
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Integer originalId) {
        this.originalId = originalId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getCarry() {
        return carry;
    }

    public void setCarry(Integer carry) {
        this.carry = carry;
    }

    public Timestamp getGoOff() {
        return goOff;
    }

    public void setGoOff(Timestamp goOff) {
        this.goOff = goOff;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}