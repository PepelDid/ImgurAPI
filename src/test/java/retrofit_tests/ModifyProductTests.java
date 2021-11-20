package retrofit_tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import retrofit.db.model.Products;
import retrofit.dto.Product;
import retrofit.utils.DbUtils;
import retrofit.utils.PrettyLogger;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ModifyProductTests extends BaseTests {


    @Test
    void modifyProductByBdMethodTest(){
        Products record = new Products();
        record.setId(Long.valueOf(idProduct));
        record.setTitle(faker.food().vegetable());
        record.setPrice((int)((Math.random() + 1) + 100));
        record.setCategory_id(2l);
        DbUtils.updateProductByKey(record);
    //обратилась к обновленному продукту для проверок
        Products updatedProductFromDB = DbUtils.selectByPrimaryKey(productsMapper, (Long.valueOf(idProduct)));
    //проверила
        assertThat(record.getId(), equalTo((Long.valueOf(updatedProductFromDB.getId()))));
        assertThat(record.getTitle(), equalTo(updatedProductFromDB.getTitle()));
        assertThat(record.getPrice(), equalTo(updatedProductFromDB.getPrice()));
    //удалила
        DbUtils.deleteProductByKey(productsMapper, record.getId());
    }

    @Test
    void modifyProductByIdTest() throws IOException {
        product.setId(idProduct);
        product.setTitle(faker.superhero().name());
        product.setPrice(500);
        //product.setCategoryTitle(CategoryType.ELECTRONICS.getTitle());
        //Я нашла косяк,Аркадий, выдает 500, только если пытаюсь поменять категорию.
        //На прочие обновления ответ 200

        System.out.println("This is info about updated product: " + product);
        Response<Product> response = productService.modifyProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());

        assertThat(response.body().getId(), equalTo(idProduct));
        assertThat(response.body().getPrice(), equalTo(500));

        Long id = Long.valueOf(idProduct);
        DbUtils.deleteProductByKey(productsMapper, id);

//Когда выдает 500, лог такой(прописала в нем все для наглядности,айдишник наследуется обновлением)
//INFO: <-- 201 http://80.78.248.82:8189/market/api/v1/products (271ms, unknown-length body)
//ноя 15, 2021 8:53:52 PM okhttp3.internal.platform.Platform log
//This is idProduct 14165
//INFO: Product(id=14165, title=Bruschette with Tomato, price=101, categoryTitle=Food, additionalProperties={})
//This is info about updated product: Product(id=14165, title=Illustrious Medusa, price=500, categoryTitle=Electronics, additionalProperties={})
//ноя 15, 2021 8:53:52 PM okhttp3.internal.platform.Platform log
//INFO: --> PUT http://80.78.248.82:8189/market/api/v1/products (83-byte body)
//ноя 15, 2021 8:53:53 PM okhttp3.internal.platform.Platform log
//INFO: <-- 500 http://80.78.248.82:8189/market/api/v1/products (198ms, unknown-length body)
//ноя 15, 2021 8:53:53 PM okhttp3.internal.platform.Platform log
//INFO: Response{protocol=http/1.1, code=500, message=, url=http://80.78.248.82:8189/market/api/v1/products}48.82:8189/market/api/v1/products}
    }

}
