// package com.example.App;

// import com.example.dao.impl.*;
// import com.example.model.*;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Scanner;

// public class Main {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         UtilisateurDAOImpl utilisateurDAO = new UtilisateurDAOImpl();
//         EvenementDAOImpl evenementDAO = new EvenementDAOImpl();
//         SalleDAOImpl salleDAO = new SalleDAOImpl();
//         TerrainDAOImpl terrainDAO = new TerrainDAOImpl();
//         ReservationDAOImpl reservationDAO = new ReservationDAOImpl();

//         while (true) {
//             System.out.println("\n=== MENU ===");
//             System.out.println("1. Gerer Utilisateurs");
//             System.out.println("2. Gerer evenements");
//             System.out.println("3. Gerer Salles");
//             System.out.println("4. Gerer Terrains");
//             System.out.println("5. Gerer Reservations");
//             System.out.println("6. Quitter");
//             System.out.print("Choisissez une option: ");
//             int choix = scanner.nextInt();
//             scanner.nextLine();

//             switch (choix) {
//                 case 1 -> gererUtilisateurs(scanner, utilisateurDAO);
//                 case 2 -> gererEvenements(scanner, evenementDAO);
//                 case 3 -> gererSalles(scanner, salleDAO);
//                 case 4 -> gererTerrains(scanner, terrainDAO);
//                 case 5 -> gererReservations(scanner, reservationDAO);
//                 case 6 -> {
//                     System.out.println("Au revoir !");
//                     return;
//                 }
//                 default -> System.out.println("Option invalide. Veuillez reessayer.");
//             }
//         }
//     }

//     private static void gererUtilisateurs(Scanner scanner, UtilisateurDAOImpl utilisateurDAO) {
//         System.out.println("\n=== Gestion des Utilisateurs ===");
//         System.out.println("1. Ajouter");
//         System.out.println("2. Afficher Tous");
//         System.out.println("3. Modifier");
//         System.out.println("4. Supprimer");
//         System.out.print("Choisissez une option: ");
//         int choix = scanner.nextInt();
//         scanner.nextLine();
    
//         switch (choix) {
//             case 1 -> {
//                 Utilisateur utilisateur = new Utilisateur();
//                 System.out.print("Nom: ");
//                 utilisateur.setNom(scanner.nextLine());
//                 System.out.print("Prenom: ");
//                 utilisateur.setPrenom(scanner.nextLine());
//                 System.out.print("Email: ");
//                 utilisateur.setEmail(scanner.nextLine());
//                 System.out.print("Type (PROFESSEUR/ETUDIANT): ");
//                 utilisateur.setType(scanner.nextLine());
//                 utilisateurDAO.ajouter(utilisateur);
//                 System.out.println("Utilisateur ajoute avec succes !");
//             }
//             case 2 -> {
//                 List<Utilisateur> utilisateurs = utilisateurDAO.afficherTous();
//                 utilisateurs.forEach(u -> {
//                     System.out.println("ID: " + u.getIdUser() +
//                             ", Nom: " + u.getNom() +
//                             ", Prenom: " + u.getPrenom() +
//                             ", Email: " + u.getEmail() +
//                             ", Type: " + u.getType());
//                 });
//             }
//             case 3 -> {
//                 System.out.print("ID de l'utilisateur a modifier: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 Utilisateur utilisateur = utilisateurDAO.afficher(id);
//                 if (utilisateur != null) {
//                     System.out.print("Nouveau Nom: ");
//                     utilisateur.setNom(scanner.nextLine());
//                     System.out.print("Nouveau Prenom: ");
//                     utilisateur.setPrenom(scanner.nextLine());
//                     System.out.print("Nouvel Email: ");
//                     utilisateur.setEmail(scanner.nextLine());
//                     System.out.print("Nouveau Type (Admin/Utilisateur): ");
//                     utilisateur.setType(scanner.nextLine());
//                     utilisateurDAO.modifier(utilisateur);
//                     System.out.println("Utilisateur modifie avec succes !");
//                 } else {
//                     System.out.println("Utilisateur non trouve.");
//                 }
//             }
//             case 4 -> {
//                 System.out.print("ID de l'utilisateur a supprimer: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 utilisateurDAO.supprimer(id);
//                 System.out.println("Utilisateur supprime avec succes !");
//             }
//             default -> System.out.println("Option invalide.");
//         }
//     }    

//     private static void gererEvenements(Scanner scanner, EvenementDAOImpl evenementDAO) {
//         System.out.println("\n=== Gestion des evenements ===");
//         System.out.println("1. Ajouter");
//         System.out.println("2. Afficher Tous");
//         System.out.println("3. Modifier");
//         System.out.println("4. Supprimer");
//         System.out.print("Choisissez une option: ");
//         int choix = scanner.nextInt();
//         scanner.nextLine(); 
    
