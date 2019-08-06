package cn.deliver.domain;

import java.util.Date;

public class Order {
    private Integer id;

    private String status;

    private Integer userOrderId;

    private Integer driverUid;

    private Integer suretyId;

    private String no;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Integer userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Integer getDriverUid() {
        return driverUid;
    }

    public void setDriverUid(Integer driverUid) {
        this.driverUid = driverUid;
    }

    public Integer getSuretyId() {
        return suretyId;
    }

    public void setSuretyId(Integer suretyId) {
        this.suretyId = suretyId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}