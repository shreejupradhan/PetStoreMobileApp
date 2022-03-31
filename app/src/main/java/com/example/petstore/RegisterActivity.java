package com.example.petstore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username,address,phone,email,password;
    Button register;
    RadioGroup gender;
    SharedPreferences sharedPreferences;
    DatabaseHelper databaseHelper;
    int id;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);
        sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);

        id=getIntent().getIntExtra("id",0);


        databaseHelper= new DatabaseHelper(this);
        username=findViewById(R.id.username);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        gender=findViewById(R.id.gender);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);


        if (id!=0)
        {
            UserInfo info=databaseHelper.getUserInfo(String.valueOf(id));
            username.setText(info.getUsername());
            email.setText(info.getEmail());
            phone.setText(info.getPhone());
            address.setText(info.getAddress());
            password.setText(info.getPassword());
            if(info.getGender().equals("male"))
            {
                ((RadioButton)findViewById(R.id.male)).setChecked(true);
            }
            else
                ((RadioButton)findViewById(R.id.female)).setChecked(true);
            register.setText("Update");

        }
    }
    public void registerUser(View view)
    {
        String usernameValue=username.getText().toString();
        String addressValue=address.getText().toString();

        String phoneValue=phone.getText().toString();
        String emailValue=email.getText().toString();
        String passwordValue=password.getText().toString();

        RadioButton checkBtn=findViewById(gender.getCheckedRadioButtonId());
        String genderValue=checkBtn.getText().toString();

        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString("username",usernameValue);
        editor.putString("address",addressValue);
        editor.putString("phone",phoneValue);
        editor.putString("email",emailValue);
        editor.putString("password",passwordValue);
        editor.putString("gender",genderValue);

        editor.apply();

        Toast.makeText(RegisterActivity.this, "DataSaved", Toast.LENGTH_LONG).show();

        ContentValues contentValues=new ContentValues();
        contentValues.put("username",usernameValue);
        contentValues.put("address",addressValue);
        contentValues.put("phone",phoneValue);
        contentValues.put("email",emailValue);
        contentValues.put("password",passwordValue);
        contentValues.put("gender",genderValue);

        if(id==0)
        {
            databaseHelper.insertUser(contentValues);
            Toast.makeText(RegisterActivity.this, "userinfo is saved", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
            intent.putExtra("username",usernameValue);
            startActivity(intent);

        }
        else
        {
            databaseHelper.updateUser(id+"",contentValues);
            Toast.makeText(RegisterActivity.this, "userinfo is updated", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}