-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: gerenciamento_alugueis
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `contrato_locacao`
--

DROP TABLE IF EXISTS `contrato_locacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contrato_locacao` (
  `idcontrato` int NOT NULL AUTO_INCREMENT,
  `data_inicio` date NOT NULL,
  `data_termino` date NOT NULL,
  `data_devolucao` date NOT NULL,
  `valor_aluguel` double NOT NULL,
  `multas` tinyint NOT NULL,
  `idimovel` int NOT NULL,
  `idproprietario` int NOT NULL,
  `idinquilino` int NOT NULL,
  PRIMARY KEY (`idcontrato`),
  KEY `idimovel` (`idimovel`),
  KEY `idproprietario` (`idproprietario`),
  KEY `idinquilino` (`idinquilino`),
  CONSTRAINT `contrato_locacao_ibfk_1` FOREIGN KEY (`idimovel`) REFERENCES `imovel` (`idimovel`),
  CONSTRAINT `contrato_locacao_ibfk_2` FOREIGN KEY (`idproprietario`) REFERENCES `proprietario` (`idproprietario`),
  CONSTRAINT `contrato_locacao_ibfk_3` FOREIGN KEY (`idinquilino`) REFERENCES `inquilino` (`idinquilino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato_locacao`
--

LOCK TABLES `contrato_locacao` WRITE;
/*!40000 ALTER TABLE `contrato_locacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato_locacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imovel`
--

DROP TABLE IF EXISTS `imovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imovel` (
  `idimovel` int NOT NULL AUTO_INCREMENT,
  `endereco` varchar(200) NOT NULL,
  `valor_aluguel` double NOT NULL,
  `tipo` enum('RESIDENTIAL','COMMERCIAL') NOT NULL,
  `status` enum('OCCUPIED','UNOCCUPIED') NOT NULL,
  `numero_quartos` int NOT NULL,
  `cpf_proprietario` varchar(14) NOT NULL,
  `idinquilino` int NOT NULL,
  PRIMARY KEY (`idimovel`),
  UNIQUE KEY `cpf_proprietario_UNIQUE` (`cpf_proprietario`),
  KEY `idinquilino` (`idinquilino`),
  CONSTRAINT `imovel_ibfk_1` FOREIGN KEY (`cpf_proprietario`) REFERENCES `proprietario` (`cpf`),
  CONSTRAINT `imovel_ibfk_2` FOREIGN KEY (`idinquilino`) REFERENCES `inquilino` (`idinquilino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imovel`
--

LOCK TABLES `imovel` WRITE;
/*!40000 ALTER TABLE `imovel` DISABLE KEYS */;
/*!40000 ALTER TABLE `imovel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imovel_comercial`
--

DROP TABLE IF EXISTS `imovel_comercial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imovel_comercial` (
  `idimovel` int NOT NULL AUTO_INCREMENT,
  `tipo_negocio` enum('FOOD','HEALTH','AUTOMOTIVESERVICES','FASHION','EDUCATION') NOT NULL,
  PRIMARY KEY (`idimovel`),
  CONSTRAINT `imovel_comercial_ibfk_1` FOREIGN KEY (`idimovel`) REFERENCES `imovel` (`idimovel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imovel_comercial`
--

LOCK TABLES `imovel_comercial` WRITE;
/*!40000 ALTER TABLE `imovel_comercial` DISABLE KEYS */;
/*!40000 ALTER TABLE `imovel_comercial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imovel_residencial`
--

DROP TABLE IF EXISTS `imovel_residencial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imovel_residencial` (
  `idimovel` int NOT NULL AUTO_INCREMENT,
  `area_lazer` tinyint NOT NULL,
  PRIMARY KEY (`idimovel`),
  CONSTRAINT `imovel_residencial_ibfk_1` FOREIGN KEY (`idimovel`) REFERENCES `imovel` (`idimovel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imovel_residencial`
--

LOCK TABLES `imovel_residencial` WRITE;
/*!40000 ALTER TABLE `imovel_residencial` DISABLE KEYS */;
/*!40000 ALTER TABLE `imovel_residencial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquilino`
--

DROP TABLE IF EXISTS `inquilino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquilino` (
  `idinquilino` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  `saldo` double NOT NULL,
  PRIMARY KEY (`idinquilino`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  CONSTRAINT `cpf` FOREIGN KEY (`cpf`) REFERENCES `pessoa` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquilino`
--

LOCK TABLES `inquilino` WRITE;
/*!40000 ALTER TABLE `inquilino` DISABLE KEYS */;
INSERT INTO `inquilino` VALUES (1,'GURU','123.654.789-44','guru@gmail.com',1235);
/*!40000 ALTER TABLE `inquilino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `idpagamento` int NOT NULL AUTO_INCREMENT,
  `data_pagamento` date NOT NULL,
  `valor_pagamento` double NOT NULL,
  `status` enum('PENDING','PAID') NOT NULL,
  `metodo_pagamento` enum('CREDITCARD','BANKSLIP','CASH') NOT NULL,
  `idcontrato` int NOT NULL,
  PRIMARY KEY (`idpagamento`),
  KEY `idcontrato` (`idcontrato`),
  CONSTRAINT `pagamento_ibfk_1` FOREIGN KEY (`idcontrato`) REFERENCES `contrato_locacao` (`idcontrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `idpessoa` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(200) NOT NULL,
  `saldo` double DEFAULT NULL,
  `cargo` enum('TENANT','LANDLORD') NOT NULL,
  PRIMARY KEY (`idpessoa`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'GURU','123.654.789-44','guru@gmail.com',1235,'TENANT');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprietario`
--

DROP TABLE IF EXISTS `proprietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proprietario` (
  `idproprietario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`idproprietario`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  CONSTRAINT `proprietario_ibfk_1` FOREIGN KEY (`cpf`) REFERENCES `pessoa` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprietario`
--

LOCK TABLES `proprietario` WRITE;
/*!40000 ALTER TABLE `proprietario` DISABLE KEYS */;
/*!40000 ALTER TABLE `proprietario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone_pessoa`
--

DROP TABLE IF EXISTS `telefone_pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefone_pessoa` (
  `idtelefone_pessoa` int NOT NULL AUTO_INCREMENT,
  `idpessoa` int NOT NULL,
  `primeiro_telefone` varchar(20) NOT NULL,
  `segundo_telefone` varchar(20) NOT NULL,
  PRIMARY KEY (`idtelefone_pessoa`),
  UNIQUE KEY `primeiro_telefone_UNIQUE` (`primeiro_telefone`),
  KEY `idpessoa` (`idpessoa`),
  CONSTRAINT `telefone_pessoa_ibfk_1` FOREIGN KEY (`idpessoa`) REFERENCES `pessoa` (`idpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone_pessoa`
--

LOCK TABLES `telefone_pessoa` WRITE;
/*!40000 ALTER TABLE `telefone_pessoa` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefone_pessoa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-25 16:04:24
