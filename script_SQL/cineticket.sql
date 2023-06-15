CREATE DATABASE  IF NOT EXISTS `cineticket` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cineticket`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: cineticket
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `acesso`
--

DROP TABLE IF EXISTS `acesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acesso` (
  `email_cliente` varchar(55) NOT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY (`email_cliente`),
  CONSTRAINT `fk_Acesso_Clientes1` FOREIGN KEY (`email_cliente`) REFERENCES `cliente` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acesso`
--

LOCK TABLES `acesso` WRITE;
/*!40000 ALTER TABLE `acesso` DISABLE KEYS */;
INSERT INTO `acesso` VALUES ('lucas@gmail.com','12345'),('rodrigo@gmail.com','abcd'),('yuri@gmail.com','1234567'),('yurimcff@gmail.com','abc1234');
/*!40000 ALTER TABLE `acesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cadeira`
--

DROP TABLE IF EXISTS `cadeira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cadeira` (
  `id_cadeira` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_cadeira`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cadeira`
--

LOCK TABLES `cadeira` WRITE;
/*!40000 ALTER TABLE `cadeira` DISABLE KEYS */;
INSERT INTO `cadeira` VALUES (1),(2),(3),(4),(5);
/*!40000 ALTER TABLE `cadeira` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `email` varchar(55) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `sobrenome` varchar(25) DEFAULT NULL,
  `data_nascimento` date NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `endereco` varchar(45) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('lucas@gmail.com','Lucas','Santos','2000-10-31','11995098635','Rua Dois'),('rodrigo@gmail.com','Rodrigo','Ferreira','1990-05-11','11995098635','Rua Três'),('yuri@gmail.com','Yuri','Mathaus','1998-10-31','11995098635','Rua Um'),('yurimcff@gmail.com','Yuri Mathaus','Ferreira','1998-10-31','11995098635','Rua Três');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filme`
--

DROP TABLE IF EXISTS `filme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filme` (
  `id_filme` int NOT NULL,
  `titulo_filme` varchar(45) NOT NULL,
  PRIMARY KEY (`id_filme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filme`
--

LOCK TABLES `filme` WRITE;
/*!40000 ALTER TABLE `filme` DISABLE KEYS */;
INSERT INTO `filme` VALUES (1,'o alto da Compadecida'),(2,'A Baleia'),(3,'Aftersun '),(4,'Campeões'),(5,'O Esquadrão Suicida');
/*!40000 ALTER TABLE `filme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresso`
--

DROP TABLE IF EXISTS `ingresso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresso` (
  `id_ingresso` int NOT NULL AUTO_INCREMENT,
  `quantidade` int NOT NULL,
  `valor_unitario` decimal(5,2) NOT NULL,
  `valor_total` decimal(5,2) NOT NULL,
  `data_compra` datetime NOT NULL,
  `email_cliente` varchar(55) NOT NULL,
  `id_sessao` int NOT NULL,
  PRIMARY KEY (`id_ingresso`),
  KEY `fk_Ingressos_Clientes_idx` (`email_cliente`),
  KEY `fk_Ingressos_Sessoes1_idx` (`id_sessao`),
  CONSTRAINT `fk_Ingressos_Clientes` FOREIGN KEY (`email_cliente`) REFERENCES `cliente` (`email`),
  CONSTRAINT `fk_Ingressos_Sessoes1` FOREIGN KEY (`id_sessao`) REFERENCES `sessao` (`id_sessao`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresso`
--

LOCK TABLES `ingresso` WRITE;
/*!40000 ALTER TABLE `ingresso` DISABLE KEYS */;
INSERT INTO `ingresso` VALUES (1,2,23.90,47.80,'2023-06-06 00:00:00','yuri@gmail.com',4),(2,2,23.90,47.80,'2023-06-09 00:00:00','yurimcff@gmail.com',4);
/*!40000 ALTER TABLE `ingresso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `id_sala` varchar(5) NOT NULL,
  `legendado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_sala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES ('sala1',1),('sala2',1),('sala3',1),('sala4',0),('sala5',0),('sala6',0);
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salacadeira`
--

DROP TABLE IF EXISTS `salacadeira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salacadeira` (
  `id_sala` varchar(5) NOT NULL,
  `id_cadeira` int NOT NULL,
  `ocupado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_sala`,`id_cadeira`),
  KEY `fk_Sala_has_Cadeira_Cadeira1_idx` (`id_cadeira`),
  KEY `fk_Sala_has_Cadeira_Sala1_idx` (`id_sala`),
  CONSTRAINT `fk_Sala_has_Cadeira_Cadeira1` FOREIGN KEY (`id_cadeira`) REFERENCES `cadeira` (`id_cadeira`),
  CONSTRAINT `fk_Sala_has_Cadeira_Sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salacadeira`
--

LOCK TABLES `salacadeira` WRITE;
/*!40000 ALTER TABLE `salacadeira` DISABLE KEYS */;
INSERT INTO `salacadeira` VALUES ('sala1',1,1),('sala1',2,1),('sala1',3,1),('sala1',4,1),('sala1',5,0),('sala2',1,0),('sala2',2,0),('sala2',3,0),('sala2',4,0),('sala2',5,0),('sala3',1,0),('sala3',2,0),('sala3',3,0),('sala3',4,0),('sala3',5,0),('sala4',1,0),('sala4',2,0),('sala4',3,0),('sala4',4,0),('sala4',5,0),('sala5',1,0),('sala5',2,0),('sala5',3,0),('sala5',4,0),('sala5',5,0),('sala6',1,0),('sala6',2,0),('sala6',3,0),('sala6',4,0),('sala6',5,0);
/*!40000 ALTER TABLE `salacadeira` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessao`
--

DROP TABLE IF EXISTS `sessao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sessao` (
  `id_sessao` int NOT NULL AUTO_INCREMENT,
  `data_sessao` datetime NOT NULL,
  `id_filme` int NOT NULL,
  `id_sala` varchar(5) NOT NULL,
  PRIMARY KEY (`id_sessao`),
  KEY `fk_Sessoes_Filmes1_idx` (`id_filme`),
  KEY `fk_Sessao_Sala1_idx` (`id_sala`),
  CONSTRAINT `fk_Sessao_Sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`),
  CONSTRAINT `fk_Sessoes_Filmes1` FOREIGN KEY (`id_filme`) REFERENCES `filme` (`id_filme`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessao`
--

LOCK TABLES `sessao` WRITE;
/*!40000 ALTER TABLE `sessao` DISABLE KEYS */;
INSERT INTO `sessao` VALUES (4,'2023-05-20 00:00:00',1,'sala1');
/*!40000 ALTER TABLE `sessao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-10 17:13:30