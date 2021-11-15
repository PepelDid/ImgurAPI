package retrofit_tests;

import org.junit.jupiter.api.Test;
import retrofit.dto.Category;
import retrofit.dto.Product;
import retrofit.enums.CategoryType;
import retrofit.utils.PrettyLogger;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetCategoryTests extends BaseTests{
    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Category> response = categoryService.getCategory(id).execute();

        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }

    @Test
    void getCategoryByWrongIdTest() throws IOException {
        Integer id = 5;
        Response response = categoryService.getCategory(id).execute();
        PrettyLogger.DEFAULT.log(response.toString());

        assertThat(response.code(), equalTo(404));
    }

    @Test
    void getCategoryByNegativeIdTest() throws IOException {
        Integer id = -7;
        Response response = categoryService.getCategory(id).execute();
        PrettyLogger.DEFAULT.log(response.toString());

        assertThat(response.code(), equalTo(404));
    }

    @Test
    void getProductByIdTest() throws IOException {
        Response<Product> response = productService.getProduct(idProduct).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(idProduct));
    }
}
