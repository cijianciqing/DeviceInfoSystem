package jsjzx.wlyw.networkmaintenance.controller;


import jsjzx.wlyw.networkmaintenance.entities.Grade;
import jsjzx.wlyw.networkmaintenance.entities.Msg;
import jsjzx.wlyw.networkmaintenance.entities.Status;
import jsjzx.wlyw.networkmaintenance.service.impl.GradeServiceImpl;
import jsjzx.wlyw.networkmaintenance.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeServiceImpl gradeService;

    @ResponseBody
    @GetMapping(value = "/getGrades")
    public Msg getGrades(){
        List<Grade> grades = gradeService.selectList(null);
        return Msg.success().add("grades",grades);
    }
}

