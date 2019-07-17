package com.example.spareservice.data.model;

import java.io.Serializable;
import java.util.Arrays;

public class Prestataire {
    private String idPrestataire;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String tel;
    private String ville;
    private String adresse;
    private String cp;

    public String getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(String idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
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
        return "Prestataire{" +
                "idPrestataire='" + idPrestataire + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", tel='" + tel + '\'' +
                ", ville='" + ville + '\'' +
                ", adresse='" + adresse + '\'' +
                ", cp='" + cp + '\'' +
                '}';
    }

    public Prestataire(String idPrestataire, String nom, String prenom, String email, String tel, String ville, String adresse, String cp) {
        this.idPrestataire = idPrestataire;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
        this.cp = cp;
    }

    public Prestataire() {
    }
}
