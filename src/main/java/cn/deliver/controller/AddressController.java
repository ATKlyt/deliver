package cn.deliver.controller;

import cn.deliver.domain.Result;
import cn.deliver.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("address")
public class AddressController {

    @Autowired
    AddressService addressService;


    @RequestMapping("getProvince")
    @ResponseBody
    public Result getProvince(){
        return addressService.getProvince();
    }


    @RequestMapping("getAddress")
    @ResponseBody
    public Result getAddress(Integer addressId){
        return addressService.getAddress(addressId);
    }

}
