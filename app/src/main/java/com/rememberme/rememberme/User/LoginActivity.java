package com.rememberme.rememberme.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rememberme.rememberme.MainActivity;
import com.rememberme.rememberme.Network.APINetwork;
import com.rememberme.rememberme.Network.ApplicationController;
import com.rememberme.rememberme.R;
import com.rememberme.rememberme.User.Results.LoginResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    APINetwork network;
    EditText idText,passwordText;
    Button loginButton,signButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        network = ApplicationController.getInstance().getApiNetwork();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = idText.getText().toString();
                String password = passwordText.getText().toString();
                login(email, password);
            }
        });

        signButton = (Button) findViewById(R.id.Signbtn);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sign_upIntent = new Intent(getApplicationContext(), Sign_upActivity.class);
                startActivity(Sign_upIntent);
            }
        });
    }
    private void login(String email, String password) {
        User user = new User(email, password);
        Call<LoginResult> LoginResultCall = network.getLoginResult(user);
        LoginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()) {
                    LoginResult loginResult = response.body();
                    Log.i("Sign", "Login Success / msg : ".concat(loginResult.msg).concat(", token : ".concat(loginResult.token)));

                    Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    Log.i("Sign", "code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i("Sign", t.getMessage());
            }
        });

        }

    }


