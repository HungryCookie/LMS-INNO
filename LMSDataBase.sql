CREATE DATABASE  IF NOT EXISTS `ldata` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ldata`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ldata
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `bookID` int(11) NOT NULL,
  `userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,2),(2,2),(3,2),(1,1),(3,1);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copies`
--

DROP TABLE IF EXISTS `copies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `copies` (
  `copyID` int(11) NOT NULL AUTO_INCREMENT,
  `commonID` int(11) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`copyID`),
  UNIQUE KEY `copysID_UNIQUE` (`copyID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copies`
--

LOCK TABLES `copies` WRITE;
/*!40000 ALTER TABLE `copies` DISABLE KEYS */;
INSERT INTO `copies` VALUES (1,2,'available'),(2,2,'available');
/*!40000 ALTER TABLE `copies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `counter` int(11) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (1,'Computer Archetechture','Patterson',1,100),(2,'Descrete Math','Rosen',2,100),(3,'Touch of Class','Bertrand Meyer',5,100);
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phoneNumber` varchar(11) NOT NULL,
  `address` varchar(45) NOT NULL,
  `status` varchar(10) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fine` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `phoneNumber_UNIQUE` (`phoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Steve Wozniak','89227654321','New York','Patron','qwerty',0),(2,'Steve Jobs','89227654123','Las Vegas','Librarian','qazwsx',0),(3,'Nero Thomas','89124124916','Stockholm','Patron','sapien,',0),(4,'Rebekah Lindsey','89643656216','Goslar','Patron','ligula.',0),(5,'Karina Witt','89401822290','Seattle','Patron','ipsum',0),(6,'Callie Benton','89404584905','Valley East','Patron','pede,',0),(7,'Jason Leblanc','89676277291','Calvi Risorta','Patron','gravida',0),(8,'Stacey Dotson','89765349049','Busso','Patron','ac',0),(9,'Noble Pena','89267772653','Tewkesbury','Patron','tellus',0),(10,'Rashad Mathis','89536777691','Mont-Sainte-Genevive','Patron','Aliquam',0),(11,'Xander Wood','89747077965','Goslar','Patron','ipsum.',0),(12,'Uriah Lott','89961762384','Belgrave','Patron','Nullam',0),(13,'Wylie Ramirez','89655995625','Wechelderzande','Patron','dapibus',0),(14,'Audrey Mccullough','89734528520','North Shore','Patron','feugiat',0),(15,'Baxter Bush','89357370891','Baton Rouge','Patron','Aliquam',0),(16,'Yael Hunt','89048282145','Pellizzano','Patron','erat.',0),(17,'Kermit Wheeler','89729156373','Colchester','Patron','sed',0),(18,'Savannah Hopkins','89360022170','Diets-Heur','Patron','Nulla',0),(19,'Heidi Jensen','89163087738','Viersel','Patron','condimentum.',0),(20,'Bruce Ward','89076758161','Kolmont','Patron','Proin',0),(21,'Nelle Fuentes','89658819221','Huesca','Patron','fermentum',0),(22,'Marah Roth','89490963689','Okigwe','Patron','Sed',0),(23,'Yael Russo','89032025304','Cedar Rapids','Patron','tellus.',0),(24,'Justina Rivas','89366038219','Crehen','Patron','pretium',0),(25,'Elvis Perkins','89086848039','Bodmin','Patron','purus,',0),(26,'Porter Finley','89483349023','Istres','Patron','tempor',0),(27,'Jacob Hurst','89734276300','Casciana Terme','Patron','rutrum',0),(28,'Kasper Dunlap','89585825525','Calais','Patron','interdum.',0),(29,'Tamara Sharp','89251950266','Devonport','Patron','non,',0),(30,'Ocean Langley','89500553584','Darlington','Patron','convallis',0),(31,'Ralph Gates','89378462174','Venlo','Patron','eros.',0),(32,'Signe Walters','89000758429','Castel Giorgio','Patron','ornare',0),(33,'Kamal Harvey','89489137825','Shahjahanpur','Patron','Phasellus',0),(34,'Rae Acevedo','89891692150','Siegendorf','Patron','Aenean',0),(35,'Kaseem Potter','89945051096','Massemen','Patron','Suspendisse',0),(36,'Lara Gibbs','89388781275','Mysore','Patron','risus.',0),(37,'Andrew Burnett','89929673684','Canberra','Patron','at',0),(38,'Colorado Christensen','89910359196','Chelmsford','Patron','vitae,',0),(39,'Ora Sosa','89228360273','Maltignano','Patron','Maecenas',0),(40,'Damon Koch','89966868943','Nîmes','Patron','fermentum',0),(41,'Nelle Monroe','89280410460','Massenhoven','Patron','consectetuer',0),(42,'Finn Chambers','89195409959','Oostkerke','Patron','ligula.',0),(43,'Calvin Compton','89333405549','Ebenthal in Kärnten','Patron','Duis',0),(44,'Griffin Schwartz','89304787787','Gespeg','Patron','at',0),(45,'Eve Flores','89723628297','Cap-de-la-Madeleine','Patron','Nam',0),(46,'Drake Marquez','89870447945','Coutisse','Patron','gravida',0),(47,'Nash Farmer','89074180130','Maidenhead','Patron','viverra.',0),(48,'Leila Burke','89547748139','Purranque','Patron','pede.',0),(49,'Lacy Montoya','89995929950','Melbourne','Patron','lorem',0),(50,'Abdul Hart','89103025248','Bazzano','Patron','natoque',0),(51,'Shaine Gaines','89135796480','Zwolle','Patron','eget,',0),(52,'Dorian Burt','89828803955','Radicofani','Patron','eros',0),(53,'Rhea Guerra','89449518815','Calama','Patron','Cras',0),(54,'Gray Hickman','89149462302','East Gwillimbury','Patron','hendrerit',0),(55,'Bryar Rodriquez','89062564889','Caloundra','Patron','ipsum',0),(56,'Nero Orr','89946078461','Parrano','Patron','tempor',0),(57,'Galvin Wise','89873854757','Ham-sur-Sambre','Patron','nec',0),(58,'Cedric Ayers','89810855766','Goiânia','Patron','venenatis',0),(59,'Cullen Charles','89477449481','Westmount','Patron','pharetra',0),(60,'Pandora Pope','89019149379','Lake Cowichan','Patron','consectetuer',0),(61,'Willow Fischer','89160437102','Fahler','Patron','natoque',0),(62,'Willow Kane','89199439212','Westkapelle','Patron','iaculis',0),(63,'Macon Williamson','89660144536','South Portland','Patron','bibendum',0),(64,'Robin Anderson','89765079075','Saint-Louis','Patron','Vestibulum',0),(65,'Malcolm Jenkins','89976282942','Lalbahadur Nagar','Patron','ac',0),(66,'Dean Gonzalez','89983243572','Musson','Patron','et,',0),(67,'Nasim Suarez','89792193641','Vaughan','Patron','magnis',0),(68,'Mary Trujillo','89870835532','Jambes','Patron','vitae',0),(69,'Victor Lancaster','89709868877','Richmond','Patron','ornare.',0),(70,'Stacey Levy','89259807478','Cles','Patron','quis',0),(71,'Harper Turner','89104171984','Rotello','Patron','dignissim.',0),(72,'Gregory Lane','89389522338','San Gregorio','Patron','gravida.',0),(73,'Faith Barron','89098445443','Mersin','Patron','id',0),(74,'Jada Wright','89119010763','Ernage','Patron','et',0),(75,'Grady Sandoval','89867434039','Monguelfo-Tesido/Welsberg-Taisten','Patron','Donec',0),(76,'Alea Curry','89159003907','Saint-Denis','Patron','auctor,',0),(77,'Gavin Bradley','89877736239','Alajuelita','Patron','erat.',0),(78,'Eleanor Lindsey','89129354113','Colico','Patron','gravida',0),(79,'Shelley Kemp','89140483956','Aklavik','Patron','Duis',0),(80,'Ulysses Buckley','89872114416','Villafranca Tirrena','Patron','augue.',0),(81,'Ross Cameron','89602633918','Łódź','Patron','sit',0),(82,'Hedley Prince','89827493024','Malbaie','Patron','nisi.',0),(83,'Brynn Mayer','89210077669','Brisbane','Patron','Nullam',0),(84,'Rachel Lang','89846487811','Tilff','Patron','et',0),(85,'Anne Navarro','89008405209','Yorkton','Patron','dolor',0),(86,'Hop Brock','89105329568','Flamierge','Patron','a,',0),(87,'Addison Campos','89068876630','Ripalta Guerina','Patron','eget',0),(88,'Hope Maxwell','89593950256','Lac-Serent','Patron','in',0),(89,'Aimee Newton','89899560672','Coinco','Patron','lobortis,',0),(90,'Tana Duncan','89930122766','Joliet','Patron','Sed',0),(91,'Christine Mcintosh','89950854246','Montluçon','Patron','pretium',0),(92,'Stewart Nielsen','89225243609','Anand','Patron','vel,',0),(93,'Galvin Harding','89327222973','Martello/Martell','Patron','purus.',0),(94,'Jenna Boyle','89393640560','South Portland','Patron','eleifend',0),(95,'Cameron Talley','89968132287','Macklin','Patron','risus',0),(96,'Alexandra Luna','89356296968','Asnières-sur-Seine','Patron','Cras',0),(97,'Elliott Robbins','89823887426','Araban','Patron','Donec',0),(98,'Heidi Roach','89379972489','Idaho Falls','Patron','purus',0),(99,'Macaulay Mason','89241568863','Dollard-des-Ormeaux','Patron','molestie',0),(100,'Cain Hayden','89966800358','Purranque','Patron','quam.',0),(101,'Erich Andrews','89365408200','Gebze','Patron','Mauris',0),(102,'Hasad Barnett','89577826984','Montemesola','Patron','cursus,',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-05 12:04:28
