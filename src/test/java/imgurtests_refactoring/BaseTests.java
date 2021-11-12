package imgurtests_refactoring;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.util.Base64;
import java.util.Properties;

import static imgur.Endpoints.GENERAL_PATH;
import static imgur.Endpoints.PATH_FOR_IMAGE_DELETE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public abstract class  BaseTests {
    static Properties properties = new Properties();
    static String token;
    static String username;
    static String PATH_TO_IMAGE = "src/test/resources/9wands.jpg";

    static String encodedFile;
    static String uploadedImageDeleteHash;
    static String uploadedImageId;

    static RequestSpecification requestSpecificationWithAuth;
    static RequestSpecification requestSpecificationWithLoggingAndBaseUri;
    static RequestSpecification requestSpecificationWithAuthWithBaseUri;

    static ResponseSpecification positiveResponseSpecification;
    static ResponseSpecification positiveResponseSpecificationForImage;

    static MultiPartSpecification base64MultipartSpec;
    static RequestSpecification requestSpecificationWithAuthWithBase64;
    static MultiPartSpecification multiPartSpecWithFile;
    static RequestSpecification requestSpecificationWithAuthAndMultiPartImage;


    @BeforeAll
    static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());

        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");

//блок  аутентификации
        requestSpecificationWithAuth = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .build();

        requestSpecificationWithAuthWithBaseUri = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setBaseUri(GENERAL_PATH)
                .build();

        requestSpecificationWithLoggingAndBaseUri = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setBaseUri(GENERAL_PATH)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .build();

//блок универсальных ответов
        positiveResponseSpecification = new ResponseSpecBuilder()
                .expectBody("status", equalTo(200))
                .expectBody("success", is(true))
                .expectBody("data.url", equalTo(username))
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();

        positiveResponseSpecificationForImage = new ResponseSpecBuilder()
                .expectBody("status", equalTo(200))
                .expectBody("success", is(true))
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();

//Блок загрузки картинок
        byte[] byteArray = getFileContent(PATH_TO_IMAGE);
        encodedFile = Base64.getEncoder().encodeToString(byteArray);

        base64MultipartSpec = new MultiPartSpecBuilder(encodedFile)
                .controlName("image")
                .build();

        requestSpecificationWithAuthWithBase64 = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setBaseUri(GENERAL_PATH)
                .addFormParam("name", "9wands")
                .addFormParam("description", "This is base64 image")
                .addMultiPart(base64MultipartSpec)
                .build();

        multiPartSpecWithFile = new MultiPartSpecBuilder(new File(PATH_TO_IMAGE))
                .controlName("image")
                .build();

        requestSpecificationWithAuthAndMultiPartImage = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .setBaseUri(GENERAL_PATH)
                .addFormParam("name", "9wands")
                .addFormParam("type", "jpg")
                .addFormParam("description", "This is a test image")
                .addMultiPart(multiPartSpecWithFile)
                .build();
    }


    private static void getProperties() {
        try {
            InputStream input = new FileInputStream("src/test/resources/application.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getFileContent(String path) {
        byte[] byteArray = new byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

}
