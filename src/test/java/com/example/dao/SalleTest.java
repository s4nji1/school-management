package com.example.dao;

import com.example.dao.impl.SalleDAOImpl;
import com.example.model.Salle;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class SalleTest {

    private final SalleDAOImpl salleDAO = mock(SalleDAOImpl.class);

    @Test
    public void testAjouterSalle() {
        Salle salle = new Salle(1, "Salle A", 50);

        doNothing().when(salleDAO).ajouter(salle);

        salleDAO.ajouter(salle);

        verify(salleDAO, times(1)).ajouter(salle);
    }

    @Test
    public void testAfficherSalle() {
        Salle salle = new Salle(1, "Salle A", 50);

        when(salleDAO.afficher(1)).thenReturn(salle);

        Salle result = salleDAO.afficher(1);

        assertNotNull(result);
        assertEquals("Salle A", result.getNomSalle());
        assertEquals(50, result.getCapacite());
    }

    @Test
    public void testModifierSalle() {
        Salle salle = new Salle(1, "Salle A", 50);

        doNothing().when(salleDAO).modifier(salle);

        salleDAO.modifier(salle);

        verify(salleDAO, times(1)).modifier(salle);
    }

    @Test
    public void testSupprimerSalle() {
        doNothing().when(salleDAO).supprimer(1);

        salleDAO.supprimer(1);

        verify(salleDAO, times(1)).supprimer(1);
    }
}
