/* city */
INSERT INTO CITY
VALUES(NEXT VALUE FOR city_id_seq, 'Wroclaw');

INSERT INTO CITY
VALUES (NEXT VALUE FOR city_id_seq, 'Warszawa');

INSERT INTO CITY
VALUES (NEXT VALUE FOR city_id_seq, 'Krakow');

/* user */

INSERT INTO "USER"
VALUES (NEXT VALUE FOR users_id_seq, 'login1', 'password1', 100, 'contact1');

INSERT INTO "USER"
VALUES (NEXT VALUE FOR users_id_seq, 'login2', 'password2', 101, 'contact2');

INSERT INTO "USER"
VALUES (NEXT VALUE FOR users_id_seq, 'login3', 'password3', 101, 'contact3');

/* fridge */

INSERT INTO fridge
VALUES (NEXT VALUE FOR fridge_id_seq, 1);

INSERT INTO fridge
VALUES (NEXT VALUE FOR fridge_id_seq, 2);

INSERT INTO fridge
VALUES (NEXT VALUE FOR fridge_id_seq, 3);

/* product */

INSERT INTO product
VALUES (NEXT VALUE FOR products_id_seq, 'jablko', '2022-07-07');

INSERT INTO product
VALUES (NEXT VALUE FOR products_id_seq, 'banan', '2022-07-07');

INSERT INTO product
VALUES (NEXT VALUE FOR products_id_seq, 'malina', '2022-07-07');

INSERT INTO product
VALUES (NEXT VALUE FOR products_id_seq, 'jablko', '2022-07-07');

INSERT INTO product
VALUES (NEXT VALUE FOR products_id_seq, 'banan', '2022-07-07');

INSERT INTO product
VALUES (NEXT VALUE FOR products_id_seq, 'malina', '2022-07-07');

/* fridge_item */

INSERT INTO fridge_item
VALUES (NEXT VALUE FOR fridge_item_id_seq, 1, 1, 1);

INSERT INTO fridge_item
VALUES (NEXT VALUE FOR fridge_item_id_seq, 2, 2, 2);

INSERT INTO fridge_item
VALUES (NEXT VALUE FOR fridge_item_id_seq, 3, 3, 2);

/* offer */

INSERT INTO offer
VALUES (NEXT VALUE FOR offers_id_seq, 3, '2022-06-07', 1, 'description 1', 'availability 1', 1, 4);

INSERT INTO offer
VALUES (NEXT VALUE FOR offers_id_seq, 3, '2022-06-07', 1, 'description 2', 'availability 2', 2, 5);

INSERT INTO offer
VALUES (NEXT VALUE FOR offers_id_seq, 3, '2022-06-07', 2, 'description 3', 'availability 3', 3, 6);

/* transaction */
INSERT INTO "TRANSACTION"
VALUES (NEXT VALUE FOR transaction_id_seq, 1, 2, '2022-06-07');

INSERT INTO "TRANSACTION"
VALUES (NEXT VALUE FOR transaction_id_seq, 2, 3, '2022-06-07');

INSERT INTO "TRANSACTION"
VALUES (NEXT VALUE FOR transaction_id_seq, 3, 1, '2022-06-07');