package br.com.roger.integrationtests.controller.withjson;

import br.com.roger.configs.TestConfigs;
import br.com.roger.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.roger.integrationtests.vo.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTests extends AbstractIntegrationTest {

	private static RequestSpecification specifacation;
	private static ObjectMapper objectMapper;
	private static PersonVO person;

	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		person = new PersonVO();
	}


	@Test
	@Order(1)
	public void shouldDisplaySwaggerUiPage() throws JsonProcessingException {

		mockPerson();

		specifacation = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "https://roger.com.br")
				.setBasePath("/api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var context =
			given().spec(specifacation)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(person)
				.when()
					.post()
				.then()
					.statusCode(200)
				.extract()
					.body().asString();

		PersonVO createdPerson = objectMapper.readValue(context, PersonVO.class);
		person = createdPerson;

		assertTrue(createdPerson.getId() > 0);

	}

	private void mockPerson() {
		person.setFirstName("Mamphis");
		person.setLastName("Depay");
		person.setGender("Male");
		person.setAddress("Parque São Jorge");
	}

}
