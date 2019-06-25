package com.example.spareservice.data.dto;

import com.google.gson.annotations.SerializedName;

public class MissionDTO {
    @SerializedName("idAnnonce") private String idAnnonce;
    @SerializedName("idPrestataire") private String idPrestataire;
    @SerializedName("idClient") private String idClient;
    @SerializedName("debutDate") private String debutDate;
    @SerializedName("debutHeure") private String debutHeure;
    @SerializedName("isValide") private boolean isValide;
    @SerializedName("inProcess") private boolean inProcess;

    public String getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(String idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(String idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getDebutDate() {
        return debutDate;
    }

    public void setDebutDate(String debutDate) {
        this.debutDate = debutDate;
    }

    public String getDebutHeure() {
        return debutHeure;
    }

    public void setDebutHeure(String debutHeure) {
        this.debutHeure = debutHeure;
    }

    public boolean isValide() {
        return isValide;
    }

    public void setValide(boolean valide) {
        isValide = valide;
    }

    public boolean isInProcess() {
        return inProcess;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

    @Override
    public String toString() {
        return "MissionDTO{" +
                "idAnnonce='" + idAnnonce + '\'' +
                ", idPrestataire='" + idPrestataire + '\'' +
                ", idClient='" + idClient + '\'' +
                ", debutDate='" + debutDate + '\'' +
                ", debutHeure='" + debutHeure + '\'' +
                ", isValide='" + isValide + '\'' +
                ", inProcess=" + inProcess +
                '}';
    }
}
