package cn.deliver.dao;

import cn.deliver.domain.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserOrderDao {

    int deleteByPrimaryKey(Integer id);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    UserOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKey(UserOrder record);

    int updateStatusByType(@Param("userOrderId") Integer userOrderId,
                           @Param("updateTime") Timestamp updateTime,
                           @Param("type") String type);

    List<UserOrder> findAllByUid(Integer userId);

    List<UserOrder> findBySuretyIdAndStatus(Integer suretyId);

    List<UserOrder> findByCid(Integer cid);


    List<UserOrder> findNearByArea(@Param("city") String city,
                                        @Param("district") String district,
                                        @Param("town") String town,
                                        @Param("village") String village);


    List<UserOrder> findInvitationByDriverUid(Integer driverUid);
}