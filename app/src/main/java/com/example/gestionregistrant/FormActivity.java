package com.example.gestionregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.gestionregistrant.models.User;

public class FormActivity extends AppCompatActivity {
    public static final String SELECTED_USER = "com.exemple.gestionregistrant.SELECTED_USER";
    private EditText firstName, lastName, email, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        firstName = findViewById(R.id.addFirstName);
        lastName = findViewById(R.id.addLastName);
        email = findViewById(R.id.addEmail);
        phone = findViewById(R.id.addPhone);


        // reead the selected user from intent
        readSelectedUser();
    }
    private void readSelectedUser() {
        Intent intent = getIntent();
        User selectedUser = intent.getParcelableExtra(SELECTED_USER);
        firstName.setText(selectedUser.getFirstName());
        lastName.setText(selectedUser.getLastName());
        email.setText(selectedUser.getEmail());
        phone.setText(selectedUser.getTelNum());
        System.out.println(selectedUser.getEmail() + "from the edited text activity");
    }
}