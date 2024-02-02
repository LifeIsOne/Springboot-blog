INSERT INTO user_tb(username, password, email, created_at) VALUES('Kenneth', '1234', 'Minsu@Kim', now());
INSERT INTO user_tb(username, password, email, created_at) VALUES('Denny', '1234', 'Youngju@Lee', now());

INSERT INTO board_tb(title, content, user_id, created_at) VALUES ('제목1', '내용1', 1, now());
INSERT INTO board_tb(title, content, user_id, created_at) VALUES ('제목2', '내용2', 1, now());
INSERT INTO board_tb(title, content, user_id, created_at) VALUES ('제목3', '내용3', 1, now());
INSERT INTO board_tb(title, content, user_id, created_at) VALUES ('제목4', '내용4', 2, now());