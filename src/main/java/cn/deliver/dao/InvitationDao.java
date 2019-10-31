package cn.deliver.dao;


import cn.deliver.domain.Invitation;
import org.apache.ibatis.annotations.Param;

public interface InvitationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Invitation record);

    int insertSelective(Invitation record);

    Invitation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Invitation record);

    int updateByPrimaryKey(Invitation record);

    /**
     * 取消司机行程时，将该司机行程的所有邀请状态修改为司机行程取消，邀请失败
     *
     * @param driverRouteId
     * @return
     */
    int updateStatusByDriverRouteId(Integer driverRouteId);

    /**
     * 司机接受邀请时，将邀请的用户订单的其他邀请状态修改为该用户订单已经被其他司机接受，邀请失败
     * 司机主动接单，将用户订单的所有邀请状态修改为该用户订单已经被其他司机接受，邀请失败
     *
     * @param userOrderId
     * @return
     */
    int updateStatusByUserOrderId(Integer userOrderId);

    /**
     * 司机接受邀请，将所接受的邀请状态修改为邀请成功
     *
     * @param driverRouteId
     * @param userOrderId
     * @return
     */
    int updateStatusByUserOrderIdAndDriverRouteId(@Param("driverRouteId") Integer driverRouteId, @Param("userOrderId") Integer userOrderId);
}