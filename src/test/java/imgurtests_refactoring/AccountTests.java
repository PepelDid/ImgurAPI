package imgurtests_refactoring;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static imgur.Endpoints.PATH_AUTH;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@Story("4 account Tests")
public class AccountTests extends BaseTests{

    @Test
    void getAccountInfoTest(){
        given(requestSpecificationWithAuth, positiveResponseSpecification)
                .get("https://api.imgur.com/3/account/{username}", username);
    }

    @Test
    void getAccountInfoWithLoggingTest(){
        given(requestSpecificationWithLoggingAndBaseUri, positiveResponseSpecification)
                .get(PATH_AUTH, username)
                .prettyPeek();
    //себе для наглядности оставила .prettyPeek()
    }

    @Test
    void getAccountInfoWithAssertionsAfterTest(){
        Response response = given(requestSpecificationWithLoggingAndBaseUri)
                .get(PATH_AUTH, username)
                .prettyPeek();
        assertThat(response.jsonPath().get("data.url"), equalTo(username));
    }
    //и этот тест,с одним спеком запроса и проверками после, себе для наглядности тоже оставила.
    //Он сам по себе работает,но  при запуске всех тестов класса выдает ошибку:
    // groovy.lang.MissingPropertyException: No such property: v2 for class: java.lang.String
}
