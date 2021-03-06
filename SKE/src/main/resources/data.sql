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

INSERT INTO domain VALUES (1, 'Arts, Lettres, Langues');
INSERT INTO domain VALUES (2, 'Droit, Economie, Gestion');
INSERT INTO domain VALUES (3, 'Sciences Humaines et Sociales');
INSERT INTO domain VALUES (4, 'Sciences, Technologies, Santé');

INSERT INTO formation VALUES (1, 'L1','Licence Arts');
INSERT INTO formation VALUES (2, 'L2','Licence Arts');
INSERT INTO formation VALUES (3, 'L3','Licence Arts');
INSERT INTO formation VALUES (4, 'L1','Licence Humanités');
INSERT INTO formation VALUES (5, 'L2','Licence Humanités');
INSERT INTO formation VALUES (6, 'L3','Licence Humanités');
INSERT INTO formation VALUES (7, 'M1','Master Etudes Culturelles');
INSERT INTO formation VALUES (8, 'M2','Master Etudes Culturelles');
INSERT INTO formation VALUES (9, 'DEUST1','Assistant juridique');
INSERT INTO formation VALUES (10, 'DEUST2','Assistant juridique');
INSERT INTO formation VALUES (11, 'M1','Technologies Nouvelles des Systèmes d''Information - TNSI');
INSERT INTO formation VALUES (12, 'M2','Technologies Nouvelles des Systèmes d''Information - TNSI');
INSERT INTO formation VALUES (13, 'M1','Génie Civil');
INSERT INTO formation VALUES (14, 'M2','Génie Civil');
INSERT INTO formation VALUES (15, 'DEUST1','Informatique d''Organisation et de Systemes d''Informations');
INSERT INTO formation VALUES (16, 'DEUST2','Informatique d''Organisation et de Systemes d''Informations');

INSERT INTO secretquestion VALUES (1, 'Arthur', 'Quel est le nom de votre premier animal de compagnie');
INSERT INTO secretquestion VALUES (2, 'Chocolat', 'Quel est le nom de votre premier animal de compagnie');
INSERT INTO secretquestion VALUES (3,'Vanille', 'Quel est le nom de votre premier animal de compagnie');
INSERT INTO secretquestion VALUES (4,'Princesse', 'Quel est le nom de votre premier animal de compagnie');
INSERT INTO secretquestion VALUES (5,'Muerte', 'Quel est le nom de votre premier animal de compagnie');
INSERT INTO secretquestion VALUES (6,'Fantôme', 'Quel est le nom de votre premier animal de compagnie');
INSERT INTO secretquestion VALUES (7,'Gold', 'Quel est le nom de votre premier animal de compagnie');

INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (1, {ts '1990-06-07 00:00:00.00000000'}, 5, '0123456789', 'STUDENT', NULL, 'Jordane', 'jordane.quincy@etu.univ-valenciennes.fr', 'QUINCY', 'jquincy', 12, 1);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (2, {ts '1980-06-07 00:00:00.00000000'}, 1, '0213456789', 'TEACHER', NULL, 'Mickael', 'mikael.desertot@univ-valenciennes.fr', 'DESERTOT', 'mdesertot', 7, 2);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (3, {ts '1979-03-02 00:00:00.00000000'}, 1, '0312456789', 'TEACHER', NULL, 'Sylvain', 'sylvain.lecomte@univ-valenciennes.fr', 'LECOMTE', 'slecomte', 10, 3);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (4, {ts '1979-03-02 00:00:00.00000000'}, 1, '0312456789', 'TEACHER', NULL, 'Maxime', 'maxime.degres@etu.univ-valenciennes.fr', 'DEGRES', 'mdegres', 12, 3);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (5, {ts '1979-03-02 00:00:00.00000000'}, 1, '0312456789', 'TEACHER', NULL, 'Maxime', 'maxime.grassart@etu.univ-valenciennes.fr', 'GRASSART', 'mgrassart', 12, 3);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (6, {ts '1973-03-02 00:00:00.00000000'}, 1, '0512346789', 'STUDENT', NULL, 'Morgan', 'morgan.basset@etu.univ-valenciennes.fr', 'BASSET', 'mbasset', 12, 5);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (7, {ts '1988-03-02 00:00:00.00000000'}, 1, '0612345789', 'STUDENT', NULL, 'Samir', 'samir.moutawakil@etu.univ-valenciennes.fr', 'MOUTAWAKIL', 'smoutawakil', 12, 6);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (8, {ts '1893-03-02 00:00:00.00000000'}, 1, '0712345689', 'STUDENT', NULL, 'Axel', 'axel.latour@etu.univ-valenciennes.fr', 'DUTHOIT', 'alatour', 12, 7);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (9, {ts '1893-03-02 00:00:00.00000000'}, 1, '0712345689', 'STUDENT', NULL, 'Clement', 'clement.ruffin@etu.univ-valenciennes.fr', 'RUFFIN', 'cruffin', 12, 7);
INSERT INTO "PUBLIC"."USER" (ID,BIRTHDAY,CREDIT,PHONENUMBER,ROLE,TOKEN,USERFIRSTNAME,USERMAIL,USERNAME,USERPASSWORD,FORMATION_ID,QUESTION_ID) VALUES (10, {ts '1893-03-02 00:00:00.00000000'}, 1, '0712345689', 'STUDENT', NULL, 'Frederic', 'Frederic.duthoit@etu.univ-valenciennes.fr', 'DUTHOIT', 'fduthoit', 12, 7);

