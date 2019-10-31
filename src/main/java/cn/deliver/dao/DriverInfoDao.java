package cn.deliver.dao;

import cn.deliver.domain.DriverInfo;

public interface DriverInfoDao {

    int deleteByPrimaryKey(Integer id);

    int insert(DriverInfo record);

    int insertSelective(DriverInfo record);

    DriverInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DriverInfo record);

    int updateByPrimaryKey(DriverInfo record);

    /**
     * 司机注册时填写详细信息
     * @param driverInfo 司机详细信息
     * @author 康俊彬
     * @return
     */
    int driverInfoRegister(DriverInfo driverInfo);

    /**
     * 根据用户id获取UserInfo表信息
     * @param id 用户id
     * @return 对应UserInfo表信息
     */
    DriverInfo getDriverInfoById(Integer id);

    /**
     * 修改用户个人信息
     * @param driverInfo 存储用户个人信息的对象
     * @return 修改结果
     */
    int updateDriverInfo(DriverInfo driverInfo);
}