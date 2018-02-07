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
  `availability` char(1) DEFAULT NULL,
  PRIMARY KEY (`copyID`),
  UNIQUE KEY `copysID_UNIQUE` (`copyID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copies`
--

LOCK TABLES `copies` WRITE;
/*!40000 ALTER TABLE `copies` DISABLE KEYS */;
INSERT INTO `copies` VALUES (1,1,'T'),(2,2,'T'),(3,2,'T'),(4,2,'T'),(5,2,'T'),(6,2,'T'),(7,2,'T'),(8,2,'T'),(9,3,'T'),(10,3,'T'),(11,3,'T'),(12,3,'T'),(13,3,'T'),(14,4,'T'),(15,4,'T');
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
  `reference` char(1) NOT NULL,
  `bestseller` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (1,'Computer Architecture','Patterson',1,100,'T','F'),(2,'Discrete Math','Rosen',7,100,'F','F'),(3,'Touch of Class','Bertrand Meyer',5,100,'F','T'),(4,'Academy','Isaac Asimov',2,100,'F','F');
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
  `status` varchar(13) NOT NULL,
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
INSERT INTO `users` VALUES (1,'Steve Wozniak','89227654321','New York','Student','qwerty',0),(2,'Steve Jobs','89227654123','Las Vegas','Librarian','qazwsx',0),(3,'Zeph Glover','89984916433','Carleton','FacultyMember','inceptos',0),(4,'Andrew Hammond','89335872952','Galvarino','FacultyMember','Nullam',0),(5,'Fitzgerald Evans','89915399132','San José de Alajuela','Student','vulputate,',0),(6,'Kelly Wilkins','89716636960','Groß-Gerau','Student','blandit.',0),(7,'Tanya Roberson','89492969173','Lucknow','Student','Etiam',0),(8,'Quamar Harrison','89055402478','Bouffioulx','Student','nec',0),(9,'Dennis Shields','89275314811','Aschersleben','Student','eu,',0),(10,'Jonah Nolan','89070046580','Darion','Student','sit',0),(11,'Octavius Weeks','89250762250','Bojano','Student','blandit',0),(12,'Evangeline Gibbs','89106427373','Candidoni','Student','imperdiet',0),(13,'Jada Woods','89753479565','İskenderun','Student','nunc,',0),(14,'Elton Bishop','89320538190','Fauglia','FacultyMember','In',0),(15,'Doris Griffin','89768056171','Vernon','FacultyMember','Sed',0),(16,'Brenden Miles','89791510518','Precenicco','Student','a',0),(17,'Nichole Mathews','89796422817','Ruddervoorde','Student','libero',0),(18,'Galvin Malone','89357393669','Rendsburg','Student','feugiat',0),(19,'Jamal Romero','89342875172','Villers-Perwin','FacultyMember','orci',0),(20,'Brendan Whitaker','89281650056','Fino Mornasco','FacultyMember','Mauris',0),(21,'Ann Neal','89668563399','Banff','Student','Cras',0),(22,'Maryam Owen','89671155143','Blehen','FacultyMember','ut,',0),(23,'Vielka Goodman','89303600072','Vastogirardi','FacultyMember','non,',0),(24,'Stephen Frederick','89548288545','Montemesola','Student','cubilia',0),(25,'Addison Atkinson','89566543707','Acireale','Student','Donec',0),(26,'Blaze Bell','89663012231','Nashville','FacultyMember','Fusce',0),(27,'Anthony Landry','89641987563','Alness','Student','et',0),(28,'Gloria Mathews','89915212365','Cambridge Bay','Student','urna.',0),(29,'Violet Oneil','89454921564','Petorca','Student','rutrum',0),(30,'Chancellor Pope','89575180077','Erpion','FacultyMember','arcu.',0),(31,'Honorato Orr','89456407822','South Dum Dum','Student','pellentesque.',0),(32,'Kasper Moody','89336853317','Kawawachikamach','Student','sollicitudin',0),(33,'Owen Houston','89432612913','Thiaumont','Student','Quisque',0),(34,'Keegan Davis','89254762448','Portici','FacultyMember','egestas.',0),(35,'Fay Hatfield','89659394207','Harrison Hot Springs','Student','In',0),(36,'Sybill Vincent','89813530035','Sonnino','Student','eu',0),(37,'Hall Lambert','89591220443','Habra','FacultyMember','ut',0),(38,'Herrod Burks','89327305756','Orbais','Student','Nunc',0),(39,'Nita Nguyen','89215251728','Providencia','Student','vel',0),(40,'Alexandra Cochran','89673503657','Kapuskasing','Student','fringilla',0),(41,'Howard Sherman','89739183340','Meeuwen','Student','ligula',0),(42,'Chastity Vasquez','89564054811','Jefferson City','FacultyMember','sit',0),(43,'Jenna Roth','89242897736','Diegem','FacultyMember','placerat',0),(44,'Ferris Mcintyre','89825656229','Coltauco','Student','mauris',0),(45,'Ella Dillard','89227650227','Eindhoven','Student','in',0),(46,'Martena Hyde','89973817233','Sierning','Student','neque',0),(47,'Griffin Yates','89756808681','Victoria','FacultyMember','nunc',0),(48,'Upton Gallegos','89758934530','Khanpur','Student','tincidunt,',0),(49,'Debra Cote','89879917410','Cap-de-la-Madeleine','FacultyMember','placerat,',0),(50,'Kim Schultz','89892039878','Cedar Rapids','FacultyMember','Donec',0),(51,'Britanney Bruce','89932585389','Niort','FacultyMember','dictum',0),(52,'Scarlett Willis','89929147717','Memphis','Student','fringilla',0),(53,'Raymond Randolph','89351956790','Zittau','Student','elit.',0),(54,'Isadora Nguyen','89142289898','Alexandra','FacultyMember','sed',0),(55,'Eagan Lyons','89821843924','Fort Good Hope','Student','mi',0),(56,'Amery Ware','89580466874','Serrungarina','FacultyMember','purus',0),(57,'India Patterson','89229252062','Lusevera','FacultyMember','cursus',0),(58,'Nevada Gomez','89406756509','Tufo','FacultyMember','ante.',0),(59,'Constance Browning','89800052554','Langford','FacultyMember','lacus.',0),(60,'Stewart Tyson','89623205847','East Linton','FacultyMember','nisi.',0),(61,'Dillon Ferguson','89950255536','Canino','FacultyMember','adipiscing.',0),(62,'Damian Potts','89475479972','Woodstock','FacultyMember','vitae',0),(63,'Angelica Kennedy','89063136865','Clermont-Ferrand','FacultyMember','in,',0),(64,'Owen Boyle','89641793321','Russell','FacultyMember','Integer',0),(65,'Blythe Bridges','89571450699','Brentwood','Student','nec',0),(66,'Charde Jennings','89478103660','Stuttgart','FacultyMember','sapien',0),(67,'Kaitlin Woods','89237921813','Pugwash','Student','justo',0),(68,'Hanae Barton','89829652563','Khanpur','Student','eu',0),(69,'Francis Elliott','89494254063','Saavedra','Student','consectetuer',0),(70,'Lesley Curry','89879061024','Sankt Ingbert','FacultyMember','at,',0),(71,'Celeste Knox','89182722596','Neerrepen','FacultyMember','aliquam',0),(72,'Winter Monroe','89186848568','Niort','FacultyMember','Mauris',0),(73,'Destiny Leon','89594165964','Bonavista','Student','penatibus',0),(74,'Kirby Mcgee','89721236016','Mezzana','Student','ridiculus',0),(75,'Finn Mullen','89999348880','Pudahuel','Student','semper.',0),(76,'Ifeoma Knight','89283051750','Llangollen','Student','a,',0),(77,'Addison Maddox','89853983911','Miranda','FacultyMember','luctus',0),(78,'Addison Mueller','89816119688','San Sostene','FacultyMember','ante,',0),(79,'Shaine Hood','89826487389','Torino','Student','non,',0),(80,'Levi Montoya','89757369291','Santa Coloma de Gramenet','FacultyMember','tempor',0),(81,'Raja Trevino','89796783461','Jabalpur','Student','erat',0),(82,'Sasha Mason','89433100197','New Quay','Student','Proin',0),(83,'Cadman Pruitt','89818375671','Lagundo/Algund','FacultyMember','aliquet',0),(84,'Jeremy Nelson','89532633021','Suarlee','FacultyMember','sapien',0),(85,'Lareina Sawyer','89462293581','Limbach-Oberfrohna','FacultyMember','ipsum',0),(86,'Fritz Maldonado','89736751164','Malegaon','FacultyMember','dolor',0),(87,'Rudyard Greene','89207337208','Portsoy','Student','eget',0),(88,'Ella Shaffer','89936595109','Aliano','FacultyMember','nec',0),(89,'Charde Cain','89368036358','Houston','FacultyMember','Quisque',0),(90,'Dana Klein','89626326122','Juneau','FacultyMember','sem',0),(91,'Joshua Guerrero','89250256271','Delianuova','Student','eget',0),(92,'Serena Duran','89256441036','Newcastle','Student','massa.',0),(93,'Walker Stafford','89061634732','Machilipatnam','FacultyMember','nascetur',0),(94,'Magee Landry','89840857325','Constitución','Student','sagittis.',0),(95,'Giacomo Warner','89005574488','Muradiye','FacultyMember','ipsum',0),(96,'Amity Schultz','89970238136','Kallo','Student','facilisis,',0),(97,'Neve Suarez','89729906089','Benalla','Student','Nulla',0),(98,'Cora Henry','89181774377','Saharanpur','Student','dolor',0),(99,'Fay Vance','89452619091','Seraing','FacultyMember','accumsan',0),(100,'Garrett Freeman','89615324721','Ipswich','Student','mi,',0),(101,'Hop Valenzuela','89389602019','Alnwick','FacultyMember','vehicula',0),(102,'Cecilia Decker','89502521173','Trowbridge','Student','mollis',0);
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

-- Dump completed on 2018-02-07 16:22:49
