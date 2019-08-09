package cn.deliver.dao;

import cn.deliver.domain.Address;
import cn.deliver.domain.AddressExample;
import cn.deliver.domain.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDao {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<Address> selectByPid(Integer pid);

}