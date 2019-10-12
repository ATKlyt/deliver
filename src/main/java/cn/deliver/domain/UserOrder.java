package cn.deliver.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserOrder {
    private Integer id;

    private Integer uid;

    private String description;

    private BigDecimal pay;

    private Timestamp deliveryStart;

    private Integer consigneeAreaId;

    private Integer deliverAreaId;

    /**
     * 0/1/2/3/4/5  --->
     * 用户订单等待被接
     * 用户订单已被司机接单，达成交易关系/
     * 用户已被司机接单，等待担保人确认/
     * 用户订单被用户取消/
     * 用户订单过期/
     * 用户订单已完成
     */
    private String status;

    private Timestamp deliveryEnd;

    private String goodsPicture1;

    private String goodsPicture2;

    private String goodsPicture3;

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

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


    public Timestamp getDeliveryStart() {
        return deliveryStart;
    }

    public void setDeliveryStart(Timestamp deliveryStart) {
        this.deliveryStart = deliveryStart;
    }

    public Integer getConsigneeAreaId() {
        return consigneeAreaId;
    }

    public void setConsigneeAreaId(Integer consigneeAreaId) {
        this.consigneeAreaId = consigneeAreaId;
    }

    public Integer getDeliverAreaId() {
        return deliverAreaId;
    }

    public void setDeliverAreaId(Integer deliverAreaId) {
        this.deliverAreaId = deliverAreaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getDeliveryEnd() {
        return deliveryEnd;
    }

    public void setDeliveryEnd(Timestamp deliveryEnd) {
        this.deliveryEnd = deliveryEnd;
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
}