--liquibase formatted SQL
--changeset viktor:3
--comment:Вставка данных в таблицу client_account
INSERT INTO client_account(full_name, gender, status, create_dttm, modify_dttm)
VALUES ('Andrey Grakov', 'M', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Viktor Grakov', 'M', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Alex Grakov', 'M', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
--rollback DELETE FROM client_account WHERE full_name = 'Andrey Grakov'