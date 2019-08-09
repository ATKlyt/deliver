package cn.deliver.dao;

import cn.deliver.domain.DriverOrder;
import cn.deliver.domain.DriverOrderExample;
import cn.deliver.domain.DriverOrderMessage;
import cn.deliver.domain.Result;
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
     * 查询司机订单出发地址与用户订单发货地址相同的司机行程(村子相同)，司机已接订单小于5，该司机订单状态为不过期
     * 司机行程id/姓名/车牌/出发地/出发时间
     * @param village 用户订单发货地址所在村
     * @return
     */
    List<DriverOrderMessage> findNearbyDriverOrder(String village);

    /**
     * 查找司机行程具体信息
     * @param driverOrderId
     * @return
     */
    DriverOrderMessage findDetailByUserOrder(Integer driverOrderId);
}