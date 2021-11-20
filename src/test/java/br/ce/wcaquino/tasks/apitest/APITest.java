package br.ce.wcaquino.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup( ) {
		
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
		
	}

	@Test
	public void deveRetornarTarefas() {
		
		RestAssured.given()
		.when()
		    .get("/todo")
		.then()
		    .statusCode(200)
		;
		
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		
		RestAssured.given()
		.body("{\"task\": \"Teste via API\", \"dueDate\": \"2021-11-25\"}")
		.contentType(ContentType.JSON)
		.when()
		    .post("/todo")
		.then()
		    .statusCode(201)
		;
		
	}
	
	
	@Test
	public void naoDeveAdicionarTarefaInvalida() {
		
		RestAssured.given()
		.body("{\"task\": \"Teste via API\", \"dueDate\": \"2011-11-15\"}")
		.contentType(ContentType.JSON)
		.when()
		    .post("/todo")
		.then()
		    .statusCode(400)
		    .body("message", CoreMatchers.is("Due date must not be in past"))
		;
		
	}	
	
}
