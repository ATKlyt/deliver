package cn.deliver.controller;

import cn.deliver.domain.Area;
import cn.deliver.domain.Result;
import cn.deliver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("area")
public class AreaController {
    @Autowired
    AreaService areaService;
    private final String CONSIGNEE_TYPE = "consignee";
    private final String DELIVER_TYPE = "deliver";


    /**
     * 修改所属地区
     * 需要 用户id
     *     地址(省、市、区、镇、村、详细地址)
     * @param area
     * @return
     */
    @RequestMapping("updateBelong")
    @ResponseBody
    public Result updateBelongArea(@RequestBody Area area){
        return areaService.updateBelongAreaByUid(area);
    }

    /**
     * 新增收货地址
     * @param area
     * @return
     */
    @RequestMapping("addConsignee")
    @ResponseBody
    public Result addConsigneeArea(@RequestBody Area area){
        if (area.getCid() == null){
            return new Result("请填写联系人id","1");
        }
        //将此地址状态设置为收货地址
        area.setType("3");
        return areaService.insertSelective(area);
    }

    /**
     * 新增发货地址
     * @param area
     * @return
     */
    @RequestMapping("addDeliver")
    @ResponseBody
    public Result addDeliver(@RequestBody Area area){
        //将此地址类型设置为收货地址
        area.setType("5");
        return areaService.insertSelective(area);
    }

    /**
     * 修改默认收货地址
     * areaId 要修改成默认收货地址的areaId
     * uid    所登陆用户的id
     * @param parameters
     * @return
     */
    @RequestMapping("updateDefaultConsignee")
    @ResponseBody
    public Result updateDefaultConsigneeArea(@RequestBody Map<String, Object> parameters){
        Integer areaId = (Integer) parameters.get("areaId");
        Integer uid = (Integer) parameters.get("uid");
        return areaService.updateDefaultArea(areaId,uid,CONSIGNEE_TYPE);
    }

    /**
     * 修改默认发货地址
     * areaId 要修改成默认发货地址的areaId
     * uid   所登陆用户的id
     * @param parameters
     * @return
     */
    @RequestMapping("updateDefaultDeliver")
    @ResponseBody
    public Result updateDefaultDeliverArea(@RequestBody Map<String, Object> parameters){
        Integer areaId = (Integer) parameters.get("areaId");
        Integer uid = (Integer) parameters.get("uid");
        return areaService.updateDefaultArea(areaId,uid,DELIVER_TYPE);
    }

    /**
     * 查询所有收货地址
     * @param parameters
     * @return
     */
    @RequestMapping("queryAllConsignee")
    @ResponseBody
    public Result queryAllConsigneeArea(@RequestBody Map<String, Object> parameters){
        Integer uid = (Integer) parameters.get("uid");
        return areaService.findAllConsigneeAreaByUid(uid);
    }

    /**
     * 查询所有发货地址
     * @param parameters
     * @return
     */
    @RequestMapping("queryAllDeliver")
    @ResponseBody
    public Result queryAllDeliverArea(@RequestBody Map<String, Object> parameters){
        Integer uid = (Integer) parameters.get("uid");
        //验证
        return areaService.findAllDeliverAreaByUid(uid);
    }

    /**
     * 删除地址
     * @param parameters
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Result deleteArea(@RequestBody Map<String, Object> parameters){
        Integer areaId = (Integer) parameters.get("areaId");
        return areaService.deleteAreaByAreaId(areaId);
    }

    /**
     * 修改发货/收货地址
     * @param area
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Result updateArea(@RequestBody Area area){
        return areaService.updateByUserOrderIdSelective(area);
    }


}
