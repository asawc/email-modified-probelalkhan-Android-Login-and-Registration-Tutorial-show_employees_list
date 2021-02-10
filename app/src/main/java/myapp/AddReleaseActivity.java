package myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.simplifiedcoding.simplifiedcoding.R;

import java.util.List;

import myapp.api.ApiBuilder;
import myapp.api.MyApi;
import myapp.model.Employee;
import myapp.modelView.EmployeeView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReleaseActivity extends AppCompatActivity {

    List<EmployeeView> employeeViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_add_activity);

        Spinner spin = (Spinner) findViewById(R.id.employees_spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        } );
        // wykonanie zapytania - pobranie pracowników
        MyApi apiService = new ApiBuilder().getApiService();
        Call<List<Employee>> call = apiService.getEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                int statusCode = response.code();
                List<Employee> empls = response.body();
                Log.d("Get employees: ", response.toString());
                for(Employee emp : empls) {
                    employeeViews.add(new EmployeeView(emp.getSymbol(), emp.getName(), emp.getSurname()));
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                // Log error here since request failed
                Log.d("Get employees: ", "FAIL");
            }
        });

        //R.layout.spinner_dropdown_item
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, employeeViews);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
//        spin.setAdapter(adapter);
    }


}