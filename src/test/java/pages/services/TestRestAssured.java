package pages.services;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestRestAssured {

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "https://reqres.in/api";
        //RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void loginTest(){
        given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("token",notNullValue());
    }

    @Test
    public void getSingleUserTest(){
        given()
                .get("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id",equalTo(2));

    }

    @Test
    public void deleteUserTest(){
        given()
                .delete("users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void patchUserTest(){
        String nameUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString("name");
        assertThat(nameUpdated, equalTo("morpheus"));

    }

    @Test
    public void putUserTest(){
        String job = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .put("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString("job");
        assertThat(job, equalTo("zion resident"));

    }

    @Test
    public void testPostUser(){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Alejandra");
        map.put("job", "Costumer Success");

        given()
                .log().all()
                .body(map.toString())
        .when()
                .post("/users")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
