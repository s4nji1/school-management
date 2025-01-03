# Documentation du Projet DAO

## Vue d’ensemble

Ce projet met en œuvre une architecture basée sur les DAO (Data Access Object) afin d’assurer une séparation claire des responsabilités dans l’application. Il utilise JDBC pour les opérations sur la base de données, tout en respectant les bonnes pratiques comme la gestion des ressources et l’utilisation de requêtes paramétrées.

## Structure des DAO

### Interface DAO Générique
L’interface `GenericDAO<T>` offre une approche standardisée pour les opérations CRUD :
- **Méthodes** :
  - `void add(T entity)` – Ajoute un nouvel enregistrement.
  - `T get(int id)` – Récupère un enregistrement par son ID.
  - `List<T> getAll()` – Récupère tous les enregistrements.
  - `void update(T entity)` – Met à jour un enregistrement existant.
  - `void delete(int id)` – Supprime un enregistrement par son ID.

### Implémentations des DAO
Chaque entité dispose de son propre DAO, qui implémente l’interface `GenericDAO` :
- `UtilisateurDAO`
- `EvenementDAO`
- `SalleDAO`
- `TerrainDAO`
- `ReservationDAO`

### Gestion des Transactions
La classe `TransactionManager` gère les transactions afin de garantir l’intégrité des opérations impliquant plusieurs requêtes à la base de données.

**Exemple :**
```java
public void executeInTransaction(Runnable operation) {
    try (Connection conn = DriverManager.getConnection(...)) {
        conn.setAutoCommit(false);
        operation.run();
        conn.commit();
    } catch (SQLException e) {
        conn.rollback();
        throw e;
    }
}
