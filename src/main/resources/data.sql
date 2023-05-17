INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (5, 'Nick', 'Green', 'nick@mail.com', '$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC', 2);
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (6, 'Nora', 'White', 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', 2);
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (4, 'Mike', 'Brown', 'mike@mail.com', '$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN.Y4MTMoY9pjfPALO', 1);



INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (7, 'Mike''s Exhibition #1', '2020-09-16 14:00:04.810221', 4);
INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (8, 'Mike''s Exhibition #2', '2020-09-16 14:00:11.480271', 4);
INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (9, 'Mike''s Exhibition #3', '2020-09-16 14:00:16.351238', 4);
INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (10, 'Nick''s Exhibition #1', '2020-09-16 14:14:54.532337', 5);
INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (11, 'Nick''s Exhibition #2', '2020-09-16 14:15:04.707176', 5);
INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (12, 'Nora''s Exhibition #1', '2020-09-16 14:15:32.464391', 6);
INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (13, 'Nora''s Exhibition #2', '2020-09-16 14:15:39.16246', 6);

INSERT INTO pieces (id, name, genre, exhibition_id) VALUES (6, 'Piece #2', 'REALISM', 7);
INSERT INTO pieces (id, name, genre, exhibition_id) VALUES (5, 'Piece #1', 'IMPRESSIONISM', 7);
INSERT INTO pieces (id, name, genre, exhibition_id) VALUES (7, 'Piece #3', 'EXPRESSIONISM', 7);

INSERT INTO exhibition_collaborator (exhibition_id, collaborator_id) VALUES (7, 5);
INSERT INTO exhibition_collaborator (exhibition_id, collaborator_id) VALUES (7, 6);
INSERT INTO exhibition_collaborator (exhibition_id, collaborator_id) VALUES (10, 6);
INSERT INTO exhibition_collaborator (exhibition_id, collaborator_id) VALUES (10, 4);
INSERT INTO exhibition_collaborator (exhibition_id, collaborator_id) VALUES (12, 5);
INSERT INTO exhibition_collaborator (exhibition_id, collaborator_id) VALUES (12, 4);