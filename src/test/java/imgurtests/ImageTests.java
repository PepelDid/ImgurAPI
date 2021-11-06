package imgurtests;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import io.qameta.allure.Story;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@Story("Image Tests")
public class ImageTests extends BaseTest{
    private final String PATH_TO_IMAGE = "src/test/resources/9wands.jpg";
    static String encodedFile;
    private String uploadedImageId;

    @BeforeEach
    void beforeTest(){
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);
    }

    private byte[] getFileContent(){
        byte[] byteArray = new  byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(PATH_TO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }


    @DisplayName("Base64 Image Upload Test")
    @Test
    void uploadFileTest(){
        uploadedImageId = given()
                .header("Authorization", token)
                .multiPart("image", encodedFile)
                .multiPart("name", "9wands")
                .multiPart("description", "This is a test image")
                .expect()
                .body("data.id", is(notNullValue()))
                .body("success", is(true))
                .body("data.description", is("This is a test image"))
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }

    @DisplayName("Image Delete Test")
    @Test
    void xtearDown(){
            given()
                    .header("Authorization", token)
                    .when()
                    .delete("https://api.imgur.com/3/image/{deleteHash}", uploadedImageId)
                    .prettyPeek()
                    .then()
                    .statusCode(200);
    }

}
