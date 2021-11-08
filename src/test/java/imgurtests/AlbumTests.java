package imgurtests;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AlbumTests extends BaseTest{
    static String uploadedImageId1;
    static String uploadedImageId2;
    static String uploadedImageId3;
    static String uploadedImageDeleteHash1;
    static String uploadedImageDeleteHash2;
    static String uploadedImageDeleteHash3;
    static String albumHash;


    @BeforeAll
    static void uploadImagesForAlbum () {
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_TEMP_IMAGE))
                .multiPart("title", "image1 for album")
                .when()
                .post("https://api.imgur.com/3/image");
        uploadedImageDeleteHash1 = response.jsonPath().get("data.deletehash");
        uploadedImageId1 = response.jsonPath().get("data.id");

        Response response2 = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_TEMP_IMAGE2))
                .multiPart("title", "image2 for album")
                .when()
                .post("https://api.imgur.com/3/image");
        uploadedImageDeleteHash2 = response2.jsonPath().get("data.deletehash");
        uploadedImageId2 = response2.jsonPath().get("data.id");

        Response response3 = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_TEMP_IMAGE3))
                .multiPart("title", "image3 for album")
                .when()
                .post("https://api.imgur.com/3/image");
        uploadedImageDeleteHash3 = response3.jsonPath().get("data.deletehash");
        uploadedImageId3 = response3.jsonPath().get("data.id");
//это я для себя написала ориентир,чтобы видеть,что метод выполнился и переменные приняли значения
        System.out.println("hash is " + uploadedImageId1 + ", deletehash is " + uploadedImageDeleteHash1
                + "hash2 is " + uploadedImageId2 + ", deletehash2 is " + uploadedImageDeleteHash2
                + "hash3 is " + uploadedImageId3 + ", deletehash3 is " + uploadedImageDeleteHash3);
    }

    @DisplayName("Album Creation Test")
    @Test
    void albumCreation(){
        Response response = given()
                .header("Authorization", token)
                .multiPart("ids[]", uploadedImageId1)
                .multiPart("ids[]", uploadedImageId2)
                .multiPart("description", "This albums contains arcana" )
                .multiPart("title", "Tarot album")
                .when()
                .post("https://api.imgur.com/3/album")
                .prettyPeek();
        assertThat(response.jsonPath().get("success"), equalTo(true));

        albumHash = response.jsonPath().get("data.id");


    }

    @DisplayName("Album Update Test")
    @Test
    void albumUpdate(){
        given()
                .header("Authorization", token)
                .multiPart("description", "The new description" )
                .multiPart("title", "The new Tarot album")
                .when()
                .put("https://api.imgur.com/3/album/{albumHash}", albumHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("Album Image2 Get Test")
    @Test
    void getImageFromAlbum(){
        given()
                .header("Authorization", token)
                .expect()
                .statusCode(200)
                .body("data.title", equalTo("image2 for album"))
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/album/{albumHash}/image/{imageHash}", albumHash, uploadedImageId2)
                .prettyPeek();
    }

    @DisplayName("Add Image3 to Album Test")
    @Test
    void addImageToAlbum(){
        given()
                .header("Authorization", token)
                .multiPart("ids[]", uploadedImageId3)
                .expect()
                .statusCode(200)
                .body("data", equalTo(true))
                .contentType("application/json")
                .when()
                .post("https://api.imgur.com/3/album/{albumHash}/add", albumHash)
                .prettyPeek();
    }

    @DisplayName("Remove Images From Album Test")
    @Test
    void removeImagesFromAlbum(){
        given()
                .header("Authorization", token)
                .multiPart("ids[]", uploadedImageId1)
                .multiPart("ids[]", uploadedImageId2)
                .multiPart("ids[]", uploadedImageId3)
                .expect()
                .statusCode(200)
                .body("data", equalTo(true))
                .contentType("application/json")
                .when()
                .post("https://api.imgur.com/3/album/{albumHash}/remove_images", albumHash)
                .prettyPeek();
    }

    @DisplayName("Delete Album Test")
    @Test
    void deleteAlbum(){
        given()
                .header("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/album/{albumHash}", albumHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @AfterAll
    static void deleteAllImages () {
        String[] arrayHash = {
                String.valueOf(uploadedImageDeleteHash1),
                String.valueOf(uploadedImageDeleteHash2),
                String.valueOf(uploadedImageDeleteHash3)
        };
        for(int i=0; i<3; i++) {
            given()
                    .header("Authorization", token)
                    .when()
                    .delete("https://api.imgur.com/3/image/{deleteHash}", arrayHash[i])
                    .prettyPeek()
                    .then()
                    .statusCode(200);
        }
    }

}
