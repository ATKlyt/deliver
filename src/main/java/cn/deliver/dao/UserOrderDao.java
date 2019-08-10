package cn.deliver.dao;

import cn.deliver.domain.UserOrder;
import cn.deliver.domain.UserOrderExample;
import cn.deliver.domain.UserOrderMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrderDao {
    long countByExample(UserOrderExample example);

    int deleteByExample(UserOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    List<UserOrder> selectByExample(UserOrderExample example);

    UserOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserOrder record, @Param("example") UserOrderExample example);

    int updateByExample(@Param("record") UserOrder record, @Param("example") UserOrderExample example);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKey(UserOrder record);

    List<UserOrderMessage> findNearByVillage(String village);

//    UserOrderMessage findDetailByUserOrderId(Integer userOrderId);
}