package com.example.marketim.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.marketim.HomeScreen.ui.HomeScreenActivity;
import com.example.marketim.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;
    private Button btn_login;
    private EditText edt_username, edt_password;
    private Switch remember_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        if (presenter.isLogged(getApplicationContext())) // beni hatıla seçilmişse anasayfaya geç
            userLogged();
        init();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(getApplicationContext(), edt_username.getText().toString(), edt_password.getText().toString(), remember_me.isChecked());
            }
        });
    }

    private void init() {
        btn_login = findViewById(R.id.btn_login);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        remember_me = findViewById(R.id.switch_remember_me);
    }

    @Override
    public void loginError() {
        Toast.makeText(this, getString(R.string.error_login), Toast.LENGTH_LONG).show();
    }

    @Override
    public void userLogged() {
        startActivity(new Intent(this, HomeScreenActivity.class));
        finish();
    }

    @Override
    public void setErrorUsername() {
        edt_username.setError(getString(R.string.edt_null));
    }

    @Override
    public void setErrorPassword() {
        edt_password.setError(getString(R.string.edt_null));
    }
}
