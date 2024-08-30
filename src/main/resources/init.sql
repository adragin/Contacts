CREATE TABLE IF NOT EXISTS contacts (
    id  int PRIMARY KEY auto_increment not null,
    created_at timestamp default current_timestamp not null,
    name  VARCHAR(256) not null,
    email VARCHAR(256) not null
)

INSERT INTO contacts(id, name, email) VALUES
(1, 'Katya', 'katya@mail.com'),
(2, 'Lena', 'lena@mail.com'),
(3, 'John', 'john@mail.com'),
(4, 'Sally', 'sally@mail.com'),
(5, 'Maria', 'maria@mail.com')