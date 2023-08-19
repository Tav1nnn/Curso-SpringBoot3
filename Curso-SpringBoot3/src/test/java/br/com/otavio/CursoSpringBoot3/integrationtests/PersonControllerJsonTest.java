package br.com.otavio.CursoSpringBoot3.integrationtests;


import br.com.otavio.CursoSpringBoot3.configs.TestsConfigs;
import br.com.otavio.CursoSpringBoot3.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.otavio.CursoSpringBoot3.integrationtests.vo.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest{

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static PersonVO person;

	@BeforeAll
	public static void setup(){
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		person = new PersonVO();
	}

	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockperson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_OTAVIO)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		var content = RestAssured.given().spec(specification)
				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
					.body(person)
					.when()
					.post()
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();

		PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
		person = createdPerson;

		assertNotNull(createdPerson);

		assertNotNull(createdPerson.getId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());

		assertTrue(createdPerson.getId() > 0);

		assertEquals("Otavio", createdPerson.getFirstName());
		assertEquals("Marques", createdPerson.getLastName());
		assertEquals("New York City", createdPerson.getAddress());
		assertEquals("m", createdPerson.getGender());

	}

	@Test
	@Order(2)
	public void testCreateWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
		mockperson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_OTAVIO_ERROR)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		var content = RestAssured.given().spec(specification)
				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
					.body(person)
				.when()
					.post()
				.then()
					.statusCode(403)
						.extract()
						.body()
							.asString();

		System.out.println(content);
		assertNotNull(content);
		assertEquals("Invalid CORS request", content);
	}

	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
		mockperson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_OTAVIO)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		var content = RestAssured.given().spec(specification)
				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
					.pathParams("id", person.getId())
					.when()
					.get("{id}")
				.then()
					.statusCode(200)
						.extract()
							.body()
								.asString();

		PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
		person = createdPerson;

		assertNotNull(createdPerson);

		assertNotNull(createdPerson.getId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());

		assertTrue(createdPerson.getId() > 0);

		assertEquals("Otavio", createdPerson.getFirstName());
		assertEquals("Marques", createdPerson.getLastName());
		assertEquals("New York City", createdPerson.getAddress());
		assertEquals("m", createdPerson.getGender());

	}

	private void mockperson() {
		person.setFirstName("Otavio");
		person.setLastName("Marques");
		person.setAddress("New York City");
		person.setGender("m");
	}

}
