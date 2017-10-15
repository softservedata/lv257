-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydatabase
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `abstract_owner`
--

DROP TABLE IF EXISTS `abstract_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abstract_owner` (
  `owner_id` int(11) NOT NULL,
  `owner_type` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`owner_id`),
  KEY `FKtomgjp4khpsnb539uxy1tv2kg` (`address_id`),
  CONSTRAINT `FKtomgjp4khpsnb539uxy1tv2kg` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abstract_owner`
--

LOCK TABLES `abstract_owner` WRITE;
/*!40000 ALTER TABLE `abstract_owner` DISABLE KEYS */;
/*!40000 ALTER TABLE `abstract_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apartment` int(11) NOT NULL,
  `block` varchar(255) DEFAULT NULL,
  `building` int(11) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `locality` varchar(255) DEFAULT NULL,
  `postal_index` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `id` int(11) NOT NULL,
  `id_Address` int(11) DEFAULT NULL,
  `Model` varchar(45) DEFAULT NULL,
  `Year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,1,'Mazda',2005),(2,2,'Volvo',2000),(3,3,'Mazda',2006),(4,4,'Mazda',2005);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_owner`
--

DROP TABLE IF EXISTS `company_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_owner` (
  `CEO` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `organization_form` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`owner_id`),
  CONSTRAINT `FKc969h1i7kesjuj6cvkxh21ssp` FOREIGN KEY (`owner_id`) REFERENCES `abstract_owner` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_owner`
--

