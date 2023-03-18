package com.example.myapplication;

public class Contact {
    int _id;
    String _name;
    String _phone_number;
    String _email;
    String _job;

    public Contact(){   }
    public Contact(int id, String name, String _phone_number, String email, String job){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this._email = email;
        this._job = job;
    }

    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getJob(){
        return this._job;
    }

    public void setJob(String job){
        this._job = job;
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
