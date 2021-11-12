package imgurtests_refactoring;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static imgur.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateInfoTests extends BaseTests{

    RequestSpecification requestSpecificationForUpdate;


    @BeforeEach
    void uploadFileTest () {
        Response response= given(requestSpecificationWithAuthAndMultiPartImage)
                .post(PATH_FOR_IMAGE_UPLOAD)
                .prettyPeek();

        uploadedImageId = response.jsonPath().get("data.id");
        uploadedImageDeleteHash = response.jsonPath().get("data.deletehash");

        requestSpecificationForUpdate = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setBaseUri(GENERAL_PATH)
                .addFormParam("name", "klacky to jsou")
                .addFormParam("description", "this new info about image")
                .addFormParam("title", "new title")
                .build();

    }

    @DisplayName("Image Info Update Test")
    @Test
    void changeInfoAboutImage () {
        given(requestSpecificationForUpdate)
                .expect()
                .statusCode(200)
                .body("data", equalTo(true))
                .contentType("application/json")
                .when()
                .post("/image/{imageHash}", uploadedImageId)
                .prettyPeek();
    }

    @DisplayName("Favorite Image Test")
    @Test
    void favoriteImage () {
        given(requestSpecificationWithAuthWithBaseUri)
                .expect()
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .contentType("application/json")
                .when()
                .post("/image/{imageHash}/favorite", uploadedImageId)
                .prettyPeek();
    }
@AfterEach
    void tearDown () {
        given(requestSpecificationWithAuthWithBaseUri)
                .delete(PATH_FOR_IMAGE_DELETE, uploadedImageDeleteHash);
    }
}
