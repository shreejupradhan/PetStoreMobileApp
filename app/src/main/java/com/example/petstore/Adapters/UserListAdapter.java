package com.example.petstore.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.petstore.DetailActivity;
import com.example.petstore.R;
import com.example.petstore.UserInfo;

import java.util.ArrayList;


public class UserListAdapter extends ArrayAdapter<UserInfo> {

    Context context;

    public UserListAdapter(@NonNull Context context, ArrayList<UserInfo>list) {
        super(context,0,list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item_layout,null);
        TextView username = view.findViewById(R.id.username);
        TextView email = view.findViewById(R.id.email);
        TextView phone= view.findViewById(R.id.phone);

        UserInfo info = getItem(position);
        username.setText(info.getUsername());
        email.setText(info.getEmail());
        phone.setText(info.getPhone());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("id",info.getId());
                context.startActivity(intent);
            }

        });

        return view;
    }
}



