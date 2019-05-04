package com.example.demopageable;

import com.example.demopageable.model.UserInfoStatistics;
import com.example.demopageable.repository.PurchaseOrderStatisticCustomRepository;
import com.example.demopageable.repository.PurchaseOrderStatisticRepository;
import com.example.demopageable.service.IProcessUserInfo;
import com.example.demopageable.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemopageableApplicationTests {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private PurchaseOrderStatisticRepository repository;

	@Autowired
	private IProcessUserInfo iProcessUserInfo;

	@Autowired
	private PurchaseOrderStatisticCustomRepository customRepository;


	@Test
	public void testCustomRepository() {
		int countUniqUsers = customRepository.getCountUniqUsers();
		System.out.println(countUniqUsers);
	}

	@Test
	public void contextLoads() throws IOException {
//		userInfoService.getUserData();
		boolean b = repository.existsByUserId(19L);
		System.out.println(b);
	}

	@Test
	public void test2() {
		UserInfoStatistics userInfoStatistics = iProcessUserInfo.processUserInfoData();
		System.out.println(userInfoStatistics);
	}

}
