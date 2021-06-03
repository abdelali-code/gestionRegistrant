package com.example.gestionregistrant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestionregistrant.models.User;
import com.example.gestionregistrant.sqlite.UserDatabase;
import com.example.gestionregistrant.sqlite.UserOpenHelper;
import com.example.gestionregistrant.sqlite.UsersData;

public class AddForm extends AppCompatActivity {
    private Button addbtn;
    private EditText firstName, lastName, email, telNum;
    UserOpenHelper userOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userOpenHelper = new UserOpenHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form);

        firstName = findViewById(R.id.addFirstName);
        lastName = findViewById(R.id.addLastName);
        email = findViewById(R.id.addEmail);
        telNum = findViewById(R.id.addPhone);
        addbtn = findViewById(R.id.addUserBtn);
        System.out.println("the collected user data is " + firstName.getText().toString());

        addbtn.setOnClickListener(view -> {
            User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), telNum.getText().toString());
            UsersData.addUser(user, userOpenHelper);
            Intent intent = new Intent(AddForm.this, RegistrantList.class);
            startActivity(intent);
        });
    }

//    private void registerUser() {
//        sqLiteDatabase = userOpenHelper.getWritableDatabase();
//        ContentValues holder = new ContentValues();
//        holder.put(UserDatabase.UserEntry.COLUMN_FIRSTNAME, firstName.getText().toString());
//        holder.put(UserDatabase.UserEntry.COLUMN_LASTNAME, lastName.getText().toString());
//        holder.put(UserDatabase.UserEntry.COLUMN_EMAIL, email.getText().toString());
//        holder.put(UserDatabase.UserEntry.COLUMN_NUMTEL, email.getText().toString());
//        sqLiteDatabase.insert(UserDatabase.UserEntry.TABLE_NAME, null, holder);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userOpenHelper.close();
    }
}