//         switch (choix) {
//             case 1 -> {
//                 Evenement evenement = new Evenement();
//                 System.out.print("Nom: ");
//                 evenement.setNomEvent(scanner.nextLine());
//                 System.out.print("Description: ");
//                 evenement.setDescription(scanner.nextLine());
//                 System.out.print("Date de l'evenement (format: yyyy-MM-dd): ");
//                 evenement.setDateEvent(LocalDate.parse(scanner.nextLine()));
//                 System.out.print("ID Utilisateur: ");
//                 evenement.setIdUser(scanner.nextInt());
//                 scanner.nextLine();
//                 evenementDAO.ajouter(evenement);
//                 System.out.println("evenement ajoute avec succes !");
//             }
//             case 2 -> {
//                 List<Evenement> evenements = evenementDAO.afficherTous();
//                 evenements.forEach(e -> {
//                     System.out.println("ID: " + e.getIdEvent() + 
//                                        ", Nom: " + e.getNomEvent() + 
//                                        ", Description: " + e.getDescription() + 
//                                        ", Date: " + e.getDateEvent() + 
//                                        ", ID Utilisateur: " + e.getIdUser());
//                 });
//             }
//             case 3 -> {
//                 System.out.print("ID de l'evenement a modifier: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 Evenement evenement = evenementDAO.afficher(id);
//                 if (evenement != null) {
//                     System.out.print("Nouveau Nom: ");
//                     evenement.setNomEvent(scanner.nextLine());
//                     System.out.print("Nouvelle Description: ");
//                     evenement.setDescription(scanner.nextLine());
//                     System.out.print("Nouvelle Date de l'evenement (format: yyyy-MM-dd): ");
//                     evenement.setDateEvent(LocalDate.parse(scanner.nextLine()));
//                     System.out.print("Nouvel ID Utilisateur: ");
//                     evenement.setIdUser(scanner.nextInt());
//                     scanner.nextLine();
//                     evenementDAO.modifier(evenement);
//                     System.out.println("evenement modifie avec succes !");
//                 } else {
//                     System.out.println("evenement non trouve.");
//                 }
//             }
//             case 4 -> {
//                 System.out.print("ID de l'evenement a supprimer: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 evenementDAO.supprimer(id);
//                 System.out.println("evenement supprime avec succes !");
//             }
//             default -> System.out.println("Option invalide.");
//         }
//     }
    
    

    
//     private static void gererSalles(Scanner scanner, SalleDAOImpl salleDAO) {
//         System.out.println("\n=== Gestion des Salles ===");
//         System.out.println("1. Ajouter");
//         System.out.println("2. Afficher Toutes");
//         System.out.println("3. Modifier");
//         System.out.println("4. Supprimer");
//         System.out.print("Choisissez une option: ");
//         int choix = scanner.nextInt();
//         scanner.nextLine(); 
    
//         switch (choix) {
//             case 1 -> {
//                 Salle salle = new Salle();
//                 System.out.print("Nom: ");
//                 salle.setNomSalle(scanner.nextLine());
//                 System.out.print("Capacite: ");
//                 salle.setCapacite(scanner.nextInt());
//                 scanner.nextLine();
//                 salleDAO.ajouter(salle);
//                 System.out.println("Salle ajoutee avec succes !");
//             }
//             case 2 -> {
//                 List<Salle> salles = salleDAO.afficherTous();
//                 salles.forEach(s -> {
//                     System.out.println("ID: " + s.getIdSalle() + ", Nom: " + s.getNomSalle() + ", Capacite: " + s.getCapacite());
//                 });
//             }
//             case 3 -> {
//                 System.out.print("ID de la salle a modifier: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 Salle salle = salleDAO.afficher(id);
//                 if (salle != null) {
//                     System.out.print("Nouveau Nom: ");
//                     salle.setNomSalle(scanner.nextLine());
//                     System.out.print("Nouvelle Capacite: ");
//                     salle.setCapacite(scanner.nextInt());
//                     scanner.nextLine();
//                     salleDAO.modifier(salle);
//                     System.out.println("Salle modifiee avec succes !");
//                 } else {
//                     System.out.println("Salle non trouvee.");
//                 }
//             }
//             case 4 -> {
//                 System.out.print("ID de la salle a supprimer: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 salleDAO.supprimer(id);
//                 System.out.println("Salle supprimee avec succes !");
//             }
//             default -> System.out.println("Option invalide.");
//         }
//     }
    

