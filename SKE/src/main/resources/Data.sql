/*******************************TRUNCATE PART************************************/

TRUNCATE TABLE appointment
TRUNCATE TABLE remark
TRUNCATE TABLE offer
TRUNCATE TABLE domain
TRUNCATE TABLE notification
TRUNCATE TABLE validated_skills
TRUNCATE TABLE skill
TRUNCATE TABLE user
TRUNCATE TABLE formation
TRUNCATE TABLE secretquestion

/*******************************DROP PART***********************************

DROP TABLE appointment
DROP TABLE remark
DROP TABLE offer
DROP TABLE domain
DROP TABLE notification
DROP TABLE validated_skills
DROP TABLE skill
DROP TABLE user
DROP TABLE formation
DROP TABLE secretquestion
*/

/*******************************INSERT PART************************************/

INSERT INTO domain VALUES (1, 'Arts, Lettres, Langues')
INSERT INTO domain VALUES (2, 'Droit, Economie, Gestion')
INSERT INTO domain VALUES (3, 'Sciences Humaines et Sociales')
INSERT INTO domain VALUES (4, 'Sciences, Technologies, Santé')

INSERT INTO formation VALUES (1, 'L1','Licence Arts')
INSERT INTO formation VALUES (2, 'L2','Licence Arts')
INSERT INTO formation VALUES (3, 'L3','Licence Arts')
INSERT INTO formation VALUES (4, 'L1','Licence Humanités')
INSERT INTO formation VALUES (5, 'L2','Licence Humanités')
INSERT INTO formation VALUES (6, 'L3','Licence Humanités')
INSERT INTO formation VALUES (7, 'M1','Master Etudes Culturelles')
INSERT INTO formation VALUES (8, 'M2','Master Etudes Culturelles')
INSERT INTO formation VALUES (9, 'DEUST1','Assistant juridique')
INSERT INTO formation VALUES (10, 'DEUST2','Assistant juridique')
INSERT INTO formation VALUES (11, 'M1','Technologies Nouvelles des Systèmes d''Information - TNSI')
INSERT INTO formation VALUES (12, 'M2','Technologies Nouvelles des Systèmes d''Information - TNSI')
INSERT INTO formation VALUES (13, 'M1','Génie Civil')
INSERT INTO formation VALUES (14, 'M2','Génie Civil')
INSERT INTO formation VALUES (15, 'DEUST1','Informatique d''Organisation et de Systemes d''Informations')
INSERT INTO formation VALUES (16, 'DEUST2','Informatique d''Organisation et de Systemes d''Informations')

INSERT INTO secretquestion VALUES (1, 'Arthur', 'Quel est le nom de votre premier animal de compagnie')
INSERT INTO secretquestion VALUES (2, 'Chocolat', 'Quel est le nom de votre premier animal de compagnie')
INSERT INTO secretquestion VALUES (3,'Princesse', 'Quel est le nom de votre premier animal de compagnie')

INSERT INTO user VALUES (1, {ts '1990-06-07 00:00:00.00000000'}, 5, 'STUDENT', '123' , 'Jordan', 'jordane.quincy@etu.univ-valenciennes.fr', 'QUINCY', 'jquincy', 5, 1)
INSERT INTO user VALUES (2, {ts '1980-06-07 00:00:00.00000000'}, 1, 'TEACHER', '456' ,'Mickael', 'mikael.desertot@univ-valenciennes.fr', 'DESERTOT', 'mdesertot', 7, 2)
INSERT INTO user VALUES (3, {ts '1979-03-02 00:00:00.00000000'}, 1, 'TEACHER', '789' , 'Sylvain', 'Sylvain.Lecomte@univ-valenciennes.fr', 'LECOMTE', 'slecomte', 10, 3)
INSERT INTO user VALUES (4, {ts '1993-03-02 00:00:00.00000000'}, 1, 'STUDENT', '101112' , 'John', 'John.Ypeurien@univ-valenciennes.fr', 'YPEURIEN', 'jypeurien', 15, 3)

INSERT INTO OFFER VALUES (1, 'Cours de développement web afin d''apprendre à utiliser les langages HTML, CSS, JSS, PHP, SQL', 1, 'Cours de développement web', 3, 1)
INSERT INTO OFFER VALUES (2, 'Cours de développement JAVA afin d''apprendre à utiliser les langages JAVA, JEE', 1, 'Cours JAVA', 4, 1)
INSERT INTO OFFER VALUES (3, 'Cours de développement JEE', 1, 'Cours JEE', 1, 2)
INSERT INTO OFFER VALUES (4, 'Cours de développement mobile, nouvelles technologies', 1, 'XAMARIN', 4, 3)


INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (1, {ts '2017-06-15 12:00:00.00000000'}, 'CANCELED', 4, 2)
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (2, {ts '2017-06-15 11:00:00.000000000'}, 'PENDING', 4, 2);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (3, {ts '2017-06-18 11:00:00.000000000'}, 'PENDING', 4, 3);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (4, {ts '2017-06-08 16:00:00.000000000'}, 'VALIDATED', 4, 4);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (5, {ts '2017-06-06 11:00:00.000000000'}, 'FINISHED', 4, 2);

INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (1, 5, 'Tres compréhensible, en une heure avec lui j''ai appris plus que 1 an tout seul', 2);