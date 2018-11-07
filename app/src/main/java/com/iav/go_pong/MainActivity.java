package com.iav.go_pong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.iav.go_pong.adapter.MenuAdapter;
import com.iav.go_pong.helper.Config;
import com.iav.go_pong.model.MenuModel;
import com.iav.go_pong.rest.ApiService;
import com.iav.go_pong.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView ivMenu;
    private RecyclerView rv;
    private ArrayList<MenuModel> menuModels;
    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        menuModels = new ArrayList<>();
        getDataMenu();
    }

    private void getDataMenu() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getMenu("read","menu" )
                .enqueue(new Callback<ArrayList<MenuModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MenuModel>> call, Response<ArrayList<MenuModel>> response) {
                        if (response.isSuccessful()){
                            menuModels = response.body();
                            menuAdapter = new MenuAdapter(MainActivity.this,menuModels );
                            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            rv.setAdapter(menuAdapter);
                            rv.setHasFixedSize(true);
                            menuAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MenuModel>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, Config.ERROR_INTERNET, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        ivMenu = findViewById(R.id.iv_menu);
        rv = findViewById(R.id.rv);
    }
}
