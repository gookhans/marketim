package com.example.marketim.HomeScreen.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.marketim.HomeScreen.HomeScreenContract;
import com.example.marketim.HomeScreen.HomeScreenPresenter;
import com.example.marketim.HomeScreen.OrderModel;
import com.example.marketim.Login.LoginActivity;
import com.example.marketim.R;


import java.util.List;

public class HomeScreenActivity extends AppCompatActivity implements OrderAdapter.ItemClickListener, HomeScreenContract.View {
    private HomeScreenContract.Presenter presenter;
    private Button btn_logout;
    private RecyclerView orders;
    private HomeScreenContract.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        init();
        presenter = new HomeScreenPresenter(this, this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        orders.setLayoutManager(mLayoutManager);
        adapter = new OrderAdapter(this);
        orders.setAdapter((RecyclerView.Adapter) adapter);
        presenter.getData(); // veriyi çek
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreenActivity.this);
                builder.setTitle(getString(R.string.logout_alert_header));
                builder.setMessage(getString(R.string.logout_alert_body));
                builder.setNegativeButton(getString(R.string.no), null);
                builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.deleteRememberMe(); // beni hatırla değerini sil
                        startActivity(new Intent(HomeScreenActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                builder.show();

            }
        });

    }

    private void init() {
        btn_logout = findViewById(R.id.btn_logout);
        orders = findViewById(R.id.recycle_orders);
    }

    @Override
    public void onItemClick(View view, int position, OrderModel data) {

    }

    @Override
    public void getData(List<OrderModel> orders) {
        adapter.addList(orders);
    }
}
