package com.ddd.toy.pet.salon.domain;

import com.ddd.toy.pet.salon.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("반려동물 관련 기능")
public class PetAcceptanceTest extends AcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    /**
     * when 반려동물을 생성하면
     * Then 반려동물이 생성된다
     * Then 반려동물 목록 조회 시 생성한 반려동물을 찾을 수 있다
     */
    @Test
    @DisplayName("반려동물을 등록할 수 있다.")
    public void registerPet() {
        // when
        Pet pet = new Pet("빠숑", "M", 6, "dog");

        ExtractableResponse<Response> response =
                RestAssured.given().log().all()
                        .body(pet)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .when().post("/pets")
                        .then().log().all()
                        .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        //then
        List<String> petNames =
                RestAssured.given().log().all()
                        .when().get("/pets")
                        .then().log().all()
                        .extract().jsonPath().getList("name", String.class);
        assertThat(petNames).containsAnyOf("빠숑");
    }
}
