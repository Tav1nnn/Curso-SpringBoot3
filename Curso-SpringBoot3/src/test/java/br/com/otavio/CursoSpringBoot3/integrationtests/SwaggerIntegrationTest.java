package br.com.otavio.CursoSpringBoot3.integrationtests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.otavio.CursoSpringBoot3.configs.TestConfigs;
import br.com.otavio.CursoSpringBoot3.integrationtests.testcontainers.AbstractIntegrationTest;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest{
	
	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content = RestAssured.given()
			.basePath("/swagger-ui/index.html")
			.port(TestConfigs.SERVER_PORT)
			.when()
				.get()//for metodo get
			.then()//espero
				.statusCode(200)//status 200
			.extract()
				.body()
					.asString();
		
		Assertions.assertTrue(content.contains("Swagger UI"));
	}

}
