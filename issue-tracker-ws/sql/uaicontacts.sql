-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.22-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para uaicontacts
CREATE DATABASE IF NOT EXISTS `uaicontacts` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `uaicontacts`;


-- Volcando estructura para tabla uaicontacts.contact
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla uaicontacts.contact: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`id`, `email`, `name`, `phoneNumber`) VALUES
	(1, 'susan.inga@joedayz.pe', 'Susan Inga', '987500272');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;


-- Volcando estructura para tabla uaicontacts.system_user
CREATE TABLE IF NOT EXISTS `system_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla uaicontacts.system_user: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` (`id`, `email`, `enabled`, `name`, `password`, `user_role`) VALUES
	(1, 'jose.diaz@joedayz.pe', 'true', 'Jose Diaz', '123', 'ROLE_ADMIN'),
	(2, 'miryan.ramirez@joedayz.pe', 'true', 'Miryan Ramirez', '123', 'ROLE_USER');
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;





//en el server.xml

<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" 
	factory="org.apache.tomcat.jdbc.pool.DataSourceFactory" initialSize="34" maxActive="377" 
	maxIdle="233" minEvictableIdleTimeMillis="55000" minIdle="89" name="jdbc/tomcatDataSource"
	 password="password" removeAbandoned="true" removeAbandonedTimeout="55" testOnBorrow="true"
	  timeBetweenEvictionRunsMillis="34000" type="javax.sql.DataSource" 
	  url="jdbc:mysql://localhost:3306/uaiContacts?allowMultiQueries=true"
	   username="root" validationInterval="34000" validationQuery="SELECT 1"/>