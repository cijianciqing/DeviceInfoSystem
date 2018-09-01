package jsjzx.wlyw.networkmaintenance.service.impl;

import com.alibaba.druid.mock.MockArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import jsjzx.wlyw.networkmaintenance.entities.MyModel;
import jsjzx.wlyw.networkmaintenance.entities.Typebrand;
import jsjzx.wlyw.networkmaintenance.mapper.MyModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyModelService {
    @Autowired
    MyModelMapper myModelMapper;

    public Page<MyModel> selectMyPage(Page<MyModel> page, EntityWrapper<MyModel> wrapper) {
        List<MyModel> myModels = myModelMapper.selectMyModelPage(page, wrapper);
        page.setRecords(myModels);
        return  page;
    }

    public Integer selectCount(EntityWrapper<MyModel> wrapper) {
        return  myModelMapper.selectMyCount(wrapper);
    }


}
