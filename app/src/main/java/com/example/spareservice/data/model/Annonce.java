package com.example.spareservice.data.model;

import java.io.Serializable;


public class Annonce implements Serializable {

    private String idAnnonce;
    private String idClient;
    private String idService;
    private String descriptionAnnonce;
    private String detailAnnonce;
    private String debutDate;
    private String debutHeure;

    public String getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(String idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getDescriptionAnnonce() {
        return descriptionAnnonce;
    }

    public void setDescriptionAnnonce(String descriptionAnnonce) {
        this.descriptionAnnonce = descriptionAnnonce;
    }

    public String getDetailAnnonce() {
        return detailAnnonce;
    }

    public void setDetailAnnonce(String detailAnnonce) {
        this.detailAnnonce = detailAnnonce;
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

    @Override
    public String toString() {
        return "Annonce{" +
                "idAnnonce='" + idAnnonce + '\'' +
                ", idClient='" + idClient + '\'' +
                ", idService='" + idService + '\'' +
                ", descriptionAnnonce='" + descriptionAnnonce + '\'' +
                ", detailAnnonce='" + detailAnnonce + '\'' +
                ", debutDate='" + debutDate + '\'' +
                ", debutHeure='" + debutHeure + '\'' +
                '}';
    }
}
