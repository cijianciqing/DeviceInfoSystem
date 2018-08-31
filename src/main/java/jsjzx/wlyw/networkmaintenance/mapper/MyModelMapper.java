package jsjzx.wlyw.networkmaintenance.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import jsjzx.wlyw.networkmaintenance.entities.MyModel;

import java.util.List;

public interface MyModelMapper extends  BaseMapper<MyModel>  {
     List<MyModel> getAllMyModels();

}
