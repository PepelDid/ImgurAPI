package retrofit.utils;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import retrofit.db.dao.CategoriesMapper;
import retrofit.db.dao.ProductsMapper;
import retrofit.db.model.Categories;
import retrofit.db.model.CategoriesExample;
import retrofit.db.model.Products;
import retrofit.db.model.ProductsExample;

import java.io.IOException;
@UtilityClass
public class DbUtils {
    private static  String resource = "mybatis-config.xml";
    static Faker faker = new Faker();
    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }
    @SneakyThrows
    public static CategoriesMapper getCategoriesMapper(){
        return getSqlSession().getMapper(CategoriesMapper.class);
    }
    @SneakyThrows
    public static ProductsMapper getProductsMapper() {
        return getSqlSession().getMapper(ProductsMapper.class);
    }

    public static void createNewCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.medical().hospitalName());
        categoriesMapper.insert(newCategory);
    }

    public static void createNewProduct(ProductsMapper productsMapper) {
        Products newProduct = new Products();
        newProduct.setTitle(faker.food().spice());
        newProduct.setPrice(355);
        newProduct.setCategory_id(2l);
       productsMapper.insert(newProduct);
    }

    public static void deleteProductByKey(ProductsMapper productsMapper, Long idProduct){
        productsMapper.deleteByPrimaryKey(idProduct);
   }

   public static void updateProductByKey (Products record){
       getProductsMapper().updateByPrimaryKey(record);
    }

    public static Products selectByPrimaryKey(ProductsMapper productsMapper, Long idProduct){
        return  productsMapper.selectByPrimaryKey(idProduct);
    }

    public static Integer countCategories(CategoriesMapper categoriesMapper) {
        long categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
        return Math.toIntExact(categoriesCount);
    }

    public static Integer countProducts(ProductsMapper productsMapper) {
        long products = productsMapper.countByExample(new ProductsExample());
        return Math.toIntExact(products);
    }
}

