package myapp.api;

import java.util.List;

import myapp.model.Employee;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {

    @GET("?apicall=get_all_employees")
    Call<List<Employee>> getEmployees();

    @GET("?apicall=add_release")
    Call<List<Employee>> addRelease();

    // @Query("apicall") String apiCall
    /*
    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    @POST("users/new")
    Call<User> createUser(@Body User user);
    */
}
