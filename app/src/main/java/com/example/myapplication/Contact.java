package com.example.myapplication;

public class Contact {
    int _id;
    String _fname;
    String _lname;
    String _phone_number;
    String _email;
    public Contact(){   }
    public Contact(int id, String fname, String lname, String _phone_number, String email){
        this._id = id;
        this._fname = fname;
        this._lname = lname;
        this._phone_number = _phone_number;
        this._email = email;
    }

    public Contact(String fname, String lname, String _phone_number){
        this._fname = fname;
        this._lname = lname;
        this._phone_number = _phone_number;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getFName(){
        return this._fname;
    }

    public void setFName(String fname){
        this._fname = fname;
    }

    public String getLName(){
        return this._lname;
    }

    public void setLName(String lname){
        this._lname = lname;
    }

    public String getPhoneNumber(){
        return this._phone_number;
    }

    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }

    public String getEmail(){
        return this._email;
    }

    public void setEmail(String email){
        this._email = email;
    }
}
