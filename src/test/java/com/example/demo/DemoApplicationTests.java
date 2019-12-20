package com.example.demo;


import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import io.restassured.itest.java.support.WithJetty;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class DemoApplicationTests extends WithJetty {

	@Test
	void contextLoads() {
	}

	@Test
	void BodyCheck() {
		get("/employees").then().body("class", equalTo("thief"));
	}

	@Test public void
	BodyTest2() {
		given().body("hullo").expect().statusCode(200).when().get("/getWithContent");
	}

}
