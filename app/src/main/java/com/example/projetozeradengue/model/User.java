package com.example.projetozeradengue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.projetozeradengue.core.AppUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Date;

public class User implements Parcelable {
   private String nameUser , email, password ;
   private String id;
   private String dob;




    //CONSTRUTOR
    public User(String nameUser, String email, String password, String dob, String id) {
        this.id = id;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public User() {}


    //GETTERES E SETTERES
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;}

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    //Database Web
    public void save(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(AppUtil.REALTIME_DATABASE_USERS).child(getId()).setValue(this);

    }


    //  IMPLEMENTAÇÃO DE PARCELABLE

    //Construtor usado pelo android para criar a nossa classe, neste caso User
    protected User(Parcel in) {
        nameUser = in.readString();
        email = in.readString();
        password = in.readString();
        id = in.readString();
        dob = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            throw new UnsupportedOperationException();
            //return new User[size];
        }
    };


    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nameUser);
        dest.writeString(dob);
        dest.writeString(email);
    }


}
