package com.example.demopageable;

import com.example.demopageable.repository.PurchaseOrderStatisticRepository;
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

	@Test
	public void contextLoads() throws IOException {
//		userInfoService.getUserData();
		boolean b = repository.existsByUserId(19L);
		System.out.println(b);
	}

	@Test
	public void test2() {
		Long test = userInfoService.test();
		System.out.println(test);
	}

}
