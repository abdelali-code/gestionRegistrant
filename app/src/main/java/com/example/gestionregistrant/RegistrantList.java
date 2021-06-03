  package com.example.gestionregistrant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gestionregistrant.models.User;
import com.example.gestionregistrant.sqlite.UserDatabase;
import com.example.gestionregistrant.sqlite.UserOpenHelper;
import com.example.gestionregistrant.sqlite.UsersData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.gestionregistrant.sqlite.UserDatabase.UserEntry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RegistrantList extends AppCompatActivity {
    private FloatingActionButton addFloatBtn;
    UserOpenHelper userOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    public static ArrayAdapter userAdapter;
    private static List<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userOpenHelper = new UserOpenHelper(this);
        sqLiteDatabase = userOpenHelper.getReadableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrant_list);

        final ListView registrantList = findViewById(R.id.registrantList);
//        List<User> userList = User.getUsersList();
        UsersData.readAllUserFromDb(userOpenHelper);
        userAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, UsersData.listUser);
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

//    public static void readAllUserFromDb(UserOpenHelper userOpenHelper) {
//        SQLiteDatabase  db = userOpenHelper.getReadableDatabase();
//        String[] colums = new String[] {UserEntry._ID, UserEntry.COLUMN_NUMTEL, UserEntry.COLUMN_LASTNAME, UserEntry.COLUMN_FIRSTNAME, UserEntry.COLUMN_EMAIL};
//        final Cursor userCursor = db.query(UserEntry.TABLE_NAME, colums, null, null, null, null, null);
//        int idOpsition = userCursor.getColumnIndex(UserEntry._ID);
//        int telNumPosition = userCursor.getColumnIndex(UserEntry.COLUMN_NUMTEL);
//        int lastNamePosition = userCursor.getColumnIndex(UserEntry.COLUMN_LASTNAME);
//        int firstNamePosition = userCursor.getColumnIndex(UserEntry.COLUMN_FIRSTNAME);
//        int emailPosition = userCursor.getColumnIndex(UserEntry.COLUMN_EMAIL);
//        while (userCursor.moveToNext()) {
//            System.out.println("callled");
//            long id = userCursor.getLong(idOpsition);
//            String lsName = userCursor.getString(lastNamePosition);
//            String fsName = userCursor.getString(firstNamePosition);
//            String email = userCursor.getString(emailPosition);
//            String numTel = userCursor.getString(telNumPosition);
//            User user = new User(id, fsName, lsName, email, numTel);
//            userList.add(user);
//        }
//        userCursor.close();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userOpenHelper.close();
    }
}