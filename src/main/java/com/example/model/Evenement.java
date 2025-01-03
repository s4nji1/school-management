package com.example.model;

import java.time.LocalDate;

public class Evenement {
    private int idEvent;
    private String nomEvent;
    private LocalDate dateEvent;
    private String description;
    private int idUser;

    public Evenement(){}

    public Evenement(int idEvent, String nomEvent, LocalDate dateEvent, String description, int idUser){
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.dateEvent = dateEvent;
        this.description = description;
        this.idUser = idUser;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
