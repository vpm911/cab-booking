DROP TABLE IF EXISTS billionaires;

INSERT INTO CITY (id, name) VALUES
  (1, 'Pune'),
  (2, 'Mumbai'),
  (3, 'Delhi');

INSERT INTO CAB (LICENSE_NUMBER,STATE,CURRENT_CITY_ID,LAST_ON_TRIP) VALUES
('MH12PU1111','IDLE',1,'2021-05-21 10:39:48.0000000'),
('MH01MU1111','ON_TRIP',2,'2021-02-20 10:39:48.0000000'),
('DL14PC1111','IDLE',3,'2021-04-20 10:39:48.0000000'),
('MH12PU1112','IDLE',1,'2021-04-21 10:39:48.0000000');

INSERT INTO CUSTOMER (id,name)
VALUES (1, 'vishal'),
(2,'Bhushan'),
(3,'Omkar');



INSERT INTO BOOKING (ID,START_TIME, END_TIME, CAB_ID, CUSTOMER_ID) VALUES
(1, '2021-05-21 13:00:0.0', '2021-05-21 16:00:0.0', 1, 1),
(2, '2021-05-20 10:00:0.0', '2021-05-20 20:00:0.0', 2, 2),
(3, '2021-05-20 09:00:0.0', '2021-05-20 10:00:0.0', 1, 1),
(4, '2021-05-19 13:00:0.0', '2021-04-19 16:00:0.0', 1, 1),
(5, '2021-05-21 18:00:0.0', '2021-05-21 18:30:0.0', 1, 1),
(6, '2021-05-21 12:00:0.0', null, 2, 2);