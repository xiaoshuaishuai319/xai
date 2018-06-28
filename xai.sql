# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.22)
# Database: xai
# Generation Time: 2018-06-28 14:35:04 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table ai_bd_dish
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ai_bd_dish`;

CREATE TABLE `ai_bd_dish` (
  `icrId` int(11) NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `resultNum` int(11) DEFAULT NULL,
  `calorie` varchar(255) DEFAULT NULL,
  `hasCalorie` varchar(100) DEFAULT NULL,
  `dishName` varchar(255) DEFAULT NULL,
  `probability` varchar(255) DEFAULT NULL,
  `imagePath` varchar(1000) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL COMMENT '入口类型 web wcs',
  PRIMARY KEY (`icrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ai_bd_dish` WRITE;
/*!40000 ALTER TABLE `ai_bd_dish` DISABLE KEYS */;

INSERT INTO `ai_bd_dish` (`icrId`, `logId`, `resultNum`, `calorie`, `hasCalorie`, `dishName`, `probability`, `imagePath`, `openId`, `nikeName`, `enterType`)
VALUES
	(7,'4469858353423891234',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1525962406.jpg',NULL,NULL,'web'),
	(8,'2331219107666343514',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1526396333.jpg',NULL,NULL,'web'),
	(9,'8128821674316229359',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1526396345.jpg',NULL,NULL,'web'),
	(10,'8793176724020262223',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1529419123.jpg',NULL,NULL,'wcs'),
	(11,'1216201356377266754',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1529419134.jpg',NULL,NULL,'wcs'),
	(12,'868531977897502009',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1529419748.jpg',NULL,NULL,'wsc'),
	(13,'2226192319812611736',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1529419793.jpg',NULL,NULL,'wsc'),
	(14,'2983315820613810620',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1529505256.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','wsc'),
	(15,'648513983153541761',1,'884','true','牛油果沙拉','0.322625','/icrDish/icrBD1530112083.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','wsc');

/*!40000 ALTER TABLE `ai_bd_dish` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ai_bd_face
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ai_bd_face`;

