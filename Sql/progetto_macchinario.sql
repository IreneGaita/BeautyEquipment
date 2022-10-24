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
-- Table structure for table `macchinario`
--

DROP TABLE IF EXISTS `macchinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `macchinario` (
  `cod_seriale` char(10) NOT NULL,
  `num_lotto` int NOT NULL,
  `descrizione` varchar(45) NOT NULL,
  `prezzo` double NOT NULL,
  `corriere_id` char(10) NOT NULL,
  `progetto_id` int NOT NULL,
  `base_nome` varchar(45) DEFAULT NULL,
  `accessoria_nome` varchar(45) DEFAULT NULL,
  `cliente_cf` char(16) NOT NULL,
  PRIMARY KEY (`cod_seriale`),
  KEY `corriere_id_idx` (`corriere_id`),
  KEY `progetto_id_idx` (`progetto_id`),
  KEY `base_nome_idx` (`base_nome`),
  KEY `accessoria_nome_idx` (`accessoria_nome`),
  KEY `cliente_cf_idx` (`cliente_cf`),
  CONSTRAINT `accessoria_nome` FOREIGN KEY (`accessoria_nome`) REFERENCES `accessoria` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `base_nome` FOREIGN KEY (`base_nome`) REFERENCES `base` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cliente_cf` FOREIGN KEY (`cliente_cf`) REFERENCES `cliente` (`codice_fiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `corriere_id` FOREIGN KEY (`corriere_id`) REFERENCES `corriere` (`corriere_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `progetto_id` FOREIGN KEY (`progetto_id`) REFERENCES `progetto` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `macchinario`
--

LOCK TABLES `macchinario` WRITE;
/*!40000 ALTER TABLE `macchinario` DISABLE KEYS */;
INSERT INTO `macchinario` VALUES ('GET678HY67',4,'lettino nero',200,'0123456789',3423,'lettino',NULL,'FRTSFRT567WE43R5'),('GRT6E5SDR7',5,'scaldaletto rosa',56,'2345678912',7634,NULL,'scaldaletto','GHRT543ERDF9IRFG'),('HRTYYE453R',8,'poggia testa blu',30,'0123456789',6534,NULL,'poggia testa','FRTSFRT567WE43R5'),('STER56FT78',5,'poltrona bianca',430,'0123456789',4568,'poltrona ',NULL,'FRTSFRT567WE43R5'),('VRT67DR56G',5,'vaporizzatore viso',100,'2345678912',9876,NULL,'vaporizzatore','GHRT543ERDF9IRFG'),('VTRFD654FR',6,'lampada neon ',20,'1234567891',5674,NULL,'lampada led','FRTSFRT567WE43R5');
/*!40000 ALTER TABLE `macchinario` ENABLE KEYS */;
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
