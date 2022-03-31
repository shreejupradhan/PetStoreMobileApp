package com.example.petstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WishListFetchActivity extends AppCompatActivity {
    LinearLayout container;
    DatabaseHelper databaseHelper;
    ArrayList<WishListInfo> list;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list_layout);
        container=findViewById(R.id.container);
        sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        databaseHelper=new DatabaseHelper(this);

        list=databaseHelper.getWishListData(sharedPreferences.getString("uid","0"));
        Toast.makeText(WishListFetchActivity.this,sharedPreferences.getString("uid","0"), Toast.LENGTH_LONG).show();

        Log.i("WishListFetchActivity","List size:"+list.size());
        displayDynamicInfo();





    }

//        @Override
//        protected void onResume() {
//            super.onResume();
//
//        }

    public void displayDynamicInfo()
    {

        for(WishListInfo info:list)
        {
            View view= getLayoutInflater().inflate(R.layout.wishlist_item_layout,null);
            TextView username = view.findViewById(R.id.id);
            TextView animalname=view.findViewById(R.id.animal_id);

            username.setText(info.getUsername());
            animalname.setText(info.getAnimal_name());

            view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialog(info.getWishlist_id());
                }
            });
            container.addView(view);

        }
    }

    public  void showAlertDialog(String id)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete Wishlist");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHelper.deleteWishList(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel",null);

        builder.setCancelable(false);
        builder.show();

    }


}