//     private static void gererTerrains(Scanner scanner, TerrainDAOImpl terrainDAO) {
//         System.out.println("\n=== Gestion des Terrains ===");
//         System.out.println("1. Ajouter");
//         System.out.println("2. Afficher Tous");
//         System.out.println("3. Modifier");
//         System.out.println("4. Supprimer");
//         System.out.print("Choisissez une option: ");
//         int choix = scanner.nextInt();
//         scanner.nextLine();
    
//         switch (choix) {
//             case 1 -> {
//                 Terrain terrain = new Terrain();
//                 System.out.print("Nom: ");
//                 terrain.setNomTerrain(scanner.nextLine());
//                 System.out.print("Type: ");
//                 terrain.setType(scanner.nextLine());
//                 terrainDAO.ajouter(terrain);
//                 System.out.println("Terrain ajoute avec succes !");
//             }
//             case 2 -> {
//                 List<Terrain> terrains = terrainDAO.afficherTous();
//                 terrains.forEach(t -> {
//                     System.out.println("ID: " + t.getIdTerrain() + ", Nom: " + t.getNomTerrain() + ", Type: " + t.getType());
//                 });
//             }
//             case 3 -> {
//                 System.out.print("ID du terrain a modifier: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 Terrain terrain = terrainDAO.afficher(id);
//                 if (terrain != null) {
//                     System.out.print("Nouveau Nom: ");
//                     terrain.setNomTerrain(scanner.nextLine());
//                     System.out.print("Nouveau Type: ");
//                     terrain.setType(scanner.nextLine());
//                     terrainDAO.modifier(terrain);
//                     System.out.println("Terrain modifie avec succes !");
//                 } else {
//                     System.out.println("Terrain non trouve.");
//                 }
//             }
//             case 4 -> {
//                 System.out.print("ID du terrain a supprimer: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 terrainDAO.supprimer(id);
//                 System.out.println("Terrain supprime avec succes !");
//             }
//             default -> System.out.println("Option invalide.");
//         }
//     }
    

//     private static void gererReservations(Scanner scanner, ReservationDAOImpl reservationDAO) {
//         System.out.println("\n=== Gestion des Reservations ===");
//         System.out.println("1. Ajouter");
//         System.out.println("2. Afficher Toutes");
//         System.out.println("3. Modifier");
//         System.out.println("4. Supprimer");
//         System.out.print("Choisissez une option: ");
//         int choix = scanner.nextInt();
//         scanner.nextLine();
    
//         switch (choix) {
//             case 1 -> {
//                 Reservation reservation = new Reservation();
//                 System.out.print("ID Utilisateur: ");
//                 reservation.setIdUser(scanner.nextInt());
//                 System.out.print("ID evenement: ");
//                 reservation.setIdEvent(scanner.nextInt());
//                 System.out.print("ID Salle: ");
//                 reservation.setIdSalle(scanner.nextInt());
//                 System.out.print("ID Terrain: ");
//                 reservation.setIdTerrain(scanner.nextInt());
//                 System.out.print("Date de Reservation (AAAA-MM-JJ): ");
//                 reservation.setDateReservation(LocalDate.parse(scanner.next()));
//                 reservationDAO.ajouter(reservation);
//                 System.out.println("Reservation ajoutee avec succes !");
//             }
//             case 2 -> {
//                 List<Reservation> reservations = reservationDAO.afficherTous();
//                 reservations.forEach(r -> {
//                     System.out.println("ID Reservation: " + r.getIdReservation() +
//                             ", ID Utilisateur: " + r.getIdUser() +
//                             ", ID evenement: " + r.getIdEvent() +
//                             ", ID Salle: " + r.getIdSalle() +
//                             ", ID Terrain: " + r.getIdTerrain() +
//                             ", Date: " + r.getDateReservation());
//                 });
//             }
//             case 3 -> {
//                 System.out.print("ID de la reservation a modifier: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 Reservation reservation = reservationDAO.afficher(id);
//                 if (reservation != null) {
//                     System.out.print("Nouvelle Date de Reservation (AAAA-MM-JJ): ");
//                     reservation.setDateReservation(LocalDate.parse(scanner.next()));
//                     reservationDAO.modifier(reservation);
//                     System.out.println("Reservation modifiee avec succes !");
//                 } else {
//                     System.out.println("Reservation non trouvee.");
//                 }
//             }
//             case 4 -> {
//                 System.out.print("ID de la reservation a supprimer: ");
//                 int id = scanner.nextInt();
//                 scanner.nextLine();
//                 reservationDAO.supprimer(id);
//                 System.out.println("Reservation supprimee avec succes !");
//             }
//             default -> System.out.println("Option invalide.");
//         }
//     }
    
// }
