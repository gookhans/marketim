package com.example.marketim.Login;

import android.content.Context;

public interface LoginContract {

    interface View {
        void loginError();

        void userLogged();

        void setErrorUsername();

        void setErrorPassword();
    }

    interface Presenter {
        void login(Context context, String username, String password, Boolean rememberMe);

        boolean isLogged(Context context);
    }
}
