package com.example.spareservice.data.dto;

import com.google.gson.annotations.SerializedName;

public class PrestataireDTO {
    @SerializedName("nom") private String nom;
    @SerializedName("prenom") private String prenom;
    @SerializedName("email") private String email;
    @SerializedName("mdp") private String mdp;
    @SerializedName("tel") private String tel;


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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "PrestataireDTO{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
