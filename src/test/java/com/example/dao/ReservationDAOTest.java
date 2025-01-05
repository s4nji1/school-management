package com.example.dao;

import com.example.dao.impl.ReservationDAOImpl;
import com.example.model.Reservation;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ReservationDAOTest {

    private final ReservationDAOImpl reservationDAO = mock(ReservationDAOImpl.class);

    @Test
    public void testAjouterReservation() {
        Reservation reservation = new Reservation(1, 1, 1, 1, 1, LocalDate.of(2024, 12, 25));

        doNothing().when(reservationDAO).ajouter(reservation);

        reservationDAO.ajouter(reservation);

        verify(reservationDAO, times(1)).ajouter(reservation);
    }

    @Test
    public void testAfficherReservation() {
        Reservation reservation = new Reservation(1, 1, 1, 1, 1, LocalDate.of(2024, 12, 25));

        when(reservationDAO.afficher(1)).thenReturn(reservation);

        Reservation result = reservationDAO.afficher(1);

        assertNotNull(result);
        assertEquals(1, result.getIdUser());
        assertEquals(1, result.getIdSalle());
        assertEquals(LocalDate.of(2024, 12, 25), result.getDateReservation());
    }

    @Test
    public void testModifierReservation() {
        Reservation reservation = new Reservation(1, 1, 1, 1, 1, LocalDate.of(2024, 12, 25));

        doNothing().when(reservationDAO).modifier(reservation);

        reservationDAO.modifier(reservation);

        verify(reservationDAO, times(1)).modifier(reservation);
    }

    @Test
    public void testSupprimerReservation() {
        doNothing().when(reservationDAO).supprimer(1);

        reservationDAO.supprimer(1);

        verify(reservationDAO, times(1)).supprimer(1);
    }
}
