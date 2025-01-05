package com.example.model;

public class Utilisateur {
    
    private int idUser;
    private String nom;
    private String prenom;
    private String email;
    private String type;
    private String psswd;

    public Utilisateur(){}

    public Utilisateur(int id ,String nom ,String prenom ,String email ,String type , String password){
        this.idUser = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.type = type;
        this.psswd=password;
    }

    public Utilisateur(String nom, String prenom, String email, String type ,String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.type = type;
        this.psswd=password;
    }
    public Utilisateur(String nom, String prenom, String email, String type ) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.type = type;
     
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }
    public String getPassword() {
        return psswd;
    }

    public void setIdUser(int id) {
        this.idUser = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setPassword(String password) {
        this.psswd = password;
    }

    @Override
    public String toString(){
        return "Utilisateur : [ id : " + idUser + "\n nom : " + nom + "\n prenom : " + prenom + "\n email : " + email + "\n type : " + type + " ] ";
    }

}
