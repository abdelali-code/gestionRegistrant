  package com.example.gestionregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gestionregistrant.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RegistrantList extends AppCompatActivity {
    private FloatingActionButton addFloatBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrant_list);

        final ListView registrantList = findViewById(R.id.registrantList);
        List<User> userList = User.getUsersList();
        ArrayAdapter userAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userList);
        registrantList.setAdapter(userAdapter);

        registrantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("the selected item is ");
                Intent intent = new Intent(RegistrantList.this, FormActivity.class);
                User user = (User) registrantList.getItemAtPosition(position);
                intent.putExtra(FormActivity.SELECTED_USER, user);
                System.out.println(user.getEmail());
                startActivity(intent);
            }
        });


        addFloatBtn = findViewById(R.id.addFloatBtn);
        addFloatBtn.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrantList.this, AddForm.class);
            startActivity(intent);
        });
    }
}