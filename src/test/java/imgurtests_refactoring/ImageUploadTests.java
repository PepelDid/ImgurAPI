package imgurtests_refactoring;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import imgur.dto.ImageResponse;
import io.qameta.allure.Story;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static imgur.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



@Story("Image Tests")
public class ImageUploadTests extends BaseTests{

    static MultiPartSpecification multiPartSpecWithPdf;
    static RequestSpecification requestSpecificationWithAuthAndPdf;


    @BeforeAll
    static void beforeTest () {
        multiPartSpecWithPdf = new MultiPartSpecBuilder(new File("src/test/resources/pdf.pdf"))
                .controlName("image")
                .build();
        requestSpecificationWithAuthAndPdf = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setBaseUri(GENERAL_PATH)
                .addFormParam("name", "pdf,no image")
                .addMultiPart(multiPartSpecWithPdf)
                .build();

    }

    @DisplayName("Base64 Image Upload Test")
    @Test
    void uploadBase64Test () {
        Response response = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecificationForImage)
                .post(PATH_FOR_IMAGE)
                .prettyPeek();

        assertThat(response.jsonPath().get("data.description"), equalTo("This is base64 image"));
        uploadedImageDeleteHash = response.jsonPath().get("data.deletehash");
        tearDown();

    }

    @DisplayName("File Image Upload Test")
    @Test
    void uploadFileTest () {
        uploadedImageDeleteHash = given(requestSpecificationWithAuthAndMultiPartImage)
                .expect()
                .body("data.description", equalTo("This is a test image"))
                .statusCode(200)
                .when()
                .post(PATH_FOR_IMAGE_UPLOAD)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(ImageResponse.class)
                .getData().getDeletehash();
        tearDown();
    }


    @DisplayName("No Image Upload Test")
    @Test
    void pdfFileUpload () {
        given(requestSpecificationWithAuthAndPdf)
                .expect()
                .statusCode(400)
                .body("data.error.message", equalTo("File type invalid (1)"))
                .body("status", equalTo(400))
                .when()
                .post(PATH_FOR_IMAGE)
                .prettyPeek();

    }

    void tearDown () {
        given(requestSpecificationWithAuthWithBaseUri)
                .delete(PATH_FOR_IMAGE_DELETE, uploadedImageDeleteHash);
    }
//его не аннотировала @AfterEach,потому что он мне фейлит негативный тест.В конце каждого позитивного вызываю.
}
