package com.example.demo;


//import io.restassured.RestAssured.*;
//import io.restassured.matcher.RestAssuredMatchers.*;
//import org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

//import io.restassured.itest.java.support.WithJetty;


//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private EmployeeController employeeController;

	@Test
	void contextLoads() {
	}

	@Test
	void BodyCheck() {
		assert(employeeController.all().stream().filter(e -> e.getName().equals("Bilbo Baggins")).collect(Collectors.toList()).size() >0);
	}

	@Test public void
	BodyTest2() {
		assert(employeeController.maxId().getName().equals("Frodo Baggins"));
	}

}
