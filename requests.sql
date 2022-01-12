SELECT id,name FROM register_positions
WHERE name LIKE 'Ja%';

SELECT sum(qty) FROM register_positions
WHERE name='Java developer';

SELECT register_id
FROM register_positions
WHERE name= 'Phyton developer'
GROUP BY register_id;

SELECT id,course_id, name, price,customer_name FROM registrations
WHERE course_id=4
ORDER BY id
LIMIT 500;

SELECT course_id, sum(qty) FROM register_positions
GROUP BY course_id
HAVING sum(qty)>0;

SELECT count(*) FROM register_positions
