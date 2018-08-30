package jsjzx.wlyw.networkmaintenance;

import jsjzx.wlyw.networkmaintenance.entities.Asset;
import jsjzx.wlyw.networkmaintenance.service.impl.AssetServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetworkMaintenanceApplicationTests {

	@Autowired
	AssetServiceImpl assetService;

	@Test
	public void contextLoads() {
		Asset asset = new Asset();
		asset.setCompany("中字号");
		assetService.insert(asset);
	}

}
