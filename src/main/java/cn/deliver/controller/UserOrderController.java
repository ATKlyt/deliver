package cn.deliver.controller;

import cn.deliver.domain.Area;
import cn.deliver.domain.Result;
import cn.deliver.domain.UserOrder;
import cn.deliver.service.UserOrderService;
import cn.deliver.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("userOrder")
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;


    /**
     * 通过地址(村级)查询出发地为该村的用户订单
     * 查找发货人详细信息和发货人地址和用户订单Id
     * @param area
     * @return
     */
    @RequestMapping("findNear")
    @ResponseBody
    public Result findNearByVillage(@RequestBody Area area) {
        String village = area.getVillage();
        return userOrderService.findNearByVillage(village);
    }

    /**
     * 通过用户订单id查询收货人详细信息和用户订单详细描述
     * @param parameters
     * @return
     */
    @RequestMapping("findDetail")
    @ResponseBody
    public Result findDetailByUserOrderId(@RequestBody Map<String, Object> parameters) {
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        return userOrderService.findDetailByUserOrderId(userOrderId);
    }


    /**
     * 用户发布订单
     * @param parameters
     * @return
     */
    @RequestMapping("addUserOrder")
    @ResponseBody
    public Result addUserOrder(@RequestBody Map<String, Object> parameters) {
        ArrayList<String> goodsPictures = (ArrayList<String>) parameters.get("goodsPictures");
        Map<String, Object> userOrderMsg = (Map<String, Object>) parameters.get("deliveryMsg");
        UserOrder userOrder = new UserOrder();
        userOrder.setUid((Integer) userOrderMsg.get("uid"));
        userOrder.setDeliverAreaId((Integer) userOrderMsg.get("deliverAreaId"));
        userOrder.setConsigneeAreaId((Integer) userOrderMsg.get("consigneeAreaId"));
        userOrder.setDescription((String) userOrderMsg.get("description"));
        userOrder.setDeliveryStart(new Timestamp((Long) userOrderMsg.get("deliveryStart")));
        userOrder.setDeliveryEnd(new Timestamp((Long) userOrderMsg.get("deliveryEnd")));
        //验证
        userOrder.setStatus("0");
        int size = goodsPictures.size();
        int i = 0;
        for (String goodsPicture : goodsPictures) {
            if (i == 0){
                userOrder.setGoodsPicture1(goodsPicture);
            }else if (i == 1){
                userOrder.setGoodsPicture2(goodsPicture);
            }else {
                userOrder.setGoodsPicture3(goodsPicture);
            }
            i++;
        }
        return userOrderService.addUserOrder(userOrder);
    }


    /**
     * 上传商品图片
     * @return
     */
    @RequestMapping("uploadGoodsPictures")
    @ResponseBody
    public Result uploadGoodsPictures(MultipartFile[] goodsPictures) {
        final String GOODSPICTURE = "goodsPictures";
        List<String> totalPaths = new ArrayList<String>();
        int length = goodsPictures.length;
        for (int i = 0; i < length; i++) {
            totalPaths.add(UploadFileUtil.uploadFile(goodsPictures[i], GOODSPICTURE));
        }
        if (totalPaths.size() > 0){
            return new Result("添加成功","0",totalPaths);
        }else {
            return new Result("添加失败","1");
        }
    }
}
