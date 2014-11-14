package cn.com.innodev.pdp.proprietor.services.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.innodev.pdp.community.Community;
import cn.com.innodev.pdp.community.Room;
import cn.com.innodev.pdp.community.services.CommunityService;
import cn.com.innodev.pdp.proprietor.Proprietor;
import cn.com.innodev.pdp.proprietor.services.ProprietorService;

import com.vanstone.business.ObjectDuplicateException;
import com.vanstone.dal.id.IDBuilder;

@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProprietorServiceImplTest {
	
	@Autowired
	private CommunityService communityService;
	@Autowired
	private ProprietorService proprietorService;
	
	@Test
	public void testAddProprietor() {
		Community community = this.communityService.getCommunity("jiujuyayuan");
		for (int i=0;i<20;i++) {
			try {
				proprietorService.addProprietor("test_weixin_id_" + IDBuilder.base58Uuid(), community);
			} catch (ObjectDuplicateException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testUpdateProprietorMobile() {
		try {
			proprietorService.authenticateMobile("Gq5KfEr9M5Yjm3KFrd4vLf", "13426173105");
		} catch (ObjectDuplicateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAuthenticateMobile() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateRooms() {
		Collection<Room> rooms = new ArrayList<Room>();
		for (int i=1;i<=10;i++) {
			rooms.add(this.communityService.getRoom(i));
		}
		String proprietorId = "Gq5KfEr9M5Yjm3KFrd4vLf";
		this.proprietorService.updateRooms(proprietorId, rooms);
	}
	
	@Test
	public void testgetProprietorsMap() {
		String[] ids = new String[]{ 
				"2XjsNJCuwGYpL3yCGLhvVX",
				"39kMLUdh5jcP4GYbyZE5Jz",
				"61wUy5nfdSPozcQzBCm6iB",
				"6M8xsyb2f3XWQmkDfjfF3u",
				"8fy9kx5LzKrfYwkEEAc1cs",
				"ASc61R4PJmaHTQoSJwFP2V",
				"FAKk27SEZsQW7yRx8x8thq",
				"Gq5KfEr9M5Yjm3KFrd4vLf",
				"GSrEwjst2dLpwFux5pJHps",
				"JieHHWVgsP5v6FFmHMfnbs",
				"kux9us2jCzMTRHEAQfgLC",
				"MAy3dc57Ff7yctNGbaZKUx",
				"MpiWMwfGnaLSPZ5K1fQJtc",
				"PCXM1qgq9pYrFT9j8uWyC3",
				"SgycDSCCeMfQeQbqRQ9Z45",
				"SiLDVTZPYygo3iN1dHuBNu",
				"SLdAcYM8kfSY9mfmpiS5pu",
				"Ur6nGm383m8iVrKdaFagCf",
				"Vh2wodQ7Hcu8xCBE3s86KC",
				"WnXkop6hibBvxiL3VfM9A",
				"XR8jJ98zPnJQh4K3sXfE7t"
		};
		
		Collection<String> proprietorIds = new ArrayList<String>();
		for (String id : ids ) {
			proprietorIds.add(id);
		}
		Map<String, Proprietor> dataMap = this.proprietorService.getProprietorsMap(proprietorIds);
		
		System.out.println("   ==================================================   ");
		if (dataMap != null && dataMap.size() >0) {
			for (Map.Entry<String, Proprietor> entry : dataMap.entrySet()) {
				System.out.println(ToStringBuilder.reflectionToString(entry.getValue(), ToStringStyle.SHORT_PREFIX_STYLE));
			}
		}else{
			System.err.println("错误");
		}
		System.out.println("   ==================================================   ");
	}
	
	@Test
	public void testUpdateBaseProprietor() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteProprietor() {
		fail("Not yet implemented");
	}

	@Test
	public void testForceDeleteProprietor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProprietor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProprietorByMobile() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProprietorByWeixinOpenId() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProprietorPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteHeadImageBean() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateHeadImageBean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProprietors() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalProprietors() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProprietorsMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddQAComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteQAComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQACommentsQAIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalQAComments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQACommentsCommunityIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalComments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalProprietorsByRoomId() {
		fail("Not yet implemented");
	}

}
