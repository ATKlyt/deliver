package cn.deliver.domain;

import java.sql.Timestamp;

public class Order {
    private Integer id;

    /**
     * 订单状态
     * 1/2/3/4/5/6  ---->
     * 用户订单等待被邀请司机确认
     * 被邀请司机已确认,交易达成
     * 被邀请司机已确认，等待担保人确认
     * 被邀请司机和担保人均确认等待，交易达成
     * 司机取消
     * 用户取消
     */
    private String status;

    private Integer userOrderId;

    private Integer driverUid;

    private Integer suretyId;

    private String no;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer driverOrderId;

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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getDriverOrderId() {
        return driverOrderId;
    }

    public void setDriverOrderId(Integer driverOrderId) {
        this.driverOrderId = driverOrderId;
    }
}