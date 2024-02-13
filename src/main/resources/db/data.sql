INSERT INTO user_tb(username, password, email, created_at) VALUES('Kenneth', '$2a$10$8xSPX/EeYW8JFrj1HRUrqeZQv9zRaRAAkx7o2XjES8chixOfS1hAC', 'Minsu@Kim', now());
INSERT INTO user_tb(username, password, email, created_at) VALUES('Denny', '$2a$10$8xSPX/EeYW8JFrj1HRUrqeZQv9zRaRAAkx7o2XjES8chixOfS1hAC', 'Yongju@Lee', now());

insert into board_tb(title, content, user_id, created_at) values('제목1', '내용1', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목2', '내용2', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목3', '내용3', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목4', '내용4', 2, now());