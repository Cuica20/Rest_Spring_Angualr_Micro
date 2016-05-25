CREATE DATABASE IF NOT EXISTS issuetracker;
 USE issuetracker;

 CREATE TABLE `issue` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `title` varchar(255) DEFAULT NULL,
 `type` varchar(64) DEFAULT NULL,
 `priority` varchar(64) DEFAULT NULL,
 `status` varchar(64) DEFAULT NULL,
 `description` varchar(2000) DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
CREATE USER 'issueuser'@'localhost' IDENTIFIED BY 'issuepass';
GRANT ALL ON issuetracker.* TO 'issueuser'@'localhost';