package retrofit_tests;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit.db.model.Categories;
import retrofit.dto.Category;
import retrofit.dto.Product;
import retrofit.enums.CategoryType;
import retrofit.service.CategoryService;
import retrofit.service.ProductService;
import retrofit.utils.DbUtils;
import retrofit.utils.PrettyLogger;
import retrofit.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class CreateProductAndCategoryTests extends BaseTests{

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

    @Test
    void createProductWithBdAssertion() throws IOException {
       Integer countProductsBefore = DbUtils.countProducts(productsMapper);

       Response<Product> response = productService.createProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());

        Integer countProductsAfter = DbUtils.countProducts(productsMapper);

        assertThat(countProductsAfter, equalTo(countProductsBefore+1));

        PrettyLogger.DEFAULT.log(String.valueOf(countProductsBefore));
        PrettyLogger.DEFAULT.log(String.valueOf(countProductsAfter));

        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));

        // Удаление продукта средствами работы с БД:
        idProduct = response.body().getId();
        Long id = Long.valueOf(idProduct);
        DbUtils.deleteProductByKey(productsMapper, id);
    }

   @Test
   void createProductWithBdMethod(){
       Integer countProductsBefore = DbUtils.countProducts(productsMapper);
       DbUtils.createNewProduct(productsMapper);
       Integer countProductsAfter = DbUtils.countProducts(productsMapper);
       assertThat(countProductsAfter, equalTo(countProductsBefore+1));
       //я бы его удалила,но не знаю,как мне достать из базы его айдишник
   }


    @Test
    void createCategoryWithBdMethodAndAssertion() throws IOException {
       Integer countCategoriesBefore = DbUtils.countCategories(categoriesMapper);
       DbUtils.createNewCategory(categoriesMapper);
       Integer countCategoriesAfter = DbUtils.countCategories(categoriesMapper);
       assertThat(countCategoriesAfter, equalTo(countCategoriesBefore+1));
        //та же история

    }


    }

