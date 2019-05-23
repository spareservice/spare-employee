package com.example.spareservice.data.model;

import java.io.Serializable;

public class Annonce implements Serializable {
    private String idClient;
    private String idService;
    private String descriptionAnnonce;
    private String detailAnnonce;

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

    @Override
    public String toString() {
        return "Annonce{" +
                "idClient='" + idClient + '\'' +
                ", idService='" + idService + '\'' +
                ", descriptionAnnonce='" + descriptionAnnonce + '\'' +
                ", detailAnnonce='" + detailAnnonce + '\'' +
                '}';
    }
}
