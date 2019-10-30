package cn.deliver.service.impl;

import cn.deliver.dao.*;
import cn.deliver.domain.*;
import cn.deliver.service.OrderService;
import cn.deliver.utils.SnowflakeIdWorker;
import cn.deliver.utils.TimeUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserOrderDao userOrderDao;
    @Autowired
    DriverInfoDao driverInfoDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserInfoDao userInfoDao;
    /**
     * 成功状态
     */
    private static final String SUCCESS = "0";
    /**
     * 错误状态
     */
    private static final String ERROR = "1";
    /**
     * userOrder的状态，代表用户订单等待被接
     */
    private final String WAIT_DRIVER_STATUS = "0";
    /**
     * 错误状态:司机已接订单超出规定(即5)
     */
    private static final String OVER_ERROR = "-1";
    /**
     * 错误状态:用户订单已经被其他司机接受
     */
    private static final String RECEIVED_ERROR = "1";
    /**
     * 错误状态:用户订单已经被其他司机接，等待担保人确定
     */
    private static final String RECEIVED_NEED_SURETY_ERROR = "2";
    /**
     * 错误状态:用户取消
     */
    private static final String USER_CANCEL_ERROR = "3";
    /**
     * 错误状态:过期
     */
    private static final String PAST_DUE_ERROR = "4";
    /**
     * 错误状态:用户订单已被完成
     */
    private static final String USER_ORDER_FINISH_ERROR = "5";
    private static final Integer MAX_NUMBER = 5;


    @Override
    public Result addOrderSelective(Order order) {
        String msg = validateUserOrderStatus(order.getUserOrderId());
        if (WAIT_DRIVER_STATUS.equals(msg)){
            if (orderDao.insertSelective(order) > 0) {
                return new Result("邀请成功，等待司机回应", SUCCESS);
            } else {
                return new Result("邀请失败", ERROR);
            }
        }else {
            return new Result(msg, ERROR);
        }
    }

    @Override
    public Result findWaitOrder(Integer driverUid) {
        //获取该司机所有等待状态的order（等待司机接受,如果被其他司机接受了，则不再显示）且不过期
        //等待司机接受邀请---->status = "1"
        //邀请不过期----->现在的时间不超过更新时间2小时
        List<Order> orders = orderDao.findWaitOrder(driverUid);
        return new Result("查询成功", SUCCESS, orders);
    }

    public Map<String, String> findConsigneeAndDeliver(Integer userOrderId){
        Map<String, String> areas = new HashedMap(16);
        //收货人所在村(用户订单收货地址---->联系人cid---->所属地区)
        Area consigneeArea = areaDao.selectByPrimaryKey(userOrderDao.selectByPrimaryKey(userOrderId).getConsigneeAreaId());
        Integer consigneeCid = consigneeArea.getCid();
        String consigneeVillage = areaDao.findAreaByUidAndStatus(consigneeCid, "1").getVillage();
        areas.put("consigneeVillage", consigneeVillage);
        //发货人所在村
        Integer deliverUid = userOrderDao.selectByPrimaryKey(userOrderId).getUid();
        String deliverVillage = areaDao.findAreaByUidAndStatus(deliverUid, "1").getVillage();
        areas.put("deliverVillage", deliverVillage);
        return areas;
    }



    /**
     * 判断是否需要担保人
     * 只判断了村名字是否相同
     * @param userOrderId
     * @param driverUid
     * @return
     */
    @Transactional
    @Override
    public Result validateNeedSafety(Integer userOrderId, Integer driverUid) {
        //司机所在村
        String driverVillage = areaDao.findAreaByUidAndStatus(driverUid, "1").getVillage();

        Map<String, String> areas = findConsigneeAndDeliver(userOrderId);
        if (driverVillage.equals(areas.get("consigneeVillage")) || driverVillage.equals(areas.get("deliverVillage"))) {
            return new Result("不需要担保人", SUCCESS);
        } else {
            return new Result("需要担保人", SUCCESS);
        }
    }

    /**
     * 验证担保人是否符合规则
     * @param suretyAuthId
     * @param userOrderId
     * @return
     */
    @Override
    public Result validateSafety(String suretyAuthId, Integer userOrderId){
        User suretyUser = userDao.findByAuthId(suretyAuthId);
        if (suretyUser != null){
            //担保人所在村
            Integer suretyId = suretyUser.getId();
            String suretyVillage = areaDao.findAreaByUidAndStatus(suretyId, "1").getVillage();
            UserInfo suretyUserInfo = userInfoDao.findByUid(suretyId);
            //存储担保人电话及姓名
            Map<String, Object> map = new HashMap<>(16);
            //联系人电话
            map.put("suretyId", suretyUser.getId());
            //联系人电话
            map.put("phone", suretyUser.getPhone());
            //联系人名字
            map.put("name", suretyUserInfo.getName());
            //获取发货人和收货人的所属地址
            Map<String, String> areas = findConsigneeAndDeliver(userOrderId);

            if (suretyVillage.equals(areas.get("consigneeVillage")) || suretyVillage.equals(areas.get("deliverVillage"))) {
                return new Result("该担保人符合要求", SUCCESS, map);
            } else {
                return new Result("该担保人不合要求", ERROR, map);
            }
        }else {
            return new Result("该用户不存在", ERROR);
        }

    }

    /**
     * 被邀请司机接单错误信息
     * @param userOrderStatus
     * @return
     */
    public Map orderErrorType(String userOrderStatus){
        Map<String, String> data = new HashedMap(16);
        if (USER_CANCEL_ERROR.equals(userOrderStatus)){
            //用户订单已被取消
            data.put("errorMsg", "用户订单已被取消");
            data.put("orderStatus", "3");
        }else if (PAST_DUE_ERROR.equals(userOrderStatus)){
            //用户订单已过期
            data.put("errorMsg", "用户订单已过期");
            data.put("orderStatus", "7");
        }else {
            //用户订单已被其他司机完成
            //用户订单已经被其他司机接受(需要担保人/不需要担保人)
            data.put("errorMsg", "用户订单已经被其他司机接受");
            data.put("orderStatus", "5");
        }
        return data;
    }



    /**
     * 司机接单错误信息
     * @param userOrderStatus
     * @return
     */
    public String userOrderErrorType(String userOrderStatus){
        if (RECEIVED_ERROR.equals(userOrderStatus)){
            return "用户订单已经被其他司机接受";
        }else if (RECEIVED_NEED_SURETY_ERROR.equals(userOrderStatus)){
            return "用户订单已经被其他司机接受";
        }else if (USER_CANCEL_ERROR.equals(userOrderStatus)){
            return "用户订单已被取消";
        }else if (PAST_DUE_ERROR.equals(userOrderStatus)){
            return "用户订单已过期";
        }else {
            return "用户订单已被完成";
        }
    }



    /**
     * 检查司机此时的已接订单数量
     * 如果司机此时已接订单数量已等于5，则返回ERROR
     * 如果司机此时已接订单数量小于5，则返回SUCCESS
     *
     * @param driverUid
     * @return
     */
    public synchronized String validateNumber(Integer driverUid) {
        DriverInfo driverInfo = driverInfoDao.selectByPrimaryKey(driverUid);
        int orderNumber = driverInfo.getOrderNumber();
        if (orderNumber < MAX_NUMBER) {
            return SUCCESS;
        } else {
            return OVER_ERROR;
        }
    }


    /**
     * 更新司机此时的已接订单数量
     * 更新成功返回SUCCESS，更新失败返回ERROR
     *
     * @param driverUid
     * @return
     */
    public String updateNumber(Integer driverUid) {
        if (SUCCESS.equals(validateNumber(driverUid))) {
            DriverInfo driverInfo = driverInfoDao.selectByPrimaryKey(driverUid);
            int orderNumber = driverInfo.getOrderNumber();
            //更新此时司机订单数量----> +1
            driverInfo.setOrderNumber(orderNumber + 1);
            if (driverInfoDao.updateByPrimaryKey(driverInfo) > 0) {
                //返回
                return SUCCESS;
            } else {
                return ERROR;
            }
        } else {
            //司机此时已接订单数量已等于5
            return OVER_ERROR;
        }

    }

    /**
     * 检查用户订单(即userOrder)状态
     *
     * @param userOrderId
     * @return
     */
    public String validateUserOrderStatus(Integer userOrderId) {
        UserOrder userOrder = userOrderDao.selectByPrimaryKey(userOrderId);
        String userOrderStatus = userOrder.getStatus();

        if (WAIT_DRIVER_STATUS.equals(userOrderStatus)){
            return SUCCESS;
        }else {
            return userOrderErrorType(userOrderStatus);
        }
    }


    /**
     * 更新用户订单(即userOrder)状态
     *
     * @param userOrderId
     * @param status
     * @return
     */
    public String updateUserOrderStatus(Integer userOrderId, String status, String type) {
        String userOrderStatus = validateUserOrderStatus(userOrderId);
        if (WAIT_DRIVER_STATUS.equals(userOrderStatus)){
            UserOrder userOrder = userOrderDao.selectByPrimaryKey(userOrderId);
            //将该用户订单标记为status
            userOrder.setStatus(status);
            //跟新userOrder的status
            if (userOrderDao.updateByPrimaryKeySelective(userOrder) > 0) {
                //返回
                return SUCCESS;
            } else {
                //更新失败
                return ERROR;
            }
        }else {
            if (type == null){
                //司机主动接单
                return userOrderErrorType(userOrderStatus);
            }else {
                //被邀请司机接单
                return (String) orderErrorType(userOrderStatus).get("errorMsg");
            }
        }

    }

    /**
     * 同时修改
     *
     * @param userOrderId
     * @param driverUid
     * @return
     */
    public synchronized String updateUserOrderAndNumber(Integer userOrderId, Integer driverUid, String status, String type) {
        String updateResult = updateUserOrderStatus(userOrderId, status, type);
        if (SUCCESS.equals(updateResult)) {
            if (SUCCESS.equals(updateNumber(driverUid))) {
                return SUCCESS;
            } else {
                //手动回滚 或者 throw RuntimeException
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return OVER_ERROR;
            }
        } else {
            //返回错误信息
            return updateResult;
        }
    }


    /**
     * 更新order的状态(status)和担保人id(即suretyId)并更新数据
     *
     * @param order
     * @param status   状态
     * @param suretyId 担保人id
     * @return
     */
    public String updateOrderStatusAndSuretyId(Order order, String status, Integer suretyId) {
        //封装担保人Id
        if (suretyId != null) {
            order.setSuretyId(suretyId);
        }
        //将该订单标记为status,即该订单已被司机接单，等待担保人确认
        order.setStatus(status);
        order.setUpdateTime(TimeUtil.getNowTime());
        if (orderDao.updateByPrimaryKeySelective(order) > 0) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }


    /**
     * 司机主动接单
     * 此时数据库里order表里还没有该条order数据
     *
     * @param order
     * @return
     */
    @Override
    public Result receiveUserOrder(Order order) {
        try {
            //判断的是userOrder的状态，而不是order的状态
            Integer userOrderId = order.getUserOrderId();
            if (SUCCESS.equals(validateUserOrderStatus(userOrderId))) {
                //判断司机订单数量。
                Integer driverUid = order.getDriverUid();
                String numberResult = validateNumber(driverUid);
                String updateResult = null;
                if (SUCCESS.equals(numberResult)) {
                    final String NOT_REQUIRED = "不需要担保人";
                    //再次进行对是否需要担保人的验证
                    Result result = validateNeedSafety(userOrderId, driverUid);
                    String msg = result.getMsg();
                    //获取担保人id，可能为null
                    Integer suretyId = order.getSuretyId();
                    //不需要担保人
                    if (NOT_REQUIRED.equals(msg)) {
                        if (suretyId == null) {
                            /*
                             * 即不需要担保人，即该用户订单(即userOrder)状态为1--->司机已确认，交易达成
                             * 更新司机此时已接订单数量--->+1
                             */
                            updateResult = updateUserOrderAndNumber(userOrderId, driverUid, "1", null);
                            if (SUCCESS.equals(updateResult)) {
                                //即不需要担保人，即该订单(即order)状态为1--->司机已确认，交易达成
                                order.setStatus("1");
                                order.setNo(String.valueOf(SnowflakeIdWorker.getNo()));
                                order.setCreateTime(new Timestamp(System.currentTimeMillis()));
                                order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                                orderDao.insertSelective(order);
                                return new Result("接单成功", SUCCESS);
                            } else if (OVER_ERROR.equals(updateResult)) {
                                return new Result("接单失败，请检查司机已接订单数量", ERROR);
                            } else {
                                return new Result(updateResult, ERROR);
                            }
                        } else {
                            return new Result("错误信息，无需填写担保人", ERROR);
                        }
                    } else {
                        if (suretyId != null) {

                            /*
                             * 即需要担保人，即该用户订单(即userOrder)状态为2--->司机已确认，等待担保人确认
                             * 更新司机此时已接订单数量--->+1
                             * 同时成功才进行进行order插入
                             */
                            updateResult = updateUserOrderAndNumber(userOrderId, driverUid, "2", null);
                            if (SUCCESS.equals(updateResult)) {
                                //即需要担保人，即该订单(即order)状态为2--->司机已确认，等待担保人确认
                                order.setStatus("2");
                                order.setNo(String.valueOf(SnowflakeIdWorker.getNo()));
                                order.setCreateTime(new Timestamp(System.currentTimeMillis()));
                                order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                                orderDao.insertSelective(order);
                                return new Result("接单成功，等待担保人确认", SUCCESS);
                            } else if (OVER_ERROR.equals(updateResult)) {
                                return new Result("接单失败，请检查司机已接订单数量", ERROR);
                            } else {
                                return new Result(updateResult, ERROR);
                            }
                        } else {
                            return new Result("错误信息，请填写担保人", ERROR);
                        }
                    }
                } else {
                    return new Result("当前司机已接接单数量为5", ERROR);
                }
            } else {
                //该用户订单已被其他司机接单
                return new Result("该用户订单已被其他司机接受", ERROR);
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new Result("接单失败",ERROR);
        }
    }


    /**
     * 司机接受用户邀请
     * 此时数据库里order表里已有该条order数据
     *
     * @param orderId
     * @param suretyAuthId
     * @return
     */
    @Override
    public Result driverConfirmOrder(Integer orderId, String suretyAuthId) {
        Order order = orderDao.selectByPrimaryKey(orderId);
        Integer driverUid = order.getDriverUid();
        Integer userOrderId = order.getUserOrderId();
        //判断司机订单数量
        if (SUCCESS.equals(validateNumber(driverUid))) {
            //判断当前该订单(即order)的用户订单(即userOrder)的状态
            String userOrderStatus = validateUserOrderStatus(userOrderId);
            if (SUCCESS.equals(userOrderStatus)) {
                //可以接单
                final String NOT_REQUIRED = "不需要担保人";
                //再次进行对是否需要担保人的验证
                Result result = validateNeedSafety(userOrderId, driverUid);
                String msg = result.getMsg();
                //不需要担保人
                if (NOT_REQUIRED.equals(msg)) {
                    if (suretyAuthId == null) {
                        String updateResult = updateUserOrderAndNumber(userOrderId, driverUid, "1", "invite");
                        if (SUCCESS.equals(updateResult)) {
                            order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                            order.setStatus("1");
                            orderDao.updateByPrimaryKeySelective(order);
                            return new Result("接单成功", SUCCESS);
                        } else if (OVER_ERROR.equals(updateResult)) {
                            return new Result("接单失败，请检查司机已接订单数量", ERROR);
                        } else {
                            return new Result(updateResult, ERROR);
                        }
                    } else {
                        return new Result("错误信息，无需填写担保人", ERROR);
                    }
                } else {
                    if (suretyAuthId != null) {
                        Integer suretyId = userDao.findByAuthId(suretyAuthId).getId();
                        String updateResult = updateUserOrderAndNumber(userOrderId, driverUid, "1", "invite");
                        if (SUCCESS.equals(updateResult)) {
                            order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                            order.setStatus("2");
                            order.setSuretyId(suretyId);
                            orderDao.updateByPrimaryKeySelective(order);
                            return new Result("接单成功", SUCCESS);
                        } else if (OVER_ERROR.equals(updateResult)) {
                            return new Result("接单失败，请检查司机已接订单数量", ERROR);
                        } else {
                            return new Result(updateResult, ERROR);
                        }
                    } else {
                        return new Result("错误信息，请填写担保人", ERROR);
                    }
                }
            } else {
                //将order状态重新标记，取决于userOrder此时的状态
                Map data = orderErrorType(userOrderStatus);
                String errorMsg = (String) data.get("errorMsg");
                String orderStatus = (String) data.get("orderStatus");
                updateOrderStatusAndSuretyId(order, orderStatus, null);
                return new Result(errorMsg, ERROR);
            }
        }else {
            return new Result("接单失败，当前司机接单数量为5", ERROR);
        }
    }

}
