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
-- Table structure for table `coinvolgimento`
--

DROP TABLE IF EXISTS `coinvolgimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coinvolgimento` (
  `dipendente_cf` char(16) NOT NULL,
  `intervento_progressivo` int NOT NULL,
  `data_fine` date DEFAULT NULL,
  `data_inizio` date DEFAULT NULL,
  `ore_intervento` int NOT NULL,
  `macchinario` char(10) NOT NULL,
  PRIMARY KEY (`dipendente_cf`,`intervento_progressivo`,`macchinario`),
  KEY `intervento.#progressivo_idx` (`intervento_progressivo`),
  KEY `macchinario` (`macchinario`),
  CONSTRAINT `dipendente.cf` FOREIGN KEY (`dipendente_cf`) REFERENCES `dipendente` (`codice_fiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `intervento_progressivo` FOREIGN KEY (`intervento_progressivo`) REFERENCES `intervento` (`num_progressivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `macchinario` FOREIGN KEY (`macchinario`) REFERENCES `macchinario` (`cod_seriale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coinvolgimento`
--

LOCK TABLES `coinvolgimento` WRITE;
/*!40000 ALTER TABLE `coinvolgimento` DISABLE KEYS */;
INSERT INTO `coinvolgimento` VALUES ('5HERTS3729FERTAG',2345,'2020-11-09','2019-05-10',35,'STER56FT78'),('GRAFVE6RF7H9F8TD',6784,'2020-09-18','2017-12-14',20,'VTRFD654FR');
/*!40000 ALTER TABLE `coinvolgimento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-24 15:16:44
