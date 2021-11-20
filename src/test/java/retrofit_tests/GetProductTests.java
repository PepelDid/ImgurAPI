package retrofit_tests;

import org.junit.jupiter.api.Test;
import retrofit.db.model.Products;
import retrofit.dto.Product;
import retrofit.enums.CategoryType;
import retrofit.utils.DbUtils;
import retrofit.utils.PrettyLogger;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetProductTests extends BaseTests{

    @Test
    void getProductFromBDTest() throws IOException {
        Long id = Long.valueOf(idProduct);
        Products productFromDB = DbUtils.selectByPrimaryKey(productsMapper, id);
        productFromDB.toString();
//получила продукт из БД

        assertThat(productFromDB.getId(), equalTo(id));
        assertThat(productFromDB.getTitle(), equalTo(product.getTitle()));
//проверила

        DbUtils.deleteProductByKey(productsMapper, id);
//удалила
    }

    @Test
    void getAllProducts() throws IOException {
        Response<ArrayList<Product>> response = productService.getAllProducts().execute();
        PrettyLogger.DEFAULT.log(response.body().toString());

       assertThat(response.code(), equalTo(200));

        Long id = Long.valueOf(idProduct);
        DbUtils.deleteProductByKey(productsMapper, id);
    }
    @Test
    void getProductByIdTest() throws IOException {
        Response<Product> response = productService.getProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(idProduct));

        Long id = Long.valueOf(idProduct);
        DbUtils.deleteProductByKey(productsMapper, id);
    }


    @Test
    void getProductByNotExistIdTest() throws IOException {
        idProduct = 8954;
        Response<Product> response = productService.getProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.toString());

        assertThat(response.code(), equalTo(404));

        Long id = Long.valueOf(idProduct);
        DbUtils.deleteProductByKey(productsMapper, id);
    }

    @Test
    void getProductByNegativeIdTest() throws IOException {
        idProduct = -89;
        Response<Product> response = productService.getProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.toString());

        assertThat(response.code(), equalTo(404));

        Long id = Long.valueOf(idProduct);
        DbUtils.deleteProductByKey(productsMapper, id);
    }
}
