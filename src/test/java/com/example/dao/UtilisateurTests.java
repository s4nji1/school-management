package com.example.dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.dao.impl.UtilisateurDAOImpl;
import com.example.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

public class UtilisateurTests {

    private UtilisateurDAOImpl utilisateurDAO;

    @BeforeEach
    public void setUp() {
        utilisateurDAO = mock(UtilisateurDAOImpl.class);
    }

    @Test
    public void testAjouterUtilisateur() throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Jean");
        utilisateur.setEmail("jean.dupont@example.com");
        utilisateur.setType("Admin");
        utilisateur.setPassword("password123");

        when(utilisateurDAO.ajouter(utilisateur)).thenReturn(true);

        boolean result = utilisateurDAO.ajouter(utilisateur);

        verify(utilisateurDAO, times(1)).ajouter(utilisateur);
        assertTrue(result);
    }

    @Test
    public void testAfficherUtilisateur() throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUser(1);
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Jean");
        utilisateur.setEmail("jean.dupont@example.com");
        utilisateur.setType("Admin");
        utilisateur.setPassword("password123");

        when(utilisateurDAO.afficher(1)).thenReturn(utilisateur);

        Utilisateur result = utilisateurDAO.afficher(1);

        assertNotNull(result);
        assertEquals(1, result.getIdUser());
        assertEquals("Dupont", result.getNom());
        assertEquals("Jean", result.getPrenom());
        assertEquals("jean.dupont@example.com", result.getEmail());
        assertEquals("Admin", result.getType());
    }

    @Test
    public void testAfficherUtilisateurNonTrouve() throws SQLException {

        when(utilisateurDAO.afficher(999)).thenReturn(null);

        Utilisateur result = utilisateurDAO.afficher(999);

        assertNull(result);
    }

    @Test
    public void testModifierUtilisateur() throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUser(1);
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Jean");
        utilisateur.setEmail("jean.dupont@example.com");
        utilisateur.setType("Admin");
        utilisateur.setPassword("password123");

        doNothing().when(utilisateurDAO).modifier(utilisateur);

        utilisateurDAO.modifier(utilisateur);

        verify(utilisateurDAO, times(1)).modifier(utilisateur);
    }

    @Test
    public void testSupprimerUtilisateur() throws SQLException {
        doNothing().when(utilisateurDAO).supprimer(1);

        utilisateurDAO.supprimer(1);

        verify(utilisateurDAO, times(1)).supprimer(1);
    }
}
