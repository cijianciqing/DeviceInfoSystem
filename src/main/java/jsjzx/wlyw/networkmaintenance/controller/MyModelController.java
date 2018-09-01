package jsjzx.wlyw.networkmaintenance.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import jsjzx.wlyw.networkmaintenance.entities.*;
import jsjzx.wlyw.networkmaintenance.service.impl.BrandmodelServiceImpl;
import jsjzx.wlyw.networkmaintenance.service.impl.MyModelService;
import jsjzx.wlyw.networkmaintenance.service.impl.TypebrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/myModel")
public class MyModelController {
    @Autowired
    MyModelService myModelService;
    @Autowired
    TypebrandServiceImpl typebrandService;
    @Autowired
    BrandmodelServiceImpl brandmodelService;

    @ResponseBody
    @GetMapping(value = "/getMyModels")
    public Map<String, Object> getMyModels(BasicSearchInfo searchInfo) {
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
        Page<MyModel> page = new Page<>(pageNumber, pageSize);
        EntityWrapper<MyModel> wrapper = new EntityWrapper<>();
        wrapper.like("t.type", searchInfo1);
        wrapper.or();
        wrapper.like("t.brandcn", searchInfo1);
        wrapper.or();
        wrapper.like("b.model", searchInfo1);

        //设置排序
        if (sortOrder.equalsIgnoreCase("asc")) {
            wrapper.orderAsc(orderColumn);
        } else if (sortOrder.equalsIgnoreCase("desc")) {
            wrapper.orderDesc(orderColumn);
        }

        //获取要查询的数据
        Page<MyModel> page1 = myModelService.selectMyPage(page, wrapper);
        //获取查询结果的总量
        int totalUser = myModelService.selectCount(wrapper);
        //传递到前端的参数
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalUser);
        map.put("rows", page1.getRecords());
        return map;
    }

    //编辑设备信息前，将原始数据传递回去
    @ResponseBody
    @GetMapping(value = "/getOne/{id}")
    public Msg getOne(@PathVariable("id") Integer id){

        //根据model id获得 typebrandid
        Brandmodel brandmodel = brandmodelService.selectById(id);
        String model = brandmodel.getModel();
        Integer typebrandid = brandmodel.getTypebrandid();

        //根据typebrandid获得typebrand
        EntityWrapper<Typebrand> tWrapper01 = new EntityWrapper<>();
        tWrapper01.eq("id",typebrandid);
        Typebrand typebrand =  typebrandService.selectOne(tWrapper01);

        //根据type获得brands
        String type = typebrand.getType();
        EntityWrapper<Typebrand> tWrapper02 = new EntityWrapper<>();
        tWrapper02.eq("type",type);
        List<Typebrand> typebrands = typebrandService.selectList(tWrapper02);

        //生成MyModel
        MyModel myModel = new MyModel();
        myModel.setId(id);
        myModel.setModel(model);
        myModel.setTypebrand(typebrand);

        return Msg.success().add("mymodel",myModel).add("brands",typebrands);
    }

    //更新用户
    @ResponseBody
    @PutMapping(value = "/edit")
    public Msg updateMyModel(@Valid MyModel myModel, BindingResult result) {
        //将提交的参数转换为Asset
        //保存到数据库

        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                    map.put(fieldError.getField(), fieldError.getDefaultMessage());
                System.out.println(fieldError.getField()+":"+fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            //根据myModel获得typebrandid,因为此时model的 typebrandid肯能改变
            Typebrand typebrand = myModel.getTypebrand();
            EntityWrapper<Typebrand> tWrapper = new EntityWrapper<>();
            tWrapper.eq("type",typebrand.getType());
            tWrapper.eq("brandcn",typebrand.getBrandcn());
            Typebrand typebrand1 = (Typebrand) typebrandService.selectOne(tWrapper);
            Integer typebrandi = typebrand1.getId();
            //生成更新后的brandmodel
            Brandmodel brandmodel = new Brandmodel();
            brandmodel.setId(myModel.getId());
            brandmodel.setModel(myModel.getModel());
            brandmodel.setTypebrandid(typebrandi);

            brandmodelService.updateById(brandmodel);
            return Msg.success();
        }
    }

    //删除公司、部门
    @ResponseBody
    @RequestMapping(value="/delete/{ids}",method= RequestMethod.DELETE)
    public Msg deleteMyModel(@PathVariable("ids")String ids){
        //批量删除
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            brandmodelService.deleteBatchIds(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            brandmodelService.deleteById(id);
        }
        return Msg.success();
    }


    @ResponseBody
    @PostMapping(value = "/add")
    public Msg addMyModel(@Valid MyModel myModel, BindingResult result) {

        System.out.println(myModel);
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
            //根据myModel获得typebrandid,因为此时model的 typebrandid肯能改变
            Typebrand typebrand = myModel.getTypebrand();
            EntityWrapper<Typebrand> tWrapper = new EntityWrapper<>();

            tWrapper.eq("type",typebrand.getType());
            tWrapper.eq("brandcn",typebrand.getBrandcn());
            Typebrand typebrand1 = typebrandService.selectOne(tWrapper);
            Integer typebrandi = typebrand1.getId();
            //生成更新后的brandmodel
            Brandmodel brandmodel = new Brandmodel();
            brandmodel.setModel(myModel.getModel());
            brandmodel.setTypebrandid(typebrandi);

            brandmodelService.insert(brandmodel);
            return Msg.success();
        }
    }
}
