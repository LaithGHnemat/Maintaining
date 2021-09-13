insert into cars_tabel (car_color, car_model, car_status, convertible, date_created, engine_type, license_plate,
                        manufacturer, rating, seat_count)
VALUES ('Green', 'Accord', 'MAP', true, '2021-09-11 06:10:35.000000', 'GAS', 'JORZ84bu', 'Honda', 4.4, 4);

insert into cars_tabel (car_id, car_color, car_model, car_status, convertible, date_created, engine_type, license_plate,
                        manufacturer, rating, seat_count)
VALUES ('red', 'LS Hybrid', 'MAP', false, '2021-09-11 06:11:22.000000', 'HYBRID', 'PLO87870', 'Lexus', 3.4, 4);

insert into cars_tabel (car_id, car_color, car_model, car_status, convertible, date_created, engine_type, license_plate,
                        manufacturer, rating, seat_count)
VALUES ('Orange', 'Accord', 'MAP', true, '2021-09-11 06:12:11.000000', 'ELECTRIC', 'BRZ45045', 'Honda', 3.7, 4);

-- Here (above, look up please ) I created three cars with status MAP  so no driver can chose it
-- because others have done that

insert into cars_tabel (car_color, car_model, car_status, convertible, date_created, engine_type, license_plate,
                        manufacturer, rating, seat_count)
VALUES ('Pink', 'Tahoe', 'UNMAP', true, '2021-09-11 06:12:11.000000', 'HYBRID', 'BRZ45045', 'Chevrolet', 4.7, 4);
insert into cars_tabel (car_color, car_model, car_status, convertible, date_created, engine_type, license_plate,
                        manufacturer, rating, seat_count)
VALUES ('Yellow', 'Marquis', 'UNMAP', false, '2021-09-11 06:12:11.000000', 'HYBRID', 'DFF45045', 'Mercury', 4.8, 4);
insert into cars_tabel (car_color, car_model, car_status, convertible, date_created, engine_type, license_plate,
                        manufacturer, rating, seat_count)
VALUES ('Purple', 'B-Series', 'UNMAP', false, '2021-09-11 06:12:11.000000', 'ELECTRIC', 'BAR45045', 'Mazda', 2.7, 4);


-- Other (one more time take a look :) that is good ) three cars, but now there status is(UNMAP)
--so any(ONLINE) driver can chose just one of them.

------------------------------
--  Drivers Data
------------------------------

--driver_id, date_created they are auto generated so no need to add them here
INSERT INTO driver_tabel (has_car_now, online_status, password, username, car_id)
VALUES (false, 'OFFLINE', '14n78juyt', 'Eman', NULL);

INSERT INTO driver_tabel (has_car_now, online_status, password, username, car_id)
VALUES (false, 'OFFLINE', 'SDF456GH', 'Dalal', NULL);

INSERT INTO driver_tabel (driver_id, date_created, has_car_now, online_status, password, username, car_id)
VALUES (false, 'OFFLINE', 'CVB876NB', 'Mohanad', NULL);


-- three drivers(above again) with status (OFFLINE), that mean they are not able to chose a car.


INSERT INTO driver_tabel (has_car_now, online_status, password, username, car_id)
VALUES (false, 'ONLINE', 'REAmn987', 'Eman', NULL);

INSERT INTO driver_tabel (has_car_now, online_status, password, username, car_id)
VALUES (false, 'ONLINE', 'ZXCS56565', 'Dalal', NULL);

INSERT INTO driver_tabel (has_car_now, online_status, password, username, car_id)
VALUES (false, 'ONLINE', 'JKH87M6NB', 'Mohanad', NULL);


-- those three above have a right to choose any car because there status is (ONLINE) but the car must be (UNMAP)


INSERT INTO driver_tabel (has_car_now, online_status, password, username, car_id)
VALUES (true, 'ONLINE', 'CXZD6754', 'Laith', 30);

INSERT INTO driver_tabel (has_car_now, online_status, password, username, car_id)
VALUES (true, 'ONLINE', '09OK76GF', 'Ammar', 31);

INSERT INTO driver_tabel (has_car_now, online_status, password, username, car_id)
VALUES (true, 'ONLINE', 'SDWX87JE2', 'Yamen', 32);

-- those are the drivers who took the cars at the top, and couz of them u can't choose between cars with id(30,31,32)