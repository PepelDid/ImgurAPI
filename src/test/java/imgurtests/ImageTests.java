package imgurtests;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import io.qameta.allure.Story;

import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;


@Story("Image Tests")
public class ImageTests extends BaseTest{

    private final String PATH_TO_IMAGE = "src/test/resources/9wands.jpg";
    static String encodedFile;
    static String uploadedImageDeleteHash;
    static String uploadedImageId;

    @BeforeEach
    void beforeTest () {
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);
    }

    private byte[] getFileContent () {
        byte[] byteArray = new byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(PATH_TO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }


    @DisplayName("Base64 Image Upload Test")
    @Test
    void uploadFileTest () {
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", encodedFile)
                .multiPart("name", "9wands")
                .multiPart("description", "This is a test image")
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek();

        assertThat(response.jsonPath().get("data.description"), equalTo("This is a test image"));
        assertThat(response.jsonPath().get("success"), equalTo(true));

        uploadedImageDeleteHash = response.jsonPath().get("data.deletehash");
        uploadedImageId = response.jsonPath().get("data.id");
    }


    @DisplayName("Image Info Update Test")
    @Test
    void changeInfoAboutImage () {
        given()
                .header("Authorization", token)
                .multiPart("description", "this new info about image")
                .multiPart("title", "sun")
                .expect()
                .statusCode(200)
                .body("data", equalTo(true))
                .body("status", equalTo(200))
                .contentType("application/json")
                .when()
                .post("https://api.imgur.com/3/image/{imageHash}", uploadedImageId)
                .prettyPeek();
    }

    @DisplayName("Favorite Image Test")
    @Test
    void favoriteImage () {
        given()
                .header("Authorization", token)
                .multiPart("title", "sun")
                .expect()
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .contentType("application/json")
                .when()
                .post("https://api.imgur.com/3/image/{imageHash}/favorite", uploadedImageId)
                .prettyPeek();
    }

    @DisplayName("Image Get Test")
    @Test
    void getImage () {
        //    uploadTemporaryImage();
        given()
                .header("Authorization", token)
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}", uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("No Image Upload Test")
    @Test
    void pdfFileUpload () {
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", new File("src/test/resources/pdf.pdf"))
                .multiPart("name", "pdf, no image")
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.error.message"), equalTo("File type invalid (1)"));
        assertThat(response.jsonPath().get("status"), equalTo(400));
    }


    // @DisplayName("Image Delete Test") эта аннотация перестает действовать? он не получает имени в цепочке тестов,но выполняется
    @AfterAll
    static void tearDown () {
        given()
                .header("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/image/{deleteHash}", uploadedImageDeleteHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}

