package com.example.spareservice.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class PrestataireDTO {
    @SerializedName("_id") private String idPrestataire;
    @SerializedName("nom") private String nom;
    @SerializedName("prenom") private String prenom;
    @SerializedName("email") private String email;
    @SerializedName("mdp") private String mdp;
    @SerializedName("tel") private String tel;
    @SerializedName("adresse") private String adresse;
    @SerializedName("ville") private String ville;
    @SerializedName("cp") private String cp;
    @SerializedName("service") private String service;

    public String getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(String idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "PrestataireDTO{" +
                "idPrestataire='" + idPrestataire + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", tel='" + tel + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", cp='" + cp + '\'' +
                ", service='" + service + '\'' +
                '}';
    }
}
