-- Cancellazione dati esistenti (in ordine inverso per rispettare le foreign keys)
DELETE FROM esperienza;
DELETE FROM palestra_personal_trainers;
DELETE FROM palestra_images;
DELETE FROM credentials;
DELETE FROM personal_trainer;
DELETE FROM palestra;
DELETE FROM image_entity;
DELETE FROM users;

-- Reset delle sequenze
--ALTER SEQUENCE users_id_seq RESTART WITH 1;
--ALTER SEQUENCE credentials_id_seq RESTART WITH 1;
--ALTER SEQUENCE image_entity_id_seq RESTART WITH 1;
--ALTER SEQUENCE personal_trainer_id_seq RESTART WITH 1;
--ALTER SEQUENCE palestra_id_seq RESTART WITH 1;
--ALTER SEQUENCE esperienza_id_seq RESTART WITH 1;

-- Inserimento Users
INSERT INTO users (id, name, surname) VALUES
(1, 'Mario', 'Rossi'),
(2, 'Giulia', 'Verdi'),
(3, 'Luca', 'Bianchi'),
(4, 'Anna', 'Neri'),
(5, 'Marco', 'Gialli'),
(6, 'Sara', 'Blu'),
(7, 'Andrea', 'Viola'),
(8, 'Chiara', 'Rosa'),
(9, 'Federico', 'Arancione'),
(10, 'Elena', 'Grigio');

-- Inserimento Credentials
INSERT INTO credentials (id, username, password, role, user_id) VALUES
(1, 'admin', '$2b$12$0Wtkrr30sTRncddhmXOlNuUTVZeDJ3M6NBi3rJORc3J/4PY8FCUsq', 'ADMIN', 1),
(2, 'mrossi', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 2),
(3, 'gverdi', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 3),
(4, 'lbianchi', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 4),
(5, 'aneri', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 5),
(6, 'mgialli', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 6),
(7, 'sblu', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 7),
(8, 'aviola', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 8),
(9, 'crosa', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 9),
(10, 'farancione', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'DEFAULT', 10);

-- Inserimento ImageEntity
INSERT INTO image_entity (id, name) VALUES
(1, '/images/palestra1_1.jpg'),
(2, '/images/palestra1_2.jpg'),
(3, '/images/palestra1_3.jpg'),
(4, '/images/palestra2_1.jpg'),
(5, '/images/palestra2_2.jpg'),
(6, '/images/palestra3_1.jpg'),
(7, '/images/palestra3_2.jpg'),
(8, '/images/palestra4_1.jpg'),
(9, '/images/palestra5_1.jpg'),
(10, '/images/palestra6_1.jpg'),
(11, '/images/trainer1.jpg'),
(12, '/images/trainer2.jpg'),
(13, '/images/trainer3.jpg'),
(14, '/images/trainer4.jpg'),
(15, '/images/trainer5.jpg'),
(16, '/images/trainer6.jpg'),
(17, '/images/trainer7.jpg'),
(18, '/images/trainer8.jpg'),
(19, '/images/trainer9.jpg'),
(20, '/images/trainer10.jpg');

-- Inserimento Personal Trainers
INSERT INTO personal_trainer (id, nome, cognome, data_nascita, specializzazione, foto_id) VALUES
(1, 'Alessandro', 'Ferrari', '1985-03-15', 'Bodybuilding', 11),
(2, 'Valentina', 'Conti', '1990-07-22', 'Pilates', 12),
(3, 'Roberto', 'Marini', '1982-11-08', 'CrossFit', 13),
(4, 'Francesca', 'Romano', '1988-05-12', 'Yoga', 14),
(5, 'Davide', 'Ricci', '1987-09-30', 'Powerlifting', 15),
(6, 'Silvia', 'Colombo', '1992-02-18', 'Fitness', 16),
(7, 'Matteo', 'Esposito', '1984-12-03', 'CrossFit', 17),
(8, 'Laura', 'Moretti', '1989-06-25', 'Zumba', 18),
(9, 'Simone', 'Barbieri', '1986-04-14', 'Calisthenics', 19),
(10, 'Martina', 'Galli', '1991-10-07', 'Spinning', 20);

-- Inserimento Palestre
INSERT INTO palestra (id, nome, citta, specializzazione) VALUES
(1, 'Iron Gym', 'Roma', 'Bodybuilding'),
(2, 'Fitness Plus', 'Milano', 'Fitness'),
(3, 'CrossFit Central', 'Torino', 'CrossFit'),
(4, 'Wellness Center', 'Napoli', 'Benessere'),
(5, 'Power House', 'Bologna', 'Powerlifting'),
(6, 'Yoga & More', 'Firenze', 'Yoga'),
(7, 'Extreme Fitness', 'Roma', 'CrossFit'),
(8, 'Body & Mind', 'Milano', 'Pilates');

