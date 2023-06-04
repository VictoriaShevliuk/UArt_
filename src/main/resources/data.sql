INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (5, 'Nick', 'Green', 'nick@mail.com', '$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC', 1);
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (6, 'Nora', 'White', 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', 1);
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (4, 'Taras', 'Shevchenko', 'mike@mail.com', '$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN.Y4MTMoY9pjfPALO', 1);


INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (7, 'Taras''s Exhibition #1', '2020-09-16 14:00:04.810221', 4);
INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (8, 'Mike''s Exhibition #2', '2020-09-16 14:00:11.480271', 4);
INSERT INTO exhibitions (id, title, created_at, owner_id) VALUES (9, 'Mike''s Exhibition #3', '2020-09-16 14:00:16.351238', 4);


INSERT INTO pieces (id, name, genre, image_url, exhibition_id) VALUES (35, 'Piece', 'REALISM', 'https://i.pinimg.com/236x/06/b0/44/06b044e22611f5359383e0691a09a331.jpg', 7);
INSERT INTO pieces (id, name, genre, image_url, exhibition_id) VALUES (36, 'Piece', 'REALISM', 'https://i.pinimg.com/236x/ab/d8/4f/abd84f4a5a7c481b3bf21a1a4cc3b51f.jpg', 7);
INSERT INTO pieces (id, name, genre, image_url, exhibition_id) VALUES (37, 'Piece', 'REALISM', 'https://i.pinimg.com/564x/7f/35/06/7f350607fa4d23e7e753a5f741805a8c.jpg', 7);
INSERT INTO pieces (id, name, genre, image_url, exhibition_id) VALUES (38, 'Piece', 'REALISM', 'https://i.pinimg.com/236x/25/9e/b4/259eb439f93f7e2a9a69fdf9c1dbdf3d.jpg', 7);
INSERT INTO pieces (id, name, genre, image_url, exhibition_id) VALUES (39, 'Piece', 'REALISM', 'https://i.pinimg.com/236x/f8/5a/76/f85a7657e7389c40c7ba23f530c59661.jpg', 7);