CREATE TABLE `ai_bd_face` (
  `faceId` int(11) NOT NULL AUTO_INCREMENT,
  `errorCode` varchar(255) DEFAULT NULL,
  `errorMsg` varchar(255) DEFAULT NULL,
  `logId` varchar(255) DEFAULT NULL,
  `timestamp` varchar(255) DEFAULT NULL,
  `cached` int(255) DEFAULT NULL,
  `faceNum` int(11) DEFAULT NULL,
  `faceToken` varchar(255) DEFAULT NULL,
  `faceProbability` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `beauty` varchar(255) DEFAULT NULL COMMENT '美丑打分，范围0-100，越大表示越美。',
  `expressionType` varchar(255) DEFAULT NULL COMMENT 'none:不笑；smile:微笑；laugh:大笑',
  `faceShapeType` varchar(255) DEFAULT NULL COMMENT '脸型 square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形',
  `gender` varchar(255) DEFAULT NULL COMMENT 'male:男性 female:女性',
  `glassesType` varchar(255) DEFAULT NULL COMMENT 'none:无眼镜，common:普通眼镜，sun:墨镜',
  `raceType` varchar(255) DEFAULT NULL COMMENT 'yellow: 黄种人 white: 白种人 black:黑种人 arabs: 阿拉伯人',
  `openId` varchar(255) DEFAULT NULL COMMENT '微信openid',
  `nikeName` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `imagePath` varchar(1000) DEFAULT NULL COMMENT '可以是本地路径 远程路径 具体根据实际业务来',
  PRIMARY KEY (`faceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ai_bd_face` WRITE;
/*!40000 ALTER TABLE `ai_bd_face` DISABLE KEYS */;

INSERT INTO `ai_bd_face` (`faceId`, `errorCode`, `errorMsg`, `logId`, `timestamp`, `cached`, `faceNum`, `faceToken`, `faceProbability`, `age`, `beauty`, `expressionType`, `faceShapeType`, `gender`, `glassesType`, `raceType`, `openId`, `nikeName`, `imagePath`)
VALUES
	(28,'0','SUCCESS','2267528118','1528816284',0,1,'c739e29fc9b2bf58a18b63ae3868b237','0',18,'65.46601868','none','heart','female','none','yellow','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','/face/faceV3BD1528816282.jpeg'),
	(29,'0','SUCCESS','4137833425','1529414807',0,1,'2a5781540fb973bc2af81fd2f4e8915f','1',14,'27.21316338','none','square','male','none','yellow','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','/face/faceV3BD1529414807.jpeg'),
	(30,'0','SUCCESS','565353545997','1530023626',0,1,'4db3bf7b672fddb0dc647040d60ab567','1',35,'48.57863998','none','square','female','none','white','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','/face/faceV3BD1530023626.jpg');

/*!40000 ALTER TABLE `ai_bd_face` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ai_bd_icrfuse
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ai_bd_icrfuse`;

CREATE TABLE `ai_bd_icrfuse` (
  `icrId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `resultNum` int(11) DEFAULT NULL,
  `icrName` varchar(255) DEFAULT NULL,
  `score` varchar(100) DEFAULT NULL,
  `Pyear` varchar(100) DEFAULT NULL,
  `colorResult` varchar(100) DEFAULT NULL,
  `localWidth` int(11) DEFAULT NULL,
  `localHeight` int(11) DEFAULT NULL,
  `localTop` int(11) DEFAULT NULL,
  `localLeft` int(11) DEFAULT NULL,
  `logoType` varchar(50) DEFAULT NULL,
  `probability` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(100) DEFAULT NULL,
  `nikeName` varchar(500) DEFAULT NULL,
  `enterType` varchar(50) DEFAULT NULL,
  `apiType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`icrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ai_bd_icrfuse` WRITE;
/*!40000 ALTER TABLE `ai_bd_icrfuse` DISABLE KEYS */;

INSERT INTO `ai_bd_icrfuse` (`icrId`, `logId`, `resultNum`, `icrName`, `score`, `Pyear`, `colorResult`, `localWidth`, `localHeight`, `localTop`, `localLeft`, `logoType`, `probability`, `imagePath`, `openId`, `nikeName`, `enterType`, `apiType`)
VALUES
	(1,NULL,NULL,'宝马X6','0.82910943031311','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1526395402.jpg',NULL,NULL,'web','car'),
	(2,NULL,NULL,'俄罗斯蓝猫','0.869141',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrAnimal/icrBD1526396408.jpg',NULL,NULL,'web','animal'),
	(3,NULL,NULL,'宝马X6','0.82910943031311','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1526396478.jpg',NULL,NULL,'web','car'),
	(4,NULL,NULL,'匠汇集',NULL,NULL,NULL,26,16,240,240,'1','0.21738922119141','/icrLogo/icrBD1526396527.jpg',NULL,NULL,'web','logo'),
	(5,NULL,NULL,'红爪','0.52708631753922',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1526396544.jpeg',NULL,NULL,'web','plant'),
	(6,'3697289425431874414',3,'好乐买',NULL,NULL,NULL,45,63,26,26,'1','0.12543837327224','/icrLogo/icrBD1526397566.jpeg',NULL,NULL,'web','logo'),
	(7,'6829410080498058347',1,'西葫芦','0.6414338350296',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrIngredient/icrBD1526479875.jpg',NULL,NULL,'web','ingredient'),
	(8,'71818440341813105',NULL,' 红爪','0.52722769975662',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1526996016.jpeg',NULL,NULL,'web','plant'),
	(9,'8787199200399098809',NULL,' 红爪','0.52722769975662',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1526996022.jpeg',NULL,NULL,'web','plant'),
	(10,'6213833147870837478',NULL,' 红爪','0.52722769975662',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1526996023.jpeg',NULL,NULL,'web','plant'),
	(11,'617793941249173087',NULL,' 红爪','0.527228474617',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1526996024.jpeg',NULL,NULL,'web','plant'),
	(12,'974405947134131692',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1529501717.jpg',NULL,NULL,'wcs','car'),
	(13,'3136101768519437753',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1529501723.jpg',NULL,NULL,'wcs','car'),
	(14,'8522723153371832547',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1529501725.jpg',NULL,NULL,'wcs','car'),
	(15,'1415328028926223243',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1529501726.jpg',NULL,NULL,'wcs','car'),
	(16,'8067218700457910255',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1529501728.jpg',NULL,NULL,'wcs','car'),
	(17,'1899592488310157083',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1529502874.jpg',NULL,NULL,'wcs','car'),
	(18,'5105459253888208095',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1529503106.jpg',NULL,NULL,'wsc','car'),
	(19,'2712401642970079381',NULL,' 红爪','0.52722769975662',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1529503198.jpeg',NULL,NULL,'wcs','plant'),
	(20,'5283198016034310013',NULL,' 红爪','0.527228474617',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1529503587.jpeg',NULL,NULL,'wsc','plant'),
	(21,'2668012078729782720',NULL,'俄罗斯蓝猫','0.900236',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrAnimal/icrBD1529503621.jpg',NULL,NULL,'wcs','animal'),
	(22,'4909285853755985255',NULL,'俄罗斯蓝猫','0.900236',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrAnimal/icrBD1529504058.jpg',NULL,NULL,'wsc','animal'),
	(23,'7909721516667171995',NULL,'俄罗斯蓝猫','0.900236',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrAnimal/icrBD1529504112.jpg',NULL,NULL,'wsc','animal'),
	(24,'4115871906987965637',NULL,'俄罗斯蓝猫','0.900236',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrAnimal/icrBD1529504444.jpg',NULL,NULL,'wsc','animal'),
	(25,'4449656786413799227',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1529505267.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','wsc','car'),
	(26,'4098741631430619754',NULL,' 红爪','0.527228474617',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1529505276.jpeg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','wsc','plant'),
	(27,'7495563487449622095',NULL,'俄罗斯蓝猫','0.900236',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrAnimal/icrBD1529505288.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','wsc','animal'),
	(28,'3004452361317695708',3,'宇朔',NULL,NULL,NULL,158,81,257,257,'1','0.23676879882813','/icrLogo/icrBD1529505384.png','','','wcs','logo'),
	(29,'4922994809395688338',3,'威克多',NULL,NULL,NULL,22,16,130,130,'1','0.17916635366587','/icrLogo/icrBD1529505668.jpg','','','wcs','logo'),
	(30,'8589429329847125438',1,'好丽友',NULL,NULL,NULL,348,296,125,125,'0','0.33739825696276','/icrLogo/icrBD1529505678.jpg','','','wcs','logo'),
	(31,'2313362101957997103',3,'创康牛牛',NULL,NULL,NULL,144,69,25,25,'1','0.28404907226562','/icrLogo/icrBD1529505701.jpeg','','','wcs','logo'),
	(32,'291502941047212274',1,'好丽友',NULL,NULL,NULL,348,296,125,125,'0','0.33739825696276','/icrLogo/icrBD1529505843.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','wsc','logo'),
	(33,'8113648217635878640',1,'西葫芦','0.6414338350296',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrIngredient/icrBD1529505974.jpg','','','wcs','ingredient'),
	(34,'1342089172263543324',1,'西葫芦','0.6414338350296',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrIngredient/icrBD1529506293.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','wsc','ingredient'),
	(35,'7685779084951351016',NULL,' 红爪','0.527228474617',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1530110185.jpeg','','','wsc','plant'),
	(36,'4680658177713368614',NULL,' 红爪','0.527228474617',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1530111222.jpeg','','','wsc','plant'),
	(37,'7867527885460485506',NULL,' 红爪','0.527228474617',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1530111296.jpeg','','','wsc','plant'),
	(38,'1797382535674006281',NULL,' 红爪','0.527228474617',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrPlant/icrBD1530111414.jpeg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','wsc','plant'),
	(39,'6803994463160723641',NULL,'宝马X6','0.8291090130806','2008-2017','蓝色',390,237,140,140,NULL,NULL,'/icrCar/icrBD1530112123.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','wsc','car'),
	(40,'2805294152622082390',NULL,'俄罗斯蓝猫','0.900236',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrAnimal/icrBD1530112148.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','wsc','animal'),
	(41,'5679208255561968921',3,'立宇',NULL,NULL,NULL,119,78,72,72,'1','0.15580932617188','/icrLogo/icrBD1530112176.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','wsc','logo'),
	(42,'7306284832115947868',1,'西葫芦','0.6414338350296',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/icrIngredient/icrBD1530112196.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','wsc','ingredient');

/*!40000 ALTER TABLE `ai_bd_icrfuse` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ai_bd_ocr
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ai_bd_ocr`;

CREATE TABLE `ai_bd_ocr` (
  `ocrId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `errorCode` varchar(255) DEFAULT NULL,
  `errorMsg` varchar(255) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `wordsResultNum` int(11) DEFAULT NULL,
  `words` varchar(2000) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ocrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ai_bd_ocr` WRITE;
/*!40000 ALTER TABLE `ai_bd_ocr` DISABLE KEYS */;

INSERT INTO `ai_bd_ocr` (`ocrId`, `logId`, `errorCode`, `errorMsg`, `direction`, `wordsResultNum`, `words`, `imagePath`, `openId`, `nikeName`, `enterType`, `apiType`)
VALUES
	(6,'8780357022081957604','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/Users/zongxiaoshuai/Workspaces/MyEclipse2017CI/.metadata/.me_tcat85/webapps/mvcdo/ocrGeneral/ocrBD1526652424.png',NULL,NULL,'web','ocr'),
	(7,'2518654563593785974','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/Users/zongxiaoshuai/Workspaces/MyEclipse2017CI/.metadata/.me_tcat85/webapps/mvcdo/ocrGeneral/ocrBD1526652432.png',NULL,NULL,'web','ocr'),
	(8,'7492727543818507686','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/ocrGeneral/',NULL,NULL,'web','ocr'),
	(9,'6635941618917637739','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/ocrGeneral/ocrBD1526653116.png',NULL,NULL,'web','ocr'),
	(10,'7274616996995754710','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/ocrGeneral/ocrBD1526653187.png',NULL,NULL,'web','ocr'),
	(11,'3008174030358746337','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/ocrGeneral/ocrBD1526653204.png',NULL,NULL,'web','ocr'),
	(12,'8654089249732922646','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/ocrGeneral/ocrBD1526653205.png',NULL,NULL,'web','ocr'),
	(13,'4331531267744489658','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/ocrGeneral/ocrBD1526653210.png',NULL,NULL,'web','ocr'),
	(14,'3942980298494251495','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/ocrGeneral/ocrBD1526653214.png',NULL,NULL,'web','ocr'),
	(15,'3863257943224797287','0',NULL,0,11,'北京市国家税务局通和定额发票,北歌市政交通一卡通有限参司,发票联,发票代码111001777013,发票号码79047511,密码,伍拾元整91145381H,￥50.00,发票专用章,东印2017年06月印P840000本@50°577001-9%,0002836,','/ocrGeneral/ocrBD1526653214.png',NULL,NULL,'web','ocr'),
	(16,'4602902970040006886','0',NULL,0,1,'Ceske krumlov,','/ocrGeneral/ocrBD1526653336.png',NULL,NULL,'web','ocr'),
	(17,'8006785702414689984','0',NULL,0,1,'Ceske krumlov,','/ocrGeneral/ocrBD1526653344.png',NULL,NULL,'web','ocr'),
	(18,'6606094063097937577','0',NULL,0,1,'Ceske krumlov,','/ocrGeneral/ocrBD1526653558.png',NULL,NULL,'web','ocr'),
	(19,'2706677846350017166','0',NULL,0,1,'Ceske krumlov,','/ocrGeneral/ocrBD1526653564.png',NULL,NULL,'web','ocr'),
	(20,'2776613973605086229','0',NULL,0,1,'Ceske krumlov,','/ocrGeneral/ocrBD1526653611.png',NULL,NULL,'web','ocr'),
	(21,'4417052378281675137','0',NULL,NULL,3,'00天②影②,小帅、,博客,','/ocrHandWrite/ocrBD1527691152.png',NULL,NULL,'web','handwriting'),
	(22,'2835260364073570834','0',NULL,NULL,4,'003②影四,小帅,博客,》>>√XV×%cD②②,','/ocrHandWrite/ocrBD1527691629.png',NULL,NULL,'web','handwriting'),
	(23,'8023152142189253199','0',NULL,0,1,'8-了,','/ocrGeneral/ocrBD1529936164.jpg',NULL,NULL,'ocr','ocr'),
	(24,'1442336247427438808','0',NULL,0,5,'中华人民共和国,居民身份证,签发机关建瓯市公安局,有效期限2012.06.25-2022.06.25,汇图网wwwhuitu.com,','/ocrGeneral/ocrBD1529936183.jpg',NULL,NULL,'ocr','ocr'),
	(25,'2125268640966090782','0',NULL,0,5,'中华人民共和国,居民身份证,签发机关建瓯市公安局,有效期限2012.06.25-2022.06.25,汇图网wwwhuitu.com,','/ocrGeneral/ocrBD1529937860.jpg',NULL,NULL,'wsc','ocr'),
	(26,'8674652529319286542','0',NULL,NULL,4,'独坐敬亭山,李百,众鸟高飞尽,孤云独去闲。,相看两不厌,只有敬亭山.,','/ocrHandWrite/ocrBD1529938438.jpg',NULL,NULL,'wsc','handwriting'),
	(27,'2639858820096221484','0',NULL,3,1,'(,','/ocrGeneral/ocrBD1529939580.jpg',NULL,NULL,'wsc','ocr'),
	(28,'6278289837069651332','0',NULL,NULL,NULL,NULL,'/ocrGeneral/ocrBD1529939591.jpg',NULL,NULL,'wsc','ocr'),
	(29,'2742553981051608710','0',NULL,0,16,'中华人民共和国机动车驾驶证,Driving License of the People\'s Republic of China,446924197166122694,名黄卓,性别,男,国籍,M&I,中国,住址。广东省増城市新塘镇花园东路6号新世界,嘉华苑16座463房,厂东省东莞出生日期1971-96-12,市公安局交树次领证日期.211-95-23,通警察支队准车型,C工,有起始日期2011-65-23有微用限6年,','/ocrGeneral/ocrBD1529939774.jpeg',NULL,NULL,'wsc','ocr'),
	(30,'6284038912586719389','0',NULL,0,16,'中华人民共和国机动车驾驶证,Driving License of the People\'s Republic of China,446924197166122694,名黄卓,性别,男,国籍,M&I,中国,住址。广东省増城市新塘镇花园东路6号新世界,嘉华苑16座463房,厂东省东莞出生日期1971-96-12,市公安局交树次领证日期.211-95-23,通警察支队准车型,C工,有起始日期2011-65-23有微用限6年,','/ocrGeneral/ocrBD1529939835.jpeg',NULL,NULL,'wsc','ocr'),
	(31,'2999412274091408432','0',NULL,NULL,4,'独坐敬亭山,李百,众鸟高飞尽,孤云独去闲。,相看两不厌,只有敬亭山.,','/ocrHandWrite/ocrBD1529939987.jpg',NULL,NULL,'wsc','handwriting'),
	(32,'8659609217337155044','0',NULL,0,7,'尽设银,龙卡(储蓄卡) LONG CARD (DEBIT CARDY,4367421147620083682,CCB HRB,Union,ATM,银朕,','/ocrGeneral/ocrBD1530017493.jpeg',NULL,NULL,'wsc','ocr'),
	(33,'5515212660977054317','0',NULL,0,7,'尽设银,龙卡(储蓄卡) LONG CARD (DEBIT CARDY,4367421147620083682,CCB HRB,Union,ATM,银朕,','/ocrGeneral/ocrBD1530017646.jpeg',NULL,NULL,'wsc','ocr'),
	(34,'1274036706135426902','0',NULL,0,9,'中华人民共和国机动车行驶,皮证,粤E|565小型轿车,上人李厚堂,广东省佛山市禅城区张樣街朗沙路7|号宿,性非营运品号别克牌SGM76LEAT,东省佛山个预识别代号 LSGJT52U67H96722,公安局交动号码7611a577,警察支队2012-09-86发2014-08-27,','/ocrGeneral/ocrBD1530017760.jpg',NULL,NULL,'wsc','ocr'),
	(35,'732072515313042530','0',NULL,NULL,NULL,NULL,'/ocrHandWrite/ocrBD1530018830.jpg',NULL,NULL,'wsc','handwriting'),
	(36,'42318057660452558','0',NULL,NULL,7,'匚rEa七E己囗1日,百度A|开发者大会,姓名:小帅、,职位:RD,回锅好,2018070420180705,如有疑问联系客儿,','/ocrHandWrite/ocrBD1530018843.png',NULL,NULL,'wsc','handwriting'),
	(37,'7348861419386210320','0',NULL,0,17,'晚上10:34,求⑧令 all - alll(m75%,老顽童,我没用过啊,循环读取文件可以吗,可以循环读取文件,下一步呢,怎,么合并,所有文件的变量不同,但,是行数一样,想合成一个大表,或者说想和在一块,写入hive表中,大表得字段名相同吧,你这个变量,名都不同,就插进去数据,也不对应啊,老顽童\"撤回了一条消息,如果是7个文件,每个文件一个变,量,每个变量36万行的vaue,合,成一个大表,或者写入有77个变量,中的hve表中,','/ocrGeneral/ocrBD1530027180.jpg','op5Hs0KwMn3DiuI7Ud3ggC3GM6qs','%F0%9F%99%8C%E6%88%91%E6%98%AF%E8%B0%81%F0%9F%99%8C','wsc','ocr'),
	(38,'1423942591964039937','0',NULL,0,3,'独坐敬亭山,众多高尽,孤么热么冈,有园不反,有敬丐厶,','/ocrGeneral/ocrBD1530112260.jpg','','','wsc','ocr'),
	(39,'3432553853797078107','0',NULL,0,3,'独坐敬亭山,众多高尽,孤么热么冈,有园不反,有敬丐厶,','/ocrGeneral/ocrBD1530112355.jpg','','','wsc','ocr'),
	(40,'5723728991529110847','0',NULL,0,3,'独坐敬亭山,众多高尽,孤么热么冈,有园不反,有敬丐厶,','/ocrGeneral/ocrBD1530112395.jpg','op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','wsc','ocr');

/*!40000 ALTER TABLE `ai_bd_ocr` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ai_bd_ocrbankcard
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ai_bd_ocrbankcard`;

CREATE TABLE `ai_bd_ocrbankcard` (
  `ocrId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `errorCode` varchar(255) DEFAULT NULL,
  `errorMsg` varchar(255) DEFAULT NULL,
  `bankCardNumber` varchar(255) DEFAULT NULL,
  `bankCardType` varchar(255) DEFAULT NULL,
  `bankName` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ocrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ai_bd_ocrbankcard` WRITE;
/*!40000 ALTER TABLE `ai_bd_ocrbankcard` DISABLE KEYS */;

INSERT INTO `ai_bd_ocrbankcard` (`ocrId`, `logId`, `errorCode`, `errorMsg`, `bankCardNumber`, `bankCardType`, `bankName`, `imagePath`, `openId`, `nikeName`, `enterType`, `apiType`)
VALUES
	(4,'5933405067909508354','0',NULL,'4367 4211 4762 0083 682','1','建设银行','/ocrBank/ocrBD1527604282.jpeg',NULL,NULL,'web','bank'),
	(5,'2848363824693196024','0',NULL,'22148698484884821','0','','/ocrBank/ocrBD1529938246.jpg',NULL,NULL,'wsc','bank'),
	(6,'1632470910901303026','0',NULL,'22148698484884821','0','','/ocrBank/ocrBD1529938305.jpg',NULL,NULL,'wsc','bank'),
	(7,'2996686194979849297','216631','recognize bank card error',NULL,NULL,NULL,'/ocrBank/ocrBD1529938328.jpg',NULL,NULL,'wsc','bank'),
	(8,'2235771064011216092','0',NULL,'4367 4211 4762 0083 682','1','建设银行','/ocrBank/ocrBD1529938356.jpeg',NULL,NULL,'wsc','bank'),
	(9,'4815253803734787090','0',NULL,'4367 4211 4762 0083 682','1','建设银行','/ocrBank/ocrBD1529938362.jpeg',NULL,NULL,'wsc','bank'),
	(10,'4246343433346217424','0',NULL,'4367 4211 4762 0083 682','1','建设银行','/ocrBank/ocrBD1529938630.jpeg',NULL,NULL,'wsc','bank'),
	(11,'846388325656463488','0',NULL,'4367 4211 4762 0083 682','1','建设银行','/ocrBank/ocrBD1529939962.jpeg',NULL,NULL,'wsc','bank'),
	(12,'6287943795513948299','0',NULL,'4367 4211 4762 0083 682','1','建设银行','/ocrBank/ocrBD1530018786.jpeg',NULL,NULL,'wsc','bank');

/*!40000 ALTER TABLE `ai_bd_ocrbankcard` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ai_bd_ocridcard
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ai_bd_ocridcard`;

CREATE TABLE `ai_bd_ocridcard` (
  `ocrId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `errorCode` varchar(255) DEFAULT NULL,
  `errorMsg` varchar(255) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `wordsResultNum` int(11) DEFAULT NULL,
  `imageStatus` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `idCardNum` varchar(100) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `issueDate` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `expiryDate` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  `riskType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ocrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ai_bd_ocridcard` WRITE;
/*!40000 ALTER TABLE `ai_bd_ocridcard` DISABLE KEYS */;

INSERT INTO `ai_bd_ocridcard` (`ocrId`, `logId`, `errorCode`, `errorMsg`, `direction`, `wordsResultNum`, `imageStatus`, `address`, `birth`, `name`, `idCardNum`, `sex`, `nation`, `issueDate`, `authority`, `expiryDate`, `imagePath`, `openId`, `nikeName`, `enterType`, `apiType`, `riskType`)
VALUES
	(2,'8488556919022696932','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1526993849.jpg',NULL,NULL,'web','idcardb',NULL),
	(3,'6273085539551547829','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1526994200.jpg',NULL,NULL,'web','idcardb',NULL),
	(4,'2606931447973298326','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1526994291.jpg',NULL,NULL,'web','idcardb',NULL),
	(5,'8113456914570124307','0',NULL,0,3,'识别正常',NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1526995018.jpg',NULL,NULL,'web','idcardb',NULL),
	(6,'2821677063061076352','0',NULL,0,3,'识别正常',NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1526995080.jpg',NULL,NULL,'web','idcardb',NULL),
	(7,'8008037687136151946','0',NULL,0,3,'识别正常',NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1526995266.jpg',NULL,NULL,'web','idcardb','正常身份证'),
	(8,'1097867654454835654','0',NULL,0,3,'识别正常',NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1526995269.jpg',NULL,NULL,'web','idcardb','正常身份证'),
	(9,'4534303423244002123','0',NULL,3,6,'识别正常','山西省长治市郊区马厂镇马厂村4队105号','19930410','宗潇帅','140411199304104857','男','汉',NULL,NULL,NULL,'/ocrIdcardf/ocrBD1526995306.jpeg',NULL,NULL,'web','idcardf','正常身份证'),
	(10,'4045945823420922771','0',NULL,3,6,'识别正常','山西省长治市郊区马厂镇马厂村4队105号','19930410','宗潇帅','140411199304104857','男','汉',NULL,NULL,NULL,'/ocrIdcardf/ocrBD1526995313.jpeg',NULL,NULL,'web','idcardf','正常身份证'),
	(11,'3401588287221727815','0',NULL,0,6,'识别正常','山西省长治市郊区马厂镇马厂村4队105号','19930410','宗潇帅','140411199304104857','男','汉',NULL,NULL,NULL,'/ocrIdcardf/ocrBD1529938072.jpg',NULL,NULL,'wsc','idcardf','正常身份证'),
	(12,'2807095666979566623','0',NULL,0,6,'识别正常','山西省长治市郊区马厂镇马厂村4队105号','19930410','宗潇帅','140411199304104857','男','汉',NULL,NULL,NULL,'/ocrIdcardf/ocrBD1529938118.jpg',NULL,NULL,'wsc','idcardf','正常身份证'),
	(13,'3486564653512188665','0',NULL,0,3,'识别正常',NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1529938208.jpg',NULL,NULL,'wsc','idcardb','正常身份证'),
	(14,'7247399495466994808','216633','recognize id card error',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/ocrIdcardb/ocrBD1529938697.jpg',NULL,NULL,'wsc','idcardb',NULL),
	(15,'945501025551048885','0',NULL,0,3,'识别正常',NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1529938748.jpg',NULL,NULL,'wsc','idcardb','正常身份证'),
	(16,'6955169698698116352','0',NULL,0,6,'识别正常','山西省长治市郊区马厂镇马厂村4队105号','19930410','宗潇帅','140411199304104857','男','汉',NULL,NULL,NULL,'/ocrIdcardf/ocrBD1529938806.jpg',NULL,NULL,'wsc','idcardf','正常身份证'),
	(17,'7427523898729079298','216633','recognize id card error',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/ocrIdcardf/ocrBD1529939555.jpg',NULL,NULL,'wsc','idcardf',NULL),
	(18,'154420333612100131','0',NULL,0,6,'识别正常','山西省长治市郊区马厂镇马厂村4队105号','19930410','宗潇帅','140411199304104857','男','汉',NULL,NULL,NULL,'/ocrIdcardf/ocrBD1529939711.jpg',NULL,NULL,'wsc','idcardf','正常身份证'),
	(19,'166440514072132545','0',NULL,0,3,'识别正常',NULL,NULL,NULL,NULL,NULL,NULL,'20120625','建瓯市公安局','20220625','/ocrIdcardb/ocrBD1529940045.jpg',NULL,NULL,'wsc','idcardb','正常身份证'),
	(20,'3553200038214024848','0',NULL,0,6,'识别正常','山西省长治市郊区马厂镇马厂村4队105号','19930410','宗潇帅','140411199304104857','男','汉',NULL,NULL,NULL,'/ocrIdcardf/ocrBD1530018322.jpg',NULL,NULL,'wsc','idcardf','正常身份证');

/*!40000 ALTER TABLE `ai_bd_ocridcard` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ai_ocr_bd
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ai_ocr_bd`;

CREATE TABLE `ai_ocr_bd` (
  `ocrId` int(11) NOT NULL AUTO_INCREMENT,
  `logId` varchar(200) DEFAULT NULL,
  `wordsResultNum` int(11) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `imageStatus` varchar(200) DEFAULT NULL,
  `editTool` varchar(200) DEFAULT NULL,
  `riskType` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `birth` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `idCardNum` int(11) DEFAULT NULL,
  `sex` varchar(200) DEFAULT NULL,
  `nation` varchar(200) DEFAULT NULL,
  `issueDate` varchar(200) DEFAULT NULL,
  `authority` varchar(200) DEFAULT NULL,
  `expiryDate` varchar(200) DEFAULT NULL,
  `cardNum` int(11) DEFAULT NULL,
  `expiraDate` varchar(200) DEFAULT NULL,
  `driverModel` varchar(200) DEFAULT NULL,
  `expiraBeginDate` varchar(200) DEFAULT NULL,
  `nationality` varchar(200) DEFAULT NULL,
  `birthDate` varchar(200) DEFAULT NULL,
  `firstIssueDate` varchar(200) DEFAULT NULL,
  `brandModel` varchar(200) DEFAULT NULL,
  `dateIssue` varchar(200) DEFAULT NULL,
  `useProperty` varchar(200) DEFAULT NULL,
  `engineNum` varchar(200) DEFAULT NULL,
  `numPlate` varchar(200) DEFAULT NULL,
  `posseMan` varchar(200) DEFAULT NULL,
  `createDate` varchar(200) DEFAULT NULL,
  `vin` varchar(200) DEFAULT NULL,
  `vehicleType` varchar(200) DEFAULT NULL,
  `unitName` varchar(200) DEFAULT NULL,
  `legalPerson` varchar(200) DEFAULT NULL,
  `termValidity` varchar(200) DEFAULT NULL,
  `idNum` varchar(200) DEFAULT NULL,
  `socialCreditCode` varchar(200) DEFAULT NULL,
  `words` varchar(5000) DEFAULT NULL,
  `bankName` varchar(100) DEFAULT NULL,
  `bankCardNumber` varchar(100) DEFAULT NULL,
  `bankCardType` varchar(100) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ocrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ai_ocr_bd` WRITE;
/*!40000 ALTER TABLE `ai_ocr_bd` DISABLE KEYS */;

INSERT INTO `ai_ocr_bd` (`ocrId`, `logId`, `wordsResultNum`, `direction`, `imageStatus`, `editTool`, `riskType`, `address`, `birth`, `name`, `idCardNum`, `sex`, `nation`, `issueDate`, `authority`, `expiryDate`, `cardNum`, `expiraDate`, `driverModel`, `expiraBeginDate`, `nationality`, `birthDate`, `firstIssueDate`, `brandModel`, `dateIssue`, `useProperty`, `engineNum`, `numPlate`, `posseMan`, `createDate`, `vin`, `vehicleType`, `unitName`, `legalPerson`, `termValidity`, `idNum`, `socialCreditCode`, `words`, `bankName`, `bankCardNumber`, `bankCardType`, `apiType`, `imagePath`)
VALUES
	(4,'8577215155763486137',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'本作息时间表',NULL,NULL,NULL,'general','E:\\oscgit\\x-ai\\target\\bootdo-1.5.0\\OCRbd\\general1522835927OCRBDocr.png'),
	(5,'2101129635425516216',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'中华人民共和国机动车行驶证,Vehicle License of the Peoples Republie of China,牌警码川AA7800车辆类小型轿车,所有人叶魁友,住址成都市龙泉驿区山泉镇联合村7组3号,使用质非营运品牌型号北京现代牌57164MX,四省成都车操识别别代号 LBEHDAEB58Y3886,市公安局交发动相号码8B213508,察支队进型日期2687-68证日期2915-6-4',NULL,NULL,NULL,'general','E:\\oscgit\\x-ai\\target\\bootdo-1.5.0\\OCRbd\\general1522835997OCRBDdriver.jpg'),
	(6,'8734172069816711347',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'中华人民共和国机动车行驶证,Vehicle License of the Peoples Republie of China,牌警码川AA7800车辆类小型轿车,所有人叶魁友,住址成都市龙泉驿区山泉镇联合村7组3号,使用质非营运品牌型号北京现代牌57164MX,四省成都车操识别别代号 LBEHDAEB58Y3886,市公安局交发动相号码8B213508,察支队进型日期2687-68证日期2915-6-4',NULL,NULL,NULL,'general','E:\\oscgit\\x-ai\\target\\bootdo-1.5.0\\OCRbd\\general1522836086OCRBDdriver.jpg');

/*!40000 ALTER TABLE `ai_ocr_bd` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ai_yt_fuse
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ai_yt_fuse`;

CREATE TABLE `ai_yt_fuse` (
  `youtuId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `errorcode` int(11) DEFAULT NULL,
  `errormsg` varchar(255) DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `confidence` varchar(500) DEFAULT NULL,
  `itemstring` varchar(5000) DEFAULT NULL,
  `faceId` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `expression` int(11) DEFAULT NULL,
  `glasses` int(11) DEFAULT NULL,
  `beauty` int(11) DEFAULT NULL,
  `hat` int(11) DEFAULT NULL,
  `mask` int(11) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  `classifyCnt` int(11) DEFAULT NULL,
  PRIMARY KEY (`youtuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ai_yt_fuse` WRITE;
/*!40000 ALTER TABLE `ai_yt_fuse` DISABLE KEYS */;

INSERT INTO `ai_yt_fuse` (`youtuId`, `errorcode`, `errormsg`, `sessionId`, `label`, `confidence`, `itemstring`, `faceId`, `age`, `gender`, `expression`, `glasses`, `beauty`, `hat`, `mask`, `openId`, `nikeName`, `imagePath`, `enterType`, `apiType`, `classifyCnt`)
VALUES
	(3,0,'OK',NULL,NULL,NULL,'Hf444,M NX 3001',NULL,0,0,0,0,0,0,0,NULL,NULL,'/youtuHw/ocrBD1528210542.jpg','web','hw',0),
	(4,0,'OK',NULL,'食指','99%',NULL,NULL,0,0,0,0,0,0,0,NULL,NULL,'/youtuHt/ocrBD1528210562.jpg','web','ht',1),
	(5,0,'OK',NULL,NULL,NULL,NULL,'2610578206521063359',16,0,24,0,100,1,0,NULL,NULL,'/youtuFace/ocrBD1528210585.jpeg','web','face',0),
	(6,0,'OK',NULL,NULL,NULL,NULL,'2620628931698520680',16,0,24,0,100,1,0,NULL,NULL,'/youtuFace/ocrBD1528809654.jpeg','web','face',0),
	(7,0,'OK',NULL,NULL,NULL,NULL,'2620630671157128293',16,0,24,0,100,1,0,NULL,NULL,'/youtuFace/ocrBD1528809758.jpeg','web','face',0),
	(8,0,'OK',NULL,NULL,NULL,NULL,'2630808538012713460',16,0,24,0,100,1,0,NULL,NULL,'/youtuFace/ocrBD1529416407.jpeg','wcs','face',0),
	(9,-1101,'SDK_IMAGE_FACEDETECT_FAILED',NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,NULL,NULL,'/youtuFace/ocrBD1529416671.jpg','wcs','face',0),
	(10,0,'OK',NULL,NULL,NULL,NULL,'2630817758145704452',25,99,9,0,79,0,0,NULL,NULL,'/youtuFace/ocrBD1529416954.JPG','wsc','face',0),
	(11,0,'OK',NULL,NULL,NULL,NULL,'2632298702657552672',16,0,24,0,100,1,0,'op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','/youtuFace/ocrBD1529505228.jpeg','wsc','face',0),
	(12,0,'OK',NULL,'食指','99%',NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529588591.jpg','wcs','ht',1),
	(13,-9101,'HANDAR_NO_HANDS',NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529589675.jpg','wcs','ht',0),
	(14,0,'OK',NULL,'SIX','99%',NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529589687.jpg','wcs','ht',1),
	(15,0,'OK',NULL,'SIX','99%',NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529589783.jpg','wcs','ht',1),
	(16,0,'OK',NULL,'SIX','99%',NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529589787.jpg','wcs','ht',1),
	(17,0,'OK',NULL,'布,拳头','99%,99%',NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529589946.jpg','wcs','ht',2),
	(18,0,'OK',NULL,'布,拳头','99%,99%',NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529590321.jpg','wsc','ht',2),
	(19,0,'OK',NULL,'布,拳头','99%,99%',NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529590356.jpg','wsc','ht',2),
	(20,-9101,'HANDAR_NO_HANDS',NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,'','','/youtuHt/ocrBD1529590370.jpg','wsc','ht',0),
	(21,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529590751.jpg','wcs','hw',0),
	(22,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529591711.jpg','wsc','hw',0),
	(23,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529591743.jpg','wcs','hw',0),
	(24,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529591779.jpg','wsc','hw',0),
	(25,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529591812.jpg','wsc','hw',0),
	(26,0,'OK',NULL,NULL,NULL,'“国密”,小帅\\的赞赏码',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529591841.jpg','wsc','hw',0),
	(27,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529591966.jpg','wsc','hw',0),
	(28,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529592091.jpg','wsc','hw',0),
	(29,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529592249.jpg','wsc','hw',0),
	(30,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529592287.jpg','wsc','hw',0),
	(31,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529592623.jpg','wsc','hw',0),
	(32,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'','','/youtuHw/ocrBD1529592684.jpg','wsc','hw',0),
	(33,0,'OK',NULL,NULL,NULL,NULL,'2640996648069691833',42,0,13,0,76,1,0,'op5Hs0EYFmR7XvvWNrbsMFVn22Kk','小帅丶','/youtuFace/ocrBD1530023666.jpg','wsc','face',0),
	(34,0,'OK',NULL,NULL,NULL,NULL,'2642476048452735505',16,0,24,0,100,1,0,'op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','/youtuFace/ocrBD1530111845.jpeg','wsc','face',0),
	(35,0,'OK',NULL,'布,拳头','99%,99%',NULL,NULL,0,0,0,0,0,0,0,'op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','/youtuHt/ocrBD1530112210.jpg','wsc','ht',2),
	(36,0,'OK',NULL,NULL,NULL,'独坐敬亭山,李自,众鸟高飞尽，孤云独去闲。,相看两不厌，只有敬亭山。',NULL,0,0,0,0,0,0,0,'op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6','/youtuHw/ocrBD1530112228.jpg','wsc','hw',0);

/*!40000 ALTER TABLE `ai_yt_fuse` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table blog_content
# ------------------------------------------------------------

DROP TABLE IF EXISTS `blog_content`;

CREATE TABLE `blog_content` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `slug` varchar(255) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modified` bigint(20) DEFAULT NULL COMMENT '最近修改人id',
  `content` text COMMENT '内容',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `tags` varchar(200) DEFAULT NULL COMMENT '标签',
  `categories` varchar(200) DEFAULT NULL COMMENT '分类',
  `hits` int(5) DEFAULT NULL,
  `commentsNum` int(5) DEFAULT '0' COMMENT '评论数量',
  `allowComment` int(1) DEFAULT '0' COMMENT '开启评论',
  `allowPing` int(1) DEFAULT '0' COMMENT '允许ping',
  `allowFeed` int(1) DEFAULT '0' COMMENT '允许反馈',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `gtmCreate` datetime DEFAULT NULL COMMENT '创建时间',
  `gtmModified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章内容';

LOCK TABLES `blog_content` WRITE;
/*!40000 ALTER TABLE `blog_content` DISABLE KEYS */;

INSERT INTO `blog_content` (`cid`, `title`, `slug`, `created`, `modified`, `content`, `type`, `tags`, `categories`, `hits`, `commentsNum`, `allowComment`, `allowPing`, `allowFeed`, `status`, `author`, `gtmCreate`, `gtmModified`)
VALUES
	(123,'测试',NULL,NULL,NULL,'<p>测试测试测试测试测试<b>测试测试</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1<br></p>','article',NULL,'',NULL,NULL,1,NULL,1,1,'测试','2018-04-23 17:13:09','2018-04-23 17:09:02'),
	(125,'测试测试',NULL,NULL,NULL,'<p><span style=\"color: rgb(0, 255, 255);\"><b><u>测试测试测试测试测试测试测试测试测试测试测试测试</u></b></span><br></p>','article',NULL,'',NULL,NULL,1,NULL,0,1,'测试测试','2018-04-23 16:56:28','2018-04-23 16:56:28');

/*!40000 ALTER TABLE `blog_content` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_dept
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `deptId` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `delFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;

INSERT INTO `sys_dept` (`deptId`, `parentId`, `name`, `orderNum`, `delFlag`)
VALUES
	(6,0,'研发部',1,1),
	(7,6,'研發一部',1,1),
	(8,6,'研发二部',2,1),
	(9,0,'销售部',2,1),
	(10,9,'销售一部',1,1),
	(11,0,'产品部',3,1),
	(12,11,'产品一部',1,1),
	(13,0,'测试部',5,1),
	(14,13,'测试一部',1,1),
	(15,13,'测试二部',2,1),
	(16,0,'FACE',10,1),
	(17,16,'小学部',11,1),
	(18,16,'中学部',12,1);

/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `gmtCreate` varchar(255) DEFAULT NULL,
  `gmtModified` varchar(255) DEFAULT NULL,
  `menuType` int(11) DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;

INSERT INTO `sys_menu` (`menuId`, `parentId`, `name`, `url`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`, `menuType`)
VALUES
	(2,3,'系统菜单','menu/index','fa fa-th-list',2,'2017/8/9 22:55:15','',1),
	(3,0,'系统管理','','fa fa-desktop',1,'2017/8/9 23:06:55','2017/8/14 14:13:43',0),
	(6,3,'用户管理','user/index','fa fa-user',0,'2017/8/10 14:12:11','',1),
	(7,3,'角色管理','role/index','fa fa-paw',1,'2017/8/10 14:13:19','',1),
	(12,6,'新增','','',0,'2017/8/14 10:51:35','',2),
	(13,6,'编辑','','',0,'2017/8/14 10:52:06','',2),
	(14,6,'删除','','',0,'2017/8/14 10:52:24','',2),
	(15,7,'新增','','',0,'2017/8/14 10:56:37','',2),
	(20,2,'新增','','',0,'2017/8/14 10:59:32','',2),
	(21,2,'编辑','','',0,'2017/8/14 10:59:56','',2),
	(22,2,'删除','','',0,'2017/8/14 11:00:26','',2),
	(24,6,'批量删除','','',0,'2017/8/14 17:27:18','',2),
	(25,6,'停用','','',0,'2017/8/14 17:27:43','',2),
	(26,6,'重置密码','','',0,'2017/8/14 17:28:34','',2),
	(27,91,'系统日志','common/log','fa fa-warning',0,'2017/8/14 22:11:53','',1),
	(28,27,'刷新','','',0,'2017/8/14 22:30:22','',2),
	(29,27,'删除','','',0,'2017/8/14 22:30:43','',2),
	(30,27,'清空','','',0,'2017/8/14 22:31:02','',2),
	(48,77,'代码生成','common/generator','fa fa-code',3,'','',1),
	(55,7,'编辑','','',NULL,'','',2),
	(56,7,'删除','','',NULL,'','',2),
	(57,91,'运行监控','druid/index.html','fa fa-caret-square-o-right',1,'','',1),
	(61,2,'批量删除','','',NULL,'','',2),
	(62,7,'批量删除','','',NULL,'','',2),
	(72,77,'计划任务','common/job','fa fa-hourglass-1',4,'','',1),
	(73,3,'部门管理','dept/index','fa fa-users',3,'','',1),
	(74,73,'增加','/system/sysDept/add','',1,'','',2),
	(75,73,'刪除','system/sysDept/remove','',2,'','',2),
	(76,73,'编辑','/system/sysDept/edit','',3,'','',2),
	(77,0,'系统工具','','fa fa-gear',4,'','',0),
	(84,0,'办公管理','','fa fa-laptop',5,'','',0),
	(85,84,'通知公告','oa/notify','fa fa-pencil-square',NULL,'','',1),
	(86,85,'新增','oa/notify/add','fa fa-plus',1,'','',2),
	(87,85,'编辑','oa/notify/edit','fa fa-pencil-square-o',2,'','',2),
	(88,85,'删除','oa/notify/remove','fa fa-minus',NULL,'','',2),
	(89,85,'批量删除','oa/notify/batchRemove','',NULL,'','',2),
	(90,84,'我的通知','oa/notify/selfNotify','fa fa-envelope-square',NULL,'','',1),
	(91,0,'系统监控','','fa fa-video-camera',5,'','',0),
	(92,91,'在线用户','sys/online','fa fa-user',NULL,'','',1),
	(93,0,'百度AI','','fa fa-tree',NULL,'','',0),
	(94,93,'人脸识别','bdface/index','fa fa-male',NULL,'','',1),
	(95,93,'菜品识别','bdicr/indexDish','fa fa-picture-o',NULL,'','',1),
	(100,0,'博客管理','','fa fa-feed',NULL,NULL,NULL,0),
	(101,100,'发布文章','blogmanager/bContent/add','fa fa-book',NULL,NULL,NULL,1),
	(102,100,'文章列表','blogmanager/bContent','',NULL,NULL,NULL,1),
	(103,93,'图像识别','bdicr/indexFuse','',NULL,NULL,NULL,1),
	(104,93,'文字识别','bdocr/indexOcrGeneral','',NULL,NULL,NULL,1),
	(105,93,'身份证识别','bdocr/indexOcrIdCard','fa fa-address-card',NULL,NULL,NULL,1),
	(106,93,'银行卡识别','bdocr/indexOcrBankCard','',NULL,NULL,NULL,1),
	(107,0,'腾讯优图','','fa fa-file-image-o',NULL,NULL,NULL,0),
	(108,107,'融合识别','youtu/indexYouTuFuse','',NULL,NULL,NULL,0),
	(109,0,'微信用户','','fa fa-user',NULL,NULL,NULL,0),
	(110,109,'微信用户信息','wechat/indexWeChat','',NULL,NULL,NULL,1);

/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  `roleSign` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `userIdCreate` varchar(255) DEFAULT NULL,
  `gmtCreate` varchar(255) DEFAULT NULL,
  `gmtModified` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`roleId`, `roleName`, `roleSign`, `remark`, `userIdCreate`, `gmtCreate`, `gmtModified`)
VALUES
	(1,'超级用户角色','admin','拥有最高权限','2','2017/8/12 00:43:52','2017/8/12 19:14:59'),
	(48,'钻石会员','','消费1w块','','',''),
	(49,'白金会员','','消费5000以上','','',''),
	(52,'白银会员','','消费两千以上','','',''),
	(56,'普通用户','','普通用户','','','');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `menuId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;

INSERT INTO `sys_role_menu` (`id`, `roleId`, `menuId`)
VALUES
	(367,44,1),
	(368,44,32),
	(369,44,33),
	(370,44,34),
	(371,44,35),
	(372,44,28),
	(373,44,29),
	(374,44,30),
	(375,44,38),
	(376,44,4),
	(377,44,27),
	(378,45,38),
	(379,46,3),
	(380,46,20),
	(381,46,21),
	(382,46,22),
	(383,46,23),
	(384,46,11),
	(385,46,12),
	(386,46,13),
	(387,46,14),
	(388,46,24),
	(389,46,25),
	(390,46,26),
	(391,46,15),
	(392,46,2),
	(393,46,6),
	(394,46,7),
	(598,50,38),
	(632,38,42),
	(737,51,38),
	(738,51,39),
	(739,51,40),
	(740,51,41),
	(741,51,4),
	(742,51,32),
	(743,51,33),
	(744,51,34),
	(745,51,35),
	(746,51,27),
	(747,51,28),
	(748,51,29),
	(749,51,30),
	(750,51,1),
	(1064,54,53),
	(1095,55,2),
	(1096,55,6),
	(1097,55,7),
	(1098,55,3),
	(1099,55,50),
	(1100,55,49),
	(1101,55,1),
	(1856,53,28),
	(1857,53,29),
	(1858,53,30),
	(1859,53,27),
	(1860,53,57),
	(1861,53,71),
	(1862,53,48),
	(1863,53,72),
	(1864,53,1),
	(1865,53,7),
	(1866,53,55),
	(1867,53,56),
	(1868,53,62),
	(1869,53,15),
	(1870,53,2),
	(1871,53,61),
	(1872,53,20),
	(1873,53,21),
	(1874,53,22),
	(1875,49,12),
	(1876,49,13),
	(1877,49,14),
	(1878,49,24),
	(1879,49,25),
	(1880,49,26),
	(1881,49,61),
	(1882,49,20),
	(1883,49,21),
	(1884,49,22),
	(1885,49,74),
	(1886,49,75),
	(1887,49,76),
	(1888,49,6),
	(1889,49,2),
	(1890,49,73),
	(2072,52,77),
	(2073,52,49),
	(2074,52,3),
	(2075,52,72),
	(2076,52,48),
	(2084,56,68),
	(2085,56,60),
	(2086,56,59),
	(2087,56,58),
	(2088,56,51),
	(2089,56,50),
	(2090,56,49),
	(2243,48,72),
	(2247,63,-1),
	(2248,63,84),
	(2249,63,85),
	(2250,63,88),
	(2251,63,87),
	(2252,64,84),
	(2253,64,89),
	(2254,64,88),
	(2255,64,87),
	(2256,64,86),
	(2257,64,85),
	(2258,65,89),
	(2259,65,88),
	(2260,65,86),
	(2262,67,48),
	(2263,68,88),
	(2264,68,87),
	(2265,69,89),
	(2266,69,88),
	(2267,69,86),
	(2268,69,87),
	(2269,69,85),
	(2270,69,84),
	(2271,70,85),
	(2272,70,89),
	(2273,70,88),
	(2274,70,87),
	(2275,70,86),
	(2276,70,84),
	(2277,71,87),
	(2278,72,59),
	(2279,73,48),
	(2280,74,88),
	(2281,74,87),
	(2282,75,88),
	(2283,75,87),
	(2284,76,85),
	(2285,76,89),
	(2286,76,88),
	(2287,76,87),
	(2288,76,86),
	(2289,76,84),
	(2292,78,88),
	(2293,78,87),
	(2294,78,NULL),
	(2295,78,NULL),
	(2296,78,NULL),
	(2308,80,87),
	(2309,80,86),
	(2310,80,-1),
	(2311,80,84),
	(2312,80,85),
	(2328,79,72),
	(2329,79,48),
	(2330,79,77),
	(2331,79,84),
	(2332,79,89),
	(2333,79,88),
	(2334,79,87),
	(2335,79,86),
	(2336,79,85),
	(2337,79,-1),
	(2338,77,89),
	(2339,77,88),
	(2340,77,87),
	(2341,77,86),
	(2342,77,85),
	(2343,77,84),
	(2344,77,72),
	(2345,77,-1),
	(2346,77,77),
	(3071,1,108),
	(3072,1,102),
	(3073,1,101),
	(3074,1,106),
	(3075,1,105),
	(3076,1,104),
	(3077,1,103),
	(3078,1,95),
	(3079,1,94),
	(3080,1,57),
	(3081,1,72),
	(3082,1,48),
	(3083,1,76),
	(3084,1,75),
	(3085,1,74),
	(3086,1,62),
	(3087,1,56),
	(3088,1,55),
	(3089,1,15),
	(3090,1,26),
	(3091,1,25),
	(3092,1,24),
	(3093,1,14),
	(3094,1,13),
	(3095,1,12),
	(3096,1,61),
	(3097,1,22),
	(3098,1,21),
	(3099,1,20),
	(3100,1,107),
	(3101,1,100),
	(3102,1,93),
	(3103,1,77),
	(3104,1,73),
	(3105,1,7),
	(3106,1,6),
	(3107,1,2),
	(3108,1,3),
	(3109,1,109),
	(3110,1,110),
	(3111,1,-1),
	(3112,1,91);

/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `deptId` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `userIdCreate` varchar(255) DEFAULT NULL,
  `gmtCreate` varchar(255) DEFAULT NULL,
  `gmtModified` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`userId`, `username`, `name`, `password`, `deptId`, `email`, `mobile`, `status`, `userIdCreate`, `gmtCreate`, `gmtModified`, `sex`, `birth`, `province`, `city`, `district`)
VALUES
	(1,'admin','超级管理员','123456',6,'admin@example.com','123456',1,'1','2017/8/15 21:40:39','2017/8/15 21:41:00',NULL,NULL,NULL,NULL,NULL),
	(137,'xiaoshuai','小帅丶','4e34bc24454ebc6e7ece909696d62329',8,'youngxiaoshuai@163.com','',1,'','','',NULL,NULL,NULL,NULL,NULL),
	(138,'xsinfo','小帅','125bcea642c30012be7124af2738064f',NULL,'youngxiaoshuai@163.com','',1,'','','',NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;

INSERT INTO `sys_user_role` (`id`, `userId`, `roleId`)
VALUES
	(73,30,48),
	(74,30,49),
	(75,30,50),
	(76,31,48),
	(77,31,49),
	(78,31,52),
	(79,32,48),
	(80,32,49),
	(81,32,50),
	(82,32,51),
	(83,32,52),
	(84,33,38),
	(85,33,49),
	(86,33,52),
	(87,34,50),
	(88,34,51),
	(89,34,52),
	(97,36,48),
	(106,124,1),
	(110,1,1),
	(111,2,1),
	(113,131,48),
	(117,135,1),
	(120,134,1),
	(121,134,48),
	(122,133,1),
	(123,130,1),
	(124,NULL,48),
	(126,138,1),
	(127,137,1);

/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table wechat_userinfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `wechat_userinfo`;

CREATE TABLE `wechat_userinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openId` varchar(255) DEFAULT NULL,
  `nickName` char(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `language` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `avatarUrl` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `wechat_userinfo` WRITE;
/*!40000 ALTER TABLE `wechat_userinfo` DISABLE KEYS */;

INSERT INTO `wechat_userinfo` (`id`, `openId`, `nickName`, `gender`, `language`, `city`, `province`, `country`, `avatarUrl`)
VALUES
	(1,'op5Hs0EYFmR7XvvWNrbsMFVn22Kk','%E5%B0%8F%E5%B8%85%E4%B8%B6',1,'zh_CN','Haidian','Beijing','China','https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLib098UOLAHuE1fDldajSPuwR0RcPf3rxCtVicwhvdKibYFE0JNibwMwGdiagRzibdAtkSTU1fYxiaz8CIQ/132');

/*!40000 ALTER TABLE `wechat_userinfo` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
