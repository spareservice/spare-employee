package com.example.spareservice.data.dto;

import com.google.gson.annotations.SerializedName;

public class MissionDTO {
    @SerializedName("_id") private String idMission;
    @SerializedName("_idAnnonce") private String idAnnonce;
    @SerializedName("_idPrestataire") private String idPrestataire;
    @SerializedName("_idClient") private String idClient;
    @SerializedName("debutDate") private String debutDate;
    @SerializedName("debutHeure") private String debutHeure;
    @SerializedName("infoPrestataire") private String infoPrestataire;
    @SerializedName("isValide") private String isValide;
    @SerializedName("inProcess") private String inProcess;

    public String getIdMission() {
        return idMission;
    }

    public void setIdMission(String idMission) {
        this.idMission = idMission;
    }

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

    public String getInfoPrestataire() {
        return infoPrestataire;
    }

    public void setInfoPrestataire(String infoPrestataire) {
        this.infoPrestataire = infoPrestataire;
    }

    public String getIsValide() {
        return isValide;
    }

    public void setIsValide(String isValide) {
        this.isValide = isValide;
    }

    public String getInProcess() {
        return inProcess;
    }

    public void setInProcess(String inProcess) {
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
                ", infoPrestataire='" + infoPrestataire + '\'' +
                ", isValide=" + isValide +
                ", inProcess=" + inProcess +
                '}';
    }



}
