package com.example.marketim.Util;

import com.androidnetworking.error.ANError;

import org.json.JSONArray;

// Servisten çekilecek array veriler için interface
public interface ArrayNetworkResponse {
    void onResponse(JSONArray response, NetworkMethod type);

    void onError(ANError error, NetworkMethod type);
}
