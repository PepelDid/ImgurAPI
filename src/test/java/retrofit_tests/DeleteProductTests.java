package retrofit_tests;

import org.junit.jupiter.api.Test;
import retrofit.dto.Product;
import retrofit.utils.DbUtils;
import retrofit.utils.PrettyLogger;
import retrofit2.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.IOException;


public class DeleteProductTests extends BaseTests{
    @Test
    void deleteProductByIdTest() throws IOException {
        Response response =  productService.deleteProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.headers().toString());
        assertThat(response.code(), equalTo(200));
    }

    @Test
    void deleteProductByNotExistIdTest() throws IOException {
        idProduct = 12809;

        Response response = productService.deleteProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.headers().toString());
        assertThat(response.code(), equalTo(500));
    }

}
