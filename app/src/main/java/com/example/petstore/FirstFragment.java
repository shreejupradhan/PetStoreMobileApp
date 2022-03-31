package com.example.petstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    LinearLayout container;
    DatabaseHelper databaseHelper;
    ArrayList<UserInfo> list;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container1, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.user_list_activity,null);

        container=view.findViewById(R.id.container);


        databaseHelper=new DatabaseHelper(getActivity());
        displayDynamicInfo();

        return view;
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
            View view= LayoutInflater.from(getActivity()).inflate(R.layout.list_item_layout,null);
            TextView username = view.findViewById(R.id.username);
            TextView email = view.findViewById(R.id.email);
            TextView phone= view.findViewById(R.id.phone);

            username.setText(info.getUsername());
            email.setText(info.getEmail());
            phone.setText(info.getPhone());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(),DetailActivity.class);
                    intent.putExtra("id",info.getId());
                    startActivity(intent);
                }
            });

            container.addView(view);

        }
    }
}
