INSERT INTO courses ( course_id,name, price,image,numberseats)
VALUES (1,'Java developer', 20000,'037646e7-1b56-4df9-8fe3-6c0bd9937cf0.jpg',10),
       (2,'Phyton developer', 15000,'74392782-9a11-4fe5-a433-abaf7b06dfe6.jpg',5),
       (3,'QA engineer', 10000,'22a80fdc-f693-475a-9e2d-9988947ace49.jpg',15),
       (4,'JavaScript', 10000,'402fb0b7-db1d-4ced-936b-80b7fd9b9a9e.jpg',10),
       (5,'Android developer', 12000,'noimage.jpg',8);

--DROP TABLE registrations;

INSERT INTO registrations ( course_id,name , price,customer_name)
VALUES (1,'Java developer',20000,'Vasya'),
       (2,'Phyton developer',15000,'Anton'),
       (4,'JavaScript',10000,'Anna'),
       ( 1,'Java devoleper',20000,'Vanya'),
       (3,'QA engineer',10000,'Kseniya'),
       (3,'QA engineer',10000, 'Masha'),
       (1,'Java devoleper',20000,'Alex'),
       (2,'Phyton developer',15000,'Angelina'),
       (2,'Phyton developer',15000,'Nina'),
       (2,'Phyton developer',15000,'Kirill'),
       (2,'Phyton developer',15000,'Kostya'),
       (2,'Phyton developer',15000,'Jana')
