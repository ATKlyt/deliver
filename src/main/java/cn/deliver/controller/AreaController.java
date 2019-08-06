package cn.deliver.controller;

import cn.deliver.domain.Area;
import cn.deliver.domain.Result;
import cn.deliver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("area")
public class AreaController {
    @Autowired
    AreaService areaService;

    /**
     * 修改所属地区
     * 需要 用户id
     *     地址(省、市、区、详细地址)
     * @param area
     * @return
     */
    @RequestMapping("updateBelongArea")
    @ResponseBody
    public Result updateBelongArea(Area area){
        int status = areaService.updateBelongAreaByUid(area);
        if (status > 0){
            return new Result("修改成功","200");
        }else{
            return new Result("修改失败","400");
        }
    }

    /**
     * 新增收货地址
     * 需要   用户id
     *       地址(省、市、区、详细地址)
     *       联系人id
     * @param area
     * @return
     */
    @RequestMapping("addConsigneeArea")
    @ResponseBody
    public Result addConsigneeArea(Area area){
        area.setStatus("3");
        int status = areaService.insertSelective(area);
        if (status > 0){
            return new Result("添加成功","200");
        }else{
            return new Result("添加失败","400");
        }
    }

    /**
     * 修改默认收货地址
     * @param areaId 要修改成默认收货地址的areaId
     * @param uid   所登陆用户的id
     * @return
     */
    @RequestMapping("updateConsignee")
    @ResponseBody
    public Result updateConsignee(Integer areaId, Integer uid){
        int status = areaService.updateConsignee(areaId,uid);
        if (status > 0){
            return new Result("修改成功","200");
        }else{
            return new Result("修改失败","400");
        }
    }

    /**
     * 查询所有收货地址
     * 默认地址的status为2
     * @param uid
     * @return
     */
    @RequestMapping("queryAllConsignee")
    @ResponseBody
    public Result queryAllConsignee(Integer uid){
        if ( uid == null ){
            return new Result("请输入用户uid", "400");
        }

        List<Area> consignees = areaService.findAllConsigneeByUid(uid);
        if (consignees != null){
            return new Result("查询成功","200",consignees);
        }else{
            return new Result("查询失败","400");
        }
    }

    /**
     * 删除收货地址
     * @param areaId 删除
     * @return
     */
    @RequestMapping("deleteConsignee")
    @ResponseBody
    public Result deleteConsignee(Integer areaId){
        System.out.println(areaId);
        int status = areaService.deleteConsignee(areaId);
        if (status > 0){
            return new Result("删除成功","200");
        }else{
            return new Result("删除失败","400");
        }
    }


}
