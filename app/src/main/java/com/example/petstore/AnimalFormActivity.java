package com.example.petstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimalFormActivity extends AppCompatActivity {
    EditText animal_name, animal_price, animal_breed, animal_description, animal_color;
    ImageView animal_image;
    Button submit;
    DatabaseHelper databaseHelper;

    int animal_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_form);
        animal_id = getIntent().getIntExtra("animal_id", 0);

        databaseHelper = new DatabaseHelper(this);

        animal_name = findViewById(R.id.animal_name);
        animal_price = findViewById(R.id.animal_price);

        animal_breed = findViewById(R.id.animal_breed);
        animal_color = findViewById(R.id.animal_color);
        animal_description = findViewById(R.id.animal_description);
        animal_image = findViewById(R.id.animal_image);
        submit = findViewById(R.id.submit);
        if (animal_id != 0) {
            AnimalInfo Info = databaseHelper.getAnimalInfo(String.valueOf(animal_id));
            animal_name.setText(Info.getAnimal_name());
            animal_price.setText(Info.getAnimal_price());
            animal_breed.setText(Info.getAnimal_breed());
            animal_color.setText(Info.getAnimal_color());
            animal_description.setText(Info.getAnimal_description());
            submit.setText("UPDATE");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String animalnameValue = animal_name.getText().toString();
                String animalpriceValue = animal_price.getText().toString();

                String animalbreedValue = animal_breed.getText().toString();
                String animalcolorValue = animal_color.getText().toString();
                String animaldescriptionValue = animal_description.getText().toString();


                ContentValues contentValues = new ContentValues();
                contentValues.put("animal_name", animalnameValue);
                contentValues.put("animal_price", animalpriceValue);
                contentValues.put("animal_breed", animalbreedValue);
                contentValues.put("animal_color", animalcolorValue);
                contentValues.put("animal_description", animaldescriptionValue);
                if (animal_id == 0) {
                    databaseHelper.insertAnimal(contentValues);
                    Toast.makeText(AnimalFormActivity.this, "Animal data is inserted successfully", Toast.LENGTH_LONG).show();

                } else {

                    databaseHelper.updateAnimal(animal_id + "", contentValues);
                    Toast.makeText(AnimalFormActivity.this, "Animal data is updated successfully", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }
}
