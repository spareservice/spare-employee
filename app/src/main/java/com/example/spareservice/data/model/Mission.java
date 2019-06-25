package com.example.spareservice.data.model;

public class Mission {
    private String idAnnonce;
    private String idPrestataire;
    private String idClient;
    private String debutDate;
    private String debutHeure;
    private boolean isValide;
    private boolean inProcess;

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
        return "Mission{" +
                "idAnnonce='" + idAnnonce + '\'' +
                ", idPrestataire='" + idPrestataire + '\'' +
                ", idClient='" + idClient + '\'' +
                ", debutDate='" + debutDate + '\'' +
                ", debutHeure='" + debutHeure + '\'' +
                ", isValide=" + isValide +
                ", inProcess=" + inProcess +
                '}';
    }
}
