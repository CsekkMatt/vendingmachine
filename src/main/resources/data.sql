insert INTO Roles (id,name) VALUES (1,'ROLE_ADMIN');
insert INTO Roles (id,name) VALUES (2,'ROLE_BUYER');
insert INTO Roles (id,name) VALUES (3,'ROLE_SELLER');


insert INTO Users (user_pid,email,name,deposit,password,username) VALUES (10,'admin@gmail.com','admin',0,'$2a$10$2.jtPn88Wv9TGdb0jeTJ0OfKIAepXl8wJZytrUa8BucTVtTdFJTAi','admin');
insert INTO Users (user_pid,email,name,deposit,password,username) VALUES (11,'buyer@gmail.com','buyer',0,'$2a$10$2.jtPn88Wv9TGdb0jeTJ0OfKIAepXl8wJZytrUa8BucTVtTdFJTAi','buyer');
insert INTO Users (user_pid,email,name,deposit,password,username) VALUES (12,'seller@gmail.com','seller',0,'$2a$10$2.jtPn88Wv9TGdb0jeTJ0OfKIAepXl8wJZytrUa8BucTVtTdFJTAi','seller');

insert INTO user_roles (user_id,role_id) VALUES (10,1);
insert INTO user_roles (user_id,role_id) VALUES (11,2);
insert INTO user_roles (user_id,role_id) VALUES (12,3);


insert INTO products (id,amountavailable,cost,productname,sellerid) VALUES (100,5,15,'Kukorica',12);


insert INTO deposit (id,user_pid,fivecent,tencent,twentycent,fiftycent,hundredcent) VALUES (100,11,0,0,0,0,10);