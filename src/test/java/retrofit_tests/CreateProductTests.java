package retrofit_tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit.dto.Category;
import retrofit.dto.Product;
import retrofit.enums.CategoryType;
import retrofit.service.CategoryService;
import retrofit.service.ProductService;
import retrofit.utils.PrettyLogger;
import retrofit.utils.RetrofitUtils;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreateProductTests extends BaseTests{

   @Test
    void postProductWithIdTest() throws IOException {
       product = new Product()
               .withId(567)
               .withTitle(faker.food().fruit())
               .withPrice((int)((Math.random() + 1) + 100))
               .withCategoryTitle(CategoryType.FOOD.getTitle());

        Response<Product> response = productService.createProduct(product).execute();
       PrettyLogger.DEFAULT.log(response.toString());
       assertThat(response.code(), equalTo(400));

    }


    }

