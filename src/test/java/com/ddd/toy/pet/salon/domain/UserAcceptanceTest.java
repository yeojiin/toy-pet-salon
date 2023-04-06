package com.ddd.toy.pet.salon.domain;

import com.ddd.toy.pet.salon.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("사용자 관련 기능")
public class UserAcceptanceTest extends AcceptanceTest {
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    /**
     * When 사용자를 생성하면
     * Then 사용자가 생성된다
     * Then 사용자 목록 조회 시 생성한 사용자를 찾을 수 있다
     */
    @Test
    @DisplayName("사용자를 등록한다")
    void registerUserTest() {
        // when
        User user1 = new User("admin", "관리자", "admin@test.com", "1234");

        ExtractableResponse<Response> response =
                RestAssured.given().log().all()
                        .body(user1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .when().post("/users")
                        .then().log().all()
                        .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        // then
        List<String> userNames =
                RestAssured.given().log().all()
                        .when().get("/users")
                        .then().log().all()
                        .extract().jsonPath().getList("name", String.class);
        assertThat(userNames).containsAnyOf("관리자");
    }

}
