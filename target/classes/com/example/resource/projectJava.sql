CREATE TABLE utilisateurs (
    id_user SERIAL PRIMARY KEY,
    nom VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    email VARCHAR(30) UNIQUE NOT NULL,
    type VARCHAR(20) CHECK (type IN ('ETUDIANT', 'PROFESSEUR')) NOT NULL
);

CREATE TABLE evenements (
    id_event SERIAL PRIMARY KEY,
    nom_event VARCHAR(30) NOT NULL,
    date_event DATE NOT NULL,
    description TEXT,
    id_user INT REFERENCES utilisateurs(id_user)
);

CREATE TABLE salles (
    id_salle SERIAL PRIMARY KEY,
    nom_salle VARCHAR(30) NOT NULL,
    capacite INT NOT NULL
);

CREATE TABLE terrains (
    id_terrain SERIAL PRIMARY KEY,
    nom_terrain VARCHAR(30) NOT NULL,
    type VARCHAR(30) NOT NULL
);

CREATE TABLE reservations (
    id_reservation SERIAL PRIMARY KEY,
    id_user INT REFERENCES utilisateurs(id_user),
    id_event INT REFERENCES evenements(id_event),
    id_salle INT REFERENCES salles(id_salle),
    id_terrain INT REFERENCES terrains(id_terrain),
    date_reservation DATE NOT NULL
);