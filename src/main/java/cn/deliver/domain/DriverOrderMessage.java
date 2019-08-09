package cn.deliver.domain;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class DriverOrderMessage {
    private Integer driverOrderId;
    private String authId;
    private String name;
    private String phone;
    private String carNumber;
    private String carPicture;
    private Timestamp goOff;
    private List<Area> areas;

    public Integer getDriverOrderId() {
        return driverOrderId;
    }

    public void setDriverOrderId(Integer driverOrderId) {
        this.driverOrderId = driverOrderId;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Timestamp getGoOff() {
        return goOff;
    }

    public void setGoOff(Timestamp goOff) {
        this.goOff = goOff;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
