CREATE DATABASE  IF NOT EXISTS `dogbogam` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `dogbogam`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: j11b101.p.ssafy.io    Database: dogbogam
-- ------------------------------------------------------
-- Server version	5.7.44

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
-- Table structure for table `ai_diagnosis`
--

DROP TABLE IF EXISTS `ai_diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ai_diagnosis` (
  `normal` bit(1) NOT NULL,
  `ai_diagnosis_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `dog_id` bigint(20) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `diagnosis_item` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `image_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`ai_diagnosis_id`),
  KEY `FKmi9ibp53bco7jrfwg9gv22o0c` (`dog_id`),
  CONSTRAINT `FKmi9ibp53bco7jrfwg9gv22o0c` FOREIGN KEY (`dog_id`) REFERENCES `dog` (`dog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ai_diagnosis`
--

LOCK TABLES `ai_diagnosis` WRITE;
/*!40000 ALTER TABLE `ai_diagnosis` DISABLE KEYS */;
INSERT INTO `ai_diagnosis` VALUES (_binary '',1,'2024-10-10 16:42:16.757344',1,'2024-10-10 16:42:16.757344','눈','eye/02dc6a6d-9343-41d0-b34d-379a3c000fb9_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/02dc6a6d-9343-41d0-b34d-379a3c000fb9_photo.jpg'),(_binary '',2,'2024-10-10 16:42:56.511096',1,'2024-10-10 16:42:56.511096','눈','eye/1be2e0bc-45d3-4cbc-9917-7ab5fef0e5d9_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/1be2e0bc-45d3-4cbc-9917-7ab5fef0e5d9_photo.jpg'),(_binary '',3,'2024-10-10 16:43:24.751160',1,'2024-10-10 16:43:24.751160','눈','eye/f7330651-5a9c-4e0e-be11-f214f6a07dd0_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/f7330651-5a9c-4e0e-be11-f214f6a07dd0_photo.jpg'),(_binary '\0',4,'2024-10-10 16:44:42.831395',1,'2024-10-10 16:44:42.831395','견종','breed/b1484566-bbdf-45f7-87d9-7a733ca5ca3a_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/breed/b1484566-bbdf-45f7-87d9-7a733ca5ca3a_photo.jpg'),(_binary '\0',5,'2024-10-10 16:46:01.473963',1,'2024-10-10 16:46:01.473963','견종','breed/51ed6b23-8efb-490a-a7f5-4c50d2e6bf00_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/breed/51ed6b23-8efb-490a-a7f5-4c50d2e6bf00_photo.jpg'),(_binary '\0',6,'2024-10-10 16:46:41.145893',1,'2024-10-10 16:46:41.145893','피부','skin/a82439d0-4454-4e22-b2ab-d9ae2a45a152_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/skin/a82439d0-4454-4e22-b2ab-d9ae2a45a152_photo.jpg'),(_binary '\0',18,'2024-10-10 23:29:14.219965',12,'2024-10-10 23:29:14.219965','비만','obesity/00cfcf01-79f9-41ae-90ce-e4b15e9bdd58_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/obesity/00cfcf01-79f9-41ae-90ce-e4b15e9bdd58_photo.jpg'),(_binary '\0',19,'2024-10-10 23:43:35.880824',2,'2024-10-10 23:43:35.880824','피부','skin/b70e410e-6001-48a6-a1a0-24096048a317_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/skin/b70e410e-6001-48a6-a1a0-24096048a317_photo.jpg'),(_binary '',20,'2024-10-10 23:44:22.280450',2,'2024-10-10 23:44:22.280450','눈','eye/b0845cf9-e157-429f-b6d3-e9ae21edce13_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/b0845cf9-e157-429f-b6d3-e9ae21edce13_photo.jpg'),(_binary '\0',21,'2024-10-10 23:46:28.366183',2,'2024-10-10 23:46:28.366183','비만','obesity/2fb99f2d-8aba-462b-bcdd-add110edc6b1_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/obesity/2fb99f2d-8aba-462b-bcdd-add110edc6b1_photo.jpg'),(_binary '\0',22,'2024-10-11 00:07:26.533379',2,'2024-10-11 00:07:26.533379','견종','breed/e45c34ea-f3fa-4f42-9433-0e9a75312fdc_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/breed/e45c34ea-f3fa-4f42-9433-0e9a75312fdc_photo.jpg'),(_binary '',23,'2024-10-11 01:45:24.747448',2,'2024-10-11 01:45:24.747448','피부','skin/dab3e4a9-70bd-42dd-a2fe-cb51d86c6be1_free-icon-loading-7855116.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/skin/dab3e4a9-70bd-42dd-a2fe-cb51d86c6be1_free-icon-loading-7855116.png'),(_binary '',24,'2024-10-11 02:35:04.347540',17,'2024-10-11 02:35:04.347540','눈','eye/b4f7fd89-89bc-4b99-b3e1-47d65c9a61dc_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/b4f7fd89-89bc-4b99-b3e1-47d65c9a61dc_photo.jpg'),(_binary '\0',25,'2024-10-11 02:38:18.826434',17,'2024-10-11 02:38:18.826434','견종','breed/bdd66d53-c460-43c6-9931-25ae8d9a6bd8_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/breed/bdd66d53-c460-43c6-9931-25ae8d9a6bd8_photo.jpg'),(_binary '\0',26,'2024-10-11 03:39:47.780906',18,'2024-10-11 03:39:47.780906','견종','breed/b00a883d-0438-41b7-adc4-8a72d6ac6143_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/breed/b00a883d-0438-41b7-adc4-8a72d6ac6143_photo.jpg'),(_binary '\0',27,'2024-10-11 03:43:39.701598',18,'2024-10-11 03:43:39.701598','눈','eye/987b1084-8012-444f-bb27-66b628c2a955_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/987b1084-8012-444f-bb27-66b628c2a955_photo.jpg'),(_binary '',28,'2024-10-11 03:43:59.388425',18,'2024-10-11 03:43:59.388425','피부','skin/ae37953b-30ef-440b-8a82-63eb0aa31b5b_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/skin/ae37953b-30ef-440b-8a82-63eb0aa31b5b_photo.jpg'),(_binary '\0',29,'2024-10-11 03:44:17.700861',18,'2024-10-11 03:44:17.700861','비만','obesity/f641a9a1-2e3f-4848-9a46-12317590f09b_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/obesity/f641a9a1-2e3f-4848-9a46-12317590f09b_photo.jpg'),(_binary '\0',30,'2024-10-11 03:46:38.667182',2,'2024-10-11 03:46:38.667182','견종','breed/6fcb5274-84f6-49f4-b063-2bb603a2606d_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/breed/6fcb5274-84f6-49f4-b063-2bb603a2606d_photo.jpg'),(_binary '',31,'2024-10-11 03:53:28.824504',2,'2024-10-11 03:53:28.824504','눈','eye/c20d8004-5f47-4f4d-8db0-6a251a85c870_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/c20d8004-5f47-4f4d-8db0-6a251a85c870_photo.jpg'),(_binary '\0',32,'2024-10-11 03:54:09.864387',2,'2024-10-11 03:54:09.864387','눈','eye/b4f15a95-b41f-4a15-be98-52dfcf445227_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/b4f15a95-b41f-4a15-be98-52dfcf445227_photo.jpg'),(_binary '\0',33,'2024-10-11 03:56:13.144080',2,'2024-10-11 03:56:13.144080','피부','skin/ee939693-2ceb-4837-bd03-c032a83fcf72_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/skin/ee939693-2ceb-4837-bd03-c032a83fcf72_photo.jpg'),(_binary '',34,'2024-10-11 04:11:15.305858',18,'2024-10-11 04:11:15.305858','눈','eye/53cc1609-5c9e-4e5d-9220-3829660e9c40_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/53cc1609-5c9e-4e5d-9220-3829660e9c40_photo.jpg'),(_binary '\0',35,'2024-10-11 04:11:51.625881',2,'2024-10-11 04:11:51.625881','눈','eye/08b7b06c-a4d6-4342-8d17-fdd661a48bea_photo.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/eye/08b7b06c-a4d6-4342-8d17-fdd661a48bea_photo.jpg');
/*!40000 ALTER TABLE `ai_diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ai_report_diseases`
--

DROP TABLE IF EXISTS `ai_report_diseases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ai_report_diseases` (
  `percentage` decimal(5,2) NOT NULL,
  `ai_diagnosis_id` bigint(20) NOT NULL,
  `ai_report_disease_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disease` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`ai_report_disease_id`),
  KEY `FKe40k4ueyuha6uk287b4p9a2oi` (`ai_diagnosis_id`),
  CONSTRAINT `FKe40k4ueyuha6uk287b4p9a2oi` FOREIGN KEY (`ai_diagnosis_id`) REFERENCES `ai_diagnosis` (`ai_diagnosis_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ai_report_diseases`
--

LOCK TABLES `ai_report_diseases` WRITE;
/*!40000 ALTER TABLE `ai_report_diseases` DISABLE KEYS */;
INSERT INTO `ai_report_diseases` VALUES (95.52,1,1,'정상'),(2.17,1,2,'유루증'),(1.01,1,3,'안검내반증'),(99.26,2,4,'정상'),(0.36,2,5,'궤양성각막질환'),(0.29,2,6,'안검내반증'),(77.27,3,7,'정상'),(13.53,3,8,'안검내반증'),(2.90,3,9,'유루증'),(13.84,4,10,'그레이트데인'),(11.12,4,11,'그레이트피레니즈'),(8.58,4,12,'시바이누'),(75.00,5,13,'말티즈'),(18.04,5,14,'비숑프리제'),(3.55,5,15,'라사'),(23.78,6,16,'결절 종괴'),(16.35,6,17,'구진플라그'),(16.13,6,18,'정상'),(98.54,18,52,'과체중'),(0.76,18,53,'고도비만'),(0.71,18,54,'정상'),(27.88,19,55,'결절 종괴'),(27.42,19,56,'미란 궤양'),(16.79,19,57,'정상'),(75.47,20,58,'정상'),(11.40,20,59,'유루증'),(5.14,20,60,'안검내반증'),(92.16,21,61,'과체중'),(5.06,21,62,'정상'),(2.78,21,63,'고도비만'),(24.26,22,64,'라사'),(23.93,22,65,'차이니즈크레스티드'),(10.90,22,66,'말티즈'),(72.14,23,67,'정상'),(12.97,23,68,'태선화 과다색소 침착'),(6.66,23,69,'비듬 각질 상피성잔고리'),(99.24,24,70,'정상'),(0.42,24,71,'안검내반증'),(0.16,24,72,'궤양성각막질환'),(30.37,25,73,'퍼그'),(13.14,25,74,'시베리안허스키'),(11.28,25,75,'그레이하운드'),(11.00,26,76,'그레이트데인'),(7.37,26,77,'비숑프리제'),(7.35,26,78,'아메리칸헤어리스'),(53.49,27,79,'궤양성각막질환'),(36.66,27,80,'정상'),(3.64,27,81,'색소침착성각막염'),(78.99,28,82,'정상'),(6.35,28,83,'구진플라그'),(6.25,28,84,'비듬 각질 상피성잔고리'),(99.93,29,85,'과체중'),(0.06,29,86,'고도비만'),(0.01,29,87,'정상'),(91.61,30,88,'말티즈'),(3.93,30,89,'코카푸'),(3.63,30,90,'비숑프리제'),(55.38,31,91,'정상'),(36.57,31,92,'안검내반증'),(6.61,31,93,'유루증'),(37.10,32,94,'유루증'),(23.42,32,95,'정상'),(21.61,32,96,'안검내반증'),(38.89,33,97,'비듬 각질 상피성잔고리'),(29.90,33,98,'구진플라그'),(12.85,33,99,'농포 여드름'),(65.23,34,100,'정상'),(27.38,34,101,'궤양성각막질환'),(3.13,34,102,'색소침착성각막염'),(44.29,35,103,'유루증'),(30.35,35,104,'안검내반증'),(18.03,35,105,'정상');
/*!40000 ALTER TABLE `ai_report_diseases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dog`
--

DROP TABLE IF EXISTS `dog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dog` (
  `birth_date` date DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `is_neutered` bit(1) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `dog_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `breed` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `gender` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `image_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`dog_id`),
  KEY `FKhdpjd6l15cy5li9vatn0jh02x` (`member_id`),
  CONSTRAINT `FKhdpjd6l15cy5li9vatn0jh02x` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dog`
--

LOCK TABLES `dog` WRITE;
/*!40000 ALTER TABLE `dog` DISABLE KEYS */;
INSERT INTO `dog` VALUES ('2000-10-02',_binary '\0',_binary '\0',12,'2024-10-10 16:29:49.480411',1,1,'2024-10-10 16:37:54.432279','도베르만','남아','dog_image/53174645-13b7-4c7f-ab5c-a24975aafd01_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/53174645-13b7-4c7f-ab5c-a24975aafd01_default-dog.png','케르베로스'),(NULL,_binary '\0',_binary '',4.5,'2024-10-10 16:45:14.505124',2,2,'2024-10-11 03:57:43.387149','포메라니안','여아','dog_image/825c9e8a-48db-43ea-ae5d-05eb23fd16fc_1000012154.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/825c9e8a-48db-43ea-ae5d-05eb23fd16fc_1000012154.jpg','호두'),(NULL,_binary '\0',_binary '\0',3.51,'2024-10-10 17:01:05.317015',3,3,'2024-10-10 17:05:02.115444','','남아','dog_image/45236d64-9887-4d28-9862-4a6123a5a52f_hodu.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/45236d64-9887-4d28-9862-4a6123a5a52f_hodu.jpg','호두'),(NULL,_binary '\0',_binary '',4,'2024-10-10 17:01:05.321082',4,4,'2024-10-11 03:49:13.238708','포메라니안','여아','dog_image/8632a9cf-87b4-43bb-9edd-070114d98067_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/8632a9cf-87b4-43bb-9edd-070114d98067_default-dog.png','새우새우'),(NULL,_binary '\0',_binary '\0',3,'2024-10-10 17:14:31.730321',6,4,'2024-10-11 00:38:26.698313','푸들','남아','dog_image/678789ee-f872-4efa-9e20-96113a412f62_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/678789ee-f872-4efa-9e20-96113a412f62_default-dog.png','새우3'),('2012-11-22',_binary '\0',_binary '\0',1,'2024-10-10 17:22:45.092384',7,4,'2024-10-10 17:22:45.092384','푸들','여아','dog_image/330ea847-a043-4a33-ae25-635caca7632f_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/330ea847-a043-4a33-ae25-635caca7632f_default-dog.png','새우3'),('2022-12-17',_binary '\0',_binary '\0',21,'2024-10-10 18:31:15.624550',8,6,'2024-10-10 18:31:15.624550','진도믹스','남아','dog_image/b88e6326-1ff8-40b5-94d1-0ae975f55c23_20240309_160631.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/b88e6326-1ff8-40b5-94d1-0ae975f55c23_20240309_160631.jpg','백당이'),('2023-05-21',_binary '\0',_binary '\0',2.3,'2024-10-10 23:28:00.524497',12,10,'2024-10-10 23:28:00.524497','말티즈','여아','dog_image/05864304-e29e-4cdf-8dc9-826dc07418c0_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/05864304-e29e-4cdf-8dc9-826dc07418c0_default-dog.png','뽀삐'),('2016-01-01',_binary '\0',_binary '\0',6,'2024-10-11 00:34:49.259517',13,11,'2024-10-11 00:34:49.259517','푸들','여아','dog_image/95526da5-2cd2-44de-868b-91c193f86085_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/95526da5-2cd2-44de-868b-91c193f86085_default-dog.png','새우새우'),('2016-01-01',_binary '\0',_binary '\0',6,'2024-10-11 00:34:52.203604',14,12,'2024-10-11 00:34:52.203604','푸들','여아','dog_image/2b732302-dcea-411b-9615-7d7a56702931_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/2b732302-dcea-411b-9615-7d7a56702931_default-dog.png','새우새우'),('2016-01-01',_binary '\0',_binary '\0',6,'2024-10-11 00:34:55.403296',15,13,'2024-10-11 00:34:55.403296','푸들','여아','dog_image/5e1400fd-a4d6-472d-a660-904f97637221_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/5e1400fd-a4d6-472d-a660-904f97637221_default-dog.png','새우새우'),(NULL,_binary '\0',_binary '\0',2,'2024-10-11 00:42:38.764370',16,14,'2024-10-11 00:45:29.502057','포메라니안','여아','dog_image/fe3dd2bc-61da-4c38-81e5-29e13de5ec9c_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/fe3dd2bc-61da-4c38-81e5-29e13de5ec9c_default-dog.png','푸바오'),(NULL,_binary '\0',_binary '\0',38,'2024-10-11 02:34:22.112863',17,15,'2024-10-11 02:36:47.073036','리트리버','남아','dog_image/37da6897-5a5b-4198-9c38-78ff7acdf395_IMG_8459.jpeg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/37da6897-5a5b-4198-9c38-78ff7acdf395_IMG_8459.jpeg','보리'),('2017-05-06',_binary '\0',_binary '\0',6,'2024-10-11 03:39:06.982623',18,16,'2024-10-11 03:39:06.982623','포메라니안','여아','dog_image/e8422f39-f569-411b-a377-af81a6f10b26_1000012154.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/e8422f39-f569-411b-a377-af81a6f10b26_1000012154.jpg','쿠키'),('2024-01-01',_binary '\0',_binary '\0',10,'2024-10-11 04:35:11.948264',19,2,'2024-10-11 04:35:11.948264','도베르만','남아','dog_image/b74404b4-b066-4ab0-87bb-53d5095847f2_default-dog.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/b74404b4-b066-4ab0-87bb-53d5095847f2_default-dog.png','케르베로스'),('2018-04-15',_binary '\0',_binary '\0',6.5,'2024-10-11 08:59:54.667185',20,17,'2024-10-11 08:59:54.667185','말티즈','남아','dog_image/d974987a-1dce-42e4-8b99-a8ae76579df6_IMG_0145.PNG','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/dog_image/d974987a-1dce-42e4-8b99-a8ae76579df6_IMG_0145.PNG','보라미');
/*!40000 ALTER TABLE `dog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_problem`
--

DROP TABLE IF EXISTS `health_problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_problem` (
  `dog_id` bigint(20) NOT NULL,
  `health_problem_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `problem` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`health_problem_id`),
  KEY `FK6a17a5dxd0hm3j1srclioq5ef` (`dog_id`),
  CONSTRAINT `FK6a17a5dxd0hm3j1srclioq5ef` FOREIGN KEY (`dog_id`) REFERENCES `dog` (`dog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_problem`
--

LOCK TABLES `health_problem` WRITE;
/*!40000 ALTER TABLE `health_problem` DISABLE KEYS */;
INSERT INTO `health_problem` VALUES (1,4,'관절'),(1,5,'구강'),(1,6,'변비'),(3,23,'관절'),(3,24,'눈'),(3,25,'관절'),(3,26,'면역력'),(3,27,'면역력'),(12,48,'눈'),(12,49,'구강'),(12,50,'변비'),(15,51,'관절'),(15,52,'구강'),(15,53,'스트레스'),(6,55,'변비'),(16,59,'구강'),(17,66,'눈'),(17,67,'피모'),(17,68,'심장'),(18,69,'눈'),(18,70,'피모'),(18,71,'변비'),(4,72,'구강'),(4,73,'관절'),(4,74,'비만'),(2,78,'눈'),(2,79,'피모'),(2,80,'비만'),(19,81,'면역력'),(19,82,'비만'),(19,83,'스트레스'),(20,84,'구강'),(20,85,'피모'),(20,86,'면역력');
/*!40000 ALTER TABLE `health_problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance`
--

DROP TABLE IF EXISTS `insurance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance` (
  `insurance_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `coverage_ratio` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `fee` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `limit_fee` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `max_age` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `min_age` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `period` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `s3_image_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `s3_image_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`insurance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance`
--

LOCK TABLES `insurance` WRITE;
/*!40000 ALTER TABLE `insurance` DISABLE KEYS */;
INSERT INTO `insurance` VALUES (1,'메리츠화재','80% / 70% / 50%\r','전국 580여개 동물병원에서 진료부터 청구까지 가능','(말티즈 3세, 50% 기준)\r 35,990원\r','고급형/기본형 기준\n - 입원/통원 각각 최대 1,000만원 보장\n - 수술 시 1일 한도 250만원 보장, 수술 외 15만원 보장\n\n실속형 기준\n - 입원/통원 각각 최대 700만원보장\n - 수술 시 1일 한도 200만원 보장, 수술 외 10만원 보장','만 8세 (실속형은 만 10세)','생후 61일','펫퍼민트','3년 / 5년 갱신\n만 20세','insurance/펫퍼민트.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/insurance/%ED%8E%AB%ED%8D%BC%EB%AF%BC%ED%8A%B8.jpg'),(2,'DB 손해보험','50% / 70% / 80% / 90%','MRI, CT 까지 특약으로 보장\n및 견주 보장 특별 약관 가능','(말티즈 3세, 80% 기준)\r 55,890원','일반형 기준(50%, 70%)\n - 연간 보상 한도액 : 1,000만원\n - 수술 시 1일당 최고 200만원, 수술 외 15만원 보장\n\n고보장형 기준(80%, 90%)\n - 연간 보상 한도액 : 1,000원\n - 수술 시 1일 한도 250만원 보장, 수술 외 30만원 보장','만 10세','만 2개월','펫블리반려견보험','3년 / 5년 갱신\n만 20세','insurance/펫블리반려견보험.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/insurance/%ED%8E%AB%EB%B8%94%EB%A6%AC%EB%B0%98%EB%A0%A4%EA%B2%AC%EB%B3%B4%ED%97%98.jpg'),(3,'현대해상','50% / 70% / 80% / 90%','강아지 시터비용으로 활용 가능한 반려동물돌봄비 보장 가능','상담 후 확인 가능','50% 기준\n - 연간 보상 한도액 : 1,500만원\n - 수술 시 1일당 최고 200만원, 수술 외 10만원 보장\n\n그 외 기준\n - 연간 보상 한도액 : 2,000원\n - 수술 시 1일 한도 250만원 보장, 수술 외 15/30만원 보장','만 10세','생후 61일','굿엔굿우리펫보험','3년 / 5년 갱신\n만 20세','insurance/굿앤굿우리펫보험.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/insurance/%EA%B5%BF%EC%95%A4%EA%B5%BF%EC%9A%B0%EB%A6%AC%ED%8E%AB%EB%B3%B4%ED%97%98.png'),(4,'KB손해보험','50% / 70% / 80% / 90%','보호자가 필요한 다양한 보장 가능 (천식지속 상태 진단비, 호흡기관련질병 수술비, 정신질환특정진단비 등)','상담 후 확인 가능','80%, 90%기준\n - 연간한도 1,000만원\n - 수술 시 1일 한도 250만원 보장, 수술 외 15만원 보장\n\n50%, 70% 기준\n - 연간한도 350만원\n - 수술 시 1일 한도 150만원 보장, 수술 외 10만원 보장','만 8세 (만 10세)','만 0세','KB금쪽같은 펫보험','3년 / 5년 갱신\n만 20세','insurance/KB금쪽같은펫보험.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/insurance/KB%EA%B8%88%EC%AA%BD%EA%B0%99%EC%9D%80%ED%8E%AB%EB%B3%B4%ED%97%98.jpg'),(5,'삼성화재','50% / 70% / 80% / 90% / 100%','타 보험에 비해 100%보장 가능한 특약도 가입 가능','(말티즈 3세, 50% 기준) 38,032원','coverage_ratio별 보장 혜택 상이\n- 의료비 1: 200만원한도\n- 의료비 2: 10만원\n- 배상책임: 3,000만원\n- 장례비용: 30만원','만 10세','만 0세','착한펫보험','3년 / 5년 갱신\n만 20세','insurance/착한펫보험.png','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/insurance/%EC%B0%A9%ED%95%9C%ED%8E%AB%EB%B3%B4%ED%97%98.png');
/*!40000 ALTER TABLE `insurance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_benefit`
--

DROP TABLE IF EXISTS `insurance_benefit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_benefit` (
  `insurance_benefit_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insurance_id` bigint(20) NOT NULL,
  `benefit` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`insurance_benefit_id`),
  KEY `FKscedaa5ao0qamvq78j482ol9c` (`insurance_id`),
  CONSTRAINT `FKscedaa5ao0qamvq78j482ol9c` FOREIGN KEY (`insurance_id`) REFERENCES `insurance` (`insurance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_benefit`
--

LOCK TABLES `insurance_benefit` WRITE;
/*!40000 ALTER TABLE `insurance_benefit` DISABLE KEYS */;
INSERT INTO `insurance_benefit` VALUES (1,1,'관절 질환'),(2,1,'염증'),(3,1,'보행 이상'),(4,1,'구강 질환'),(5,1,'피부 질환'),(6,1,'탈장'),(7,2,'검진'),(8,2,'관절 질환'),(9,2,'구강 질환'),(10,2,'반려동물 배상책임'),(11,2,'장례비'),(12,3,'피부 질환'),(13,3,'구강 질환'),(14,3,'관절 질환'),(15,3,'반려동물 배상책임'),(16,3,'장례비'),(17,3,'반려동물 돌봄비'),(18,4,'관절 질환'),(19,4,'염증'),(20,4,'보행 이상'),(21,4,'반려동물 배상책임'),(22,4,'장례비'),(23,4,'반려동물 돌봄비'),(24,5,'피부 질환'),(25,5,'관절 질환'),(26,5,'반려동물 배상책임'),(27,5,'장례비');
/*!40000 ALTER TABLE `insurance_benefit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_record`
--

DROP TABLE IF EXISTS `insurance_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_record` (
  `expiration_date` date NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `regist_date` date NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `dog_id` bigint(20) NOT NULL,
  `insurance_id` bigint(20) NOT NULL,
  `insurance_record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `modified_at` datetime(6) DEFAULT NULL,
  `monthly_payment` bigint(20) NOT NULL,
  PRIMARY KEY (`insurance_record_id`),
  KEY `FKjujtlf0sspnwv2gr5tjxee2c7` (`dog_id`),
  KEY `FKhpp6ywaneterqy5h5m5ughcst` (`insurance_id`),
  CONSTRAINT `FKhpp6ywaneterqy5h5m5ughcst` FOREIGN KEY (`insurance_id`) REFERENCES `insurance` (`insurance_id`),
  CONSTRAINT `FKjujtlf0sspnwv2gr5tjxee2c7` FOREIGN KEY (`dog_id`) REFERENCES `dog` (`dog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_record`
--

LOCK TABLES `insurance_record` WRITE;
/*!40000 ALTER TABLE `insurance_record` DISABLE KEYS */;
INSERT INTO `insurance_record` VALUES ('2024-10-10',_binary '','2024-10-10','2024-10-10 17:38:23.957539',6,2,1,'2024-10-10 17:41:09.083197',10000),('2024-10-10',_binary '','2024-10-10','2024-10-10 17:41:22.367272',6,1,2,'2024-10-10 17:44:22.365423',10000),('2024-10-10',_binary '','2024-10-10','2024-10-10 17:43:30.599073',6,4,3,'2024-10-10 17:44:24.028277',50000),('2024-10-10',_binary '','2024-10-10','2024-10-10 17:44:49.790388',6,3,4,'2024-10-10 17:47:50.668952',20000),('2024-10-10',_binary '','2024-02-06','2024-10-10 17:47:31.716563',6,2,5,'2024-10-10 17:47:47.880963',10000),('2026-07-31',_binary '\0','2024-08-01','2024-10-10 21:49:25.404457',2,2,6,'2024-10-10 21:49:25.404457',40000),('2024-10-26',_binary '\0','2024-10-03','2024-10-11 03:40:12.995152',18,3,7,'2024-10-11 03:40:12.995152',20000);
/*!40000 ALTER TABLE `insurance_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record`
--

DROP TABLE IF EXISTS `medical_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record` (
  `cost` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `dog_id` bigint(20) NOT NULL,
  `medical_record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `modified_at` datetime(6) DEFAULT NULL,
  `record_time` datetime(6) NOT NULL,
  `content` text COLLATE utf8mb4_bin,
  `hospital` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `image_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`medical_record_id`),
  KEY `FKdr7xyqos632k0o2pvft5n776b` (`dog_id`),
  CONSTRAINT `FKdr7xyqos632k0o2pvft5n776b` FOREIGN KEY (`dog_id`) REFERENCES `dog` (`dog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record`
--

LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
INSERT INTO `medical_record` VALUES (150000,'2024-10-10 21:50:53.960914',2,1,'2024-10-10 21:50:53.960914','2024-05-01 00:50:00.000000','건강검진 - 이상없음','가나동물병원','medical_record_image/49d40dcd-3554-49df-9477-1eacaed2363b_KakaoTalk_20240903_160535302.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/medical_record_image/49d40dcd-3554-49df-9477-1eacaed2363b_KakaoTalk_20240903_160535302.jpg'),(64919,'2024-10-11 03:42:25.949762',18,4,'2024-10-11 03:42:25.949762','2024-10-02 06:41:00.000000','.','디지몬','medical_record_image/48c8d845-d625-43a4-9e95-e48781d1d226_Screenshot_20241010_175543_Chrome.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/medical_record_image/48c8d845-d625-43a4-9e95-e48781d1d226_Screenshot_20241010_175543_Chrome.jpg'),(64919,'2024-10-11 03:42:26.990821',18,5,'2024-10-11 03:42:26.990821','2024-10-02 06:41:00.000000','.','디지몬','medical_record_image/f0cf19a7-4426-41aa-93c0-b4a3e61fcf10_Screenshot_20241010_175543_Chrome.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/medical_record_image/f0cf19a7-4426-41aa-93c0-b4a3e61fcf10_Screenshot_20241010_175543_Chrome.jpg'),(54866,'2024-10-11 03:43:09.477147',18,6,'2024-10-11 03:43:09.477147','2024-10-02 18:42:00.000000','ㄷㄱ','ㄷㄱㅈ','medical_record_image/40efa169-4e92-438d-99e3-77cad51b3284_Screenshot_20241010_091504_Chrome.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/medical_record_image/40efa169-4e92-438d-99e3-77cad51b3284_Screenshot_20241010_091504_Chrome.jpg'),(8484,'2024-10-11 03:53:32.035510',18,7,'2024-10-11 03:53:32.035510','2024-10-03 06:52:00.000000','ㅈㅅㅈㅅ','딪','medical_record_image/43816ff4-8d88-43d5-9985-f8f3b4e71e68_Screenshot_20241010_090916_Chrome.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/medical_record_image/43816ff4-8d88-43d5-9985-f8f3b4e71e68_Screenshot_20241010_090916_Chrome.jpg'),(6464,'2024-10-11 04:03:15.945751',18,8,'2024-10-11 04:03:15.945751','2024-10-01 07:02:00.000000','66464','디딛','medical_record_image/20b52882-e409-44c5-ab4b-f38973a2814e_Screenshot_20241010_175543_Chrome.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/medical_record_image/20b52882-e409-44c5-ab4b-f38973a2814e_Screenshot_20241010_175543_Chrome.jpg');
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `is_deleted` bit(1) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `modified_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (_binary '\0','2024-10-10 16:29:41.954771',1,'2024-10-10 16:29:41.954771','gapple953@naver.com','장현준','$2a$10$UQQx7brFGhl653FPGytcK.zempR/9y0F9eg5BRiRU5yZxEDfsXSBu'),(_binary '\0','2024-10-10 16:43:38.833471',2,'2024-10-10 16:43:38.833471','ssafy@naver.com','김싸피','$2a$10$wh2r3bSQYtWnZVmDZwBCJ.eGP/9/TTzFR5HJX6RrzY1yOLRMdXcsu'),(_binary '\0','2024-10-10 17:01:03.801249',3,'2024-10-10 17:01:03.801249','swim@naver.com','swim','$2a$10$p4Es7zQwo5Y64hgol6rHI.IY5jZA0fEANsyYRrSdMLlB8h7sNsqAm'),(_binary '\0','2024-10-10 17:01:03.723250',4,'2024-10-10 17:01:03.723250','junhuk9703@naver.com','김준혁','$2a$10$ReAmnkCHQhRUS25UqeEY7eyc.VzumOTv7NI9/JRi9nPSOMgl/Mr1C'),(_binary '\0','2024-10-10 18:31:12.530128',6,'2024-10-10 18:31:12.530128','dudwn0762@naver.com','강영주','$2a$10$NfN4tYk.yfpPVDbjSLDhHOQ4.JZDCvrlhAenwZVAemY2lz/jJ3Uc2'),(_binary '\0','2024-10-10 23:27:59.402788',10,'2024-10-10 23:27:59.402788','user2@gmail.com','정채린','$2a$10$1TPQDogMdj7MOr5BkmH20.dL9PwWE4zWQ0OgZRX2tXWkrklruGLtO'),(_binary '\0','2024-10-11 00:34:48.203838',11,'2024-10-11 00:34:48.203838','junhuk4079@naver.com','준혁김','$2a$10$hLYOOUAs2MbLtS4dzbjUpuIcjVdFx9VQhaJj3.AjnSWRG2gLQ.KL.'),(_binary '\0','2024-10-11 00:34:51.579083',12,'2024-10-11 00:34:51.579083','junhuk4079@naver.com','준혁김','$2a$10$v3mJe7YuL00HWcVLbXppbeJavLgfKm4frK//xggyscND9Ywv6viam'),(_binary '\0','2024-10-11 00:34:54.850990',13,'2024-10-11 00:34:54.850990','junhuk4079@naver.com','준혁김','$2a$10$Jbg6r1Nx71yS5FZMUtgSvep14Cx20vUtFsWwQScitelBf.id6qg9K'),(_binary '\0','2024-10-11 00:42:37.646459',14,'2024-10-11 00:42:37.646459','junhuk3914@naver.com','김준혁','$2a$10$YvkeUuTd1qEmZ5xu2AFItunpH9osFBDrSuz0ySpM3wYryl2w4Zs0.'),(_binary '\0','2024-10-11 02:34:20.664786',15,'2024-10-11 02:34:20.664786','rosachae123@naver.com','채린','$2a$10$lCzeyWeZ6/UGXq0eOjV7SOgPRNw6mBwn5r8NB881AvWGn3.88YP/a'),(_binary '\0','2024-10-11 03:39:05.144447',16,'2024-10-11 03:39:05.144447','jaehee@gmail.com','재희5','$2a$10$Q/o/8fUkjRBDpGA8Db2ize2K2y2Un0YuwNaBXzkBtTN1lJ9KEdxHa'),(_binary '\0','2024-10-11 08:59:51.944666',17,'2024-10-11 08:59:51.944666','snnsprng@naver.com','하하호호','$2a$10$rjZM7b9iW2xLuTw0buTRj.8TQPReJc5iHAcr4mJE8Hw6vlO2vQoOe');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplement`
--

DROP TABLE IF EXISTS `supplement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplement` (
  `price` int(11) DEFAULT NULL,
  `supplement_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `basis` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `brand_name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `fat` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `feature` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `how` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `image_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `offer` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `product_name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `protein` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `target` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`supplement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplement`
--

LOCK TABLES `supplement` WRITE;
/*!40000 ALTER TABLE `supplement` DISABLE KEYS */;
INSERT INTO `supplement` VALUES (149000,2,'초록잎홍합, 크릴오일','안티놀','50%','무인공첨가물, 무합성착향료, 글루텐프리, 무합성착색료, 무방부제, non_GMO, 휴먼그레이드, 그레인프리','사료,간식에 섞어서, 바로 급여\n','113938092483150172_1815531577.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/113938092483150172_1815531577.jpg','관절강화, 피모관리, 면역력강화, 신장/요로, 심장건강	','안티놀 슬개골 영양제','0%','전연령','젤겔'),(24000,3,'빌베리추출물, 밀크씨슬, 오메가3, 루테인','하루올데이','	1.4%','무인공첨가물, 무합성착향료, 무합성착색료, 무방부제, non_GMO, 휴먼그레이드, 그레인프리, 글루텐프리\n','바로 급여, 사료,간식에 섞어서\n','61563613163252339_1028931577.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/61563613163252339_1028931577.jpg','면역력강화, 종합비타민, 눈건강, 영양공급, 항산화\n','하루올데이 강아지 눈 영양제','4.0%','퍼피, 어덜트, 시니어, 대형견, 임신/수유, 전연령\n','분말'),(28000,4,'','펫니크',NULL,NULL,NULL,'48444670962838088_1631467740.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/48444670962838088_1631467740.jpg','영양공급, 면역력강화, 종합비타민, 항산화	','아티큐펫 강아지 관절영양제',NULL,'어덜트, 퍼피, 시니어, 임신/수유, 대형견\n','알약'),(54000,5,'','마이뷰',NULL,NULL,NULL,'66505587399406893_2069826833.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/66505587399406893_2069826833.jpg','영양공급, 종합비타민, 피모관리, 체중유지	','마이뷰 반려견 종합영양제',NULL,'전연령','젤겔'),(26000,6,'초록잎홍합, MSM','Jerry Styles(자체제작 상품)\n','3%',NULL,'사료,간식에 섞어서, 바로 급여	','10046208227866972_1982725557.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/10046208227866972_1982725557.jpg','관절강화, 피모관리, 눈건강, 치석제거, 구강관리	','제리스타일스 강아지 영양제','5.6%','퍼피, 전연령, 어덜트, 대형견, 시니어, 임신/수유\n','트릿'),(22800,7,'오메가3, 루테인, 지아잔틴, 빌베리추출물, 비타민A, 비타민E, 밀크씨슬, 효모','펫생각(국내협력업체)','5.8%','무합성착색료, 휴먼그레이드, 그레인프리, 글루텐프리, non_GMO','바로 급여, 사료,간식에 섞어서','7357006702614800_1279965970.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/7357006702614800_1279965970.jpg','영양공급, 저알러지, 면역력강화, 눈건강','데일리케인 강아지 눈 영양제','11.2%','전연령, 퍼피, 어덜트, 시니어, 임신/수유, 대형견','트릿'),(32800,8,NULL,'펫펫펫(자체제작 상품)	',NULL,NULL,'바로 급여','82511044338819969_337196679.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/82511044338819969_337196679.jpg','영양공급, 관절강화, 저알러지, 체중유지, 식욕증진(기호성)','워킹 런 마스터 11',NULL,'전연령, 퍼피, 어덜트, 시니어, 대형견, 임신/수유','트릿'),(24900,9,'연어','닥터퍼피365',NULL,NULL,'바로 급여','2935559313258462_225612413.jpg ','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/2935559313258462_225612413.jpg','영양공급, 눈건강, 냄새제거, 종합비타민, 항산화	','닥터퍼피365 프리미엄 brIght',NULL,'전연령, 퍼피, 어덜트, 임신/수유, 대형견, 시니어\n','트릿'),(12000,10,'생선/해산물','포쿠코','51.5%	','무인공첨가물, 무합성착향료, 무합성착색료, 무방부제, non_GMO, 휴먼그레이드\n','바로 급여, 사료,간식에 섞어서\n','78881289963453914_628626793.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/78881289963453914_628626793.jpg','피모관리, 눈건강, 심장건강, 신장/요로, 관절강화	','강아지오메가3','18.2%','전연령, 퍼피, 시니어, 어덜트, 임신/수유, 대형견\n','젤겔'),(11900,11,'오리','닥터뉴토(자체제작 상품)	','4.4%',NULL,'바로 급여','9927433811220985_330622126.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/10046208227866972_1982725557.jpg','눈건강, 저알러지	','눈물 케어 듀얼 샌드 180g','10%	','전연령','트릿'),(52500,12,'기타','라이프펫',NULL,NULL,'바로 급여','61798316811660958_1207798141.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/61798316811660958_1207798141.jpg','피모관리, 호흡기관리, 저알러지, 영양공급, 항산화	','라이프펫 반려견 피부영양제',NULL,'전연령, 퍼피, 어덜트, 시니어, 대형견','트릿'),(21900,13,NULL,'블루베이',NULL,NULL,'사료, 간식에 섞어서','3343281345933518_1781150221.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/3343281345933518_1781150221.jpg','눈건강','아이비타 반려견 눈 영양제',NULL,'전연령','액상'),(53000,14,NULL,'미펫',NULL,NULL,'바로 급여, 사료,간식에 섞어서, 액체에 녹여서','18313032885368563_2146108852.jpg ','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/18313032885368563_2146108852.jpg','눈건강','낼름눈건강 반려견 루테인',NULL,'전연령, 시니어, 어덜트','젤겔'),(32300,15,NULL,'이거면댕냥',NULL,NULL,NULL,'49754350435306349_1237054330.jfif ','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/49754350435306349_1237054330.jfif','저알러지, 다이어트/중성화, 체중유지	','가비웁댕 250g 3통',NULL,'퍼피, 어덜트, 시니어, 임신/수유, 대형견, 전연령','트릿'),(22900,16,NULL,'미래생명자원',NULL,NULL,NULL,'49664443618.20240807181421.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/49664443618.20240807181421.jpg','관절강화, 피모관리, 눈건강, 항산화','뉴말 콜라겐 피모영양제',NULL,'전연령','트릿'),(25000,17,'북어','하루올데이','1.4%','무인공첨가물, 무합성착향료, 무합성착색료, 무방부제, non_GMO, 휴먼그레이드, 그레인프리, 글루텐프리','바로 급여, 사료,간식에 섞어서','8466241744651514_838521817.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/8466241744651514_838521817.jpg','면역력강화, 종합비타민, 항산화, 영양공급, 호흡기관리','하루올데이 강아지 기관지 영양제','4.0%','퍼피, 어덜트, 대형견, 시니어, 임신/수유\n','분말'),(52000,18,NULL,'닥터허비',NULL,NULL,NULL,'7973717832820832_1541794284.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/7973717832820832_1541794284.jpg','영양공급, 면역력강화, 냄새제거, 식욕증진(기호성)','피메푸바이오 눈물싹싹',NULL,'퍼피, 어덜트, 시니어, 임신/수유, 대형견','트릿'),(28900,19,NULL,'펫리즈',NULL,'non_GMO, 무합성착색료, 무합성착향료	','사료,간식에 섞어서, 바로 급여	','39910177642761075_1978982588.jpg ','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/39910177642761075_1978982588.jpg','관절강화, 피모관리, 심장건강, 호흡기관리, 저알러지','조하이젠 반려견 관절영양제',NULL,'전연령','트릿'),(29750,20,'연어, 과일/야채, 생선/해산물','벨라맥스','405%','휴먼그레이드, 무합성착색료, 무합성착향료, 무인공첨가물, 그레인프리','사료,간식에 섞어서, 바로 급여','113667487552735757_715108642.jpg ','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/113667487552735757_715108642.jpg','눈건강, 항산화, 식욕증진(기호성), 영양공급, 종합비타민','동물농장 강아지 눈 영양제','12%','전연령, 퍼피, 어덜트, 시니어, 임신/수유, 대형견','트릿'),(25700,21,NULL,'댕이수랏간',NULL,NULL,NULL,'107716613478576120_1856139012.jpg ','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/107716613478576120_1856139012.jpg','면역력강화, 종합비타민, 눈건강, 영양공급','댕이수랏간 강아지 눈 영양제',NULL,'퍼피, 어덜트, 시니어, 대형견, 전연령, 임신/수유','트릿'),(37900,22,'닭, 쌀','프롬더셀','4%','무합성착향료, 무합성착색료, 휴먼그레이드','바로 급여, 사료,간식에 섞어서','48302202941783291_1791530174.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/48302202941783291_1791530174.jpg','관절강화, 피모관리, 눈건강, 항산화, 영양공급','시니어 에이징 라인 3000mg','12.5%','시니어, 대형견','트릿'),(25350,23,'고구마, 연어','퍼센트퍼센트','2%','non_GMO, 휴먼그레이드, 그레인프리, 무합성착색료, 글루텐프리','바로 급여, 사료,간식에 섞어서','119141281927540277_1015188764.jfif','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/119141281927540277_1015188764.jfif','저알러지, 피모관리, 면역력강화, 눈건강, 영양공급','퍼센트퍼센트 강아지 눈 영양제','18%','시니어, 어덜트, 퍼피, 임신/수유, 대형견, 전연령','트릿'),(16000,24,NULL,'대웅펫',NULL,NULL,NULL,'4838212776190745_1007888176.jfif','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/4838212776190745_1007888176.jfif','관절강화, 피모관리, 항산화','애니웰 식물성 rTG 오메가3',NULL,'전연령, 퍼피, 어덜트, 시니어, 임신/수유, 대형견','알약'),(21800,25,'오리, 기타','비타펫','2%','무합성착향료, 무합성착색료, non_GMO, 글루텐프리','바로 급여, 사료,간식에 섞어서','112048180601930593_266082993.jpg ','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/112048180601930593_266082993.jpg','영양공급, 저알러지, 눈건강, 체중유지, 면역력강화','슈퍼이뮨 아이즈 365 120g','10%','전연령, 퍼피, 어덜트, 시니어, 임신/수유, 대형견\n','트릿'),(26850,26,'곡물, 기타','벡스퍼트',NULL,NULL,'바로 급여, 사료, 간식에 섞어서','70607771592933084_485756122.jfif','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/70607771592933084_485756122.jfif','영양공급, 피모관리, 저알러지, 면역력강화','벡스퍼트 알로벡스 영양제',NULL,'전연령, 퍼피, 어덜트, 시니어, 대형견','트릿'),(21500,27,NULL,'슈퍼펫',NULL,NULL,'바로 급여, 사료,간식에 섞어서','49315757619.20240722152142.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/49315757619.20240722152142.jpg','영양공급, 관절강화, 피모관리, 소화개선, 식욕증진(기호성), 스트레스완화','슈퍼펫 츄어블정 60캡슐',NULL,'전연령','트릿'),(75000,28,'','ADORED BEAST APOTHECARY\n',NULL,NULL,'바로 급여','13332861165087021_1622308847.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/13332861165087021_1622308847.jpg','영양공급, 피모관리, 면역력강화, 눈건강, 종합비타민','어돌비스트 반려경 영양제',NULL,'전연령, 퍼피, 어덜트, 임신/수유, 시니어, 대형견','오일'),(28900,29,'연어','페노비스','6%','무합성착색료','바로 급여, 사료,간식에 섞어서','55030825005972577_1192643230.jfif','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/55030825005972577_1192643230.jfif','영양공급, 피모관리, 눈건강, 항산화, 종합비타민','페노비스 강아지 눈 관리 세트','11%','어덜트, 전연령, 대형견, 시니어','트릿'),(29000,30,'기타','단바이오텍','8%','그레인프리, 무인공첨가물, 무합성착향료, 무합성착색료, 휴먼그레이드, 글루텐프리','바로 급여, 사료,간식에 섞어서, 액체에 녹여서','18977195741145836_850442914.jfif','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/18977195741145836_850442914.jfif','면역력강화, 영양공급, 관절강화, 눈건강, 소화개선','퍼피락 시니어','8%','시니어','젤겔'),(23900,31,NULL,'파파메디(자체제작 상품)',NULL,NULL,NULL,'107026792992121273_1336707563.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/supplement_profile/107026792992121273_1336707563.jpg','면역력강화, 종합비타민, 항산화, 영양공급','메디아이즈 눈 영양제',NULL,'퍼피, 전연령, 어덜트, 시니어, 임신/수유, 대형견','분말');
/*!40000 ALTER TABLE `supplement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaccination_record`
--

DROP TABLE IF EXISTS `vaccination_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaccination_record` (
  `vaccination_round` int(11) DEFAULT NULL,
  `cost` bigint(20) NOT NULL,
  `dog_id` bigint(20) NOT NULL,
  `record_time` datetime(6) NOT NULL,
  `vaccination_record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_bin,
  `hospital` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `image_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`vaccination_record_id`),
  KEY `FKmlwsias212l2fdi3ijcupslm2` (`dog_id`),
  CONSTRAINT `FKmlwsias212l2fdi3ijcupslm2` FOREIGN KEY (`dog_id`) REFERENCES `dog` (`dog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaccination_record`
--

LOCK TABLES `vaccination_record` WRITE;
/*!40000 ALTER TABLE `vaccination_record` DISABLE KEYS */;
INSERT INTO `vaccination_record` VALUES (4,100000,6,'2024-10-09 10:51:00.000000',1,'4차 예방접종 맞았음','대전 동물 병원',NULL,NULL),(1,100000,6,'2024-10-08 09:56:00.000000',2,'사진','대전 동물병원 사진등록',NULL,NULL),(1,60000,2,'2023-09-01 03:51:00.000000',3,'','가나동물병원',NULL,NULL),(2,50000,2,'2024-09-23 01:52:00.000000',4,'','가나동물병원',NULL,NULL),(4,6494,18,'2024-10-01 08:53:00.000000',14,'ㅈㅂㅈ','ㅈㅂ','vaccination_record_image/06621f27-167c-4c08-bcbb-6f79ae2a973f_Screenshot_20241010_175543_Chrome.jpg','https://dog-healthcare-lab-bucket.s3.us-east-2.amazonaws.com/vaccination_record_image/06621f27-167c-4c08-bcbb-6f79ae2a973f_Screenshot_20241010_175543_Chrome.jpg');
/*!40000 ALTER TABLE `vaccination_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-11  9:02:31
