package retrofit_tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import retrofit.dto.Product;
import retrofit.enums.CategoryType;
import retrofit.service.CategoryService;
import retrofit.service.ProductService;
import retrofit.utils.PrettyLogger;
import retrofit.utils.RetrofitUtils;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

public abstract class BaseTests {
    static Retrofit client;
    static ProductService productService;
    static CategoryService categoryService;
    Product product;
    Faker faker = new Faker();
    static Integer idProduct;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
    }

    @BeforeEach
    void setUp(){
        product = new Product()
                .withTitle(faker.food().dish())
                .withPrice((int)((Math.random() + 1) + 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

   @BeforeEach
    void createProduct() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        idProduct = response.body().getId();
        }

}
