package cn.deliver.controller;

import cn.deliver.domain.Area;
import cn.deliver.domain.Result;
import cn.deliver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("area")
public class AreaController {
    @Autowired
    AreaService areaService;
    private final String CONSIGNEE = "consignee";
    private final String DELIVER = "deliver";


    /**
     * 修改所属地区
     * 需要 用户id
     *     地址(省、市、区、详细地址)
     * @param area
     * @return
     */
    @RequestMapping("updateBelongArea")
    @ResponseBody
    public Result updateBelongArea(@RequestBody Area area){
        //验证area
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
        //验证area是不是为空

        //将此地址状态设置为收货地址
        area.setStatus("3");
        return areaService.insertSelective(area);
    }

    /**
     * 修改默认收货地址
     * areaId 要修改成默认收货地址的areaId
     * uid    所登陆用户的id
     * @param parameters
     * @return
     */
    @RequestMapping("updateConsignee")
    @ResponseBody
    public Result updateConsignee(@RequestBody Map<String, Object> parameters){
        Integer areaId = (Integer) parameters.get("areaId");
        Integer uid = (Integer) parameters.get("uid");
        return areaService.updateArea(areaId,uid,CONSIGNEE);
    }

    /**
     * 查询所有收货地址
     * @param parameters
     * @return
     */
    @RequestMapping("queryAllConsignee")
    @ResponseBody
    public Result queryAllConsignee(@RequestBody Map<String, Object> parameters){
        Integer uid = (Integer) parameters.get("uid");
        //验证
        return areaService.findAllConsigneeByUid(uid);
    }

    /**
     * 新增发货地址
     * @param area
     * @return
     */
    @RequestMapping("addDeliver")
    @ResponseBody
    public Result addDeliver(@RequestBody Area area){
        //验证area是不是为空

        //将此地址状态设置为收货地址
        area.setStatus("5");
        return areaService.insertSelective(area);
    }

    /**
     * 修改默认发货地址
     * areaId 要修改成默认发货地址的areaId
     * uid   所登陆用户的id
     * @param parameters
     * @return
     */
    @RequestMapping("updateDeliver")
    @ResponseBody
    public Result updateDeliver(@RequestBody Map<String, Object> parameters){
        Integer areaId = (Integer) parameters.get("areaId");
        Integer uid = (Integer) parameters.get("uid");
        return areaService.updateArea(areaId,uid,DELIVER);
    }

    /**
     * 查询所有发货地址
     * @param parameters
     * @return
     */
    @RequestMapping("queryAllDeliver")
    @ResponseBody
    public Result queryAllDeliver(@RequestBody Map<String, Object> parameters){
        Integer uid = (Integer) parameters.get("uid");
        //验证
        return areaService.findAllDeliverByUid(uid);
    }

    /**
     * 删除地址
     * @param parameters
     * @return
     */
    @RequestMapping("deleteArea")
    @ResponseBody
    public Result deleteArea(@RequestBody Map<String, Object> parameters){
        Integer areaId = (Integer) parameters.get("areaId");
        return areaService.deleteAreaByAreaId(areaId);
    }


}
