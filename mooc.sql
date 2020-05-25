-- MySQL dump 10.13  Distrib 8.0.19, for osx10.15 (x86_64)
--
-- Host: localhost    Database: mooc
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classification`
--

DROP TABLE IF EXISTS `classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classification` (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classification`
--

LOCK TABLES `classification` WRITE;
/*!40000 ALTER TABLE `classification` DISABLE KEYS */;
INSERT INTO `classification` VALUES (1,'考研'),(2,'计算机'),(3,'经济管理'),(4,'外语'),(5,'文学历史'),(6,'工学'),(7,'理学'),(8,'生命科学');
/*!40000 ALTER TABLE `classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) DEFAULT NULL,
  `classify` varchar(30) DEFAULT NULL,
  `cteacher` varchar(20) DEFAULT NULL,
  `cpath` varchar(100) DEFAULT NULL,
  `ctime` int DEFAULT NULL,
  `cmore` text,
  `climit` tinyint(1) DEFAULT '1',
  `cdel` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cpath` (`cpath`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'C语言程序设计1','2`7','翁凯','course//C语言程序设计1',10,'C语言是古老而长青的编程语言，它具备了现代程序设计的基础要求，它的语法是很多其他编程语言的基础，在系统程序、嵌入式系统等领域依然是无可替代的编程语言，在各类编程语言排行榜上常年占据前两名的位置。 本课程是计算机专业的数据结构、汇编语言、操作系统、编译原理、体系结构等课程的基石。',1,0),(2,'C语言程序设计2','2`7','翁凯','course//C语言程序设计2',12,'C语言是古老而长青的编程语言，它具备了现代程序设计的基础要求，它的语法是很多其他编程语言的基础，在系统程序、嵌入式系统等领域依然是无可替代的编程语言，在各类编程语言排行榜上常年占据前两名的位置。 本课程是计算机专业的数据结构、汇编语言、操作系统、编译原理、体系结构等课程的基石。',1,0),(3,'C语言程序设计3','2`7','翁凯','course//C语言程序设计3',9,'C语言是古老而长青的编程语言，它具备了现代程序设计的基础要求，它的语法是很多其他编程语言的基础，在系统程序、嵌入式系统等领域依然是无可替代的编程语言，在各类编程语言排行榜上常年占据前两名的位置。 本课程是计算机专业的数据结构、汇编语言、操作系统、编译原理、体系结构等课程的基石。',1,0),(4,'C语言程序设计4','2`7','翁凯','course//C语言程序设计4',7,'C语言是古老而长青的编程语言，它具备了现代程序设计的基础要求，它的语法是很多其他编程语言的基础，在系统程序、嵌入式系统等领域依然是无可替代的编程语言，在各类编程语言排行榜上常年占据前两名的位置。 本课程是计算机专业的数据结构、汇编语言、操作系统、编译原理、体系结构等课程的基石。',1,0),(5,'C语言程序设计5','2`7','翁凯','course//C语言程序设计5',8,'C语言是古老而长青的编程语言，它具备了现代程序设计的基础要求，它的语法是很多其他编程语言的基础，在系统程序、嵌入式系统等领域依然是无可替代的编程语言，在各类编程语言排行榜上常年占据前两名的位置。 本课程是计算机专业的数据结构、汇编语言、操作系统、编译原理、体系结构等课程的基石。',1,0),(6,'现代礼仪1','7`8','袁涤非','course//现代礼仪1',4,'现代社会离不开人际交往，人际交往离不开现代礼仪。湖南大学袁涤非副教授向你精彩讲授《现代礼仪》课程。课程融理论性、指导性、实践性、艺术性和趣味性为一体，可操作性强，使用范围广，妙语连珠，精彩纷呈，助你成为有修养、有品位、有风度、有气质，懂得爱己爱人的现代人。',1,0),(7,'现代礼仪2','7`8','袁涤非','course//现代礼仪2',4,'现代社会离不开人际交往，人际交往离不开现代礼仪。湖南大学袁涤非副教授向你精彩讲授《现代礼仪》课程。课程融理论性、指导性、实践性、艺术性和趣味性为一体，可操作性强，使用范围广，妙语连珠，精彩纷呈，助你成为有修养、有品位、有风度、有气质，懂得爱己爱人的现代人。',1,0),(8,'现代礼仪3','7`8','袁涤非','course//现代礼仪3',4,'现代社会离不开人际交往，人际交往离不开现代礼仪。湖南大学袁涤非副教授向你精彩讲授《现代礼仪》课程。课程融理论性、指导性、实践性、艺术性和趣味性为一体，可操作性强，使用范围广，妙语连珠，精彩纷呈，助你成为有修养、有品位、有风度、有气质，懂得爱己爱人的现代人。',1,0),(9,'课程1','1`3','李教师','测试连接1',6,'详细信息1',1,0),(10,'课程2','8','张教师','测试连接2',7,'详细信息2',1,0),(11,'课程3','6','王教师','测试连接3',9,'详细信息3',1,0),(12,'课程4','2','李教师','测试连接4',6,'详细信息4',1,0),(13,'课程5','3','张教师','测试连接5',7,'详细信息5',1,0),(14,'课程6','4','王教师','测试连接6',9,'详细信息6',1,0),(15,'课程7','5','李教师','测试连接7',6,'详细信息7',1,0),(16,'课程8','6','张教师','测试连接8',7,'详细信息8',1,0),(17,'课程9','3`6','王教师','测试连接9',9,'详细信息9',1,0),(18,'课程10','4`5','李教师','测试连接10',6,'详细信息10',1,0),(19,'课程11','1`8','张教师','测试连接11',7,'详细信息11',1,0),(20,'课程12','2`6','王教师','测试连接12',9,'详细信息12',1,0),(21,'课程13','3`7','李教师','测试连接13',6,'详细信息12',1,0),(22,'课程14','2`8','张教师','测试连接14',7,'详细信息14',1,0),(23,'课程15','4`5`6','王教师','测试连接15',9,'详细信息15',1,0),(24,'课程16','2`4`7','李教师','测试连接16',6,'详细信息16',1,0),(25,'课程17','1`8','张教师','测试连接17',7,'详细信息17',1,0),(26,'课程18','2`4`6','王教师','测试连接18',9,'详细信息18',1,0),(27,'课程19','1`2`3`7','李教师','测试连接19',6,'详细信息19',1,0),(28,'课程20','1`6`8','张教师','测试连接20',7,'详细信息20',1,0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `uid` int DEFAULT NULL,
  `cid` int DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `uid_fk` (`uid`),
  KEY `cid_fk` (`cid`),
  CONSTRAINT `cid_fk` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE,
  CONSTRAINT `uid_fk` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (11,6,1),(12,7,2),(13,8,1),(14,8,2),(15,8,3),(16,10,5),(17,11,2),(18,15,2),(19,15,1),(20,15,5);
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `upassword` varchar(32) NOT NULL,
  `uQQ` varchar(11) NOT NULL,
  `uidCard` varchar(18) DEFAULT NULL,
  `ulimit` tinyint(1) DEFAULT '0',
  `udel` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uQQ` (`uQQ`),
  UNIQUE KEY `uidCard` (`uidCard`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','7FEF6171469E80D32C0559F88B377245','404608626','220882199901013416',1,0),(2,'admin','C4CEFC53CA414D25294FD23B8FCCD356','404608622','220882199201013416',1,0),(3,'admin','A2787C0ADCAEFE209513B85067302A42','404608623','220882199301013416',1,0),(4,'admin','D4BD123BD05D1BE65C8B9802A4E31340','404608624','220882199401013416',1,0),(5,'admin','6D33CDF2CF612EC8F534F1939BBF26EE','404608625','220882199501013416',1,0),(6,'金平文','91F0E6916363ADACE72D94AC7C4957C4','10000000','372323197802181180',0,0),(7,'唐昊天','984E8AE64FA012707EB8D678D6008E87','10000001','440307199009228815',0,0),(8,'韦兴平','969E7B7BBB38FC8733C75D1E1716DA14','10000002','450103200009069819',0,0),(9,'罗宛亦','004A5B22E09FD4DEACD040B92E63DA2E','10000003','511681198802137462',0,0),(10,'熊奇略','C627E64F16AF251A9B0966CC6F8A1B95','10000004','542526197202206470',0,0),(11,'尹德惠','C8389B55E4829808B305DC7019844316','10000005','640382198010013638',0,0),(12,'姜承允','AA3B72CDA01A17EED5F65D33AC04F67B','10000006','130703197303200199',0,0),(13,'闫冬莲','7789087F07C2A344E0E1FCD7AA1F2A24','10000007','13072319880712840X',0,0),(14,'姜正平','3B5B96255C5382C906270EB60D797ABB','10000008','330127197303105031',0,0),(15,'袁致远','4DD4D64FBF72668AA224D981E8C2D5F1','10000009','130131197405017611',0,0),(16,'张博简','98AF3AD3D9D6712CB8529DD6696B10EF','10000010','220621200009136433',0,0),(17,'黄高格','5DBBCE3E148FAE2DE4B3F0AEE4F7E492','10000011','14010019971217383X',0,0),(18,'陶翠琴','9922AC24941F0217128EFEEC94A83ADB','10000012','632600197103196426',0,0),(19,'罗海菡','05796DEFE2106FDB68DF10AA3DD90A82','10000013','320100200002200409',0,0),(20,'闫问夏','466E34C146A7EEE589D85BCDD3939ACD','10000014','362427199903188347',0,0),(21,'苏嘉良','F49ED8E2515BB5A9EB53B76E36C92843','10000015','220701197710157676',0,0),(22,'曹鸿运','37EAEEBCB94732BCC400E09C728DA197','10000016','350701198210258159',0,0),(23,'贾敏智','1CC0E5741A161FD073B820A815DB435E','10000017','511527198212094876',0,0),(24,'余奇正','040DB47F29F9EE9429B6DB99689D711A','10000018','533123199703211056',0,0),(25,'覃笑天','DB1A4C366892F9C1373E4C03D4E5243C','10000019','650205198306281045',0,0),(26,'秦初晴','59121D7CBFB7317600B9DCC68B09CDE8','10000020','441821198411165227',0,0),(27,'胡怀薇','65AE95774C80BBEE6A9183D032362165','10000021','410782197404225742',0,0),(28,'沈弘图','76DF7D3986E111F489F58D011979CAD0','10000022','220201199310106812',0,0),(29,'任阳云','2D7E1A1C7866080F6C216ED570089946','10000023','142325197406185251',0,0),(30,'汤元纬','7BAA895D3292F8EC0952BDFE573FFBF3','10000024','50010819700818653X',0,0),(31,'武琪睿','54598F86DA469FA8CF192C32C9B2DB64','10000025','370202199001156399',0,0),(32,'林建明','7172E0878A5473BE93B56D0E29E11179','10000026','211003198806099894',0,0),(33,'孔弘雅','677A84463BE65198F0BFBAF2A0EAF7EB','10000027','421200197611249596',0,0),(34,'杜如曼','9F8DD0D26A46AC51988EB63CD9F52E88','10000028','440801199601174763',0,0),(35,'梁修贤','E5E204CCDD7E7C33FBAE908C20343168','10000029','420115197911132734',0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-25 14:27:29
