package com.example.petstore;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petstore.Adapters.UserListAdapter;

public class UserListViewActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView listView;
    UserListAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_listview);
        listView=findViewById(R.id.listview);
        Adapter=new UserListAdapter(this,databaseHelper.getUserList());
        listView.setAdapter(Adapter);
        databaseHelper=new DatabaseHelper(this);
    }
}
