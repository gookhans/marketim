package com.example.marketim.HomeScreen;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.example.marketim.R;
import com.example.marketim.Services.OrdersService;
import com.example.marketim.Util.ArrayNetworkResponse;
import com.example.marketim.Util.NetworkMethod;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

public class HomeScreenActivity extends AppCompatActivity implements OrderAdapter.ItemClickListener, ArrayNetworkResponse {

    private Button btn_logout;
    private RecyclerView orders;
    private OrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        init();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        orders.setLayoutManager(mLayoutManager);
        adapter = new OrderAdapter(this);
        orders.setAdapter(adapter);

        new OrdersService(this).get();
    }
    private void init() {
        btn_logout = findViewById(R.id.btn_logout);
        orders = findViewById(R.id.recycle_orders);
    }

    @Override
    public void onOrderModelClick(View view, int position, OrderModel data) {

    }
    @Override
    public void onResponse(JSONArray response, NetworkMethod type) {
        Log.e(this.getClass().getSimpleName(), response.toString());
        try {
            List<OrderModel> orders = new Gson().fromJson(response.toString(), new TypeToken<List<OrderModel>>() {
            }.getType());
            adapter.addList(orders);


        } catch (JsonSyntaxException exception) {
            Toast.makeText(this, "Sunucudan gelen veri anlamlandırılamadı!\n" + exception.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(this.getClass().getSimpleName(), exception.getMessage());
        }
        if (response.length() < 1)
            Toast.makeText(this, "Siparişiniz Bulunmamaktadır", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(ANError error, NetworkMethod type) {
        if (error.getErrorCode() != 0) {
            switch (error.getErrorCode()) {

                case 403:
                    Toast.makeText(this, "Bu işlem için yetkilendirilemediniz!", Toast.LENGTH_LONG).show();
                    break;
                case 500:
                    Toast.makeText(this, "Sunucuda bir hata oldu!\nLütfen daha sonra tekrar deneyiniz", Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(this, error.getErrorCode() + "_" + error.getErrorBody(), Toast.LENGTH_LONG).show();
                    break;
            }
            Log.e(this.getClass().getSimpleName(), " errorCode: " + error.getErrorCode());
            Log.e(this.getClass().getSimpleName(), " errorBody: " + error.getErrorBody());
        } else {
            Toast.makeText(this, "Sunucuya ulaşılamıyor!\nLütfen daha sonra tekrar deneyiniz", Toast.LENGTH_LONG).show();
            Log.e(this.getClass().getSimpleName(), " errorDetail: " + error.getErrorDetail());
        }
    }
}
