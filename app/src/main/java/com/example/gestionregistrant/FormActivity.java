package com.example.gestionregistrant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestionregistrant.models.User;
import com.example.gestionregistrant.sqlite.UserOpenHelper;
import com.example.gestionregistrant.sqlite.UsersData;

public class FormActivity extends AppCompatActivity {
    public static final String SELECTED_USER = "com.exemple.gestionregistrant.SELECTED_USER";
    private EditText firstName, lastName, email, phone;
    private Button delBtn, updateBtn;
    private UserOpenHelper userOpenHelper;
    private User selectedUserItem;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedUserItem = getIntent().getParcelableExtra(SELECTED_USER);
        userOpenHelper = new UserOpenHelper(this);
        setContentView(R.layout.activity_form);
        firstName = findViewById(R.id.addFirstName);
        lastName = findViewById(R.id.addLastName);
        email = findViewById(R.id.addEmail);
        phone = findViewById(R.id.addPhone);
        delBtn = findViewById(R.id.delBtn);
        updateBtn = findViewById(R.id.updateBtn);

        delBtn.setOnClickListener(view -> {
            UsersData.deleteUser(userOpenHelper, selectedUserItem);
            Intent intent = new Intent(FormActivity.this, RegistrantList.class);
            startActivity(intent);
        });
        updateBtn.setOnClickListener(view -> {
            User targetUser = new User(selectedUserItem.getId(), firstName.getText().toString(),
                    lastName.getText().toString(), email.getText().toString(), phone.getText().toString());
            UsersData.updateUser(userOpenHelper, targetUser, selectedUserItem);
            Intent intent = new Intent(FormActivity.this, RegistrantList.class);
            startActivity(intent);
        });
        // reead the selected user from intent
        readSelectedUser();
    }

    private void updateUser() {

    }

    private void deleteUser() {

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