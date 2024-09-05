package eni.tp.app.eni_app.Bo;

import jakarta.validation.constraints.Email;


public class User {
    @Email
    public int id;
    public String nom;
    public String prenom;
    public String email;
    public String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {}
    @Email
    public int getId() {
        return id;
    }

    public void setId(@Email int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean admin;

    public User(int id, String nom, String prenom, String email, String password, boolean admin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }
}