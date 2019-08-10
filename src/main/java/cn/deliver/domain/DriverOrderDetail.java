package cn.deliver.domain;

import java.util.List;

/**
 * 司机发布行程初始信息(包含出发地址和目的地址的详细信息)
 */
public class DriverOrderDetail {
    private DriverOrder driverOrder;
    private Area originalArea;
    private Area consigneeArea;

    public DriverOrder getDriverOrder() {
        return driverOrder;
    }

    public void setDriverOrder(DriverOrder driverOrder) {
        this.driverOrder = driverOrder;
    }

    public Area getOriginalArea() {
        return originalArea;
    }

    public void setOriginalArea(Area originalArea) {
        this.originalArea = originalArea;
    }

    public Area getConsigneeArea() {
        return consigneeArea;
    }

    public void setConsigneeArea(Area consigneeArea) {
        this.consigneeArea = consigneeArea;
    }
}
