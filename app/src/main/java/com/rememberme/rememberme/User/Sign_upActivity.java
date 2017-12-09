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
import com.rememberme.rememberme.R;
import com.rememberme.rememberme.User.Results.SignUpResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rememberme.rememberme.R.id.emailText;

public class Sign_upActivity extends AppCompatActivity   {
    APINetwork network;
    EditText idText, passwordText, passwordconfirmText, nameText;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        idText = (EditText) findViewById(emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        passwordconfirmText = (EditText) findViewById(R.id.PasswordConfirmText);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = idText.getText().toString();
                String password = passwordText.getText().toString();
                String password_confirm = passwordconfirmText.getText().toString();
                String name = nameText.getText().toString();
                signup(email,password,password_confirm,name);


                Intent signComplete = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(signComplete);
            }
        });

    }

    private void signup(String email, String password, String password_confirm, String name) {
        User user = new User(email,password,password_confirm,name);
        Call<SignUpResult> SignUpCallBack = network.getSignUpResult(user);
        SignUpCallBack.enqueue(new Callback<SignUpResult>() {
            @Override
            public void onResponse(Call<SignUpResult> call, Response<SignUpResult> response) {
                if(response.isSuccessful()){
                    SignUpResult signupResult = response.body();
                    Log.i("Sign", "Sign success/ msg ::".concat(signupResult.msg));
                    Intent signInIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(signInIntent);
                }
                else{
                    Toast.makeText(Sign_upActivity.this,"회원가입을 실패하셨습니다.",Toast.LENGTH_LONG).show();
                    Log.i("Sign", "code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SignUpResult> call, Throwable t) {

            }
        });
    }
}