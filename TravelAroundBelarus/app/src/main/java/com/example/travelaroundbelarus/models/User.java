package com.example.travelaroundbelarus.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class User implements Parcelable {

    private String email,password,name,lastname,phone;
    private boolean admin;
    private List<Attraction> attractions;

    public User(){}

    public User(String email, String password, String name, String lastname, String phone,boolean admin) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.admin = admin;
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
        lastname = in.readString();
        phone = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(lastname);
        dest.writeString(password);
        dest.writeString(phone);
        dest.writeList(attractions);


    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }
}
