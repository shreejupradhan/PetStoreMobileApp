package com.example.petstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    DatabaseHelper databaseHelper;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        user = findViewById(R.id.usernamee);

        user.setText(getIntent().getStringExtra("username"));
//       password=getIntent().getStringExtra("password");
//       UserInfo info=databaseHelper.getUserInfo(password);
//       username.setText(info.getUsername());

//        displayInfo();
        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean("rememberme", false).commit();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
//    public void displayInfo()
//    {
//        UserInfo info=databaseHelper.getUserInfo(username);
//        user.setText(info.getUsername());
//
//
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_id = item.getItemId();
        switch (menu_id) {
            case R.id.menu1:
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                Toast.makeText(HomeActivity.this, "Menu is clicked", Toast.LENGTH_SHORT).show();

                break;

            case R.id.menu2:
                startActivity(new Intent(HomeActivity.this, UserListActivity.class));
                Toast.makeText(HomeActivity.this, "UserInfo is clicked", Toast.LENGTH_SHORT).show();
                break;


            case R.id.menu3:
                startActivity(new Intent(HomeActivity.this, AnimalFormActivity.class));
                Toast.makeText(HomeActivity.this, "Animal form is clicked", Toast.LENGTH_SHORT).show();
                break;


            case R.id.menu4:
                startActivity(new Intent(HomeActivity.this, AnimalListActivity.class));

                Toast.makeText(HomeActivity.this, "Animal info", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu5:
                startActivity(new Intent(HomeActivity.this, WishListFetchActivity.class));

                Toast.makeText(HomeActivity.this, "Wishlist is clicked", Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}