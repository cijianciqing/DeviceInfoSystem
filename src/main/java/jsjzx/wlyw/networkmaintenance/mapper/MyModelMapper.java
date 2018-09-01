package jsjzx.wlyw.networkmaintenance.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import jsjzx.wlyw.networkmaintenance.entities.MyModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MyModelMapper extends  BaseMapper<MyModel>  {
//     List<MyModel> getAllMyModels();

     List<MyModel> selectMyModelPage(RowBounds rowBounds, @Param("ew") Wrapper<MyModel> wrapper);

     Integer selectMyCount(@Param("ew") Wrapper<MyModel> var1);

     void updateMyModel(MyModel myModel);
}
