install java development kit 8 (update JAVA_HOME system variable, Java, Path)
install maven 3.3.3 (update M2 system variable, M2_HOME, Path)
Install Python 3.4 (this could be done within next step, latest mysql will not install until python 3.4 isn't installed)
Install MySql (https://dev.mysql.com/downloads/windows/installer/ - will require registration or sign up on oracle)

Create schema with name 'charity', user with role 'owner', name 'charityAdmin' and password 'CharServ2015'. Use following cmd commands, please put ; only as it shown:

1) mysql
2) create database charity;
3) CREATE USER 'charityAdmin'@'localhost' IDENTIFIED BY 'CharServ2015';
4) GRANT ALL PRIVILEGES ON *.* TO 'charityAdmin'@'localhost';
5) flush privileges;

Then you have to download Postman plugin to Chrome and open it.

Run backend: mvn spring-boot:run

To test connection with backend use http://localhost:8088/api as an address, if you receive valid JSON with API list, everything is set up on backend.

If you have error in case of lack memory on VM, please allocate more memory: 

Start->Control Panel->System->Advanced(tab)->Environment Variables->System Variables->New:
Variable name: _JAVA_OPTIONS
Variable value: -Xmx512M