package com.example.marketim.Util;

import com.androidnetworking.error.ANError;

import org.json.JSONArray;

public interface ArrayNetworkResponse {
    void onResponse(JSONArray response, NetworkMethod type);

    void onError(ANError error, NetworkMethod type);
}