LOCK TABLES `company_owner` WRITE;
/*!40000 ALTER TABLE `company_owner` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (9),(9),(9),(9),(9);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwordresettoken`
--

DROP TABLE IF EXISTS `passwordresettoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passwordresettoken` (
  `id` bigint(20) NOT NULL,
  `expiryDate` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgdew0adk8xruaoq2rgdsy34w2` (`user_id`),
  CONSTRAINT `FKgdew0adk8xruaoq2rgdsy34w2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwordresettoken`
--

LOCK TABLES `passwordresettoken` WRITE;
/*!40000 ALTER TABLE `passwordresettoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `passwordresettoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_owner`
--

DROP TABLE IF EXISTS `person_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_owner` (
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `passport_number` varchar(255) DEFAULT NULL,
  `passport_series` varchar(255) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`owner_id`),
  CONSTRAINT `FKdqwd3mwi2ecf2howc14hibcpo` FOREIGN KEY (`owner_id`) REFERENCES `abstract_owner` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_owner`
--

LOCK TABLES `person_owner` WRITE;
/*!40000 ALTER TABLE `person_owner` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (1,'READ_PRIVILEGE'),(2,'WRITE_PRIVILEGE'),(3,'CHANGE_PASSWORD_PRIVILEGE'),(4,'ADD_RESOURCE'),(5,'ASSIGN_ROLE_TO_USER'),(6,'CREATE_ROLE'),(7,'DELETE_RESOURCE'),(8,'DELETE_USER'),(9,'CREATE_ROLE');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requestresourcetype`
--

DROP TABLE IF EXISTS `requestresourcetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requestresourcetype` (
  `id_request` bigint(20) NOT NULL AUTO_INCREMENT,
  `details` varchar(255) NOT NULL,
  `notify_executor` bit(1) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `theme` varchar(255) NOT NULL,
  `updated` datetime DEFAULT NULL,
  `id_document` bigint(20) DEFAULT NULL,
  `id_from` bigint(20) DEFAULT NULL,
  `id_executor_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_request`),
  KEY `FK23xfawv41bafwjs4x0df7f9td` (`id_document`),
  KEY `FK3wcxqt54ny778c9sv14sjm15e` (`id_from`),
  KEY `FKbnyyabot2xfdtu84c74k3dbpi` (`id_executor_user`),
  CONSTRAINT `FK23xfawv41bafwjs4x0df7f9td` FOREIGN KEY (`id_document`) REFERENCES `document` (`id`),
  CONSTRAINT `FK3wcxqt54ny778c9sv14sjm15e` FOREIGN KEY (`id_from`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FKbnyyabot2xfdtu84c74k3dbpi` FOREIGN KEY (`id_executor_user`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requestresourcetype`
--

LOCK TABLES `requestresourcetype` WRITE;
/*!40000 ALTER TABLE `requestresourcetype` DISABLE KEYS */;
/*!40000 ALTER TABLE `requestresourcetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_categories`
--

DROP TABLE IF EXISTS `resource_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_categories` (
  `Id` bigint(20) NOT NULL,
  `Category_Name` varchar(255) DEFAULT NULL,
  `Hierarchy_Level` int(11) DEFAULT NULL,
  `Path_To_Root` varchar(255) DEFAULT NULL,
  `Id_Parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKbj18dq5luxuahtrdl7m9id2k5` (`Id_Parent`),
  CONSTRAINT `FKbj18dq5luxuahtrdl7m9id2k5` FOREIGN KEY (`Id_Parent`) REFERENCES `resource_categories` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_categories`
--

LOCK TABLES `resource_categories` WRITE;
/*!40000 ALTER TABLE `resource_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_properties`
--

DROP TABLE IF EXISTS `resource_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_properties` (
  `id` bigint(20) NOT NULL,
  `Allows_Null` bit(1) DEFAULT NULL,
  `Column_Name` varchar(255) DEFAULT NULL,
  `Essential` bit(1) DEFAULT NULL,
  `Multivalued` bit(1) DEFAULT NULL,
  `Regex` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Units` varchar(255) DEFAULT NULL,
  `Value_Type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_properties`
--

LOCK TABLES `resource_properties` WRITE;
/*!40000 ALTER TABLE `resource_properties` DISABLE KEYS */;
INSERT INTO `resource_properties` VALUES (1,'','Model','','\0','[a-zA-Z0-9]+',NULL,NULL,'STRING'),(2,'','Year','','\0','[0-9]+',NULL,NULL,'INTEGER');
/*!40000 ALTER TABLE `resource_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_request`
--

DROP TABLE IF EXISTS `resource_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_request` (
  `id_request` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `details` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `theme` varchar(255) NOT NULL,
  `updated` datetime DEFAULT NULL,
  `id_from` bigint(20) DEFAULT NULL,
  `id_executor_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_request`),
  KEY `FKop05b6nq681po86jigytdbjuf` (`id_from`),
  KEY `FK138diif1ovxmylyoy0h0qjkw0` (`id_executor_user`),
  CONSTRAINT `FK138diif1ovxmylyoy0h0qjkw0` FOREIGN KEY (`id_executor_user`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FKop05b6nq681po86jigytdbjuf` FOREIGN KEY (`id_from`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_request`
--

LOCK TABLES `resource_request` WRITE;
/*!40000 ALTER TABLE `resource_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_types`
--

DROP TABLE IF EXISTS `resource_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_types` (
  `id` bigint(20) NOT NULL,
  `Instantiated` bit(1) DEFAULT NULL,
  `Table_Name` varchar(255) NOT NULL,
  `Type_Name` varchar(255) NOT NULL,
  `Id_Category` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2ntr139k7xdpo39ojouhiushv` (`Table_Name`),
  UNIQUE KEY `UK_b7t94xp2gr35lfkeshakdlaau` (`Type_Name`),
  KEY `FKha9m3tb027yqv8ogdf9rn36t3` (`Id_Category`),
  CONSTRAINT `FKha9m3tb027yqv8ogdf9rn36t3` FOREIGN KEY (`Id_Category`) REFERENCES `resource_categories` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_types`
--

LOCK TABLES `resource_types` WRITE;
/*!40000 ALTER TABLE `resource_types` DISABLE KEYS */;
INSERT INTO `resource_types` VALUES (1,'','cars','Cars',NULL);
/*!40000 ALTER TABLE `resource_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_types_properties`
--

DROP TABLE IF EXISTS `resource_types_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_types_properties` (
  `Resource_Type_Id` bigint(20) NOT NULL,
  `Property_Id` bigint(20) NOT NULL,
  PRIMARY KEY (`Resource_Type_Id`,`Property_Id`),
  KEY `FK819spt14xwp0k447igk6dv22h` (`Property_Id`),
  CONSTRAINT `FK1b5333ua22p8x4eevp7i7th1y` FOREIGN KEY (`Resource_Type_Id`) REFERENCES `resource_types` (`id`),
  CONSTRAINT `FK819spt14xwp0k447igk6dv22h` FOREIGN KEY (`Property_Id`) REFERENCES `resource_properties` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_types_properties`
--

LOCK TABLES `resource_types_properties` WRITE;
/*!40000 ALTER TABLE `resource_types_properties` DISABLE KEYS */;
INSERT INTO `resource_types_properties` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `resource_types_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (4,'ROLE_ADMIN'),(5,'ROLE_USER'),(6,'ROLE_SYSTEM_ADMIN'),(7,'ROLE_SYSTEM_ADMIN_CUSTOM'),(8,'ROLE_RESOURCE_ADMIN'),(9,'ROLE_REGISTRATOR');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_privileges`
--

