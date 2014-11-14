package cn.com.innodev.pdp.community.services.impl;

import static org.junit.Assert.fail;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.innodev.pdp.community.Building;
import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.ProjectCompany;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.framework.bizcommon.CommonService;

import com.google.gson.Gson;
import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.business.serialize.GsonCreator;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class CommunityServiceImplTest {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private CommonService commonService;
	
	@Test
	public void testAddCommunity() {
		Community community = new Community();
		community.setCommunityName("久居雅园");
		community.setId("jiujuyayuan");
		community.setCity(this.commonService.getCity(1));
		
		ProjectCompany projectCompany = new ProjectCompany();
		projectCompany.setCompanyName("久居雅园物业公司");
		
		try {
			this.communityService.addCommunity(community, projectCompany);
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteCommunity() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCommunityBaseInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteQRImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateQRImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommunity() {
		String cid = "asdas";
		Community loadCommunity = this.communityService.getCommunity(cid);
		Gson gson = GsonCreator.createPretty();
		System.out.println(gson.toJson(loadCommunity));
	}

	@Test
	public void testGetProjectCompany() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProjectCompany() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteProjectCompanyLogoInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProjectCompanyLogoInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCommunityImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCommunityImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCommunityImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommunityImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommunityImages() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddStaff() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStaffState() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStaffRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStaff() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteStaff() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStaffByAccountName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStaffBaseInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStaffHeaderImageBean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStaffs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalStaffs() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAnnouncement() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAnnouncement() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAnnouncement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAnnouncement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAnnouncements() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalAnnouncements() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddQA() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateQA() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteQA() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQA() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQAs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalQAs() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBuilding() {
		Community community = this.communityService.getCommunity("jiujuyayuan");
		Set<String> roomNos = new LinkedHashSet<String>();
		for (int i=1;i<=12;i++) {
			if (i >=10) {
				roomNos.add(i + "01");
				roomNos.add(i + "02");
			}else{
				roomNos.add(i + "001");
				roomNos.add(i + "002");
			}
		}
		try {
			this.communityService.addBuilding(community, "107", null, roomNos);
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateBaseBuilding() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBuilding() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBuilding() {
		Building building = this.communityService.getBuilding(1);
		System.out.println(building);
		//Gson gson = GsonCreator.createPretty();
		//System.out.println(gson.toJson(building));
	}

	@Test
	public void testGetBuildings() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRoom() {
		fail("Not yet implemented");
	}

}
