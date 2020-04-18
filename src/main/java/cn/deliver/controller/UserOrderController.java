package cn.deliver.controller;

import cn.deliver.domain.Area;
import cn.deliver.domain.Result;
import cn.deliver.domain.UserOrder;
import cn.deliver.domain.UserRelated;
import cn.deliver.service.AreaService;
import cn.deliver.service.UserOrderService;
import cn.deliver.service.UserService;
import cn.deliver.utils.SnowflakeIdWorker;
import cn.deliver.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("userOrder")
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;
    @Autowired
    UserService userService;
    @Autowired
    AreaService areaService;

    /**
     * 用户发布订单
     * @param userOrder
     * @return
     */
    @RequestMapping("orderRelease")
    @ResponseBody
    public Result orderRelease(@RequestBody UserOrder userOrder){
        //设置创建时间和更新时间
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        userOrder.setCreateTime(nowTime);
        userOrder.setUpdateTime(nowTime);
        //生成订单编号
        userOrder.setUserOrderNumber(String.valueOf(new SnowflakeIdWorker(1, 1).nextId()));
        //将该用户订单设置为等待担保人确认
        userOrder.setStatus("0");
        return userOrderService.orderRelease(userOrder);
    }

    /**
     * 验证担保人是否符合要求
     * @param parameters
     * @return
     */
    @RequestMapping("validateSurety")
    @ResponseBody
    public Result validateSurety(@RequestBody Map<String, Object> parameters){
        Integer suretyId = (Integer) parameters.get("suretyId");
        Integer shipperId = (Integer) parameters.get("shipperId");
        return userOrderService.validateSurety(suretyId, shipperId);
    }

    /**
     * 司机接受用户订单
     * @param parameters
     * @return
     */
    @RequestMapping("orderReceive")
    @ResponseBody
    public Result orderReceive(@RequestBody Map<String, Object> parameters){
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        Integer driverUid = (Integer) parameters.get("driverUid");
        UserOrder userOrder = userOrderService.selectByPrimaryKey(userOrderId);
        userOrder.setDriverUid(driverUid);
        userOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        userOrder.setStatus("3");
        return userOrderService.orderReceive(userOrder);
    }

    /**
     * 担保人拒绝担保
     * @param parameters
     * @return
     */
    @RequestMapping("suretyRefuse")
    @ResponseBody
    public Result suretyRefuse(@RequestBody Map<String, Object> parameters){
        Integer suretyId = (Integer) parameters.get("suretyId");
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        return userOrderService.suretyRefuse(suretyId, userOrderId);
    }

    /**
     * 担保人确认担保
     * @param parameters
     * @return
     */
    @RequestMapping("suretyConfirm")
    @ResponseBody
    public Result suretyConfirm(@RequestBody Map<String, Object> parameters){
        Integer suretyId = (Integer) parameters.get("suretyId");
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        return userOrderService.suretyConfirm(suretyId, userOrderId);
    }

    /**
     * 查找用户发布的所有订单
     * 包括订单照片、描述、状态
     * @param parameters
     * @return
     */
    @RequestMapping("findAll")
    @ResponseBody
    public Result findAllByUid(@RequestBody Map<String, Object> parameters){
        Integer userId = (Integer) parameters.get("userId");
        Integer pageNumber = (Integer) parameters.get("pageNumber");
        if (pageNumber == null){
            pageNumber = 1;
        }
        return userOrderService.findAllByUid(userId, pageNumber);
    }

    /**
     * 查找用户订单详情
     * 包括订单编号、发布时间以及担保人，收货人和司机的姓名电话
     * @param parameters
     * @return
     */
    @RequestMapping("findDetail")
    @ResponseBody
    public Result findDetailByUserOrderId(@RequestBody Map<String, Object> parameters){
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        Map<String, Object> map = new HashMap<>(16);
        UserOrder userOrder = userOrderService.selectByPrimaryKey(userOrderId);
        if (userOrder == null){
            return new Result("查询失败，查询不到该用户订单", "1", null);
        }
        Area consigneeArea = areaService.selectByPrimaryKey(userOrder.getConsigneeAreaId());
        Area deliverArea = areaService.selectByPrimaryKey(userOrder.getDeliverAreaId());
        //担保人信息
        UserRelated suretyRelate = userService.findNameAndPhoneByUid(userOrder.getUid());
        //收货人信息
        UserRelated contactRelate = userService.findNameAndPhoneByUid(consigneeArea.getCid());
        //司机信息
        UserRelated driverRelate = userService.findNameAndPhoneByUid(userOrder.getDriverUid());
        map.put("driverRelate", driverRelate);
        map.put("suretyRelate", suretyRelate);
        map.put("contactRelate", contactRelate);
        map.put("deliverArea", deliverArea);
        map.put("consigneeArea", consigneeArea);
        map.put("createTime", userOrder.getCreateTime());
        map.put("userOrderNumber", userOrder.getUserOrderNumber());
        return new Result("查询成功", "0", map);
    }

    /**
     * 需要担保人担保的所有用户订单
     * @param parameters
     * @return
     */
    @RequestMapping("needSurety")
    @ResponseBody
    public Result findNeedSurety(@RequestBody Map<String, Object> parameters){
        Integer suretyId = (Integer) parameters.get("suretyId");
        Integer pageNumber = (Integer) parameters.get("pageNumber");
        if (pageNumber == null){
            pageNumber = 1;
        }
        return userOrderService.findNeedSurety(suretyId, pageNumber);
    }

    /**
     * 获取待收货的所有用户订单
     * @param parameters
     * @return
     */
    @RequestMapping("waitDeliver")
    @ResponseBody
    public Result findWaitDeliver(@RequestBody Map<String, Object> parameters){
        Integer cid = (Integer) parameters.get("cid");
        Integer pageNumber = (Integer) parameters.get("pageNumber");
        if (pageNumber == null){
            pageNumber = 1;
        }
        return userOrderService.findWaitDeliver(cid, pageNumber);
    }

    /**
     * 收货人确认收货
     * @param parameters
     * @return
     */
    @RequestMapping("contactConfirm")
    @ResponseBody
    public Result contactConfirm(@RequestBody Map<String, Object> parameters){
        Integer cid = (Integer) parameters.get("cid");
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        return userOrderService.contactConfirm(cid, userOrderId);
    }


    /**
     * 查找附近订单
     * @param parameters
     * @return
     */
    @RequestMapping("findNear")
    @ResponseBody
    public Result findNearByArea(@RequestBody Map<String, Object> parameters) {
        String city = (String) parameters.get("city");
        String district = (String) parameters.get("district");
        String town = (String) parameters.get("town");
        String village = (String) parameters.get("village");
        Integer pageNumber = (Integer) parameters.get("pageNumber");
        if (pageNumber == null){
            pageNumber = 1;
        }
        return userOrderService.findNearByArea(city, district, town, village, pageNumber);
    }



    /**
     * 司机接受邀请
     * @param parameters
     * @return
     */
    @RequestMapping("accept")
    @ResponseBody
    public Result driverAcceptInvite(@RequestBody Map<String, Object> parameters) {
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        Integer driverUid = (Integer) parameters.get("driverUid");
        Integer driverRouteId = (Integer) parameters.get("driverRouteId");
        UserOrder userOrder = userOrderService.selectByPrimaryKey(userOrderId);
        userOrder.setDriverRouteId(driverRouteId);
        userOrder.setDriverUid(driverUid);
        userOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        userOrder.setStatus("3");
        return userOrderService.driverAccept(userOrder);
    }




    /**
     * 上传商品图片
     * @param goodsPictures
     * @return
     */
    @RequestMapping("uploadGoodsPictures")
    @ResponseBody
    public Result uploadGoodsPictures(MultipartFile[] goodsPictures) {
        final String GOODS_PICTURE = "goodsPictures";
        List<String> totalPaths = new ArrayList<String>();
        int length = goodsPictures.length;
        for (int i = 0; i < length; i++) {
            totalPaths.add(UploadFileUtil.uploadFile(goodsPictures[i], GOODS_PICTURE));
        }
        if (totalPaths.size() > 0){
            return new Result("添加成功","0",totalPaths);
        }else {
            return new Result("添加失败","1");
        }
    }





}
