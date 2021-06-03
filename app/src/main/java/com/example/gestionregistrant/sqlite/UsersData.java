package com.example.gestionregistrant.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.gestionregistrant.RegistrantList;
import com.example.gestionregistrant.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersData {
    public static List<User> listUser = null;
    private static SQLiteDatabase sqLiteDatabase;

    public static void addUser(User user, UserOpenHelper userOpenHelper) {
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        System.out.println("the current user is " + user.getFirstName() + user.getLastName() + user.getEmail());
        sqLiteDatabase = userOpenHelper.getWritableDatabase();
        ContentValues holder = new ContentValues();
        holder.put(UserDatabase.UserEntry.COLUMN_FIRSTNAME, user.getFirstName());
        holder.put(UserDatabase.UserEntry.COLUMN_LASTNAME, user.getLastName());
        holder.put(UserDatabase.UserEntry.COLUMN_EMAIL, user.getEmail());
        holder.put(UserDatabase.UserEntry.COLUMN_NUMTEL, user.getTelNum());
        long addedUserId = sqLiteDatabase.insert(UserDatabase.UserEntry.TABLE_NAME, null, holder);
        user.setId(addedUserId);
        listUser.add(user);
    }

    public static void readAllUserFromDb(UserOpenHelper userOpenHelper) {
        if (listUser == null) {
            listUser = new ArrayList<>();
            SQLiteDatabase  db = userOpenHelper.getReadableDatabase();
            String[] colums = new String[] {UserDatabase.UserEntry._ID, UserDatabase.UserEntry.COLUMN_NUMTEL, UserDatabase.UserEntry.COLUMN_LASTNAME, UserDatabase.UserEntry.COLUMN_FIRSTNAME, UserDatabase.UserEntry.COLUMN_EMAIL};
            final Cursor userCursor = db.query(UserDatabase.UserEntry.TABLE_NAME, colums, null, null, null, null, null);
            int idOpsition = userCursor.getColumnIndex(UserDatabase.UserEntry._ID);
            int telNumPosition = userCursor.getColumnIndex(UserDatabase.UserEntry.COLUMN_NUMTEL);
            int lastNamePosition = userCursor.getColumnIndex(UserDatabase.UserEntry.COLUMN_LASTNAME);
            int firstNamePosition = userCursor.getColumnIndex(UserDatabase.UserEntry.COLUMN_FIRSTNAME);
            int emailPosition = userCursor.getColumnIndex(UserDatabase.UserEntry.COLUMN_EMAIL);
            while (userCursor.moveToNext()) {
                long id = userCursor.getLong(idOpsition);
                String lsName = userCursor.getString(lastNamePosition);
                String fsName = userCursor.getString(firstNamePosition);
                String email = userCursor.getString(emailPosition);
                String numTel = userCursor.getString(telNumPosition);
                User user = new User(id, fsName, lsName, email, numTel);
                listUser.add(user);
            }
            userCursor.close();
        }
    }

    public static void deleteUser(UserOpenHelper userOpenHelper, User user) {

        SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = UserDatabase.UserEntry._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { Long.toString(user.getId()) };
        // Issue SQL statement.
        int deletedRows = db.delete(UserDatabase.UserEntry.TABLE_NAME, selection, selectionArgs);
        System.out.println("the number of delered row is" + deletedRows);
        if (deletedRows > 0) {
            // remove this element from the list
            listUser.remove(user);
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getId() == user.getId()) {
                    listUser.remove(i);
                    break;
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void updateUser(UserOpenHelper userOpenHelper, User user, User oldValue) {
        SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = UserDatabase.UserEntry._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { Long.toString(user.getId()) };
        /*
        public int update (String table,
                ContentValues values,
                String whereClause,
                String[] whereArgs)
         */
        // Issue SQL statement.
        ContentValues holder = new ContentValues();
        holder.put(UserDatabase.UserEntry._ID, user.getId());
        holder.put(UserDatabase.UserEntry.COLUMN_FIRSTNAME, user.getFirstName());
        holder.put(UserDatabase.UserEntry.COLUMN_LASTNAME, user.getLastName());
        holder.put(UserDatabase.UserEntry.COLUMN_EMAIL, user.getEmail());
        holder.put(UserDatabase.UserEntry.COLUMN_NUMTEL, user.getTelNum());
        int updatedUser = db.update(UserDatabase.UserEntry.TABLE_NAME, holder, selection, selectionArgs);
        if (updatedUser > 0) {
            for(User usr : listUser) {
                if(usr.getId() == oldValue.getId()) {
                    System.out.println("the old value id is " + oldValue.getId() + "the current user is " + usr.getLastName());
                    user.setTelNum(oldValue.getTelNum());
                    user.setFirstName(oldValue.getFirstName());
                    user.setLastName(oldValue.getLastName());
                    user.setEmail(oldValue.getEmail());
                }
            }
        }
    }

}
