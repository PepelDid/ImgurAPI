package imgurtests_refactoring;

import com.fasterxml.jackson.databind.json.JsonMapper;
import imgur.dto.ImageResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;


import static imgur.Endpoints.PATH_FOR_IMAGE_DELETE;
import static imgur.Endpoints.PATH_FOR_IMAGE_UPLOAD;
import static io.restassured.RestAssured.given;


public class ImageGetTests extends BaseTests {

    @BeforeEach
    void uploadFileTest() {
        Response response = given(requestSpecificationWithAuthAndMultiPartImage)
                .post(PATH_FOR_IMAGE_UPLOAD)
                .prettyPeek();

        uploadedImageId = response.jsonPath().get("data.id");
        uploadedImageDeleteHash = response.jsonPath().get("data.deletehash");

    }

    @DisplayName("Image Get Test")
    @Test
    void getImage() {
        given(requestSpecificationWithAuthWithBaseUri, positiveResponseSpecificationForImage)
                .get("/image/{imageHash}", uploadedImageId)
                .prettyPeek();
// выдает ошибку как при самостоятельном, так и при групповом запуске
// groovy.lang.MissingPropertyException: No such property: v2 for class: java.lang.String
    }

    @DisplayName("Account Images Get Test")
    @Test
    void getImages() {
        given(requestSpecificationWithAuthWithBaseUri)
                .get("/account/me/images")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @AfterEach
    void tearDown() {
        given(requestSpecificationWithAuthWithBaseUri)
                .delete(PATH_FOR_IMAGE_DELETE, uploadedImageDeleteHash);

    }
}
