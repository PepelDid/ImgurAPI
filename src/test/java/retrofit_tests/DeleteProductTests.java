package retrofit_tests;

import org.junit.jupiter.api.Test;
import retrofit.dto.Product;
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
        product.setId(idProduct);
        product.setTitle(null);
        product.setPrice(null);
        product.setCategoryTitle(null);

        Response<Product> response = productService.modifyProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.toString());

        Response response1 =  productService.deleteProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response1.headers().toString());
        assertThat(response.code(), equalTo(204));

        //здесь я хотела получить 204 ответ, No Content. Выставила продукту нулевые поля.Но опять пошла 500 ошибка
        //а дальнейшее стирание прошло как обычно с 200 кодом. Не получилось у меня симулировать пустой продукт.

    }
}
