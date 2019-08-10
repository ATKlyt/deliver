package cn.deliver.domain;

/**
 * @author Ming
 * @date 2019/8/2 - 18:28
 */
public class UserDriverInfo {
    private User user ;
    private UserInfo userInfo;
    private DriverInfo driverInfo;

    public UserDriverInfo() {
    }

    public UserDriverInfo(User user, DriverInfo driverInfo) {
        this.user = user;
        this.driverInfo = driverInfo;
    }

    public UserDriverInfo( User user , UserInfo userInfo) {
        this.user = user;
        this.userInfo = userInfo;
    }

    public UserDriverInfo( User user, UserInfo userInfo, DriverInfo driverInfo) {
        this.user = user;
        this.userInfo = userInfo;
        this.driverInfo = driverInfo;
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public DriverInfo getDriverInfo() {
        return driverInfo;
    }
    public void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }


}