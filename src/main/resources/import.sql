DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars` (  `id` int(11) NOT NULL,  `model` varchar(45) DEFAULT NULL,  `year` int(11) DEFAULT NULL,  `vin` varchar(45) DEFAULT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `cars` (`id`,`model`,`year`,`vin`) VALUES (1,'Mazda',2000,'JB423S'),(2,'Mazda',2005,'J2423S'),(3,'Volvo',2005,'JBAA23S'),(4,'Mazda',2006,'KOLS223'),(5,'Volvo',2000,'OKLS223');

INSERT INTO `address`(`id`,`apartment`,`block`,`building`,`country`,`district`,`locality`,`postal_index`,`region`,`street`) VALUES (100000,17,'',37,'Ukraine','Drogobutskiy district','Boryslav','83200','Lviv region','Kovaliva'),(100001,68,'A',130,'Ukraine','Drogobutskiy district','Boryslav','83200','Lviv region','Volodymyra Velykoho'),(100002,25,'',50,'Ukraine','Drogobutskiy district','Boryslav','83200','Lviv region','Dovzhenka'),(100100,6,'',65,'Ukraine','No district','Lviv City','79068','Lviv region','Mazepu'),(100101,1,'',56,'Ukraine','Mukachivskuy','Mukacheve','89611','Uzhgorod','Zelena');

INSERT INTO `abstract_owner` (`owner_id`, `phone`, `address_id`) VALUES (100000,'380978456321',100000),(100001,'380659814321',100100),(100002,'380671225012',100101),(100100,'380978456321',100001),(100101,'380938001667',100002);

INSERT INTO `company` (`CEO`,`edrpo_number`,`full_name`,`organization_form`,`short_name`,`owner_id`) VALUES ('Petro Petrenko', '12345678','Blyzenko','TzOV','None',100001),('Ivan Ivanchencko','12345679','Sunshine','TzOV','Sunny',100100);

INSERT INTO `person` (`first_name`, `identifier_number`,`last_name`,`middle_name`,`passport_number`,`passport_series`,`owner_id`) VALUES ('Andriy','1234567890','Tsebak','Anatoliiovych','659832','HC',100000),('Anna','1234567899','Frank','Volodumurivna','145863','TC',100002),('Oleh','1234567897','Tsebak','Anatoliiovych','176895','KC',100101);

INSERT INTO `document` (`id_document`,`code`,`URL`,`extension`) VALUES (100001,'fileE4953FCCD6','some text here','png');

INSERT INTO `RESOURCE_CATEGORIES` (`Id`,`Category_Name`,`Id_Parent`) VALUES (134310,'Natural resources',NULL),(134311,'Land resources',134310),(134312,'Forest resources',134310),(134313,'Mineral resources',134310),(134314,'Water resources',134310),(134315,'Real estate',NULL),(134316,'Residental real estate',134315),(134317,'Non-residental real estate',134315),(134318,'Commercial non-residental real estate',134317),(134319,'Industrial non-residental real estate',134317),(134320,'Transport',NULL),(134321,'Water transport',134320),(134322,'Sea transport',134321),(134323,'River transport',134321),(134324,'Air transport',134320),(134325,'Reactive airtransport',134324),(134326,'Land transport',134320),(134327,'Autotransport',134326),(134329,'Propeller airtransport',134324),(134330,'Mototransport',134326),(134331,'Railway transport',134326);

INSERT INTO `RESOURCE_PROPERTIES` (`id`,`Column_Name`,`Hint`,`Multivalued`,`Regex`,`Title`,`Units`,`Units_short`,`Value_Type`) VALUES (100200,'model','letters,digits,e.g: Mazda6','','[A-Za-z0-9]{2,20}','Model','','','STRING'),(100203,'year','only digits, e.g: 2004','','[0-9]{4,20}','Year','digits','','STRING'),(100204,'weight','only double, e.g: 200.20(not more than 3 digits after dot)','','\\d+\\.\\d{1,3}','Weight','kilograms','kg','DOUBLE'),(100205,'load_capacity','only integers, e.g: 2000','','\\d+','Load-carrying capacity','kilograms','kg','INTEGER'),(100206,'vin','only letters with upper case and integers, e.g: V2WWW45KP','','[A-Z0-9]+','VIN',NULL,NULL,'STRING');

INSERT INTO `user_account` (`id`,`enabled`,`password`,`secret`,`email`) VALUES (8, TRUE,'$2a$11$kGAnapbAjKje5p5vs.uHnOIgsFBvIgso9BKq9xGuzanFPX6YWP0T.','gbfgb','dbuser8@gmail.com'),(9,TRUE,'$2a$11$kGAnapbAjKje5p5vs.uHnOIgsFBvIgso9BKq9xGuzanFPX6YWP0T.','bbfgb','dbuser9@gmail.com'),(88,TRUE,'$2a$11$kGAnapbAjKje5p5vs.uHnOIgsFBvIgso9BKq9xGuzanFPX6YWP0T.','bgb','dbuser88@gmail.com'),(99,TRUE,'$2a$11$kGAnapbAjKje5p5vs.uHnOIgsFBvIgso9BKq9xGuzanFPX6YWP0T.','gbgfb','dbuser99@gmail.com');

INSERT INTO `RESOURCE_REQUEST` (`id_request`,`description`,`resourceType`,`status`,`updated`,`id_document`,`id_requester`,`id_assigner`) VALUES (100100,'Street varshavska 12','House Private','DECLINED', '2017-10-10' ,100001,9,8);

INSERT INTO `RESOURCE_TYPES` (`Id`,`Instantiated`,`Table_Name`,`Type_Name`,`Id_Assigner`,`Id_Category`) VALUES (100300, TRUE,'cars','Car',8,134327),(100302,TRUE,'yachts','Yacht',8,134323),(100303,TRUE,'trains','Train',88,134331),(100304,'','vehicles','Vehicle',8,134327),(100305,'','trucks','Truck',88,134327),(100306,'','apartments','Apartment',88,134316),(100307,'','cottages','Cottage',8,134316);

INSERT INTO `role` (`id`,`description`,`name`) VALUES (4,'lk','ROLE_ADMIN'),(5,'','ROLE_USER'),(6,'vv','ROLE_SYSTEM_ADMIN'),(7,'mmm','ROLE_SYSTEM_ADMIN_CUSTOM'),(8,'hhh','ROLE_RESOURCE_ADMIN'),(9,'hhh','ROLE_REGISTRATOR');

DROP TABLE IF EXISTS `trains`;

CREATE TABLE `trains` (  `id` int(11) NOT NULL,  `model` varchar(45) DEFAULT NULL,  `year` int(11) DEFAULT NULL,  `weight` decimal(10,2) DEFAULT NULL,  `load_capacity` int(11) DEFAULT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `trains` (`id`,`model`,`year`,`weight`,`load_capacity`) VALUES (1,'Hobbylinc',2012,3000.00,5000),(2,'Hyundai',2009,4000.00,10000),(3,'Kato',2009,2500.00,6000),(4,'Kato',2010,2000.00,4000),(5,'Apper',2005,5000.00,5000),(6,'Apper',2004,6000.00,4000);

INSERT INTO `Type_Properties` (`Type_Id`,`Id_Property`,`Required`,`Searchable`) VALUES (100300,100200,'',TRUE),(100302,100200,'',TRUE),(100303,100200,'',TRUE),(100304,100200,'',TRUE),(100300,100203,'',TRUE),(100302,100203,'',TRUE),(100303,100203,'',TRUE),(100304,100203,'',TRUE),(100302,100204,'',TRUE),(100303,100204,'',TRUE),(100303,100205,'',TRUE),(100304,100205,'',TRUE),(100300,100206,'',TRUE);

INSERT INTO `demotest`.`user_details` (`id`, `bank_id`, `date_of_issue`, `first_name`, `id_address`, `issued_by`, `middle_name`, `passport_number`, `passport_series`, `phone`, `second_name`, `id_user`) VALUES ('9', '9', '2012-12-12', 'FirstName', '9', '9', 'MiddleName', '123456', 'AB', '+12(345)-678-90-12', 'SecondName', '9');

INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (8,8),(88,8),(9,9),(99,9);

DROP TABLE IF EXISTS `yachts`;

CREATE TABLE `yachts` (  `id` int(11) NOT NULL,  `model` varchar(45) DEFAULT NULL,  `year` int(11) DEFAULT NULL,  `weight` decimal(10,2) DEFAULT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `yachts` (`id`,`model`,`year`,`weight`) VALUES (1,'Sailing',2010,2000.00),(2,'Vintage',2012,3000.00),(3,'Woodle',2010,2500.00),(4,'Mario',2012,2300.00),(5,'Grand',2013,2200.00);

INSERT INTO `RESOURCES` (`Id`,`ADDRESS_ID`) VALUES (1,100000),(2,100001),(3,100002),(4,100100);

INSERT INTO `RESOURCE_OWNINGS` (`Id`,`OWNER_ID`,`RESOURCE_ID`,`RESOURCETYPE_ID`) VALUES (1,100001,1,100300),(2,100001,2,100300),(3,100100,1,100300),(4,100100,3,100302),(5,100100,4,100303);


