package jsjzx.wlyw.networkmaintenance.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import jsjzx.wlyw.networkmaintenance.entities.Brandmodel;
import jsjzx.wlyw.networkmaintenance.entities.Msg;
import jsjzx.wlyw.networkmaintenance.entities.Typebrand;
import jsjzx.wlyw.networkmaintenance.service.impl.BrandmodelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CiJian
 * @since 2018-08-30
 */
@Controller
@RequestMapping("/brandmodel")
public class BrandmodelController {
    @Autowired
    BrandmodelServiceImpl brandmodelService;

    @ResponseBody
    @GetMapping(value = "/getModels")
    public Msg getModels(@RequestParam("assetBrandId") String assetBrandId){
        EntityWrapper<Brandmodel> wrapper = new EntityWrapper<>();
        wrapper.eq("typebrandid",assetBrandId);
        List<Brandmodel> brandmodels = brandmodelService.selectList(wrapper);
        return Msg.success().add("models",brandmodels);
    }
}

