INSERT INTO customer (id, first_name, middle_name, last_name, username, status, email, cellphone, date_of_birth)
VALUES
('a03f5242-89e7-4b5d-9a2a-789012345678', 'John', 'M', 'Doe', 'john_doe', 'Active', 'john.doe@example.com', '123-456-7890', '1990-01-15'),
('b1a2b0f6-56c9-4a81-9b4f-123456789012', 'Jane', 'A', 'Smith', 'jane_smith', 'Active', 'jane.smith@example.com', '987-654-3210', '1985-07-22'),
('c90123d4-5678-49ab-8cde-012345678901', 'Bob', null, 'Johnson', 'bob_j', 'Inactive', 'bob.johnson@example.com', '555-123-4567', '1978-03-10');


INSERT INTO payment_method (id, customer_id, type, status, is_default, info)
VALUES
('eeaa527e-5a1f-40a9-8b3a-012345678901', 'a03f5242-89e7-4b5d-9a2a-789012345678', 'CREDIT_CARD', 'ACTIVE', true, 'Credit Card ending in 1234'),
('f9e03517-6c3d-4a92-80ff-123456789012', 'b1a2b0f6-56c9-4a81-9b4f-123456789012', 'CASH', 'ACTIVE', false, 'PayPal account: jane.smith@example.com'),
('1d073481-2e12-4c0f-a4fb-012345678901', 'c90123d4-5678-49ab-8cde-012345678901', 'BANK_TRANSFER', 'EXPIRE', false, 'Debit Card ending in 5678');

INSERT INTO room_info (code, max_capacity, description, is_locked)
VALUES
('A101', 2, 'Standard Room', false),
('A102', 1, 'Single Room', false),
('A103', 1, 'Single Room', false),
('A104', 1, 'Single Room', false),
('A105', 1, 'Single Room', false),
('B202', 3, 'Deluxe Suite', false),
('B203', 1, 'Single Room', false),
('B204', 1, 'Single Room', false),
('B205', 1, 'Single Room', false),
('C303', 1, 'Single Room', false),
('C304', 1, 'Deluxe Suite', false),
('C305', 1, 'Deluxe Suite', false),
('C306', 1, 'Deluxe Suite', false);


INSERT INTO reservation_order (id, customer_id, ref_no, status, check_in_date, check_in_time, check_out_date, check_out_time, payment_method_id, room_code, guest_count, payment_status)
VALUES
('89425a8b-2b31-4a9b-9f47-012345678902', 'a03f5242-89e7-4b5d-9a2a-789012345678', 'NW231207212602001', 'INITIAL', '2023-08-10', '12:30', '2023-08-15', '10:00','eeaa527e-5a1f-40a9-8b3a-012345678901', 'A102', 3, 'PAID'),

('f8f5cf20-0e78-4bb3-b42e-123456789013', 'b1a2b0f6-56c9-4a81-9b4f-123456789012', 'NW231207212602002', 'PENDING_PAYMENT', '2023-09-05', '15:45', '2023-09-10', '09:30', 'f9e03517-6c3d-4a92-80ff-123456789012', 'B203', 2, 'UNPAID'),

('4d7fbeca-baa6-40a0-933b-012345678904', 'c90123d4-5678-49ab-8cde-012345678901', 'NW231207212602003', 'CANCELLED', '2023-10-15', '14:15', '2023-10-20', '11:00', '1d073481-2e12-4c0f-a4fb-012345678901', 'C304', 1, 'PAID'),

('5e601dab-f0b1-4e09-8155-012345678905', 'a03f5242-89e7-4b5d-9a2a-789012345678', 'NW231207212602004', 'COMPLETE', '2023-11-20', '10:00', '2023-11-25', '13:30', 'eeaa527e-5a1f-40a9-8b3a-012345678901', 'A103', 2, 'PAID'),

('6f4bc1c1-e17c-4e49-9e22-012345678906', 'b1a2b0f6-56c9-4a81-9b4f-123456789012', 'NW231207212603001', 'COMPLETE', '2023-12-05', '12:45', '2023-12-10', '08:00', 'f9e03517-6c3d-4a92-80ff-123456789012', 'B204', 3, 'UNPAID'),

('7bb490db-4e4e-4aa2-9e34-012345678907', 'c90123d4-5678-49ab-8cde-012345678901', 'NW231207212603002', 'PAYMENT_FAILED', '2024-01-10', '18:00', '2024-01-15', '15:15', '1d073481-2e12-4c0f-a4fb-012345678901', 'C305', 1, 'PAID'),

('8cd599d3-d196-4ec1-99b4-012345678908', 'a03f5242-89e7-4b5d-9a2a-789012345678', 'NW231207212603003', 'UPCOMING', '2024-02-15', '14:30', '2024-02-20', '11:45', 'eeaa527e-5a1f-40a9-8b3a-012345678901', 'A104', 2, 'PAID'),

('9de6ef69-4e71-4d8a-8013-012345678909', 'b1a2b0f6-56c9-4a81-9b4f-123456789012', 'NW231207212603004', 'UPCOMING', '2024-03-01', '11:15', '2024-03-06', '09:30', 'f9e03517-6c3d-4a92-80ff-123456789012', 'B205', 3, 'UNPAID'),

('aef79e6d-76f9-4e0c-bb94-012345678910', 'c90123d4-5678-49ab-8cde-012345678901', 'NW231207212603005', 'CANCELLED', '2024-04-05', '13:00', '2024-04-10', '10:15', '1d073481-2e12-4c0f-a4fb-012345678901', 'C306', 1, 'PAID'),

('bf61c2bc-e5a9-43fe-8b8a-012345678911', 'a03f5242-89e7-4b5d-9a2a-789012345678', 'NW231207212603006', 'CANCELLED', '2024-05-10', '09:45', '2024-05-15', '14:00', 'eeaa527e-5a1f-40a9-8b3a-012345678901', 'A105', 2, 'PAID');
