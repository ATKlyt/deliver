package cn.deliver.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class UserOrder {
    private Integer id;

    private Integer uid;

    private String description;

    private BigDecimal pay;

    private Timestamp deliveryStartTime;

    private Timestamp deliveryEndTime;

    private Integer deliverAreaId;

    private Integer consigneeAreaId;

    /**0
     *      * 订单状态
     *      * 等待担保人确认 0
     *      * 等待被司机接受 1
     *      * //司机主动接单
     *      * //用户邀请司机接单，等待司机回应
     *      * //司机同意邀请，确认接单
     *      * 货物开始配送状态
     *      * 收货人确认收货，订单完成
     *      * 用户取消订单
     *      * 司机取消订单
     *      * 用户发布订单失败(担保人拒绝担保) 2
     *      * 删除订单
     */
    private String status;

    private String goodsPicture1;

    private String goodsPicture2;

    private String goodsPicture3;

    private String userOrderNumber;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer suretyId;

    private Integer driverUid;

    private Integer driverRouteId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }


    public Integer getDeliverAreaId() {
        return deliverAreaId;
    }

    public void setDeliverAreaId(Integer deliverAreaId) {
        this.deliverAreaId = deliverAreaId;
    }

    public Integer getConsigneeAreaId() {
        return consigneeAreaId;
    }

    public void setConsigneeAreaId(Integer consigneeAreaId) {
        this.consigneeAreaId = consigneeAreaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getGoodsPicture1() {
        return goodsPicture1;
    }

    public void setGoodsPicture1(String goodsPicture1) {
        this.goodsPicture1 = goodsPicture1 == null ? null : goodsPicture1.trim();
    }

    public String getGoodsPicture2() {
        return goodsPicture2;
    }

    public void setGoodsPicture2(String goodsPicture2) {
        this.goodsPicture2 = goodsPicture2 == null ? null : goodsPicture2.trim();
    }

    public String getGoodsPicture3() {
        return goodsPicture3;
    }

    public void setGoodsPicture3(String goodsPicture3) {
        this.goodsPicture3 = goodsPicture3 == null ? null : goodsPicture3.trim();
    }

    public String getUserOrderNumber() {
        return userOrderNumber;
    }

    public void setUserOrderNumber(String userOrderNumber) {
        this.userOrderNumber = userOrderNumber == null ? null : userOrderNumber.trim();
    }

    public Timestamp getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(Timestamp deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public Timestamp getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(Timestamp deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSuretyId() {
        return suretyId;
    }

    public void setSuretyId(Integer suretyId) {
        this.suretyId = suretyId;
    }

    public Integer getDriverUid() {
        return driverUid;
    }

    public void setDriverUid(Integer driverUid) {
        this.driverUid = driverUid;
    }

    public Integer getDriverRouteId() {
        return driverRouteId;
    }

    public void setDriverRouteId(Integer driverRouteId) {
        this.driverRouteId = driverRouteId;
    }
}