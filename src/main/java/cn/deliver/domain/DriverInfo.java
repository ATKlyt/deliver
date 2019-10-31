package cn.deliver.domain;

public class DriverInfo {
    private Integer id;

    private Integer uiid;

    private String carType;

    private String drivingLicencePicture;

    private String carPicture;

    private String runningRoute;

    private Integer orderNumber;

    private String carNumber;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUiid() {
        return uiid;
    }

    public void setUiid(Integer uiid) {
        this.uiid = uiid;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getDrivingLicencePicture() {
        return drivingLicencePicture;
    }

    public void setDrivingLicencePicture(String drivingLicencePicture) {
        this.drivingLicencePicture = drivingLicencePicture == null ? null : drivingLicencePicture.trim();
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture == null ? null : carPicture.trim();
    }

    public String getRunningRoute() {
        return runningRoute;
    }

    public void setRunningRoute(String runningRoute) {
        this.runningRoute = runningRoute == null ? null : runningRoute.trim();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public DriverInfo(Integer id, Integer uiid, String carType, String drivingLicencePicture, String carPicture, String runningRoute, Integer orderNumber, String carNumber, String status) {
        this.id = id;
        this.uiid = uiid;
        this.carType = carType;
        this.drivingLicencePicture = drivingLicencePicture;
        this.carPicture = carPicture;
        this.runningRoute = runningRoute;
        this.orderNumber = orderNumber;
        this.carNumber = carNumber;
        this.status = status;
    }

    public DriverInfo() {
    }

    @Override
    public String toString() {
        return "DriverInfo{" +
                "id=" + id +
                ", uiid=" + uiid +
                ", carType='" + carType + '\'' +
                ", drivingLicencePicture='" + drivingLicencePicture + '\'' +
                ", carPicture='" + carPicture + '\'' +
                ", runningRoute='" + runningRoute + '\'' +
                ", orderNumber=" + orderNumber +
                ", carNumber='" + carNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}