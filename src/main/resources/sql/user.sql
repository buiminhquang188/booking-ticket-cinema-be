SET NAMES utf8mb4;

INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (1, '2024-01-01 10:00:00', '2024-01-01 10:00:00', true, true, '1234567890', 'admin', 'john.doe1@example.com',
        'John', 'Doe', 'John Doe', 'https://avatar.iran.liara.run/public/1',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (2, '2024-01-02 11:00:00', '2024-01-02 11:00:00', false, true, '0987654321', 'user', 'jane.smith2@example.com',
        'Jane', 'Smith', 'Jane Smith', 'https://avatar.iran.liara.run/public/2',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (3, '2024-01-03 12:00:00', '2024-01-03 12:00:00', true, false, '1111111111', 'super_admin',
        'alice.brown3@example.com', 'Alice', 'Brown', 'Alice Brown', 'https://avatar.iran.liara.run/public/3',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (4, '2024-01-04 13:00:00', '2024-01-04 13:00:00', false, false, '2222222222', 'user', 'bob.jones4@example.com',
        'Bob', 'Jones', 'Bob Jones', 'https://avatar.iran.liara.run/public/4',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (5, '2024-01-05 14:00:00', '2024-01-05 14:00:00', true, true, '3333333333', 'super_admin',
        'carol.miller5@example.com', 'Carol', 'Miller', 'Carol Miller', 'https://avatar.iran.liara.run/public/5',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (6, '2024-01-06 15:00:00', '2024-01-06 15:00:00', false, true, '4444444444', 'user', 'david.wilson6@example.com',
        'David', 'Wilson', 'David Wilson', 'https://avatar.iran.liara.run/public/6',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (7, '2024-01-07 16:00:00', '2024-01-07 16:00:00', true, false, '5555555555', 'admin', 'emma.moore7@example.com',
        'Emma', 'Moore', 'Emma Moore', 'https://avatar.iran.liara.run/public/7',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (8, '2024-01-08 17:00:00', '2024-01-08 17:00:00', false, false, '6666666666', 'user',
        'frank.taylor8@example.com', 'Frank', 'Taylor', 'Frank Taylor', 'https://avatar.iran.liara.run/public/8',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (9, '2024-01-09 18:00:00', '2024-01-09 18:00:00', true, true, '7777777777', 'admin',
        'grace.anderson9@example.com', 'Grace', 'Anderson', 'Grace Anderson', 'https://avatar.iran.liara.run/public/9',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (10, '2024-01-10 19:00:00', '2024-01-10 19:00:00', false, true, '8888888888', 'user',
        'henry.thomas10@example.com', 'Henry', 'Thomas', 'Henry Thomas', 'https://avatar.iran.liara.run/public/10',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (11, '2024-01-11 20:00:00', '2024-01-11 20:00:00', true, false, '9999999999', 'super_admin',
        'isabella.jackson11@example.com', 'Isabella', 'Jackson', 'Isabella Jackson',
        'https://avatar.iran.liara.run/public/11', '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (12, '2024-01-12 21:00:00', '2024-01-12 21:00:00', false, false, '1010101010', 'user',
        'jack.white12@example.com', 'Jack', 'White', 'Jack White', 'https://avatar.iran.liara.run/public/12',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (13, '2024-01-13 22:00:00', '2024-01-13 22:00:00', true, true, '2020202020', 'admin',
        'kate.harris13@example.com', 'Kate', 'Harris', 'Kate Harris', 'https://avatar.iran.liara.run/public/13',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (14, '2024-01-14 23:00:00', '2024-01-14 23:00:00', false, true, '3030303030', 'user', 'leo.martin14@example.com',
        'Leo', 'Martin', 'Leo Martin', 'https://avatar.iran.liara.run/public/14',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (15, '2024-01-15 09:00:00', '2024-01-15 09:00:00', true, false, '4040404040', 'admin', 'mia.lee15@example.com',
        'Mia', 'Lee', 'Mia Lee', 'https://avatar.iran.liara.run/public/15',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (16, '2024-01-16 10:00:00', '2024-01-16 10:00:00', false, false, '5050505050', 'user',
        'noah.wright16@example.com', 'Noah', 'Wright', 'Noah Wright', 'https://avatar.iran.liara.run/public/16',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (17, '2024-01-17 11:00:00', '2024-01-17 11:00:00', true, true, '6060606060', 'user', 'olivia.king17@example.com',
        'Olivia', 'King', 'Olivia King', 'https://avatar.iran.liara.run/public/17',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (18, '2024-01-18 12:00:00', '2024-01-18 12:00:00', false, true, '7070707070', 'user', 'peter.hill18@example.com',
        'Peter', 'Hill', 'Peter Hill', 'https://avatar.iran.liara.run/public/18',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (19, '2024-01-19 13:00:00', '2024-01-19 13:00:00', true, false, '8080808080', 'user',
        'quinn.scott19@example.com', 'Quinn', 'Scott', 'Quinn Scott', 'https://avatar.iran.liara.run/public/19',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (20, '2024-01-20 14:00:00', '2024-01-20 14:00:00', false, false, '9090909090', 'user',
        'ryan.green20@example.com', 'Ryan', 'Green', 'Ryan Green', 'https://avatar.iran.liara.run/public/20',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (21, '2024-01-21 15:00:00', '2024-01-21 15:00:00', true, true, '1122334455', 'admin',
        'sophia.adams21@example.com', 'Sophia', 'Adams', 'Sophia Adams', 'https://avatar.iran.liara.run/public/21',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (22, '2024-01-22 16:00:00', '2024-01-22 16:00:00', false, true, '2233445566', 'user',
        'thomas.baker22@example.com', 'Thomas', 'Baker', 'Thomas Baker', 'https://avatar.iran.liara.run/public/22',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (23, '2024-01-23 17:00:00', '2024-01-23 17:00:00', true, false, '3344556677', 'user',
        'ursula.clark23@example.com', 'Ursula', 'Clark', 'Ursula Clark', 'https://avatar.iran.liara.run/public/23',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (24, '2024-01-24 18:00:00', '2024-01-24 18:00:00', false, false, '4455667788', 'user',
        'victor.roberts24@example.com', 'Victor', 'Roberts', 'Victor Roberts',
        'https://avatar.iran.liara.run/public/24', '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (25, '2024-01-25 19:00:00', '2024-01-25 19:00:00', true, true, '5566778899', 'user',
        'william.walker25@example.com', 'William', 'Walker', 'William Walker',
        'https://avatar.iran.liara.run/public/25', '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (26, '2024-01-26 20:00:00', '2024-01-26 20:00:00', false, true, '6677889900', 'user',
        'xavier.young26@example.com', 'Xavier', 'Young', 'Xavier Young', 'https://avatar.iran.liara.run/public/26',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (27, '2024-01-27 21:00:00', '2024-01-27 21:00:00', true, false, '7788990011', 'user', 'yara.allen27@example.com',
        'Yara', 'Allen', 'Yara Allen', 'https://avatar.iran.liara.run/public/27',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
INSERT INTO capstone.user (id, created_at, updated_at, is_email_verified, is_phone_verified, phone, role, email,
                           first_name, last_name, full_name, avatar, password)
VALUES (28, '2024-01-28 22:00:00', '2024-01-28 22:00:00', false, false, '8899001122', 'user',
        'zachary.james28@example.com', 'Zachary', 'James', 'Zachary James', 'https://avatar.iran.liara.run/public/28',
        '$2a$10$bNzKcp68FveCVQJavjuXCu7pnEs6yG5SocTR1pV1OE4cZM30RwkQS');
