package com.example.marketim.HomeScreen;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.example.marketim.Data.SharedPreferencesManagerHelper;
import com.example.marketim.Services.OrdersService;
import com.example.marketim.Util.ArrayNetworkResponse;
import com.example.marketim.Util.NetworkMethod;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, ArrayNetworkResponse {

    private HomeScreenContract.View view;
    private Context context;

    public HomeScreenPresenter(HomeScreenContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void getData() {
        new OrdersService(this).get();
    }

    public void deleteRememberMe() {
        SharedPreferencesManagerHelper.deleteRememberMe(context);
    }

    @Override
    public void onResponse(JSONArray response, NetworkMethod type) {
        Log.e(this.getClass().getSimpleName(), response.toString());
        try {
            List<OrderModel> orders = new Gson().fromJson(response.toString(), new TypeToken<List<OrderModel>>() {
            }.getType());
            view.getData(orders); // viewe veriyi gönder


        } catch (JsonSyntaxException exception) {
            // Error Message
        }
    }

    @Override
    public void onError(ANError error, NetworkMethod type) {
        // Error Message
    }
}