-- Associazione Palestre-Immagini (tabella di join)
INSERT INTO palestra_images (palestra_id, images_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 4), (2, 5),
(3, 6), (3, 7),
(4, 8),
(5, 9),
(6, 10);

-- Associazione Palestre-Personal Trainers (tabella di join)
INSERT INTO palestra_personal_trainers (palestre_id, personal_trainers_id) VALUES
(1, 1), (1, 5), (1, 9),
(2, 2), (2, 6), (2, 8),
(3, 3), (3, 7),
(4, 2), (4, 4), (4, 8),
(5, 1), (5, 5),
(6, 4), (6, 2),
(7, 3), (7, 7), (7, 9),
(8, 2), (8, 4), (8, 6);

-- Inserimento Esperienze (recensioni)
INSERT INTO esperienza (id, titolo, stelle, descrizione, palestra_id, user_id) VALUES
(1, 'Ottima esperienza di allenamento', 5, 'Palestra ben attrezzata con macchinari moderni. Staff competente e ambiente pulito. Consigliatissima per chi vuole allenarsi seriamente.', 1, 2),
(2, 'Ambiente accogliente', 4, 'Mi sono trovato molto bene, istruttori preparati e sempre disponibili. Unico neo: un po'' affollata nelle ore di punta.', 1, 3),
(3, 'Perfetta per il CrossFit', 5, 'Finalmente una palestra specializzata in CrossFit con attrezzature professionali. I WOD sono sempre ben strutturati.', 3, 4),
(4, 'Deludente', 2, 'Palestra datata, macchinari vecchi e spogliatoi non sempre puliti. Rapporto qualità-prezzo non soddisfacente.', 2, 5),
(5, 'Buona per principianti', 4, 'Ottima per chi inizia, istruttori pazienti che seguono bene i neofiti. Prezzi onesti.', 2, 6),
(6, 'Centro benessere fantastico', 5, 'Non è solo una palestra ma un vero centro benessere. Corsi di yoga rilassanti e ambiente zen. Perfetto per staccare dallo stress.', 4, 7),
(7, 'Powerlifting al top', 5, 'Per chi fa powerlifting è il paradiso. Bilancieri olimpici, pedane dedicate e atmosfera da vera palestra old school.', 5, 8),
(8, 'Corsi di gruppo divertenti', 4, 'I corsi di gruppo sono molto coinvolgenti, specialmente quelli di Zumba. Istruttori energici e motivanti.', 4, 9),
(9, 'Palestra moderna', 4, 'Struttura nuova con tutto quello che serve. Mancano solo più attività per i bambini.', 6, 10),
(10, 'CrossFit intenso', 5, 'Allenamenti tosti ma che danno risultati. Coach preparatissimi che ti spingono sempre a dare il massimo.', 7, 2),
(11, 'Pilates rilassante', 5, 'Corsi di Pilates perfetti per la postura. Ambiente tranquillo e istruttrice molto brava.', 8, 3),
(12, 'Buon rapporto qualità-prezzo', 4, 'Niente di eccezionale ma onesta. Prezzi accessibili e servizi essenziali ma funzionali.', 2, 4),
(13, 'Attrezzature all''avanguardia', 5, 'Macchinari di ultima generazione e area cardio ben fornita. Vale ogni euro speso.', 1, 5),
(14, 'Staff poco professionale', 2, 'Istruttori spesso distratti e poco disponibili. Peccato perché la struttura sarebbe anche bella.', 3, 6),
(15, 'Orari comodi', 4, 'Aperta fino a tardi, perfetta per chi lavora. Buona varietà di corsi serali.', 6, 7);

-- Reset delle sequenze per PostgreSQL (opzionale, utile se usi PostgreSQL)
-- SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
-- SELECT setval('credentials_id_seq', (SELECT MAX(id) FROM credentials));
-- SELECT setval('image_entity_id_seq', (SELECT MAX(id) FROM image_entity));
-- SELECT setval('personal_trainer_id_seq', (SELECT MAX(id) FROM personal_trainer));
-- SELECT setval('palestra_id_seq', (SELECT MAX(id) FROM palestra));
-- SELECT setval('esperienza_id_seq', (SELECT MAX(id) FROM esperienza));