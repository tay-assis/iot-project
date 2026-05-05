-- 1. insere no users
INSERT INTO users (id, email, password, role)
VALUES (1, 'admin@smartfrequency.com', '$2a$10$Fk.NnJr5V0DVV3HOYpxMDu9EzTWxyaQCfPtvlJ56I8cjdzHrA66dm', 'ADMIN');

-- 2. referencia no admins
INSERT INTO admins (id)
VALUES (1);

-- 1. insere no users
INSERT INTO users (id, email, password, role)
VALUES (2, 'professor@smartfrequency.com', '$2a$10$AtOf8RVJqoY9a7wiefDofeZ024IiSoSZR0MQjY70VkO/Ctc5EKbyS', 'PROFESSOR');

-- 2. referencia no professors
INSERT INTO professors (id, name)
VALUES (2, 'Dr. Luis Vitor');