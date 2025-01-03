package com.example.dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.dao.impl.UtilisateurDAOImpl;
import com.example.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilisateurTests {

    private UtilisateurDAOImpl utilisateurDAO;

    @BeforeEach
    public void setUp() {
        utilisateurDAO = mock(UtilisateurDAOImpl.class);
    }

    @Test
    public void testAjouterUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Jean");
        utilisateur.setEmail("jean.dupont@example.com");
        utilisateur.setType("Admin");

        doNothing().when(utilisateurDAO).ajouter(utilisateur);

        utilisateurDAO.ajouter(utilisateur);

        verify(utilisateurDAO, times(1)).ajouter(utilisateur);
    }

    @Test
    public void testAfficherUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUser(1);
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Jean");
        utilisateur.setEmail("jean.dupont@example.com");
        utilisateur.setType("Admin");

        when(utilisateurDAO.afficher(1)).thenReturn(utilisateur);

        Utilisateur resultat = utilisateurDAO.afficher(1);

        assertNotNull(resultat);
        assertEquals(1, resultat.getIdUser());
        assertEquals("Dupont", resultat.getNom());
        assertEquals("Jean", resultat.getPrenom());
        assertEquals("jean.dupont@example.com", resultat.getEmail());
        assertEquals("Admin", resultat.getType());
    }

    @Test
    public void testAfficherUtilisateurNonTrouve() {
        when(utilisateurDAO.afficher(999)).thenReturn(null);

        Utilisateur resultat = utilisateurDAO.afficher(999);

        assertNull(resultat);
    }

    @Test
    public void testModifierUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUser(1);
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Jean");
        utilisateur.setEmail("jean.dupont@example.com");
        utilisateur.setType("Admin");

        doNothing().when(utilisateurDAO).modifier(utilisateur);

        utilisateurDAO.modifier(utilisateur);

        verify(utilisateurDAO, times(1)).modifier(utilisateur);
    }

    @Test
    public void testSupprimerUtilisateur() {
        doNothing().when(utilisateurDAO).supprimer(1);

        utilisateurDAO.supprimer(1);

        verify(utilisateurDAO, times(1)).supprimer(1);
    }
}
