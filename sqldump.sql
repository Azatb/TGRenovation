-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: renovationdb
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `additionalinfo`
--

DROP TABLE IF EXISTS `additionalinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `additionalinfo` (
  `settlement` varchar(50) NOT NULL,
  `comments` varchar(400) DEFAULT NULL,
  `weekday` varchar(50) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_CVR` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `additionalinfo_id_uindex` (`id`),
  KEY `additionalInfo_company_CVR_fk` (`fk_CVR`),
  CONSTRAINT `additionalInfo_company_CVR_fk` FOREIGN KEY (`fk_CVR`) REFERENCES `company` (`CVR`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `additionalinfo`
--

LOCK TABLES `additionalinfo` WRITE;
/*!40000 ALTER TABLE `additionalinfo` DISABLE KEYS */;
INSERT INTO `additionalinfo` VALUES ('Dankort','Its finger lickin good','Onsdag','Region Hovedstaden',45,94857389),('Mobilepay','Its finger lickin good','Mandag','Region Hovedstaden',46,94857389),('Cash','Its finger lickin good','Lørdag','Region Syddanmark',47,94857389);
/*!40000 ALTER TABLE `additionalinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `CVR` int(11) NOT NULL,
  `p_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`CVR`),
  UNIQUE KEY `Company_CVR_uindex` (`CVR`),
  UNIQUE KEY `company_company_name_uindex` (`company_name`),
  UNIQUE KEY `company_p_number_uindex` (`p_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('KFC','1234',94857389,1007742610);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_person`
--

DROP TABLE IF EXISTS `contact_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_person` (
  `name` varchar(50) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `pickup_adress` varchar(50) NOT NULL,
  `fk_CVR` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contact_person_id_uindex` (`id`),
  KEY `customer_company_CVR_fk` (`fk_CVR`),
  CONSTRAINT `customer_company_CVR_fk` FOREIGN KEY (`fk_CVR`) REFERENCES `company` (`CVR`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_person`
--

LOCK TABLES `contact_person` WRITE;
/*!40000 ALTER TABLE `contact_person` DISABLE KEYS */;
INSERT INTO `contact_person` VALUES ('Colonel Sanders',23232322,'Amagerbrogade 95 2300 København S',94857389,45),('Colonel Sanders',19238433,'Ishøj Østergade 31, 2635 Ishøj',94857389,46),('Colonel Sanders',74748583,'Blomstervej 2K',94857389,47);
/*!40000 ALTER TABLE `contact_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `driver_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES ('Thomas','1234','Region Hovedstaden',16),('Jens','1234','Region Syddanmark',17);
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oil`
--

DROP TABLE IF EXISTS `oil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oil` (
  `size` varchar(50) NOT NULL,
  `amount` int(11) NOT NULL,
  `fk_CVR` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `oil_id_uindex` (`id`),
  KEY `oil_company_CVR_fk` (`fk_CVR`),
  CONSTRAINT `oil_company_CVR_fk` FOREIGN KEY (`fk_CVR`) REFERENCES `company` (`CVR`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oil`
--

LOCK TABLES `oil` WRITE;
/*!40000 ALTER TABLE `oil` DISABLE KEYS */;
INSERT INTO `oil` VALUES ('150L (115kg)',3,94857389,45),('IBC 800-1000L',38,94857389,46),('150L (115kg)',6,94857389,47);
/*!40000 ALTER TABLE `oil` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-12 14:58:55
