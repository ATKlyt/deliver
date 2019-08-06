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
     * @return
     */
    int driverInfoRegister(DriverInfo driverInfo);
}