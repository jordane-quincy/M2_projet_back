# M2_projet_back

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
