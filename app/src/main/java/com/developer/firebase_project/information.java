package com.developer.firebase_project;

public class information {
    //---imp---name of variable should match with name in firebase
   String Email;
   String Name;
    public information() {
    }

    public information(String email, String name) {
        this.Email = email;
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
