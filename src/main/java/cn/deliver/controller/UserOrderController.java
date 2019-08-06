package cn.deliver.controller;

import cn.deliver.domain.Result;
import cn.deliver.domain.UserOrder;
import cn.deliver.service.UserOrderService;
import cn.deliver.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("userOrder")
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;





    /**
     *  需要发货人的id    uid
     *  发货起始时间      deliveryStart
     *  发货终止时间      deliveryEnd
     *  货物描述         description
     *  货物图片         goodsPicture
     *  收货地址id       consigneeAreaId
     *  金额            pay
     * @param request
     * @param userOrder
     * @return
     * @throws IOException
     */
    @RequestMapping("addUserOrder")
    @ResponseBody
    public Result addUserOrder(HttpServletRequest request, UserOrder userOrder) throws IOException {
        final String picture = "picture";
        //获取userOrder里边商品图片
        if (request.getParameter(picture) != null){
            return new Result("未选择图片","400");
        }else {
            String uploadFolder = request.getServletContext().getRealPath("/goodsPicture");
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取文件数据
            MultipartFile mFile = multiRequest.getFile(picture);
            //调用UploadUtil工具类的上传方法
            String totalPath = UploadFileUtil.uploadFile(mFile, uploadFolder);
            if (totalPath != null){
                //将用户发单的商品图片设置成图片路径
                //userOrder.setGoodsPicture(totalPath);

                userOrder.setStatus("0");
                int status = userOrderService.addUserOrder(userOrder);
                if (status > 0){
                    //通知收货人.........
                    return new Result("发单成功", "200");
                }else{
                    return new Result("发单失败", "400");
                }
            }else{
                return new Result("图片保存失败","400");
            }
        }
    }




}
