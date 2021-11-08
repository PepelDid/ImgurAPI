package imgurtests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public abstract class  BaseTest {
    static Properties properties = new Properties();
    static String token;
    static String username;
    static String PATH_TO_TEMP_IMAGE = "src/test/resources/sun.jpg";
    static String PATH_TO_TEMP_IMAGE2 = "src/test/resources/sila.jpg";
    static String PATH_TO_TEMP_IMAGE3 = "src/test/resources/chariot.jpg";



    @BeforeAll
    static void beforeAll(){
       // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
       // RestAssured.filters(new AllureRestAssured());
        /* это я закомментила на всякий случай,потому что у меня проблемы с плагином surefire, при тесте с правой панели:
        [INFO] --- maven-surefire-plugin:3.0.0-M5:test (default-cli) @ ImgurAPI ---
        [WARNING] Error injecting: org.apache.maven.plugin.surefire.SurefirePlugin
        java.lang.NoClassDefFoundError: org/apache/maven/surefire/api/testset/TestSetFailedException
        Я пробовала разные конфигурации в поме прописывать,но не помогает*/
        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");
    }

    private static void getProperties() {
        try {InputStream input = new FileInputStream("src/test/resources/application.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

