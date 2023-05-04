package com.ddd.toy.pet.salon.domain;

import com.ddd.toy.pet.salon.AcceptanceTest;
import com.ddd.toy.pet.salon.application.UserService;
import com.ddd.toy.pet.salon.dto.PetRequest;
import com.ddd.toy.pet.salon.dto.UserRequest;
import com.ddd.toy.pet.salon.dto.UserResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("반려동물 관련 기능")
public class PetAcceptanceTest extends AcceptanceTest {

    @Autowired
    UserService userService;

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
        PetRequest petRequest = new PetRequest("애옹이", "M", 2, "cat");

        ExtractableResponse<Response> response =
                RestAssured.given().log().all()
                        .body(petRequest)
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
        assertThat(petNames).containsAnyOf(petRequest.getName());
    }

    /**
     * when 주인이 있는 반려동물을 생성하면
     * Then 반려동물이 생성되고 주인도 지정된다
     * Then 반려돌물의 정보를 조회하면 주인 정보를 알 수 있다.
     */
    @Test
    @DisplayName("주인이 있는 반려동물을 등록할 수 있다.")
    public void registerPetWithUser() {
        // given
        UserRequest userRequest = new UserRequest("jylim", "빠숑맘", "jylim@test.com", "1234");
        UserResponse userResponse = userService.saveUser(userRequest);

        // when
        PetRequest petRequest = new PetRequest("빠숑", "M", 6, "dog", userResponse.getUserNo());
        ExtractableResponse<Response> response =
                RestAssured.given().log().all()
                        .body(petRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .when().post("/pets")
                        .then().log().all()
                        .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        ExtractableResponse<Response> result = RestAssured.given().log().all()
                .when().get("/pets")
                .then().log().all()
                .extract();

        List<String> petNames = result.jsonPath().getList("name", String.class);
        List<String> ownerName = result.jsonPath().getList("userName", String.class);

        assertAll(
                () -> assertThat(petNames).containsAnyOf(petRequest.getName()),
                () -> assertThat(ownerName).containsAnyOf(userRequest.getName())
        );


    }

    /**
     * when 반려동물을 생성하고 등록한다
     * Then pet_id가 트리거로 업데이트 된다.
     * Then 반려돌물의 정보를 조회하면 pet_id가 조회된다.
     */
    @Test
    @DisplayName("주인이 있는 반려동물을 등록할 수 있다.")
    public void createPetIdWithTrigger() {
        // given
        UserRequest userRequest = new UserRequest("test", "테스터", "test@test.com", "1234");
        UserResponse userResponse = userService.saveUser(userRequest);

        // when
        PetRequest petRequest = new PetRequest("먼지", "M", 1, "puppy", userResponse.getUserNo());
        ExtractableResponse<Response> response =
                RestAssured.given().log().all()
                        .body(petRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .when().post("/pets")
                        .then().log().all()
                        .extract();

        PetRequest petRequest2 = new PetRequest("보솜이", "F", 2, "puppy", userResponse.getUserNo());
        ExtractableResponse<Response> response2 =
                RestAssured.given().log().all()
                        .body(petRequest2)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .when().post("/pets")
                        .then().log().all()
                        .extract();


        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response2.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        ExtractableResponse<Response> result = RestAssured.given().log().all()
                .when().get("/pets")
                .then().log().all()
                .extract();

        List<String> petIds = result.jsonPath().getList("petId", String.class);

        assertAll(
                () -> assertThat(petIds).isNotEmpty()
        );


    }
}
