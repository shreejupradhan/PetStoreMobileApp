package com.example.petstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView username,email,phone,address,password,gender;


    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        id=getIntent().getStringExtra("id");
        databaseHelper=new DatabaseHelper(this);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        password=findViewById(R.id.password);
        gender=findViewById(R.id.gender);



        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailActivity.this,RegisterActivity.class);
                intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowAlertDialog();
            }
        });
    }

    public void ShowAlertDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete User!");
        builder.setMessage("Are You Sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHelper.deleteUser(id);
                finish();

            }
        });
        builder .setNegativeButton("Cancel",null);


        builder.setCancelable(false);
        builder.show();
    }

    public void displayInfo()
    {
        UserInfo info=databaseHelper.getUserInfo(id);
        username.setText(info.getUsername());
        email.setText(info.getEmail());
        phone.setText(info.getPhone());
        address.setText(info.getAddress());
        password.setText(info.getPassword());
        gender.setText(info.getGender());

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayInfo();
    }
}
