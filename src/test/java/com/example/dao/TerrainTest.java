package com.example.dao;

import com.example.dao.impl.TerrainDAOImpl;
import com.example.model.Terrain;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class TerrainTest {

    private final TerrainDAOImpl terrainDAO = mock(TerrainDAOImpl.class);

    @Test
    public void testAjouterTerrain() {
        Terrain terrain = new Terrain(1, "Terrain 1", "Football");

        doNothing().when(terrainDAO).ajouter(terrain);

        terrainDAO.ajouter(terrain);

        verify(terrainDAO, times(1)).ajouter(terrain);
    }

    @Test
    public void testAfficherTerrain() {
        Terrain terrain = new Terrain(1, "Terrain 1", "Football");

        when(terrainDAO.afficher(1)).thenReturn(terrain);

        Terrain result = terrainDAO.afficher(1);

        assertNotNull(result);
        assertEquals("Terrain 1", result.getNomTerrain());
        assertEquals("Football", result.getType());
    }

    @Test
    public void testModifierTerrain() {
        Terrain terrain = new Terrain(1, "Terrain 1", "Football");

        doNothing().when(terrainDAO).modifier(terrain);

        terrainDAO.modifier(terrain);

        verify(terrainDAO, times(1)).modifier(terrain);
    }

    @Test
    public void testSupprimerTerrain() {
        doNothing().when(terrainDAO).supprimer(1);

        terrainDAO.supprimer(1);

        verify(terrainDAO, times(1)).supprimer(1);
    }
}
