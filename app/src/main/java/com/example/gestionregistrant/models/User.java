package com.example.gestionregistrant.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telNum;


    public User(long id, String firstName, String lastName, String email, String telNum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNum = telNum;
    }
    public User(String firstName, String lastName, String email, String telNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNum = telNum;
    }



    private User(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        telNum = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " \n" + email;
    }

    public static List<User> getUsersList() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "name", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(2, "name2", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(3, "name3", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(4, "name4", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(5, "name5", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(1, "name", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(2, "name2", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(3, "name3", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(4, "name4", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(5, "name5", "lastname", "email@gmail.com", "0609999999"));

        users.add(new User(1, "name", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(2, "name2", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(3, "name3", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(4, "name4", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(5, "name5", "lastname", "email@gmail.com", "0609999999"));

        users.add(new User(1, "name", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(2, "name2", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(3, "name3", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(4, "name4", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(5, "name5", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(1, "name", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(2, "name2", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(3, "name3", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(4, "name4", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(5, "name5", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(1, "name", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(2, "name2", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(3, "name3", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(4, "name4", "lastname", "email@gmail.com", "0609999999"));
        users.add(new User(5, "name5", "lastname", "email@gmail.com", "0609999999"));



        return users;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(telNum);
    }
}
