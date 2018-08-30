package jsjzx.wlyw.networkmaintenance.controller;


import jsjzx.wlyw.networkmaintenance.entities.Msg;
import jsjzx.wlyw.networkmaintenance.entities.Status;
import jsjzx.wlyw.networkmaintenance.entities.Typebrand;
import jsjzx.wlyw.networkmaintenance.service.impl.StatusServiceImpl;
import jsjzx.wlyw.networkmaintenance.service.impl.TypebrandServiceImpl;
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
@RequestMapping("/status")
public class StatusController {
    @Autowired
    StatusServiceImpl statusService;

    @ResponseBody
    @GetMapping(value = "/getStatuses")
    public Msg getStatuses(){
        List<Status> statuses = statusService.selectList(null);
        return Msg.success().add("statuses",statuses);
    }
}

