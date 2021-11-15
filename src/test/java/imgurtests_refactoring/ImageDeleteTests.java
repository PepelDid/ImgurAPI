package imgurtests_refactoring;

import imgur.dto.ImageResponse;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static imgur.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ImageDeleteTests extends BaseTests {

    @BeforeEach
    void beforeTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecificationForImage)
                .post(PATH_FOR_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(ImageResponse.class)
                .getData().getDeletehash();
    }

    @DisplayName("Positive Delete Test")
    @Test
    void deleteTest() {
        given(requestSpecificationWithAuthWithBaseUri, positiveResponseSpecificationForImage)
                .delete(PATH_FOR_IMAGE_DELETE, uploadedImageDeleteHash)
                .prettyPeek();
    }

    @DisplayName("Negative Delete Test")
    @Test
    void deleteTestWithWrongHash() {
        given(requestSpecificationWithAuthWithBaseUri)
                .delete(PATH_FOR_IMAGE_DELETE, WRONG_HASH)
                .prettyPeek()
                .then()
                .statusCode(403)
                .body("success", equalTo(false));
        tearDown();
    }

 //это здесь,чтобы за негативными тестами файлы подчищать
    void tearDown() {
        given(requestSpecificationWithAuthWithBaseUri)
                .delete(PATH_FOR_IMAGE_DELETE, uploadedImageDeleteHash);
    }
}