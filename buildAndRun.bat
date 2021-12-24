@echo off
call mvn clean package
call docker build -t ma.benbrik/TpCompteBancaire .
call docker rm -f TpCompteBancaire
call docker run -d -p 9080:9080 -p 9443:9443 --name TpCompteBancaire ma.benbrik/TpCompteBancaire