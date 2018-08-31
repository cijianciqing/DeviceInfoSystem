package jsjzx.wlyw.networkmaintenance;

import jsjzx.wlyw.networkmaintenance.entities.Asset;
import jsjzx.wlyw.networkmaintenance.entities.MyModel;
import jsjzx.wlyw.networkmaintenance.entities.Unit;
import jsjzx.wlyw.networkmaintenance.mapper.MyModelMapper;
import jsjzx.wlyw.networkmaintenance.mapper.UnitMapper;
import jsjzx.wlyw.networkmaintenance.service.impl.AssetServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetworkMaintenanceApplicationTests {

	/*@Autowired
    AssetServiceImpl assetService;

	@Test
	public void contextLoads() {
		Asset asset = new Asset();
		asset.setCompany("中字号");
		assetService.insert(asset);
	}*/

    @Autowired
    MyModelMapper myModelMapper;

    @Test
    public void testCustomMapper() {
        List<MyModel> allMyModels = myModelMapper.getAllMyModels();
        System.out.println(allMyModels.get(1));
//        System.out.println(myModelMapper);
    }

    @Autowired
    UnitMapper unitMapper;
    @Test
    public void testOlderMapper(){
        Unit unit = unitMapper.selectById(1);
        System.out.println(unit);
    }

}
