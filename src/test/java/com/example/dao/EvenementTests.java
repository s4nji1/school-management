package com.example.dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.dao.impl.EvenementDAOImpl;
import com.example.model.Evenement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EvenementTests {

    private EvenementDAOImpl evenementDAO;

    @BeforeEach
    public void setUp() {
        evenementDAO = mock(EvenementDAOImpl.class);
    }

    @Test
    public void testAjouterEvenement() {
        Evenement evenement = new Evenement();
        evenement.setNomEvent("Concert");
        evenement.setDescription("Concert de musique");
        evenement.setDateEvent(LocalDate.of(2024, 12, 25));
        evenement.setIdUser(1);

        doNothing().when(evenementDAO).ajouter(evenement);

        evenementDAO.ajouter(evenement);

        verify(evenementDAO, times(1)).ajouter(evenement);
    }

    @Test
    public void testAfficherEvenement() {
        Evenement evenement = new Evenement();
        evenement.setIdEvent(1);
        evenement.setNomEvent("Concert");
        evenement.setDescription("Concert de musique");
        evenement.setDateEvent(LocalDate.of(2024, 12, 25));
        evenement.setIdUser(1);

        when(evenementDAO.afficher(1)).thenReturn(evenement);

        Evenement resultat = evenementDAO.afficher(1);

        assertNotNull(resultat);
        assertEquals(1, resultat.getIdEvent());
        assertEquals("Concert", resultat.getNomEvent());
        assertEquals("Concert de musique", resultat.getDescription());
        assertEquals(LocalDate.of(2024, 12, 25), resultat.getDateEvent());
    }

    @Test
    public void testAfficherEvenementNonTrouve() {
        when(evenementDAO.afficher(999)).thenReturn(null);

        Evenement resultat = evenementDAO.afficher(999);

        assertNull(resultat);
    }

    @Test
    public void testModifierEvenement() {
        Evenement evenement = new Evenement();
        evenement.setIdEvent(1);
        evenement.setNomEvent("Concert");
        evenement.setDescription("Concert de musique");
        evenement.setDateEvent(LocalDate.of(2024, 12, 25));
        evenement.setIdUser(1);

        doNothing().when(evenementDAO).modifier(evenement);

        evenementDAO.modifier(evenement);

        verify(evenementDAO, times(1)).modifier(evenement);
    }

    @Test
    public void testSupprimerEvenement() {
        doNothing().when(evenementDAO).supprimer(1);

        evenementDAO.supprimer(1);

        verify(evenementDAO, times(1)).supprimer(1);
    }
}
