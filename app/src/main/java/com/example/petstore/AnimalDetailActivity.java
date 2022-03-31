package com.example.petstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalDetailActivity extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    TextView animal_name,animal_price,animal_breed,animal_color,animal_description;
    ImageView animal_image;
    String animal_id;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animaldetail_activity);
        sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
//         Log.i("AnimalActivity","id:"+databaseHelper.getUserInfo());

        animal_id=getIntent().getStringExtra("animal_id");
        databaseHelper=new DatabaseHelper(this);
        animal_name=findViewById(R.id.animal_name);
        animal_price=findViewById(R.id.animal_price);
        animal_breed=findViewById(R.id.animal_breed);
        animal_color=findViewById(R.id.animal_color);
        animal_description=findViewById(R.id.animal_description);


        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AnimalDetailActivity.this,AnimalFormActivity.class);
                intent.putExtra("animal_id",Integer.parseInt(animal_id));
//                startActivity(intent);
                showCustomDialog();
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        findViewById(R.id.wishlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues=new ContentValues();
                contentValues.put("id",sharedPreferences.getString("uid","0"));
                contentValues.put("animal_id",animal_id);
                databaseHelper.insertWishlist(contentValues);
                Toast.makeText(AnimalDetailActivity.this, "Added to wishlist", Toast.LENGTH_LONG).show();

            }
        });
    }

    public  void showAlertDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete the selected animal");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHelper.deleteAnimal(animal_id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.setCancelable(false);
        builder.show();

    }
    public void displayInfo()
    {
        AnimalInfo Info=databaseHelper.getAnimalInfo(animal_id);
        animal_name.setText(Info.getAnimal_name());
        animal_price.setText(Info.getAnimal_price());
        animal_breed.setText(Info.getAnimal_breed());
        animal_color.setText(Info.getAnimal_color());
        animal_description.setText(Info.getAnimal_description());
    }

    //for custom dialogue box to update


    public void showCustomDialog()
    {
        Dialog dialog=new Dialog(this);
        EditText animal_name,animal_price,animal_breed,animal_color,animal_description;
        Button submit;

        View view= LayoutInflater.from(this).inflate(R.layout.animal_form,null);
        animal_name=view.findViewById(R.id.animal_name);
        animal_price=view.findViewById(R.id.animal_price);
        animal_breed=view.findViewById(R.id.animal_breed);
        animal_color=view.findViewById(R.id.animal_color);
        animal_description=view.findViewById(R.id.animal_description);
        animal_image=view.findViewById(R.id.animal_image);
        submit=view.findViewById(R.id.submit);
        submit.setText("UPDATE");


        AnimalInfo Info=databaseHelper.getAnimalInfo(animal_id);
        animal_name.setText(Info.getAnimal_name());
        animal_price.setText(Info.getAnimal_price());
        animal_breed.setText(Info.getAnimal_breed());
        animal_color.setText(Info.getAnimal_color());
        animal_description.setText(Info.getAnimal_description());



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String animalnameValue=animal_name.getText().toString();
                String animalpriceValue=animal_price.getText().toString();

                String animalbreedValue=animal_breed.getText().toString();
                String animalcolorValue=animal_color.getText().toString();
                String animaldescriptionValue=animal_description.getText().toString();


                ContentValues contentValues=new ContentValues();
                contentValues.put("animal_name",animalnameValue);
                contentValues.put("animal_price",animalpriceValue);
                contentValues.put("animal_breed",animalbreedValue);
                contentValues.put("animal_color",animalcolorValue);
                contentValues.put("animal_description",animaldescriptionValue);
                databaseHelper.updateAnimal(animal_id,contentValues);
                displayInfo();
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayInfo();
    }
}
