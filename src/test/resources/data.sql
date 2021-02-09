INSERT INTO location.town
VALUES (1, 'Aaron''s Hill', 'Surrey', 'England', 'SU957435', 495783, 143522, 51.18291, -0.63098, 78, 'GU7 2',
        'Waverley District', 'South East', 'Suburban Area'),
       (2, 'Abbas Combe', 'Somerset', 'England', 'ST707226', 370749, 122688, 51.00283, -2.41825, 91, 'BA8 0',
        'South Somerset District', 'South West', 'Village');

INSERT INTO location.address (first_line, second_line, third_line, town_id, postcode)
VALUES ('1 Bobcat Street', 'Catisworth', NULL, 1, 'GU7 2AA'),
       ('1 Tolsworth Avenue', 'Empi Hill', NULL, 2, 'BA8 0AB'),
       ('32 Deckwith Road', 'Lands Lane', NULL, 2, 'BA8 1AB');
