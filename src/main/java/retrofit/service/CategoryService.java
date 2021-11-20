package retrofit.service;

import retrofit.dto.Category;
import retrofit2.Call;
import retrofit2.http.*;

public interface CategoryService {

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") int id);

    @POST("categories")
    Call<Category> createCategory(@Body Category category);


}
