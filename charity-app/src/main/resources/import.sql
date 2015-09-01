insert into users (name, email, password) VALUES ('admin', 'admin@gmail.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918')
insert into users (name, email, password) VALUES ('volunteer', 'volunteer@gmail.com', 'dcbb0f3cafb30402d5ed4cb826e000bcae930c7ce60763e0458665150dffa879')
insert into users (name, email, password) VALUES ('user', 'user@gmail.com', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb')
insert into user_roles (description, role) VALUES ('admin', 'ADMIN')
insert into user_roles (description, role) VALUES ('volunteer', 'VOLUNTEER')
insert into user_roles (description, role) VALUES ('user', 'USER')
insert into user_authority(user_id, authority) VALUES  (1, 'ROLE_USER')
insert into user_authority(user_id, authority) VALUES  (2, 'ROLE_ADMIN')
insert into user_authority(user_id, authority) VALUES  (2, 'ROLE_USER')
insert into user_authority(user_id, authority) VALUES  (3, 'ROLE_USER')
insert into user_authority(user_id, authority) VALUES  (3, 'ROLE_ADMIN')
insert into user_authority(user_id, authority) VALUES  (3, 'ROLE_VOLUNTEER')


insert into categories (name, parent_id) VALUES ('clothes', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('child''s', @parent1);
    set @parent2 = LAST_INSERT_ID();
        insert into categories (name, parent_id) VALUES ('outerwear', @parent2);
        insert into categories (name, parent_id) VALUES ('sweater', @parent2);
        insert into categories (name, parent_id) VALUES ('trousers', @parent2);
        insert into categories (name, parent_id) VALUES ('t-shirt', @parent2);
        insert into categories (name, parent_id) VALUES ('underwear', @parent2);
        insert into categories (name, parent_id) VALUES ('for babies', @parent2);
        insert into categories (name, parent_id) VALUES ('etc', @parent2);
    insert into categories (name, parent_id) VALUES ('for women', @parent1);
    set @parent2 = LAST_INSERT_ID();
        insert into categories (name, parent_id) VALUES ('outerwear', @parent2);
        insert into categories (name, parent_id) VALUES ('sweater', @parent2);
        insert into categories (name, parent_id) VALUES ('trousers', @parent2);
        insert into categories (name, parent_id) VALUES ('t-shirt', @parent2);
        insert into categories (name, parent_id) VALUES ('skirts', @parent2);
        insert into categories (name, parent_id) VALUES ('dress', @parent2);
        insert into categories (name, parent_id) VALUES ('underwear', @parent2);
        insert into categories (name, parent_id) VALUES ('for pregnant', @parent2);
        insert into categories (name, parent_id) VALUES ('etc', @parent2);
    insert into categories (name, parent_id) VALUES ('for men', @parent1);
    set @parent2 = LAST_INSERT_ID();
        insert into categories (name, parent_id) VALUES ('outerwear', @parent2);
        insert into categories (name, parent_id) VALUES ('sweater', @parent2);
        insert into categories (name, parent_id) VALUES ('trousers', @parent2);
        insert into categories (name, parent_id) VALUES ('t-shirt', @parent2);
        insert into categories (name, parent_id) VALUES ('underwear', @parent2);
        insert into categories (name, parent_id) VALUES ('etc', @parent2);
insert into categories (name, parent_id) VALUES ('footwear', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('child''s', @parent1);
    set @parent2 = LAST_INSERT_ID();
        insert into categories (name, parent_id) VALUES ('summer', @parent2);
        insert into categories (name, parent_id) VALUES ('winter', @parent2);
        insert into categories (name, parent_id) VALUES ('shouldered', @parent2);
        insert into categories (name, parent_id) VALUES ('etc', @parent2);
    insert into categories (name, parent_id) VALUES ('for women', @parent1);
    set @parent2 = LAST_INSERT_ID();
        insert into categories (name, parent_id) VALUES ('summer', @parent2);
        insert into categories (name, parent_id) VALUES ('winter', @parent2);
        insert into categories (name, parent_id) VALUES ('shouldered', @parent2);
        insert into categories (name, parent_id) VALUES ('etc', @parent2);
    insert into categories (name, parent_id) VALUES ('for men', @parent1);
    set @parent2 = LAST_INSERT_ID();
        insert into categories (name, parent_id) VALUES ('summer', @parent2);
        insert into categories (name, parent_id) VALUES ('winter', @parent2);
        insert into categories (name, parent_id) VALUES ('shouldered', @parent2);
        insert into categories (name, parent_id) VALUES ('etc', @parent2);

insert into categories (name, parent_id) VALUES ('stationery', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('pen', @parent1);
    insert into categories (name, parent_id) VALUES ('pencil', @parent1);
    insert into categories (name, parent_id) VALUES ('notebook', @parent1);
    insert into categories (name, parent_id) VALUES ('felt pen', @parent1);
    insert into categories (name, parent_id) VALUES ('album', @parent1);
    insert into categories (name, parent_id) VALUES ('paint', @parent1);
    insert into categories (name, parent_id) VALUES ('paper', @parent1);
    insert into categories (name, parent_id) VALUES ('glue', @parent1);
    insert into categories (name, parent_id) VALUES ('plasticine', @parent1);
    insert into categories (name, parent_id) VALUES ('scissors', @parent1);
    insert into categories (name, parent_id) VALUES ('tutorials', @parent1);
    insert into categories (name, parent_id) VALUES ('backpack', @parent1);
    insert into categories (name, parent_id) VALUES ('etc', @parent1);

insert into categories (name, parent_id) VALUES ('technique', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('PC', @parent1);
    insert into categories (name, parent_id) VALUES ('mobile phone', @parent1);
    insert into categories (name, parent_id) VALUES ('washing machine', @parent1);
    insert into categories (name, parent_id) VALUES ('fridge', @parent1);
    insert into categories (name, parent_id) VALUES ('microwave', @parent1);
    insert into categories (name, parent_id) VALUES ('etc', @parent1);
insert into categories (name, parent_id) VALUES ('household goods', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('hygiene', @parent1);
    insert into categories (name, parent_id) VALUES ('hygiene for children', @parent1);
    insert into categories (name, parent_id) VALUES ('furniture', @parent1);
    insert into categories (name, parent_id) VALUES ('medecines', @parent1);
    insert into categories (name, parent_id) VALUES ('pram', @parent1);
    insert into categories (name, parent_id) VALUES ('etc', @parent1);
insert into categories (name, parent_id) VALUES ('toys', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('games', @parent1);
    insert into categories (name, parent_id) VALUES ('soft toys', @parent1);
    insert into categories (name, parent_id) VALUES ('cars', @parent1);
    insert into categories (name, parent_id) VALUES ('dolls', @parent1);
    insert into categories (name, parent_id) VALUES ('inventory', @parent1);
    insert into categories (name, parent_id) VALUES ('constructor', @parent1);
    insert into categories (name, parent_id) VALUES ('bicycle', @parent1);
    insert into categories (name, parent_id) VALUES ('for newborns', @parent1);
    insert into categories (name, parent_id) VALUES ('etc', @parent1);
insert into categories (name, parent_id) VALUES ('food', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('vegetables', @parent1);
    insert into categories (name, parent_id) VALUES ('fruits', @parent1);
    insert into categories (name, parent_id) VALUES ('sweets', @parent1);
    insert into categories (name, parent_id) VALUES ('for newborns', @parent1);
    insert into categories (name, parent_id) VALUES ('etc', @parent1);
insert into categories (name, parent_id) VALUES ('crafts', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('knitting', @parent1);
    insert into categories (name, parent_id) VALUES ('painting', @parent1);
    insert into categories (name, parent_id) VALUES ('embroidery', @parent1);
    insert into categories (name, parent_id) VALUES ('woodwork', @parent1);
    insert into categories (name, parent_id) VALUES ('applique', @parent1);
    insert into categories (name, parent_id) VALUES ('etc', @parent1);
insert into categories (name, parent_id) VALUES ('ATO', null);
set @parent1 = LAST_INSERT_ID();
    insert into categories (name, parent_id) VALUES ('medecines', @parent1);
    insert into categories (name, parent_id) VALUES ('military technique', @parent1);
    insert into categories (name, parent_id) VALUES ('equipment', @parent1);
    insert into categories (name, parent_id) VALUES ('coveralls', @parent1);
    insert into categories (name, parent_id) VALUES ('etc', @parent1);
insert into categories (name, parent_id) VALUES ('etc', null);



INSERT INTO regions (name) VALUES ( 'AR Krym');
INSERT INTO regions (name) VALUES ( 'Vinnickaja oblast');
INSERT INTO regions (name) VALUES ( 'Volynskaja oblast');
INSERT INTO regions (name) VALUES ( 'Dnepropetrovskaja oblast');
INSERT INTO regions (name) VALUES ( 'Doneckaja oblast');
INSERT INTO regions (name) VALUES ( 'Zhitomirskaja oblast');
INSERT INTO regions (name) VALUES ( 'Zakarpatskaja oblast');
INSERT INTO regions (name) VALUES ( 'Zaporozhskaja oblast');
INSERT INTO regions (name) VALUES ( 'Ivano-Frankovskaja oblast');
INSERT INTO regions (name) VALUES ( 'Kievskaja oblast');
INSERT INTO regions (name) VALUES ( 'Kirovogradskaja oblast');
INSERT INTO regions (name) VALUES ( 'Luganskaja oblast');
INSERT INTO regions (name) VALUES ( 'L''vovskaja oblast');
INSERT INTO regions (name) VALUES ( 'Nikolaevskaja oblast');
INSERT INTO regions (name) VALUES ( 'Odesskaja oblast');
INSERT INTO regions (name) VALUES ( 'Poltavskaja oblast');
INSERT INTO regions (name) VALUES ( 'Rovenskaja oblast');
INSERT INTO regions (name) VALUES ( 'Sumskaja oblast');
INSERT INTO regions (name) VALUES ( 'Ternopol''skaja oblast');
INSERT INTO regions (name) VALUES ( 'Har''kovskaja oblast');
INSERT INTO regions (name) VALUES ( 'Hersonskaja oblast');
INSERT INTO regions (name) VALUES ( 'Hmel''nickaja oblast');
INSERT INTO regions (name) VALUES ( 'Cherkasskaja oblast');
INSERT INTO regions (name) VALUES ( 'Chernigovskaja oblast');
INSERT INTO regions (name) VALUES ( 'Chernovickaja oblast');
