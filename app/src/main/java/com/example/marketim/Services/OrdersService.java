package com.example.marketim.Services;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.marketim.Util.ArrayNetworkResponse;
import com.example.marketim.Util.NetworkMethod;

import org.json.JSONArray;


public class OrdersService implements JSONArrayRequestListener {

    public static final String BASE_URL = "http://kariyertechchallenge.mockable.io/";
    private ArrayNetworkResponse arrayNetworkResponse;
    private NetworkMethod method;

    public OrdersService(ArrayNetworkResponse arrayNetworkResponse) {
        this.arrayNetworkResponse = arrayNetworkResponse;
    }

    public void get() {
        method = NetworkMethod.GET;
        AndroidNetworking.get(BASE_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(this);
    }

    @Override
    public void onResponse(JSONArray response) {
        arrayNetworkResponse.onResponse(response, method);
    }

    @Override
    public void onError(ANError anError) {
        arrayNetworkResponse.onError(anError, method);
    }
}

