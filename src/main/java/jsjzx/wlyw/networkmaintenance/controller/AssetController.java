package jsjzx.wlyw.networkmaintenance.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import jsjzx.wlyw.networkmaintenance.entities.*;
import jsjzx.wlyw.networkmaintenance.service.impl.AssetServiceImpl;
import jsjzx.wlyw.networkmaintenance.service.impl.BrandmodelServiceImpl;
import jsjzx.wlyw.networkmaintenance.service.impl.TypebrandServiceImpl;
import jsjzx.wlyw.networkmaintenance.service.impl.UnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CiJian
 * @since 2018-08-30
 */
@Controller
@RequestMapping("/device")
public class AssetController {

    @Autowired
    AssetServiceImpl assetService;
    @Autowired
    UnitServiceImpl unitService;
    @Autowired
    TypebrandServiceImpl typebrandService;
    @Autowired
    BrandmodelServiceImpl brandmodelService;

    @ResponseBody
    @GetMapping(value = "/getDevices")
    public Map<String, Object> getUsers(SearchInfo searchInfo) {
        //输出当前的查询信息
        System.out.println("当前的查询信息： " + searchInfo);
        //获取前台传递的参数
        Integer pageSize = searchInfo.getPageSize();
        Integer pageNumber = searchInfo.getPageNumber();
        String searchText = searchInfo.getSearchText();
        String sortName = searchInfo.getSortName();
        String sortOrder = searchInfo.getSortOrder();
        String soIP = searchInfo.getSoIP();
        String soBieMing = searchInfo.getSoBieMing();
        String soHostName = searchInfo.getSoHostName();
        //排序使用的列
        List<String> orderColumn = new ArrayList<>();
        orderColumn.add(sortName);
        //后台分页查询
        Page<Asset> page = new Page<>(pageNumber, pageSize);
        EntityWrapper<Asset> wrapper = new EntityWrapper<>();
        wrapper.like("ip", soIP);
        wrapper.like("devicename", soBieMing);
        wrapper.like("hostname", soHostName);
        //设置排序
        if (sortOrder.equalsIgnoreCase("asc")) {
            wrapper.orderAsc(orderColumn);
        }else if(sortOrder.equalsIgnoreCase("desc")){
            wrapper.orderDesc(orderColumn);
        }
        //获取要查询的数据
        Page<Asset> page1 = assetService.selectPage(page, wrapper);
        //获取查询结果的总量
        int totalUser = assetService.selectCount(wrapper);
        //传递到前端的参数
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalUser);
        map.put("rows", page1.getRecords());
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public Msg addUser(@Valid Asset asset, BindingResult result) {
        //将提交的参数转换为Asset
        //保存到数据库
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                if(fieldError.getField().equalsIgnoreCase("buytime")|fieldError.getField().equalsIgnoreCase("buytime")|fieldError.getField().equalsIgnoreCase("buytime")){
                    map.put(fieldError.getField(), "日期格式不正确");
                }else{
                    map.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
            }
            return Msg.fail().add("errorFields", map);
        } else {
            assetService.insert(asset);
            return Msg.success();
        }
    }

    //编辑设备信息前，将原始数据传递回去
    @ResponseBody
    @GetMapping(value = "/getOne/{hostid}")
    public Msg getOne(@PathVariable("hostid") Integer hostid){

        Asset asset = assetService.selectById(hostid);

        String company = asset.getCompany();
        String type = asset.getType();
        String brand = asset.getBrand();

        //根据company获得department
        EntityWrapper<Unit> uWrapper = new EntityWrapper<>();
        uWrapper.eq("companyname",company);
        List<Unit> units = unitService.selectList(uWrapper);

        //根据type获得brand
        EntityWrapper<Typebrand> tWrapper01 = new EntityWrapper<>();
        tWrapper01.eq("type",type);
        List<Typebrand> typebrands = typebrandService.selectList(tWrapper01);

        //根据type,brand获得id,最终获得model
        EntityWrapper<Typebrand> tWrapper02 = new EntityWrapper<>();
        tWrapper02.eq("type",type);
        tWrapper02.eq("brandcn",brand);
        Integer tbid = typebrandService.selectList(tWrapper02).get(0).getId();

        EntityWrapper<Brandmodel> bWrapper = new EntityWrapper<>();
        bWrapper.eq("typebrandid",tbid);
        List<Brandmodel> brandmodels = brandmodelService.selectList(bWrapper);

        return Msg.success().add("device",asset).add("departments",units).add("brands",typebrands).add("models",brandmodels);
    }

    //更新用户
    @ResponseBody
    @PutMapping(value = "/edit")
    public Msg updateUser(@Valid Asset asset, BindingResult result) {
        //将提交的参数转换为Asset
        //保存到数据库

        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
//                System.out.println("错误的字段名：" + fieldError.getField());
//                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                if(fieldError.getField().equalsIgnoreCase("buytime")|fieldError.getField().equalsIgnoreCase("buytime")|fieldError.getField().equalsIgnoreCase("buytime")){
                    map.put(fieldError.getField(), "日期格式不正确");
                }else{
                    map.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
            }
            return Msg.fail().add("errorFields", map);
        } else {
            assetService.updateById(asset);
            return Msg.success();
        }
    }

    //删除用户
    @ResponseBody
    @RequestMapping(value="/delete/{ids}",method=RequestMethod.DELETE)
    public Msg delUser(@PathVariable("ids")String ids){
        //批量删除
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            assetService.deleteBatchIds(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            assetService.deleteById(id);
        }
        return Msg.success();
    }

}

