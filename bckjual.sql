-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for osx10.10 (x86_64)
--
-- Host: localhost    Database: penjualan
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `barang`
--

DROP TABLE IF EXISTS `barang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang` (
  `kd_brg` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nm_brg` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `satuan` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `harga` double NOT NULL,
  `stok` int(5) DEFAULT NULL,
  `stok_min` int(4) NOT NULL,
  PRIMARY KEY (`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang`
--

LOCK TABLES `barang` WRITE;
/*!40000 ALTER TABLE `barang` DISABLE KEYS */;
INSERT INTO `barang` VALUES ('01','Boneka','Buah',1000000,592,10),('02','Asus TUF','Unit',15000000,399,10),('03','Komik Boruto','Pcs',25000000,1007,13),('04','Genesis Krystal','Buah',2500000,1341,20),('05','buku','Lusin',15000,200,20);
/*!40000 ALTER TABLE `barang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beli`
--

DROP TABLE IF EXISTS `beli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beli` (
  `no_beli` int(10) NOT NULL,
  `kd_pms` char(6) DEFAULT NULL,
  `tgl_beli` date DEFAULT NULL,
  PRIMARY KEY (`no_beli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beli`
--

LOCK TABLES `beli` WRITE;
/*!40000 ALTER TABLE `beli` DISABLE KEYS */;
INSERT INTO `beli` VALUES (1,'02','2023-07-01'),(2,'03','2023-07-02'),(3,'01','2023-07-02'),(4,'03','2023-07-02');
/*!40000 ALTER TABLE `beli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbeli`
--

DROP TABLE IF EXISTS `dbeli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbeli` (
  `no_beli` int(10) NOT NULL,
  `kd_brg` char(6) NOT NULL,
  `harga_beli` float DEFAULT NULL,
  `jml_beli` int(4) DEFAULT NULL,
  PRIMARY KEY (`no_beli`,`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbeli`
--

LOCK TABLES `dbeli` WRITE;
/*!40000 ALTER TABLE `dbeli` DISABLE KEYS */;
INSERT INTO `dbeli` VALUES (1,'01',1000000,2),(2,'02',15000000,2),(2,'03',25000000,7),(2,'04',2500000,5),(2,'06',650000,4),(3,'05',15000,8),(4,'05',15000,7);
/*!40000 ALTER TABLE `dbeli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `djual`
--

DROP TABLE IF EXISTS `djual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `djual` (
  `no_jual` int(10) NOT NULL,
  `kd_brg` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `harga_jual` float DEFAULT NULL,
  `jml_jual` int(4) DEFAULT NULL,
  PRIMARY KEY (`no_jual`,`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `djual`
--

LOCK TABLES `djual` WRITE;
/*!40000 ALTER TABLE `djual` DISABLE KEYS */;
INSERT INTO `djual` VALUES (8,'02',15000000,2),(8,'03',25000000,2),(8,'04',2500000,3),(8,'06',650000,1),(9,'01',1000000,1),(9,'02',15000000,2),(9,'03',25000000,2),(9,'04',2500000,3),(9,'06',650000,1),(10,'01',1000000,5),(10,'02',15000000,2),(10,'03',25000000,3),(10,'04',2500000,4),(11,'01',1000000,5),(12,'01',1000000,5),(12,'02',15000000,3),(12,'05',15000,3),(13,'05',15000,7);
/*!40000 ALTER TABLE `djual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jual`
--

DROP TABLE IF EXISTS `jual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jual` (
  `no_jual` int(10) NOT NULL,
  `kd_kons` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tgl_jual` date DEFAULT NULL,
  PRIMARY KEY (`no_jual`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jual`
--

LOCK TABLES `jual` WRITE;
/*!40000 ALTER TABLE `jual` DISABLE KEYS */;
INSERT INTO `jual` VALUES (8,'06','2023-06-16'),(9,'03','2023-06-16'),(10,'04','2023-06-23'),(11,'02','2023-07-01'),(12,'04','2023-07-02'),(13,'04','2023-07-02');
/*!40000 ALTER TABLE `jual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `konsumen`
--

DROP TABLE IF EXISTS `konsumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `konsumen` (
  `kd_kons` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nm_kons` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `alm_kons` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `kota_kons` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `kd_pos` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`kd_kons`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `konsumen`
--

LOCK TABLES `konsumen` WRITE;
/*!40000 ALTER TABLE `konsumen` DISABLE KEYS */;
INSERT INTO `konsumen` VALUES ('01','Fidela Azzahra','Sri Rejeki Selatan 8','Semarang','50149','8262','fidela@gmail.com'),('03','Qanitah','Sri Rejeki Selatan','Semarang','50149','0004','qani@gmail.com'),('04','Qani','Sri Rejeki','Semarang','50149','4000','qani@gmail.com');
/*!40000 ALTER TABLE `konsumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pemasok`
--

DROP TABLE IF EXISTS `pemasok`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pemasok` (
  `kd_pms` varchar(6) NOT NULL,
  `nm_pms` varchar(30) DEFAULT NULL,
  `nm_brg` varchar(30) DEFAULT NULL,
  `kota_pms` varchar(20) DEFAULT NULL,
  `kd_pos` varchar(5) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`kd_pms`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pemasok`
--

LOCK TABLES `pemasok` WRITE;
/*!40000 ALTER TABLE `pemasok` DISABLE KEYS */;
INSERT INTO `pemasok` VALUES ('01','Thoma','Genesis Krystal','Inazuma City','42626','8765'),('02','Sara','Boneka Shogun','Inazuma City','42626','8765'),('03','Yae Miko','Boneka','Inazuma City','42626','9999'),('04','Diluc','Asus TUF','Monsdstat','00001','2020');
/*!40000 ALTER TABLE `pemasok` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(10) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'12345','alcina','15fc33b24e2d528ffc5603547b9e6d27'),(2,'12345','fidel','fa1776fe544c44fad1cf2bec71a14464'),(3,'12345','admin','21232f297a57a5a743894a0e4a801fc3');
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

-- Dump completed on 2023-07-02 19:57:34