DROP TABLE IF EXISTS `roles_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_privileges` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  KEY `FKp0x1d9k5aksyqd1akwwfkh0ki` (`privilege_id`),
  KEY `FK2rfl694fu6ls2f2mqcxesqc6p` (`role_id`),
  CONSTRAINT `FK2rfl694fu6ls2f2mqcxesqc6p` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKp0x1d9k5aksyqd1akwwfkh0ki` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_privileges`
--

LOCK TABLES `roles_privileges` WRITE;
/*!40000 ALTER TABLE `roles_privileges` DISABLE KEYS */;
INSERT INTO `roles_privileges` VALUES (4,1),(4,2),(4,3),(5,1),(5,3);
/*!40000 ALTER TABLE `roles_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `isUsing2FA` bit(1) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_role_idx` (`role_id`),
  CONSTRAINT `FK_fiywq2x4mg0ht0oq58ihdshpe` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKflwilhmbxmvvn9yvrod5extav` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (6,'test@test.com','','Test','\0','Test','$2a$11$hRwuexFC6bRcvkn0L6er/u9bgcAnct5tTpKqPkEuwI2/VjlwG3Oty','HL6P4ZDO77CV5S3C',4),(7,'test@gmail.com','\0','p','\0','g','$2a$11$57KeR/jA2s9CoSZ7Rj5gYeKjjbjrZbUJIv7DWdzfixim8caGvrDse','JTBQE336UW3GST6O',4),(77,'dbuser1','','db','\0','user','12345',NULL,5);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `id` bigint(20) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `date_of_issue` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `id_address` int(11) NOT NULL,
  `issued_by` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `passport_number` varchar(255) DEFAULT NULL,
  `passport_series` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `second_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKa9r8g5hiyy57ts5u4tkf0lbab` (`role_id`),
  KEY `FKci4mdvg1fmo9eqmwno1y9o0fa` (`user_id`),
  CONSTRAINT `FKa9r8g5hiyy57ts5u4tkf0lbab` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKci4mdvg1fmo9eqmwno1y9o0fa` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (6,4),(7,5),(77,4);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verificationtoken`
--

DROP TABLE IF EXISTS `verificationtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verificationtoken` (
  `id` bigint(20) NOT NULL,
  `expiryDate` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VERIFY_USER` (`user_id`),
  CONSTRAINT `FK_VERIFY_USER` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationtoken`
--

LOCK TABLES `verificationtoken` WRITE;
/*!40000 ALTER TABLE `verificationtoken` DISABLE KEYS */;
INSERT INTO `verificationtoken` VALUES (8,'2017-08-26 01:52:41','03809b7f-f618-4f7d-96a1-8d00a4164a36',7);
/*!40000 ALTER TABLE `verificationtoken` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-19 15:11:28
