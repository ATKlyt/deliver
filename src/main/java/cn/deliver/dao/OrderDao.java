package cn.deliver.dao;

import cn.deliver.domain.Order;
import cn.deliver.domain.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> findWaitOrder(Integer driverUid);

    int updateDriverConfirm(Integer orderId);
}