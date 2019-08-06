package cn.deliver.dao;

import cn.deliver.domain.DriverOrder;
import cn.deliver.domain.DriverOrderExample;
import cn.deliver.domain.DriverOrderMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverOrderDao {
    long countByExample(DriverOrderExample example);

    int deleteByExample(DriverOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DriverOrder record);

    int insertSelective(DriverOrder record);

    List<DriverOrder> selectByExample(DriverOrderExample example);

    DriverOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DriverOrder record, @Param("example") DriverOrderExample example);

    int updateByExample(@Param("record") DriverOrder record, @Param("example") DriverOrderExample example);

    int updateByPrimaryKeySelective(DriverOrder record);

    int updateByPrimaryKey(DriverOrder record);

    /**
     * 查找用户所属区域=司机发单的出发地所在区，且司机已接订单数量小于5，司机发单的状态为1则不过期
     * @param district
     * @return
     */
    List<DriverOrderMessage> findNearbyDriverOrder(String district);
}