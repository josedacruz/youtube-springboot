INSERT INTO users (username, password, name, email, department) VALUES
    ('alice', 'hashed_pass1', 'Alice Johnson', 'alice@example.com', 'Finance'),
    ('bob', 'hashed_pass2', 'Bob Smith', 'bob@example.com', 'IT');

INSERT INTO entities (name) VALUES
    ('SuperMart'),
    ('Acme Bank'),
    ('Globex Corp'),
    ('Tech Solutions');


INSERT INTO categories (name, type) VALUES
    ('Groceries', 'EXPENSE'),
    ('Salary', 'INCOME'),
    ('Rent', 'EXPENSE'),
    ('Freelance', 'INCOME'),
    ('Utilities', 'EXPENSE');

-- Groceries (Category ID: 1)
INSERT INTO transactions (user_id, entity_id, category_id, date, amount, description, type, payment_method) VALUES
    (1, 1, 1, '2025-05-01', -120.50, 'Groceries - Week 1', 'EXPENSE', 'Card'),
    (1, 1, 1, '2025-05-08', -89.30, 'Groceries - Week 2', 'EXPENSE', 'Card'),
    (2, 1, 1, '2025-05-03', -100.00, 'Groceries - Week 1', 'EXPENSE', 'Card'),
    (2, 1, 1, '2025-05-10', -95.00, 'Groceries - Week 2', 'EXPENSE', 'Card'),
    (1, 2, 2, '2025-05-05', 3000.00, 'Salary - May', 'INCOME', 'Transfer'),
    (2, 2, 2, '2025-05-05', 3100.00, 'Salary - May', 'INCOME', 'Transfer'),
    (1, 2, 2, '2025-06-05', 3000.00, 'Salary - June', 'INCOME', 'Transfer'),
    (2, 2, 2, '2025-06-05', 3100.00, 'Salary - June', 'INCOME', 'Transfer'),
    (1, 3, 3, '2025-05-01', -900.00, 'Rent - May', 'EXPENSE', 'Transfer'),
    (2, 3, 3, '2025-05-01', -950.00, 'Rent - May', 'EXPENSE', 'Transfer'),
    (1, 3, 3, '2025-06-01', -900.00, 'Rent - June', 'EXPENSE', 'Transfer'),
    (2, 3, 3, '2025-06-01', -950.00, 'Rent - June', 'EXPENSE', 'Transfer'),
    (1, 1, 4, '2025-05-12', 550.00, 'Freelance Design Project', 'INCOME', 'PayPal'),
    (2, 2, 4, '2025-05-15', 700.00, 'Freelance Web Dev', 'INCOME', 'PayPal'),
    (1, 3, 4, '2025-06-10', 500.00, 'Consulting gig', 'INCOME', 'Transfer'),
    (1, 1, 5, '2025-05-06', -75.00, 'Electricity bill', 'EXPENSE', 'Card'),
    (2, 1, 5, '2025-05-06', -80.00, 'Electricity bill', 'EXPENSE', 'Card'),
    (1, 1, 5, '2025-06-06', -70.00, 'Electricity bill', 'EXPENSE', 'Card'),
    (2, 1, 5, '2025-06-06', -85.00, 'Electricity bill', 'EXPENSE', 'Card');
