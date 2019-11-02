package cn.deliver.domain;

import java.math.BigDecimal;

public class UserOrderBrief {

    private Integer userOrderId;
    private Integer driverRouteId;
    private String description;
    private BigDecimal pay;
    private String goodsPicture1;
    private String goodsPicture2;
    private String goodsPicture3;
    private String status;
    private UserRelated userRelated;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserRelated getUserRelated() {
        return userRelated;
    }

    public void setUserRelated(UserRelated userRelated) {
        this.userRelated = userRelated;
    }

    public Integer getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Integer userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Integer getDriverRouteId() {
        return driverRouteId;
    }

    public void setDriverRouteId(Integer driverRouteId) {
        this.driverRouteId = driverRouteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public String getGoodsPicture1() {
        return goodsPicture1;
    }

    public void setGoodsPicture1(String goodsPicture1) {
        this.goodsPicture1 = goodsPicture1;
    }

    public String getGoodsPicture2() {
        return goodsPicture2;
    }

    public void setGoodsPicture2(String goodsPicture2) {
        this.goodsPicture2 = goodsPicture2;
    }

    public String getGoodsPicture3() {
        return goodsPicture3;
    }

    public void setGoodsPicture3(String goodsPicture3) {
        this.goodsPicture3 = goodsPicture3;
    }
}
