package com.example.petstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    CheckBox remember;
    SharedPreferences sharedPreferences;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);


        databaseHelper = new DatabaseHelper(this);
        username = findViewById(R.id.username);

        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        remember = findViewById(R.id.rememberme);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
//                String registeredUsername=sharedPreferences.getString("username","");
//                        String registeredPassword=sharedPreferences.getString("password","");

                String uid = databaseHelper.isLoginSuccessful(usernameValue, passwordValue);
                if (!uid.equals("0")) {
                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("username", usernameValue);
                    sharedPreferences.edit().putString("uid", uid).commit();


                    startActivity(intent);


                    if (remember.isChecked()) {
                        sharedPreferences.edit().putBoolean("rememberme", true).commit();
                    }
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login not successful", Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("abc", "this is register");
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        if (sharedPreferences.getBoolean("rememberme", false)) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
    }
}