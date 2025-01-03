package com.example.model;

import java.time.LocalDate;

public class Reservation {
    private int idReservation;
    private int idUser;
    private int idEvent;
    private int idSalle;
    private int idTerrain;
    private LocalDate dateReservation;

    public Reservation(){}

    public Reservation(int idReservation, int idUser, int idEvent, int idSalle, int idTerrain, LocalDate dateReservation){
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.idSalle = idSalle;
        this.idTerrain = idTerrain;
        this.dateReservation = dateReservation;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEvent() {
        return idEvent; 
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }
}
