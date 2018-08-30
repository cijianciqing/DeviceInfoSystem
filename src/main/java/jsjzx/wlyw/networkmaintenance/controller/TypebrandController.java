package jsjzx.wlyw.networkmaintenance.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import jsjzx.wlyw.networkmaintenance.entities.BasicSearchInfo;
import jsjzx.wlyw.networkmaintenance.entities.Msg;
import jsjzx.wlyw.networkmaintenance.entities.Typebrand;
import jsjzx.wlyw.networkmaintenance.entities.Typebrand;
import jsjzx.wlyw.networkmaintenance.service.impl.TypebrandServiceImpl;
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
 *  前端控制器
 * </p>
 *
 * @author CiJian
 * @since 2018-08-30
 */
@Controller
@RequestMapping("/typebrand")
public class TypebrandController {
    @Autowired
    TypebrandServiceImpl typebrandService;

    //添加或更新数据时获取所有的Type
    @ResponseBody
    @GetMapping(value = "/getTypes")
    public Msg getTypes(){
        EntityWrapper<Typebrand> wrapper = new EntityWrapper<>();
        wrapper.groupBy("type");
        List<Typebrand> typebrands = typebrandService.selectList(wrapper);
        return Msg.success().add("types",typebrands);
    }

    //根据type获得所有相关的Brand
    @ResponseBody
    @GetMapping(value = "/getBrands")
    public Msg getBrands(@RequestParam("assetType") String assetType){
        EntityWrapper<Typebrand> wrapper = new EntityWrapper<>();
        wrapper.eq("type",assetType);
        List<Typebrand> typebrands = typebrandService.selectList(wrapper);
        return Msg.success().add("brands",typebrands);
    }

    @ResponseBody
    @GetMapping(value = "/getBTTypes")
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
        Page<Typebrand> page = new Page<>(pageNumber, pageSize);
        EntityWrapper<Typebrand> wrapper = new EntityWrapper<>();
        wrapper.like("type", searchInfo1);
        wrapper.or();
        wrapper.like("brandcn", searchInfo1);
        wrapper.or();
        wrapper.like("branden", searchInfo1);

        //设置排序
        if (sortOrder.equalsIgnoreCase("asc")) {
            wrapper.orderAsc(orderColumn);
        } else if (sortOrder.equalsIgnoreCase("desc")) {
            wrapper.orderDesc(orderColumn);
        }

        //获取要查询的数据
        Page<Typebrand> page1 = typebrandService.selectPage(page, wrapper);
        //获取查询结果的总量
        int totalUser = typebrandService.selectCount(wrapper);
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

        Typebrand Typebrand = typebrandService.selectById(id);

        return Msg.success().add("brand", Typebrand);
    }


    @ResponseBody
    @PostMapping(value = "/add")
    public Msg addCompany(@Valid Typebrand Typebrand, BindingResult result) {
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
            typebrandService.insert(Typebrand);
            return Msg.success();
        }
    }

    //更新公司
    @ResponseBody
    @PutMapping(value = "/edit")
    public Msg updateUser(@Valid Typebrand Typebrand, BindingResult result) {
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
            typebrandService.updateById(Typebrand);
            return Msg.success();
        }
    }

    //删除公司、部门
    @ResponseBody
    @RequestMapping(value="/delete/{ids}",method= RequestMethod.DELETE)
    public Msg delUser(@PathVariable("ids")String ids){
        //批量删除
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            typebrandService.deleteBatchIds(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            typebrandService.deleteById(id);
        }
        return Msg.success();
    }


}

