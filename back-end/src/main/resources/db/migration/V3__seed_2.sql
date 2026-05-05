INSERT INTO students (name, registration_number, fingerprint_id)
VALUES
    ('João Silva', '20260001', 1),
    ('Maria Oliveira', '20260002', 2),
    ('Pedro Santos', '20260003', 3);


INSERT INTO classes (name, professor_id)
VALUES
    (
        'Engenharia de Software',
        (SELECT id FROM users WHERE email = 'professor@smartfrequency.com')
    );


INSERT INTO enrollments (student_id, class_id)
VALUES
    (
        (SELECT id FROM students WHERE registration_number = '20260001'),
        (SELECT id FROM classes WHERE name = 'Engenharia de Software')
    ),
    (
        (SELECT id FROM students WHERE registration_number = '20260002'),
        (SELECT id FROM classes WHERE name = 'Engenharia de Software')
    );

