/*INSERT INTO phone(id, area_code, country_code, number)
VALUES (1111, 2222, 3333, 4444);

INSERT INTO role(role_id, role)
VALUES (1, 'ADMIN');
INSERT INTO role(role_id, role)
VALUES (2, 'BUYER');
INSERT INTO role(role_id, role)
VALUES (3, 'SELLER');


INSERT INTO t_user(u_id, email, f_name, l_name, password, username)
VALUES (1, 'aiub@miu.edu', 'Aiub', 'Khan', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',
        'admin'); --123
INSERT INTO t_user(u_id, email, f_name, l_name, password, username)
VALUES (2, 'asma@miu.edu', 'Asma', 'Aiouez', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',
        'buyer'); --123
INSERT INTO t_user(u_id, email, f_name, l_name, password, username)
VALUES (3, 'hassan@miu.edu', 'Hassan', 'Ali', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'seller');
--123


-- INSERT INTO t_user(u_id, email, f_name, l_name, password, username)
-- VALUES (1, 'mak@miu.edu', 'Aiub', 'Khan', '1234', 'admin');
-- INSERT INTO t_user(u_id, email, f_name, l_name, password, username)
-- VALUES (2, 'asma@miu.edu', 'Asma', 'Aiouez', '1234', 'buyer');
-- INSERT INTO t_user(u_id, email, f_name, l_name, password, username)
-- VALUES (3, 'hassan@miu.edu', 'Hassan', 'Ali', '1234', 'seller');

INSERT INTO ADMIN
VALUES (1, 1);
INSERT INTO BUYER(B_ID, POINTS, USER_ID)
values (2, 0, 2);
INSERT INTO SELLER(id, status, user_id)
values (3, 'Approved', 3);

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1);
INSERT INTO user_roles(user_id, role_id)
VALUES (2, 2);
INSERT INTO user_roles(user_id, role_id)
VALUES (3, 3);

INSERT INTO product(id, created_on, description, price, rating, status, title, image, number_in_stock, seller_id)
VALUES (1, '2021-09-23', 'Made from solid Teak wood', 320, '4', 'APPROVED', 'Wooden Bed',
        'https://www.obc-uk.net/static/images/resized/1000px/beds/wood/keats-wood-bed-dark-cherry-icon-linen.jpg',
        50,
        3);

INSERT INTO product(id, created_on, description, price, rating, status, title, image, number_in_stock, seller_id)
VALUES (2, '2021-09-23', 'Pure Aluminium made', 115, '4', 'APPROVED', 'Steel Bed',
        'https://s.alicdn.com/@sc01/kf/Hbe125e52a81a4429a211a747d3479655T/223406935/Hbe125e52a81a4429a211a747d3479655T.jpg?quality=close',
        50,
        3);

INSERT INTO product(id, created_on, description, price, rating, status, title, image, number_in_stock, seller_id)
VALUES (3, '2021-09-23', 'Pure Aluminium rim with Micheline Tyre', 250, '5', 'APPROVED', 'Car Wheels',
        'https://carx-media-prod.s3.amazonaws.com/media/2018/01/tires-landing.png', 50,
        3);

INSERT INTO product(id, created_on, description, price, rating, status, title, image, number_in_stock, seller_id)
VALUES (4, '2021-09-23', 'Durable Steel Frame, Full-Wrap Fenders', 110, '3', 'APPROVED', 'Bicycle',
        'https://cdn.shopify.com/s/files/1/2423/6599/products/Beaumont_2021_MatteOliveDrab_1_720x.jpg', 50,
        3);

INSERT INTO product(id, created_on, description, price, rating, status, title, image, number_in_stock, seller_id)
VALUES (5, '2021-09-23', 'Very good Oak made', 90, '4', 'APPROVED', 'Wooden Table',
        'https://m.media-amazon.com/images/I/61BP9WMEXgL._AC_SX425_.jpg',
        100, 3);

INSERT INTO product(id, created_on, description, price, rating, status, title, image, number_in_stock, seller_id)
VALUES (6, '2021-09-23', 'Made from solid steel and pure leather', 320, '5', 'APPROVED', 'Office Chair',
        'https://secure.img1-fg.wfcdn.com/im/81427268/resize-h350%5Ecompr-r85/8646/86468584/Lollie+Executive+Chair.jpg',
        50,
        3);

*/