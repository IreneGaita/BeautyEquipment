-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
--
-- Host: localhost    Database: progetto
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `intervento`
--

DROP TABLE IF EXISTS `intervento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intervento` (
  `num_progressivo` int NOT NULL,
  `macchinario_codice_seriale` char(10) NOT NULL,
  `richiesto` varchar(45) DEFAULT NULL,
  `in_lavorazione` varchar(45) DEFAULT NULL,
  `completato` varchar(45) DEFAULT NULL,
  `valutato` varchar(45) DEFAULT NULL,
  `verificato` varchar(45) DEFAULT NULL,
  `data_completamento` date DEFAULT NULL,
  `data_arrivo` date DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`num_progressivo`,`macchinario_codice_seriale`),
  KEY `macchinario_codice seriale_idx` (`macchinario_codice_seriale`),
  CONSTRAINT `macchinario_codice seriale` FOREIGN KEY (`macchinario_codice_seriale`) REFERENCES `macchinario` (`cod_seriale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vincoloms` CHECK (((`tipo` = _utf8mb4'sostituzione') or (`tipo` = _utf8mb4'manutenzione')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervento`
--

LOCK TABLES `intervento` WRITE;
/*!40000 ALTER TABLE `intervento` DISABLE KEYS */;
INSERT INTO `intervento` VALUES (2345,'STER56FT78',NULL,NULL,'X',NULL,NULL,'2020-11-09','2019-05-03','sostituzione'),(6784,'VTRFD654FR','X',NULL,NULL,NULL,NULL,'2020-09-18','2017-12-14','sostituzione'),(7653,'GET678HY67',NULL,'X',NULL,NULL,NULL,NULL,'2020-10-13','manutenzione');
/*!40000 ALTER TABLE `intervento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-24 15:16:45
