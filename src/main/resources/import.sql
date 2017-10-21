LOCK TABLES `abstract_owner` WRITE;
UNLOCK TABLES;
LOCK TABLES `address` WRITE;
UNLOCK TABLES;
DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars` (  `id` int(11) NOT NULL,  `id_address` int(11) DEFAULT NULL,  `model` varchar(45) DEFAULT NULL,  `year` int(11) DEFAULT NULL,  `vin` varchar(45) DEFAULT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `cars` WRITE;

INSERT INTO `cars` VALUES (1,1,'Mazda',2000,'JB423S'),(2,2,'Mazda',2005,'J2423S'),(3,3,'Volvo',2005,'JBAA23S'),(4,4,'Mazda',2006,'KOLS223'),(5,5,'Volvo',2000,'OKLS223');

UNLOCK TABLES;

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (100000,17,'',37,'Ukraine','Drogobutskiy district','Boryslav','83200','Lviv region','Kovaliva'),(100001,68,'A',130,'Ukraine','Drogobutskiy district','Boryslav','83200','Lviv region','Volodymyra Velykoho'),(100002,25,'',50,'Ukraine','Drogobutskiy district','Boryslav','83200','Lviv region','Dovzhenka'),(100100,6,'',65,'Ukraine','No district','Lviv City','79068','Lviv region','Mazepu'),(100101,1,'Б',56,'Ukraine','Mukachivskuy','Mukacheve','89611','Uzhgorod','Zelena');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `abstract_owner` WRITE;
/*!40000 ALTER TABLE `abstract_owner` DISABLE KEYS */;
INSERT INTO `abstract_owner` VALUES (100000,'380978456321',100000),(100001,'380659814321',100100),(100002,'380671225012',100101),(100100,'380978456321',100001),(100101,'380938001667',100002);
/*!40000 ALTER TABLE `abstract_owner` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `company_owner` WRITE;
/*!40000 ALTER TABLE `company_owner` DISABLE KEYS */;
INSERT INTO `company_owner` VALUES ('Petro Petrenko','Blyzenko','TzOV','None',100001),('Ivan Ivanchencko','Sunshine','TzOV','Sunny',100100);
/*!40000 ALTER TABLE `company_owner` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `person_owner` WRITE;
/*!40000 ALTER TABLE `person_owner` DISABLE KEYS */;
INSERT INTO `person_owner` VALUES ('Andriy','Tsebak','Anatoliiovych','659832','HC',100000),('Anna','Frank','Volodumurivna','145863','TC',100002),('Oleh','Tsebak','Anatoliiovych','176895','KC',100101);
/*!40000 ALTER TABLE `person_owner` ENABLE KEYS */;
UNLOCK TABLES;




LOCK TABLES `document` WRITE;

INSERT INTO `document` VALUES (100000,'fileE4953FCCD6','https://filee4953fccd6.s3.eu-central-1.amazonaws.com/fileE4953FCCD6.png','png'),(100001,'fileE4953FCCD6','https://filee4953fccd6.s3.eu-central-1.amazonaws.com/fileE4953FCCD6.png','png'),(100002,'fileB1139539DE','https://fileb1139539de.s3.eu-central-1.amazonaws.com/fileB1139539DE.jpg','jpeg'),(100003,'fileB1139539DE','https://fileb1139539de.s3.eu-central-1.amazonaws.com/fileB1139539DE.jpg','jpeg'),(100004,'fileE7D27D53E3','https://filee7d27d53e3.s3.eu-central-1.amazonaws.com/fileE7D27D53E3.jpg','jpeg'),(100005,'fileE7D27D53E3','https://filee7d27d53e3.s3.eu-central-1.amazonaws.com/fileE7D27D53E3.jpg','jpeg'),(100006,'fileE7510CF709','https://filee7510cf709.s3.eu-central-1.amazonaws.com/fileE7510CF709.pdf','pdf'),(100007,'fileE7510CF709','https://filee7510cf709.s3.eu-central-1.amazonaws.com/fileE7510CF709.pdf','pdf'),(100008,'fileE36C95C4B0','https://filee36c95c4b0.s3.eu-central-1.amazonaws.com/fileE36C95C4B0.pdf','pdf'),(100009,'fileE36C95C4B0','https://filee36c95c4b0.s3.eu-central-1.amazonaws.com/fileE36C95C4B0.pdf','pdf'),(100010,'file1491253D30','https://file1491253d30.s3.eu-central-1.amazonaws.com/file1491253D30.pdf','pdf'),(100011,'file1491253D30','https://file1491253d30.s3.eu-central-1.amazonaws.com/file1491253D30.pdf','pdf'),(100012,'file9762C2E284','https://file9762c2e284.s3.eu-central-1.amazonaws.com/file9762C2E284.pdf','pdf'),(100013,'file9762C2E284','https://file9762c2e284.s3.eu-central-1.amazonaws.com/file9762C2E284.pdf','pdf'),(100014,'fileA26FEB14C8','https://filea26feb14c8.s3.eu-central-1.amazonaws.com/fileA26FEB14C8.jpg','jpeg'),(100015,'fileA26FEB14C8','https://filea26feb14c8.s3.eu-central-1.amazonaws.com/fileA26FEB14C8.jpg','jpeg'),(100016,'file3A8DCCC7A2','https://file3a8dccc7a2.s3.eu-central-1.amazonaws.com/file3A8DCCC7A2.pdf','pdf'),(100017,'file3A8DCCC7A2','https://file3a8dccc7a2.s3.eu-central-1.amazonaws.com/file3A8DCCC7A2.pdf','pdf'),(100018,'file84528708D2','https://file84528708d2.s3.eu-central-1.amazonaws.com/file84528708D2.pdf','pdf'),(100019,'file84528708D2','https://file84528708d2.s3.eu-central-1.amazonaws.com/file84528708D2.pdf','pdf'),(100020,'file7D167B8D68','https://file7d167b8d68.s3.eu-central-1.amazonaws.com/file7D167B8D68.pdf','pdf'),(100021,'file7D167B8D68','https://file7d167b8d68.s3.eu-central-1.amazonaws.com/file7D167B8D68.pdf','pdf'),(100022,'file0F12508D26','https://file0f12508d26.s3.eu-central-1.amazonaws.com/file0F12508D26.jpg','jpeg'),(100023,'file0F12508D26','https://file0f12508d26.s3.eu-central-1.amazonaws.com/file0F12508D26.jpg','jpeg'),(100024,'file656F3EBBA6','https://file656f3ebba6.s3.eu-central-1.amazonaws.com/file656F3EBBA6.pdf','pdf'),(100025,'file656F3EBBA6','https://file656f3ebba6.s3.eu-central-1.amazonaws.com/file656F3EBBA6.pdf','pdf'),(100026,'file552F1B46F3','https://file552f1b46f3.s3.eu-central-1.amazonaws.com/file552F1B46F3.pdf','pdf'),(100027,'file552F1B46F3','https://file552f1b46f3.s3.eu-central-1.amazonaws.com/file552F1B46F3.pdf','pdf'),(100028,'file02E4ABCF4B','https://file02e4abcf4b.s3.eu-central-1.amazonaws.com/file02E4ABCF4B.pdf','pdf'),(100029,'file02E4ABCF4B','https://file02e4abcf4b.s3.eu-central-1.amazonaws.com/file02E4ABCF4B.pdf','pdf'),(100030,'file831B25A80F','https://file831b25a80f.s3.eu-central-1.amazonaws.com/file831B25A80F.jpg','jpeg'),(100031,'file831B25A80F','https://file831b25a80f.s3.eu-central-1.amazonaws.com/file831B25A80F.jpg','jpeg'),(100032,'fileB1F546B30B','https://fileb1f546b30b.s3.eu-central-1.amazonaws.com/fileB1F546B30B.pdf','pdf'),(100033,'fileB1F546B30B','https://fileb1f546b30b.s3.eu-central-1.amazonaws.com/fileB1F546B30B.pdf','pdf'),(100034,'fileDC3E571809','https://filedc3e571809.s3.eu-central-1.amazonaws.com/fileDC3E571809.pdf','pdf'),(100035,'fileDC3E571809','https://filedc3e571809.s3.eu-central-1.amazonaws.com/fileDC3E571809.pdf','pdf'),(100036,'file5156C5E8A8','https://file5156c5e8a8.s3.eu-central-1.amazonaws.com/file5156C5E8A8.pdf','pdf'),(100037,'file5156C5E8A8','https://file5156c5e8a8.s3.eu-central-1.amazonaws.com/file5156C5E8A8.pdf','pdf'),(100038,'file7B801B4410','https://file7b801b4410.s3.eu-central-1.amazonaws.com/file7B801B4410.pdf','pdf'),(100039,'file7B801B4410','https://file7b801b4410.s3.eu-central-1.amazonaws.com/file7B801B4410.pdf','pdf'),(100040,'fileE18E7712AF','https://filee18e7712af.s3.eu-central-1.amazonaws.com/fileE18E7712AF.pdf','pdf'),(100041,'fileE18E7712AF','https://filee18e7712af.s3.eu-central-1.amazonaws.com/fileE18E7712AF.pdf','pdf'),(100042,'file4EC9F05E56','https://file4ec9f05e56.s3.eu-central-1.amazonaws.com/file4EC9F05E56.pdf','pdf'),(100043,'file4EC9F05E56','https://file4ec9f05e56.s3.eu-central-1.amazonaws.com/file4EC9F05E56.pdf','pdf');

UNLOCK TABLES;









LOCK TABLES `resource_categories` WRITE;

INSERT INTO `resource_categories` VALUES (134310,'Natural resources',NULL),(134311,'Land resources',134310),(134312,'Forest resources',134310),(134313,'Mineral resources',134310),(134314,'Water resources',134310),(134315,'Real estate',NULL),(134316,'Residental real estate',134315),(134317,'Non-residental real estate',134315),(134318,'Coininercial non-residental real estate',134317),(134319,'Industrial non-residental real estate',134317),(134320,'Transport',NULL),(134321,'Water transport',134320),(134322,'Sea transport',134321),(134323,'River transport',134321),(134324,'Air transport',134320),(134325,'Reactive airtransport',134324),(134326,'Land transport',134320),(134327,'Autotransport',134326),(134329,'Propeller airtransport',134324),(134330,'Mototransport',134326),(134331,'Railway transport',134326);

UNLOCK TABLES;



LOCK TABLES `resource_properties` WRITE;

INSERT INTO `resource_properties` VALUES (100200,'model','\0','[A-Za-z0-9]{2,20}','Model','','','STRING'),(100203,'year','\0','[0-9]{4,20}','Year','digits','','STRING'),(100204,'weight','\0','\\d+\\.\\d{1,3}','Weight','kilograms','kg','DOUBLE'),(100205,'load_capacity','\0','\\d+','Load-carrying capacity','kilograms','kg','INTEGER'),(100206,'vin','\0','[A-Z0-9]+','VIN',NULL,NULL,'STRING');

UNLOCK TABLES;


LOCK TABLES `user_account` WRITE;

INSERT INTO `user_account` VALUES (8,'','$2a$11$kGAnapbAjKje5p5vs.uHnOIgsFBvIgso9BKq9xGuzanFPX6YWP0T.','gbfgb','dbuser8@gmail.com'),(9,'','$2a$11$kGAnapbAjKje5p5vs.uHnOIgsFBvIgso9BKq9xGuzanFPX6YWP0T.','bbfgb','dbuser9@gmail.com'),(88,'','$2a$11$kGAnapbAjKje5p5vs.uHnOIgsFBvIgso9BKq9xGuzanFPX6YWP0T.','bgb','dbuser88@gmail.com'),(99,'','$2a$11$kGAnapbAjKje5p5vs.uHnOIgsFBvIgso9BKq9xGuzanFPX6YWP0T.','gbgfb','dbuser99@gmail.com');

UNLOCK TABLES;


LOCK TABLES `resource_request` WRITE;

INSERT INTO `resource_request` VALUES (100100,'Street varshavska 12','House Private','DECLINED','2017-10-10 21:11:05',100001,9,8),(100101,'KS095555','Car Vaz09','NEW','2017-10-10 21:11:47',100003,9,NULL),(100102,'Number registration 222999','Chair green','DECLINED','2017-10-10 21:12:19',100005,9,88),(100103,'smt. Volos\'anka','Forest in the village','ACCEPTED','2017-10-10 21:13:07',100007,9,88),(100104,'Number registration 2266677','Laptop Lenovo','DECLINED','2017-10-10 21:14:05',100009,9,8),(100105,'Number registration 2667888','Personal Computer','ACCEPTED','2017-10-10 21:14:37',100011,9,88),(100106,'KS09577898','Tevevition LG','NEW','2017-10-10 21:15:15',100013,9,NULL),(100107,'Viz Sertification','Telefon Nokia','NEW','2017-10-10 21:15:53',100015,9,8),(100108,'Number registration 288999','Table made from wood','NEW','2017-10-10 21:16:37',100017,9,88),(100109,'Number registration 222999666','Door big one','NEW','2017-10-10 21:17:08',100019,9,8),(100110,'Number registration 2229997777','Shooes two pieces','NEW','2017-10-10 21:18:40',100021,9,88),(100111,'smt. hoover','Lake Amazonka','NEW','2017-10-10 21:19:16',100023,9,8),(100112,'Number registration 2229997777777','Mouse Logitech','NEW','2017-10-10 21:19:37',100025,9,88),(100113,'Number registration 22299955','Keybord logitech','NEW','2017-10-10 21:20:19',100027,9,88),(100114,'Lonetto','Sound System','NEW','2017-10-10 21:20:45',100029,9,NULL),(100115,'Number registration 2229997777','Car Mercedes','NEW','2017-10-10 21:21:05',100031,9,NULL),(100116,'Number registration 2229997777','Car Chevrolet','NEW','2017-10-10 21:21:28',100033,9,88),(100117,'Number registration 2229997777','Resousce type ','NEW','2017-10-10 21:28:43',100035,9,NULL),(100118,'Number registration 2229997777','Telefon Lg','NEW','2017-10-10 21:29:01',100037,9,8),(100119,'Number registration 2229997777','Telefon ifon','NEW','2017-10-10 21:29:19',100039,9,NULL),(100120,'Number registration 22299955555','Ipad','NEW','2017-10-10 21:29:35',100041,9,8),(100121,'Number registration 22299955','Ifon 7','NEW','2017-10-10 21:29:49',100043,9,8);

UNLOCK TABLES;




--LOCK TABLES `resource_sequence` WRITE;

--INSERT INTO `resource_sequence` VALUES (100400),(100400),(100400),(100400),(100400),(100400),(100400),(100400),(100400),(100400),(100400),(100400);

--UNLOCK TABLES;




LOCK TABLES `resource_types` WRITE;

INSERT INTO `resource_types` VALUES (100300,'','cars','Car',134327),(100302,'','yachts','Yacht',134323),(100303,'','trains','Train',134331),(124000,'\0','vehicles','Vehicle',134327),(124001,'\0','trucks','Truck',134327),(124002,'\0','apartments','Apartment',134316),(124003,'\0','cottages','Cottage',134316);

UNLOCK TABLES;



LOCK TABLES `role` WRITE;

INSERT INTO `role` VALUES (4,'lk','ROLE_ADMIN'),(5,'','ROLE_USER'),(6,'vv','ROLE_SYSTEM_ADMIN'),(7,'mmm','ROLE_SYSTEM_ADMIN_CUSTOM'),(8,'hhh','ROLE_RESOURCE_ADMIN'),(9,'hhh','ROLE_REGISTRATOR');

UNLOCK TABLES;



LOCK TABLES `roles_privileges` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `trains`
--

DROP TABLE IF EXISTS `trains`;

CREATE TABLE `trains` (  `id` int(11) NOT NULL,  `id_address` int(11) DEFAULT NULL,  `model` varchar(45) DEFAULT NULL,  `year` int(11) DEFAULT NULL,  `weight` decimal(10,2) DEFAULT NULL,  `load_capacity` int(11) DEFAULT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;



LOCK TABLES `trains` WRITE;

INSERT INTO `trains` VALUES (1,1,'Hobbylinc',2012,3000.00,5000),(2,2,'Hyundai',2009,4000.00,10000),(3,3,'Kato',2009,2500.00,6000),(4,4,'Kato',2010,2000.00,4000),(5,5,'Apper',2005,5000.00,5000),(6,6,'Apper',2004,6000.00,4000);

UNLOCK TABLES;



LOCK TABLES `type_properties` WRITE;

INSERT INTO `type_properties` VALUES (100300,100200,'',''),(100302,100200,'',''),(100303,100200,'',''),(124001,100200,'',''),(100300,100203,'',''),(100302,100203,'',''),(100303,100203,'',''),(124001,100203,'',''),(100302,100204,'',''),(100303,100204,'',''),(100303,100205,'',''),(124001,100205,'',''),(100300,100206,'','');

UNLOCK TABLES;







LOCK TABLES `user_details` WRITE;

INSERT INTO `demotest`.`user_details` (`id`, `bank_id`, `date_of_issue`, `first_name`, `id_address`, `issued_by`, `middle_name`, `passport_number`, `passport_series`, `phone`, `second_name`, `id_user`) VALUES ('9', '9', '2012-12-12', 'FirstName', '9', '9', 'MiddleName', '123456', 'AB', '+12(345)-678-90-12', 'SecondName', '9');

UNLOCK TABLES;





LOCK TABLES `users_roles` WRITE;

INSERT INTO `users_roles` VALUES (8,8),(88,8),(9,9),(99,9);

UNLOCK TABLES;



DROP TABLE IF EXISTS `yachts`;

CREATE TABLE `yachts` (  `id` int(11) NOT NULL,  `id_address` int(11) DEFAULT NULL,  `model` varchar(45) DEFAULT NULL,  `year` int(11) DEFAULT NULL,  `weight` decimal(10,2) DEFAULT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;



LOCK TABLES `yachts` WRITE;

INSERT INTO `yachts` VALUES (1,1,'Sailing',2010,2000.00),(2,2,'Vintage',2012,3000.00),(3,3,'Woodle',2010,2500.00),(4,4,'Mario',2012,2300.00),(5,5,'Grand',2013,2200.00);

UNLOCK TABLES;
