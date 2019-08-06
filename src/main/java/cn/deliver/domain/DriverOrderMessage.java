package cn.deliver.domain;


import java.sql.Date;

public class DriverOrderMessage {
    private String phone;
    private String name;
    private Date birthday;
    private String carNumber;
    private String carPicture;
    private Integer authId;
    private Area area;
    private DriverOrder driverOrder;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public DriverOrder getDriverOrder() {
        return driverOrder;
    }

    public void setDriverOrder(DriverOrder driverOrder) {
        this.driverOrder = driverOrder;
    }
}
