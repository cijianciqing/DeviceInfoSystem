package jsjzx.wlyw.networkmaintenance.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import jsjzx.wlyw.networkmaintenance.entities.*;
import jsjzx.wlyw.networkmaintenance.service.impl.UnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CiJian
 * @since 2018-08-30
 */
@Controller
@RequestMapping("/company")
public class UnitController {

    @Autowired
    UnitServiceImpl unitService;

    @ResponseBody
    @GetMapping(value = "/getCompanies")
    public Msg getCompanies() {
        EntityWrapper<Unit> wrapper = new EntityWrapper<>();
        wrapper.groupBy("companyname");
        List<Unit> units = unitService.selectList(wrapper);
        return Msg.success().add("companies", units);
    }

    @ResponseBody
    @GetMapping(value = "/getDepartments")
    public Msg getDepartments(@RequestParam("assetComponyName") String assetComponyName) {
        EntityWrapper<Unit> wrapper = new EntityWrapper<>();
        wrapper.eq("companyname", assetComponyName);
        List<Unit> units = unitService.selectList(wrapper);
        return Msg.success().add("units", units);
    }


    @ResponseBody
    @GetMapping(value = "/getBTCompanies")
    public Map<String, Object> getBTCompanies(BasicSearchInfo searchInfo) {
        //输出当前的查询信息
        System.out.println("当前的查询信息： " + searchInfo);
        //获取前台传递的参数
        Integer pageSize = searchInfo.getPageSize();
        Integer pageNumber = searchInfo.getPageNumber();
        String searchText = searchInfo.getSearchText();
        String sortName = searchInfo.getSortName();
        String sortOrder = searchInfo.getSortOrder();
        String searchInfo1 = searchInfo.getSearchInfo();

        //排序使用的列
        List<String> orderColumn = new ArrayList<>();
        orderColumn.add(sortName);
        //后台分页查询
        Page<Unit> page = new Page<>(pageNumber, pageSize);
        EntityWrapper<Unit> wrapper = new EntityWrapper<>();
        wrapper.like("companyname", searchInfo1);
        wrapper.or();
        wrapper.like("companynameshort", searchInfo1);
        wrapper.or();
        wrapper.like("departmentname", searchInfo1);

        //设置排序
        if (sortOrder.equalsIgnoreCase("asc")) {
            wrapper.orderAsc(orderColumn);
        } else if (sortOrder.equalsIgnoreCase("desc")) {
            wrapper.orderDesc(orderColumn);
        }

        //获取要查询的数据
        Page<Unit> page1 = unitService.selectPage(page, wrapper);
        //获取查询结果的总量
        int totalUser = unitService.selectCount(wrapper);
        //传递到前端的参数
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalUser);
        map.put("rows", page1.getRecords());
        return map;
    }

    //编辑公司信息前，将原始数据传递回去
    @ResponseBody
    @GetMapping(value = "/getOne/{id}")
    public Msg getOne(@PathVariable("id") Integer id) {

        Unit unit = unitService.selectById(id);

        return Msg.success().add("company", unit);
    }


    @ResponseBody
    @PostMapping(value = "/add")
    public Msg addCompany(@Valid Unit unit, BindingResult result) {
        //将提交的参数转换为Asset
        //保存到数据库
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            unitService.insert(unit);
            return Msg.success();
        }
    }

    //更新公司
    @ResponseBody
    @PutMapping(value = "/edit")
    public Msg updateUser(@Valid Unit unit, BindingResult result) {
        //将提交的参数转换为Asset
        //保存到数据库

        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                    map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            unitService.updateById(unit);
            return Msg.success();
        }
    }

    //删除公司、部门
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
            unitService.deleteBatchIds(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            unitService.deleteById(id);
        }
        return Msg.success();
    }

}

