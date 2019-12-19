-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: networkstore
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `logging`
--

DROP TABLE IF EXISTS `logging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logging` (
  `idLogging` int(11) NOT NULL AUTO_INCREMENT,
  `Log_Text` varchar(255) NOT NULL,
  `Log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`idLogging`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging`
--

LOCK TABLES `logging` WRITE;
/*!40000 ALTER TABLE `logging` DISABLE KEYS */;
INSERT INTO `logging` VALUES (2,'Added user, username=1','2019-12-17 21:22:44'),(3,'Added supplier, name=test','2019-12-18 13:22:43'),(4,'Added shop, name=mk','2019-12-18 13:33:12'),(5,'Added supply, supplyid=11','2019-12-18 16:47:55'),(6,'Added order, orderid=6','2019-12-18 16:47:55'),(7,'Added product, product=3 , orderid=6','2019-12-18 16:47:55'),(8,'Added shop, name=SSTU','2019-12-18 16:52:37'),(9,'Added supplier, name=SSTU-company','2019-12-18 16:53:53'),(10,'Added product name=tomato','2019-12-18 17:06:52'),(11,'Added shop, name=','2019-12-18 17:47:09'),(12,'Added user, username=vit','2019-12-18 18:30:30'),(13,'Added supply, supplyid=12','2019-12-18 23:08:12'),(14,'Added order, orderid=7','2019-12-18 23:08:12'),(15,'Added product, product=3 , orderid=7','2019-12-18 23:08:12'),(16,'Started suppling, supplyid=11','2019-12-19 08:12:15'),(17,'Added supplierproducts sellerid=2, productid=6, price=87','2019-12-19 10:15:51'),(18,'Started suppling, supplyid=12','2019-12-19 10:16:04'),(19,'Added supply, supplyid=13','2019-12-19 10:16:59'),(20,'Added order, orderid=8','2019-12-19 10:16:59'),(21,'Added product, product=6 , orderid=8','2019-12-19 10:16:59'),(22,'Ended suppling, supplyid=12','2019-12-19 10:17:20'),(23,'Ended suppling, supplyid=11','2019-12-19 10:17:32'),(24,'Added supply, supplyid=14','2019-12-19 10:19:34'),(25,'Added order, orderid=9','2019-12-19 10:19:34'),(26,'Added product, product=3 , orderid=9','2019-12-19 10:19:34'),(27,'Added product, product=6 , orderid=9','2019-12-19 10:19:34'),(28,'Added shop, name=ss','2019-12-19 10:41:10'),(29,'Added supplier, name=rgr','2019-12-19 10:50:05');
/*!40000 ALTER TABLE `logging` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `Placement_Date` date DEFAULT NULL,
  `Order_Text` text,
  `idShop` int(11) DEFAULT NULL,
  `idSupply` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `idShop_idx` (`idShop`),
  KEY `idSupply_idx` (`idSupply`),
  CONSTRAINT `idShop` FOREIGN KEY (`idShop`) REFERENCES `shop` (`idShop`),
  CONSTRAINT `idSupply` FOREIGN KEY (`idSupply`) REFERENCES `supply` (`idSupply`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (6,'2019-12-18','test',2,11),(7,'2019-12-18','test',2,12),(8,'2019-12-19','',2,13),(9,'2019-12-19','',2,14);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `idProduct` int(11) NOT NULL AUTO_INCREMENT,
  `Product_Name` varchar(45) DEFAULT NULL,
  `Product_Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'Банан','желтый'),(4,'tomato','red'),(5,'potato','potato'),(6,'tomato','red');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productlist`
--

DROP TABLE IF EXISTS `productlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productlist` (
  `Order_idOrder` int(11) NOT NULL,
  `Product_idProduct` int(11) NOT NULL,
  PRIMARY KEY (`Order_idOrder`,`Product_idProduct`),
  KEY `fk_Order_has_Product_Product1_idx` (`Product_idProduct`),
  KEY `fk_Order_has_Product_Order1_idx` (`Order_idOrder`),
  CONSTRAINT `fk_Order_has_Product_Order1` FOREIGN KEY (`Order_idOrder`) REFERENCES `order` (`idOrder`),
  CONSTRAINT `fk_Order_has_Product_Product1` FOREIGN KEY (`Product_idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productlist`
--

LOCK TABLES `productlist` WRITE;
/*!40000 ALTER TABLE `productlist` DISABLE KEYS */;
INSERT INTO `productlist` VALUES (6,3),(7,3),(9,3),(8,6),(9,6);
/*!40000 ALTER TABLE `productlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `Role_Name` varchar(45) DEFAULT NULL,
  `User_idUser` int(11) NOT NULL,
  PRIMARY KEY (`idRole`),
  KEY `fk_Role_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_Role_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (5,'ROLE_ADMIN',1),(9,'ROLE_USER',9),(10,'ROLE_USER',11);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `idShop` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `User_idUser` int(11) NOT NULL,
  PRIMARY KEY (`idShop`),
  UNIQUE KEY `Address_UNIQUE` (`Address`),
  UNIQUE KEY `Name_UNIQUE` (`Name`),
  UNIQUE KEY `Phone_UNIQUE` (`Phone`),
  KEY `fk_Shop_User_idx` (`User_idUser`),
  CONSTRAINT `fk_Shop_User` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (2,'mk','mk','mk',1),(3,'SSTU','street','99',1),(12,'ss','ss','ss',1);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `idSupplier` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `User_idUser` int(11) NOT NULL,
  PRIMARY KEY (`idSupplier`),
  UNIQUE KEY `Name_UNIQUE` (`Name`),
  UNIQUE KEY `Address_UNIQUE` (`Address`),
  UNIQUE KEY `Phone_UNIQUE` (`Phone`),
  KEY `fk_Supplier_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_Supplier_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (2,'test','test','test',1),(3,'SSTU-company','saratov','77',1),(5,'rgr','grg','rg',1);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplierproducts`
--

DROP TABLE IF EXISTS `supplierproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplierproducts` (
  `idSupplier` int(11) NOT NULL,
  `idProduct` int(11) DEFAULT NULL,
  `price` double NOT NULL,
  KEY `idSupplier_idx` (`idSupplier`),
  KEY `idProduct_idx` (`idProduct`),
  CONSTRAINT `fk_Product` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`),
  CONSTRAINT `fk_Supplier` FOREIGN KEY (`idSupplier`) REFERENCES `supplier` (`idSupplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplierproducts`
--

LOCK TABLES `supplierproducts` WRITE;
/*!40000 ALTER TABLE `supplierproducts` DISABLE KEYS */;
INSERT INTO `supplierproducts` VALUES (2,3,10),(2,6,87);
/*!40000 ALTER TABLE `supplierproducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply`
--

DROP TABLE IF EXISTS `supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supply` (
  `idSupply` int(11) NOT NULL AUTO_INCREMENT,
  `Data_Start` date DEFAULT NULL,
  `Data_End` date DEFAULT NULL,
  `idSupplier` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSupply`),
  KEY `idSupplier_idx` (`idSupplier`),
  CONSTRAINT `idSupplier` FOREIGN KEY (`idSupplier`) REFERENCES `supplier` (`idSupplier`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply`
--

LOCK TABLES `supply` WRITE;
/*!40000 ALTER TABLE `supply` DISABLE KEYS */;
INSERT INTO `supply` VALUES (11,'2019-12-19','2019-12-19',2),(12,'2019-12-19','2019-12-19',2),(13,NULL,NULL,2),(14,NULL,NULL,2);
/*!40000 ALTER TABLE `supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','seliverstov_vitaly@mail.ru','202cb962ac59075b964b07152d234b70',1),(9,'1','1@mail.ru','c4ca4238a0b923820dcc509a6f75849b',1),(11,'vit','vitashe1998@mail.ru','a1a1c4dbebfbcb0569e5142c2f9c8de2',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-19 18:25:56
