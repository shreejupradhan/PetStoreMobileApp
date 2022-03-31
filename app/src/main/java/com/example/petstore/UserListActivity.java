package com.example.petstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    LinearLayout container;
    DatabaseHelper databaseHelper;
    ArrayList<UserInfo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_activity);
        container=findViewById(R.id.container);

        databaseHelper=new DatabaseHelper(this);

        list=databaseHelper.getUserList();

        // Log.i("UserListActivity","List size:"+list.size());


    }

    @Override
    protected void onResume() {
        super.onResume();
        displayDynamicInfo();
    }

    public void displayDynamicInfo()
    {
        list=databaseHelper.getUserList();
        for (int i=0;i<list.size();i++)
        {
            UserInfo info=list.get(i);
        }
        container.removeAllViews();
        for(UserInfo info:list)
        {
            View view= LayoutInflater.from(this).inflate(R.layout.list_item_layout,null);
            TextView username = view.findViewById(R.id.username);
            TextView email = view.findViewById(R.id.email);
            TextView phone= view.findViewById(R.id.phone);

            username.setText(info.getUsername());
            email.setText(info.getEmail());
            phone.setText(info.getPhone());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(UserListActivity.this,DetailActivity.class);
                    intent.putExtra("id",info.getId());
                    startActivity(intent);
                }
            });

            container.addView(view);

        }
    }
}
