INSERT INTO permission_group (name) values ('ADMIN');

INSERT INTO permission ( user_email, permission_level, group_id)
values ('admin@mail.com', 'EDIT', (SELECT id from permission_group where name = 'ADMIN'));


INSERT INTO permission ( user_email, permission_level, group_id)
values ('user@mail.com', 'VIEW', (SELECT id from permission_group where name = 'ADMIN'));
