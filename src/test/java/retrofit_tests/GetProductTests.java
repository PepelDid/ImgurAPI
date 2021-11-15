package retrofit_tests;

import org.junit.jupiter.api.Test;
import retrofit.dto.Product;
import retrofit.enums.CategoryType;
import retrofit.utils.PrettyLogger;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetProductTests extends BaseTests{

    @Test
    void getAllProducts() throws IOException {
        Response<ArrayList<Product>> response = productService.getAllProducts().execute();
        PrettyLogger.DEFAULT.log(response.body().toString());

       assertThat(response.code(), equalTo(200));
      // assertThat(response.body().getId(), equalTo(idProduct));
    }
    @Test
    void getProductByIdTest() throws IOException {
        Response<Product> response = productService.getProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(idProduct));
    }

    @Test
    void getProductByNotExistIdTest() throws IOException {
        idProduct = 8954;
        Response<Product> response = productService.getProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.toString());

        assertThat(response.code(), equalTo(404));
    }

    @Test
    void getProductByNegativetIdTest() throws IOException {
        idProduct = -89;
        Response<Product> response = productService.getProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.toString());

        assertThat(response.code(), equalTo(404));
    }
}
