package com.example.petstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
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

public class AnimalListActivity extends AppCompatActivity {


    LinearLayout container;
    DatabaseHelper databaseHelper;
    Button wish;
    SharedPreferences sharedPreferences;
    ArrayList<AnimalInfo>animallist;
    String a_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animaldetail_activity);


        sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        Log.i("test", "displayDynamicInfo: "+"Asd");
        container=findViewById(R.id.container);
        databaseHelper=new DatabaseHelper(this);

        displayDynamicInfo();


    }

    @Override
    protected void onResume() {
        super.onResume();
        displayDynamicInfo();
    }

    public void displayDynamicInfo()
    {
        animallist=databaseHelper.getAnimalList();

        container.removeAllViews();

        Log.i("test", "displayDynamicInfo: "+animallist.size());
        for(AnimalInfo info:animallist)
        {

            Log.i("test", "displayDynamicInfo: "+info.getAnimal_price());

            View view= LayoutInflater.from(this).inflate(R.layout.animallist_item_layout,null);
            TextView name=view.findViewById(R.id.animal_name);
            TextView price=view.findViewById(R.id.animal_price);
            a_id=info.getAnimal_id();
//            Log.i("AnimalFetch","animalid"+a_id);
            name.setText(info.getAnimal_name());
            price.setText(info.getAnimal_price());
            view.findViewById(R.id.wish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("id",sharedPreferences.getString("uid","0"));
                    contentValues.put("animal_id",a_id);
                    databaseHelper.insertWishlist(contentValues);
                    Toast.makeText(AnimalListActivity.this,"Added to wishlist",Toast.LENGTH_LONG).show();

                }
            });
            Log.i("test", "displayDynamicInfo: "+info.getAnimal_price());
//
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=new Intent(AnimalListActivity.this, AnimalDetailActivity.class);
//                    intent.putExtra("animal_id",Info.getAnimal_id());
//                    startActivity(intent);
//                }
//            });
            container.addView(view);

        }
    }
}
