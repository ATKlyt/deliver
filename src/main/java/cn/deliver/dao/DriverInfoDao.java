package cn.deliver.dao;

import cn.deliver.domain.DriverInfo;
import cn.deliver.domain.DriverInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverInfoDao {
    long countByExample(DriverInfoExample example);

    int deleteByExample(DriverInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DriverInfo record);

    int insertSelective(DriverInfo record);

    List<DriverInfo> selectByExample(DriverInfoExample example);

    DriverInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DriverInfo record, @Param("example") DriverInfoExample example);

    int updateByExample(@Param("record") DriverInfo record, @Param("example") DriverInfoExample example);

    int updateByPrimaryKeySelective(DriverInfo record);

    int updateByPrimaryKey(DriverInfo record);

//==================================俊彬=====================================
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