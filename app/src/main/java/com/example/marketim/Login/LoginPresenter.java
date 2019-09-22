package com.example.marketim.Login;

import android.content.Context;

import com.example.marketim.Data.SharedPreferencesManagerHelper;
import com.example.marketim.Services.LoginService;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private UserModel user;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        user = new UserModel();
    }

    @Override
    public void login(Context context, String username, String password, Boolean rememberMe) {
        if (username.length() < 1) {
            view.setErrorUsername();
        } else if (password.length() < 1) {
            view.setErrorPassword();
        } else {
            if (new LoginService().login(username, password)) {
                if (rememberMe) // beni hatırla seçili ise, bu seçimi kaydet
                    SharedPreferencesManagerHelper.setRememberMe(context);
                user.setUser(username, password);
                view.userLogged();
            } else {
                view.loginError();
            }
        }
    }

    @Override
    public boolean isLogged(Context context) { //  sonraki girişlerde giriş ekranı veya anasayfaya geçmek için kontrol
        if (SharedPreferencesManagerHelper.getRememberMe(context) != null)
            return true;
        return false;
    }

}
