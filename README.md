# M2_projet_back

[![Build Status](https://travis-ci.org/jordane-quincy/M2_projet_back.svg?branch=master)](https://travis-ci.org/jordane-quincy/M2_projet_back) 

Nom du package principal : 
org.istv.ske

Import du projet Back dans Eclipse :
	- git pull
	- import Maven Project
	- SKE
	- Maven Update
	- Run as Java Application
	
Configuration d'Eclipse :
	- Avoir un serveur (tomcat ou wildfly) configuré dans les runtimes


Ligne de commande pour lancer la base HSQLDB :
java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:skedb --dbname.0 skedb

Utilisation de git :

premiere fois : git clone https://github.com/jordane-quincy/M2_projet_back.git

modification du fichier1 : git add fichier1

commit : git commit -m "maj fichier1"

on rappatrie en local les modifications que les autres ont pu faire : git pull

2 choix :

	- soit c'est le merge est automatique, on valide que tout fonctionne en local puis : git push

	- soit il y a un conflit, on fais le merge (on demande si on ne sais pas faire), on valide que tout fonctionne en local puis : git push

Git tips :

Si vous avez un soucis avec un fichier que vous n'avez pas modifié (ou par mégarde et que vous voulez la version du repo) :

git checkout fichierPosantProblem
