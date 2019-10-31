package cn.deliver.domain;


import java.sql.Timestamp;
import java.util.List;

public class DriverRouteRelated {
    private Integer driverRouteId;
    private Integer driverUid;
    private String driverName;
    private String driverPhone;
    private String carNumber;
    private String carPicture;
    private Timestamp departTime;
    private Timestamp createTime;
    private Integer capacity;
    private List<Area> areas;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture;
    }

    public Timestamp getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Timestamp departTime) {
        this.departTime = departTime;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
