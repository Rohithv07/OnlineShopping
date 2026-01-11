package com.rohith.microservices.order;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderServiceApplicationTests {

	@Container
	@ServiceConnection
	static MySQLContainer mysqlContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = """
				{
				     "skuCode": "iphone_15",
				     "price": 1000,
				     "quantity": 1
				}
				""";

		String responseBodyString = RestAssured.given().contentType("application/json").body(submitOrderJson).when()
				.post("/api/order").then().log().all().statusCode(201).extract().body().asString();

		assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
	}

}
