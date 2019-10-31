package cn.deliver.domain;

public class Invitation {
    private Integer id;

    private Integer userOrderId;

    private Integer driverRouteId;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }



}