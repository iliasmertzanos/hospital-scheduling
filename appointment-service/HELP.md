#Docker 

- MySql :   ``docker run --detach --name=mysql_appointment --env="MYSQL_ROOT_PASSWORD=24081983" --publish 6733:3306 mysql``
- connect to mysql server:  `` docker exec -it mysql_appointment mysql -uroot -p24081983``
 - and run: <br>
``create database db_appointment;``<br> 
``create user 'springuser'@'%' identified by 'ThePassword';``<br> 
``grant all on db_appointment.* to 'springuser'@'%'; ``<br>   