INSERT INTO "PUBLIC"."OFFER" (ID,DESCRIPTION,DURATION,KEYWORDS,STATUS,TITLE,DOMAIN_ID,USER_ID) VALUES (1, 'Cours de développement web afin d''apprendre à utiliser les langages HTML, CSS, JSS, PHP, SQL', 1, 'css,developpement,web,utiliser,afin,php,html,langages,jss,cours,dapprendre,sql', false, 'Cours de développement web', 3, 1);
INSERT INTO "PUBLIC"."OFFER" (ID,DESCRIPTION,DURATION,KEYWORDS,STATUS,TITLE,DOMAIN_ID,USER_ID) VALUES (2, 'Cours de développement JAVA afin d''apprendre à utiliser les langages JAVA, JEE', 1, 'java,developpement,utiliser,afin,langages,jee,cours,dapprendre', false, 'Cours JAVA', 4, 1);
INSERT INTO "PUBLIC"."OFFER" (ID,DESCRIPTION,DURATION,KEYWORDS,STATUS,TITLE,DOMAIN_ID,USER_ID) VALUES (3, 'Cours de développement JEE', 1, 'developpement,jee,cours', true, 'Cours JEE', 1, 2);
INSERT INTO "PUBLIC"."OFFER" (ID,DESCRIPTION,DURATION,KEYWORDS,STATUS,TITLE,DOMAIN_ID,USER_ID) VALUES (4, 'Cours de développement mobile, nouvelles technologies', 1, 'nouvelles,technologies,xamarin,developpement,mobile,cours', false, 'XAMARIN', 4, 3);

INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (1, {ts '2017-06-15 12:00:00.00000000'}, 'CANCELLED', 4, 2);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (2, {ts '2017-06-15 11:00:00.000000000'}, 'PENDING', 4, 2);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (3, {ts '2017-06-18 11:00:00.000000000'}, 'PENDING', 4, 3);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (4, {ts '2017-06-08 16:00:00.000000000'}, 'VALIDATED', 4, 4);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (5, {ts '2017-06-06 11:00:00.000000000'}, 'FINISHED', 4, 2);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (6, {ts '2017-06-06 12:00:00.000000000'}, 'FINISHED', 5, 2);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (7, {ts '2017-06-06 13:00:00.000000000'}, 'FINISHED', 6, 3);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (8, {ts '2017-06-07 13:00:00.000000000'}, 'FINISHED', 7, 3);
INSERT INTO "PUBLIC"."APPOINTMENT" (ID,DATE,STATUS,APPLICANT_ID,OFFER_ID) VALUES (9, {ts '2017-06-07 8:00:00.000000000'}, 'FINISHED', 7, 1);

INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (1, 0, 'Incompréhensible, en une heure avec lui je n''ai strictement rien appris de nouveau', 2);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (2, 0, 'Inapte à proposer un cours', 2);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (3, 1, 'Malgres beaucoup d''efforts, le resultat n''est pas là, mais tu as les connaissances, dommage', 2);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (4, 3, 'Cours pas parfaitement organisé mais ce cours est compréhensible sachant que c''est une nouvelle techno !', 1);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (5, 0, 'J''ai remarqué que je n''aimais pas du tout le développement mobile, merci', 1);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (6, 1, 'Bien organisé mais trop difficile à comprendre', 1);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (7, 1, 'Pas pédagogue du tout ce monsieur !', 1);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (8, 5, 'Rien à dire !', 4);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (9, 3, 'Pas mal', 4);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (10, 5, 'Quel fin pédagogue !', 4);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (11, 4, 'Cours tres interessant, je vais reprendre une heure de ce pas !', 4);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (12, 5, 'Impressionnant, je recommande ce professeur', 4);
INSERT INTO "PUBLIC"."REMARK" (ID,GRADE,TEXT,OFFER_ID) VALUES (13, 5, 'Tres compréhensible, en une heure avec lui j''ai appris plus qu''en 1 an tout seul', 4);

INSERT INTO "PUBLIC"."SKILL" (ID,LABEL) VALUES (1,'JAVA');
INSERT INTO "PUBLIC"."SKILL" (ID,LABEL) VALUES (2,'SQL');
INSERT INTO "PUBLIC"."SKILL" (ID,LABEL) VALUES (3,'CSS');
INSERT INTO "PUBLIC"."SKILL" (ID,LABEL) VALUES (4,'XAMARIN');
INSERT INTO "PUBLIC"."SKILL" (ID,LABEL) VALUES (5,'JAVAEE');

INSERT INTO "PUBLIC"."VALIDATED_SKILLS" (USER_ID,VALIDATED,SKILLS_KEY) VALUES (1, false, 1);
INSERT INTO "PUBLIC"."VALIDATED_SKILLS" (USER_ID,VALIDATED,SKILLS_KEY) VALUES (1, false, 2);
INSERT INTO "PUBLIC"."VALIDATED_SKILLS" (USER_ID,VALIDATED,SKILLS_KEY) VALUES (1, true, 3);
INSERT INTO "PUBLIC"."VALIDATED_SKILLS" (USER_ID,VALIDATED,SKILLS_KEY) VALUES (3, true, 4);
INSERT INTO "PUBLIC"."VALIDATED_SKILLS" (USER_ID,VALIDATED,SKILLS_KEY) VALUES (2, true, 5);

INSERT INTO "PUBLIC"."NOTIFICATION" (ID,CONTENT,CREATIONDATE,TITLE,TYPE,USER_ID,STATUS) VALUES (1,'Rendez-vous fixé',{ts '2017-06-09 10:06:02.146000000'},'Notification title','MEETING',1,'NOTREAD');