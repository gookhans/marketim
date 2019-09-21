package com.example.marketim.Services;

public class LoginService {

    public boolean login(String username, String password) {
    if(username.equals("kariyer") && password.equals("2019ADev")) {
        return true;
        }
    return false;
    }
}