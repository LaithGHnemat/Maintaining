insert into  cars_tabel (car_id, car_color, car_model, car_status, convertible, date_created, engine_type, license_plate, manufacturer, rating, seat_count)
VALUES (30, 'Green', 'Accord', 'MAP', true, '2021-09-11 06:10:35.000000', 'GAS', 'JORZ84bu', 'Honda', 4.4, 4);

insert into  cars_tabel (car_id, car_color, car_model, car_status, convertible, date_created, engine_type, license_plate, manufacturer, rating, seat_count)
VALUES (31, 'red', 'LS Hybrid', 'MAP', false, '2021-09-11 06:11:22.000000', 'HYBRID', 'PLO87870', 'Lexus', 3.4, 4);

insert into  cars_tabel (car_id, car_color, car_model, car_status, convertible, date_created, engine_type, license_plate, manufacturer, rating, seat_count)
VALUES (32, 'Orange', 'Accord', 'MAP', true, '2021-09-11 06:12:11.000000', 'ELECTRIC', 'BRZ45045', 'Honda', 3.7, 4);

-- Here (above, look up please ) I created three cars with status MAP  so no driver can chose it
-- because others have done that

insert into  cars_tabel (car_id, car_color, car_model, car_status, convertible, date_created, engine_type, license_plate, manufacturer, rating, seat_count)
VALUES (70, 'Pink', 'Tahoe', 'UNMAP', true, '2021-09-11 06:12:11.000000', 'HYBRID', 'BRZ45045', 'Chevrolet', 4.7, 4);
insert into  cars_tabel (car_id, car_color, car_model, car_status, convertible, date_created, engine_type, license_plate, manufacturer, rating, seat_count)
VALUES (71, 'Yellow', 'Marquis', 'UNMAP', false , '2021-09-11 06:12:11.000000', 'HYBRID', 'DFF45045', 'Mercury', 4.8, 4);
insert into  cars_tabel (car_id, car_color, car_model, car_status, convertible, date_created, engine_type, license_plate, manufacturer, rating, seat_count)
VALUES (72, 'Purple', 'B-Series', 'UNMAP', false , '2021-09-11 06:12:11.000000', 'ELECTRIC', 'BAR45045', 'Mazda', 2.7, 4);


-- Other (one more time take a look :) that is good ) three cars, but now there status is(UNMAP)
--so any(ONLINE) driver can chose just one of them.

------------------------------
--  Drivers Data
------------------------------


INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
 VALUES (40, '2021-09-12 07:23:38.000000', false, 'OFFLINE', '14n78juyt', 'Eman', NULL);

INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
VALUES (41, '2021-09-12 07:23:38.000000', false, 'OFFLINE', 'SDF456GH', 'Dalal', NULL);

INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
VALUES (43, '2021-09-12 07:23:38.000000', false, 'OFFLINE', 'CVB876NB', 'Mohanad', NULL);


-- three drivers(above again) with status (OFFLINE), that mean they are not able to chose a car.




INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
 VALUES (50, '2021-09-12 07:23:38.000000', false, 'ONLINE', 'REAmn987', 'Eman', NULL);

INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
VALUES (51, '2021-09-12 07:23:38.000000', false, 'ONLINE', 'ZXCS56565', 'Dalal', NULL);

INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
VALUES (52, '2021-09-12 07:23:38.000000', false, 'ONLINE', 'JKH87M6NB', 'Mohanad', NULL);


-- those three above have a right to choose any car because there status is (ONLINE) but the car must be (UNMAP)


INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
VALUES (60, '2021-09-12 07:23:38.000000', true, 'ONLINE', 'CXZD6754', 'Laith', 30);

INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
VALUES (61, '2021-09-12 07:23:38.000000', true, 'ONLINE', '09OK76GF', 'Ammar', 31);

INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
VALUES (62, '2021-09-12 07:23:38.000000', true, 'ONLINE', 'SDWX87JE2', 'Yamen', 32);

-- those are the driver who took the cars at the top, and couz of them u can't choose between cars with id(30,31,32)