/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.56 : Database - eshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eshop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `eshop`;

/*Table structure for table `s_brand` */

DROP TABLE IF EXISTS `s_brand`;

CREATE TABLE `s_brand` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `brand_name` varchar(50) NOT NULL COMMENT '名称',
  `brand_type` varchar(64) NOT NULL COMMENT '所属分类',
  `brand_img` text NOT NULL COMMENT '品牌图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_brand` */

insert  into `s_brand`(`id`,`brand_name`,`brand_type`,`brand_img`) values 
('af19a7af18a24be0b9d4685cd6bcf0b2','格林','758afbf0a4d1474e8b53043c6697a9f7','6d1d563c31fb48bb8e4018b03fe418fd.jpg'),
('cedc50a5125c4987affc1d238a6520c2','卫龙','b0a1877ba5f84cd89a25e507fb7612d8','df24ec5f44244f14aa4fbbd808285262.jpg'),
('cf25d79aac824e82a83a424b7d339df8','繁星','29feb3d0ad4d4b86aabcaa80e435e29b','5312c7c2ff5540f68dd6fd19409de575.jpg'),
('e44ca1c151c147b5a46ac021c11b46f7','天边','0eba44a141d54156b0f0c16475c084ac','c39fdf4de2914e74b329733c250da2f7.jpg'),
('e4f4a6cc046741268edc0279007a50e7','小城故事','758afbf0a4d1474e8b53043c6697a9f7','5bb2a327aad14050b0ea1d606b442119.jpg'),
('f35d8e8d487e45509f99da6e7b071f40','美妆','8409f4cacd18457cb153f8fe8741d122','d2a88cd4a536460cbab6579432dd3e5d.jpg'),
('f8e8239af9064160a67219fdc89ac553','维达','2f95f6b82bf54b0bb065b3ac1463c6a8','2138de7c69ff44c493a0fcd04d0ad657.jpg');

/*Table structure for table `s_carousel_figure` */

DROP TABLE IF EXISTS `s_carousel_figure`;

CREATE TABLE `s_carousel_figure` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `url` text NOT NULL COMMENT '地址',
  `sequence_num` int(2) NOT NULL COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_carousel_figure` */

insert  into `s_carousel_figure`(`id`,`url`,`sequence_num`) values 
('04528a6e14d944a094f869ddcbe9198e','c1b7332dff454138a882f363f6c18d09.jpg',3),
('3a9b3659bc21474886b90eb604cf16ee','d2f13a9d51124cab872ae59693e43587.jpg',1),
('412a8fffc6a64731899907df14f805d1','50c8f540f3fe4d15a12efc0dc58b05e6.jpg',4),
('cd4b9b611b7549dcb1d0ca9895bc2a09','af31000f709e42d8bc19d322fdec0a11.jpg',5),
('d7d04b2e26634d4da87c21a050bd9bd6','8e00d2bde6674ad2bf2be5a97fb0ad85.jpg',2);

/*Table structure for table `s_order` */

DROP TABLE IF EXISTS `s_order`;

CREATE TABLE `s_order` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `create_time` datetime NOT NULL COMMENT '订单创建时间',
  `receiving_address` varchar(64) NOT NULL COMMENT '收货地址Id',
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_order` */

insert  into `s_order`(`id`,`create_time`,`receiving_address`,`user_id`) values 
('36e7c6c12d544c4bb801e255ebcd0a7e','2018-10-11 09:44:34','bb61c2e0a7a74a6ea631cc9f4466a9af','e888e30e355445a8a55f97f0320ed7a2'),
('68f208aebf01480d9dbd8ef605569908','2018-10-11 15:19:28','960565c7c8944fe1bb6fe694b5a82b9b','432ab81f72694caba2ae6a4418779175'),
('859283130178401b802e94ce31e13080','2018-10-10 16:38:07','5fd6538cb383425d8f0c3325d4071568','e888e30e355445a8a55f97f0320ed7a2'),
('f062f86c73f347268056a55e44be1309','2018-10-11 15:21:01','960565c7c8944fe1bb6fe694b5a82b9b','432ab81f72694caba2ae6a4418779175');

/*Table structure for table `s_order_product` */

DROP TABLE IF EXISTS `s_order_product`;

CREATE TABLE `s_order_product` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `order_id` varchar(64) NOT NULL COMMENT '订单id',
  `product_id` varchar(64) NOT NULL COMMENT '商品id',
  `product_num` int(6) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_order_product` */



('0d19e9946d224e2cb8578ba792e8101a','36e7c6c12d544c4bb801e255ebcd0a7e','ec9f427ef75b429fb2839218a75858cc',50),
('30069385795f40f4877eef638918cc88','f062f86c73f347268056a55e44be1309','07bbb107374a4093a6084d71138004bf',100),
('3f5e60e507dc419588de74357c8d93d6','b4a13ff8ae794d969260a187a962e32a','ceb78f5e4543496294d93c5b8774fcb3',1),
('4779582fba774f98b7040acbbbfa73aa','859283130178401b802e94ce31e13080','ceb78f5e4543496294d93c5b8774fcb3',1),
('4cbbd51e9e474376b8b13361cee4ff41','399f08cb03004eb09533c2bd2c911018','0ff9e1da2b324a28b71e8f0f15bbaf2e',1),
('4e1ed6756aa74f92bfc01cf4de082120','045acb35a2144877b662424abb999e70','89c96d4be598473b94f892d47cd24395',1),
('641aee46035a476f85a54311ba6afc09','d5ff0e99e40d44d391b721b40717cb24','ceb78f5e4543496294d93c5b8774fcb3',1),
('6965e4e3d79945a69ea51057d37bc5a7','d5ff0e99e40d44d391b721b40717cb24','d3740e27ffef46c3a59735f6b1430893',6),
('73dd5498544a4e15ac36c27e32c6ca65','32affe3172f64699a3c4e46c5a718f84','ceb78f5e4543496294d93c5b8774fcb3',1),
('9cd9df16953144dfbd047d2aa8e8c45d','32affe3172f64699a3c4e46c5a718f84','d3740e27ffef46c3a59735f6b1430893',6),
('afb23e6bb16e43328091fe0e33ed1104','859283130178401b802e94ce31e13080','d3740e27ffef46c3a59735f6b1430893',6),
('b7e1042206044d7c9f15c5e69ec9ee6e','b3e6233b72e74b4585b31d604392634f','89c96d4be598473b94f892d47cd24395',3),
('bde09824dbf94bc18cf43e73059f6d3e','f062f86c73f347268056a55e44be1309','3c064409f23043d69b93329951352b08',2),
('c6b166db0ec540e683dbcf471da22695','37dcd43cd4a842268f3ab182078add8c','89c96d4be598473b94f892d47cd24395',6),
('e7f5ec72f8654b7dbf1da42e7fca7f62','68f208aebf01480d9dbd8ef605569908','3c064409f23043d69b93329951352b08',2),
('ef075912a69d4b6a886961b5514f6f09','f49eab3ac11b43e9bf39f0ab4f92d005','89c96d4be598473b94f892d47cd24395',6);

/*Table structure for table `s_product` */

DROP TABLE IF EXISTS `s_product`;

CREATE TABLE `s_product` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `product_name` varchar(100) NOT NULL COMMENT '商品名称',
  `product_image` text NOT NULL COMMENT '商品图片',
  `price` double NOT NULL COMMENT '价格',
  `product_type` varchar(64) NOT NULL COMMENT '所属分类',
  `product_desc` text NOT NULL COMMENT '商品详情',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `product_brand` varchar(64) NOT NULL COMMENT '商品品牌',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_product` */

insert  into `s_product`(`id`,`product_name`,`product_image`,`price`,`product_type`,`product_desc`,`create_time`,`product_brand`) values 
('07bbb107374a4093a6084d71138004bf','2018秋季新款韩版高腰阔腿裤拖地西装长裤休闲裤女直筒显瘦时尚垂','c544543019814dac9459cfe8303183e9.jpg',380,'b0a1877ba5f84cd89a25e507fb7612d8','93dd8f457723441797abe4e8a66c463a.jpg,3b0b3d1dd88043728cb19c061eccdf0c.jpg,7f30e7a64ca64c2b90f6bdc6bd2e81c2.jpg,f2ede232098c4cb5a147676f3d99212a.jpg,fd1ac200bb2b4dd48b4610cd24c1f854.jpg','2018-09-19 18:25:06','cedc50a5125c4987affc1d238a6520c2'),
('0b50b6781c1e453e93b0cb2def378158','进口粮油米面-满99减50','d00848b2251f4252b568c3895e23cca0.jpg',98,'29feb3d0ad4d4b86aabcaa80e435e29b','cad586e6a62a4e9e8ac975606accc9e2.jpg,5381ccf5323849a1afe04a55dac1fba1.jpg,26efbc5437444b068fab000933e9b2bf.jpg,009d13e27e72474aa5487ed875e9133e.jpg,6b8949522a4e4203b43b04278f8694b4.jpg','2018-09-19 18:55:46','cf25d79aac824e82a83a424b7d339df8'),
('0f25438ffe7f40d485e3798449a4b765','粮油米面会场','abd590440aba46bc87c40ec4746375a4.jpg',130,'0eba44a141d54156b0f0c16475c084ac','ea0f2476a750436e8187de4665441c3e.jpg','2018-09-19 16:13:24','e44ca1c151c147b5a46ac021c11b46f7'),
('0ff9e1da2b324a28b71e8f0f15bbaf2e','进口水饮冲调-满99减50','1cea6a7ef6c64786a56cdc0edf5af1f6.jpg',233,'29feb3d0ad4d4b86aabcaa80e435e29b','303200f2278c4592b2e329314c21215a.jpg','2018-09-19 18:56:12','cf25d79aac824e82a83a424b7d339df8'),
('1151d7d1b1224c4a8387b41ae3f98766','旗袍外搭外套 开衫 小披肩女夏[逸红颜 楚菲儿]新款配旗袍的外套','f3012d30daf84ce6a6f2cf2899e53b48.jpg',180,'b0a1877ba5f84cd89a25e507fb7612d8','ffabbb4ec03546719a718b6363a54baf.jpg','2018-09-19 18:24:23','cedc50a5125c4987affc1d238a6520c2'),
('119eedc1c9b54ec595aa79e5eb26ca46','洗发护发会场','aa3155c77de14ae2858007b73b8a4049.jpg',111,'2f95f6b82bf54b0bb065b3ac1463c6a8','cbf5ba10ac7147df887ec3288350d22d.jpg','2018-09-19 16:07:13','f8e8239af9064160a67219fdc89ac553'),
('2ae73ed2026a4f4cb0805f98d8b716a9','javascript高级程序设计+JavaScript权威指南 全2册  javascript入门基础 网页设计 编程艺术','ac95744ef94c4b9699f122d99c931aa2.jpg',166,'758afbf0a4d1474e8b53043c6697a9f7','768b86fc122743b0a8a164cda419348f.jpg','2018-09-19 19:01:29','af19a7af18a24be0b9d4685cd6bcf0b2'),
('2b7ff5cb00bb44a591988ff370fe0f5d','Benefit贝玲妃完美无瑕粉饼/你好无暇遮瑕蜜粉防晒控油','620a5878a3344ebfb642eb2fce763254.jpg',233.9,'8409f4cacd18457cb153f8fe8741d122','510e4e6d61834c53944f093fec33563f.jpg','2018-09-19 18:58:27','f35d8e8d487e45509f99da6e7b071f40	'),
('352526e0ace24c68b3827f957282e91a','编程思想第4版中文版thinking in ','8c316588ba234e9baade261116f22867.jpg',86,'758afbf0a4d1474e8b53043c6697a9f7','1b674f1483df4483abe3f26e341faa07.jpg','2018-09-19 19:02:30','e4f4a6cc046741268edc0279007a50e7'),
('35a7aac43008421aa247f17fa5943f3b','2018秋冬新款女装减龄慵懒风V领毛衣女宽松套头显瘦长袖针织衫潮','4ed9888a0a5446caab870ec56dc37fe2.jpg',300,'b0a1877ba5f84cd89a25e507fb7612d8','067dbf5675214567916e4a08d7d978bb.jpg','2018-09-19 18:14:32','cedc50a5125c4987affc1d238a6520c2'),
('3c064409f23043d69b93329951352b08','✅✅西游记朝花夕拾鲁迅包邮正版初中生白洋淀纪事猎人笔记镜花缘湘行散记原著正七年级指定阅读课外书中学生必读的名著读物书籍套','81e9805a69b64009b827539a35505ac9.jpg',57.8,'758afbf0a4d1474e8b53043c6697a9f7','ac31844f5c724a5fb7f82ba5d01086a0.jpg','2018-09-19 18:16:47','e4f4a6cc046741268edc0279007a50e7'),
('46c5e62a9d854bcd951ee9446b7fdab7','裤子女2018新款潮宽松长袖拼接polo领工装裤假两件收腰西装连体裤','f7560404eda44ab0b6fcc0c09ae4f73c.jpg',147,'b0a1877ba5f84cd89a25e507fb7612d8','d6940d91ad1e4f51a4bb5e363227b343.jpg','2018-09-19 18:25:26','cedc50a5125c4987affc1d238a6520c2'),
('555dd67d48c649e0b60d4eddc49150ca','直邮韩国代购TONYMOLY魔法森林shocking lip超持久不掉色纹身唇彩','5ba7c073797541698f99e9de86cd6eb0.jpg',98,'8409f4cacd18457cb153f8fe8741d122','1f57374532d74de28d2e21cc8dbfe220.jpg','2018-09-19 18:57:41','f35d8e8d487e45509f99da6e7b071f40	'),
('55fd5fb1ceb24f00a1dc55a5f1e1830c','平凡的世界(共3册)全三册3册 完整版 路遥原著 全套全集 茅盾文学奖经典文学小说书籍 畅销书排行榜 八年级下册书籍','78e66ef1eea14f7ea18c7212f113d839.jpg',47.5,'758afbf0a4d1474e8b53043c6697a9f7','754b2baf653748af99522f8483053196.jpg','2018-09-19 19:00:44','e4f4a6cc046741268edc0279007a50e7'),
('58da3e11448d4748bbadc4f7b5618f7a','家庭实用抽纸','3ab44e9bbc664b95b827ac6bdbff00e5.jpg',45,'2f95f6b82bf54b0bb065b3ac1463c6a8','5bbbc60eda6143518be305094a4559f0.jpg','2018-09-19 16:08:29','f8e8239af9064160a67219fdc89ac553'),
('5a047f2898404e3686e03cf721dcfb29','商商sunny私人定制887226','0f6196bf64fd4cd1b261c02e3db0797e.jpg',980,'b0a1877ba5f84cd89a25e507fb7612d8','90f668bcbed34de09dbd091627bd0ac2.jpg','2018-09-19 18:27:03','cedc50a5125c4987affc1d238a6520c2'),
('69e862bd9d6247ab9ce1f0d1002938da','奶粉大牌','279d9214111f45eba60344cbc148e566.jpg',300,'29feb3d0ad4d4b86aabcaa80e435e29b','c74e26eadcf74518b3604d2409100254.jpg','2018-09-19 16:14:08','cf25d79aac824e82a83a424b7d339df8'),
('732507590eb6454eb307d5591f0958ab','日本代购直邮 SUQQU 2014春季 双色腮红新色 2色选','67ad17b2abfe496e9c7ada2758959445.jpg',432.3,'8409f4cacd18457cb153f8fe8741d122','9dfde6e14d344043a6068c262e298907.jpg','2018-09-19 18:58:03','f35d8e8d487e45509f99da6e7b071f40	'),
('78cbc00d67b14255911adb1c8d4f264b','日本代购资生堂CPB肌肤之钥新双生玫瑰口红润唇膏4g带壳直邮包邮','37e87d880fd64d3a94661e3362661d9d.jpg',390,'8409f4cacd18457cb153f8fe8741d122','ab885a51159840e79144fcfabf921faf.jpg','2018-09-19 18:21:06','f35d8e8d487e45509f99da6e7b071f40	'),
('7b403e32f9384c50b874d3508a41f83a','维达纸巾 超大袋装 16小包','a98c0f64176e49da86e524d95444dcdd.jpg',99,'2f95f6b82bf54b0bb065b3ac1463c6a8','793915bd0eb2450d8c00584f90a1ea13.jpg','2018-09-20 17:15:03','f8e8239af9064160a67219fdc89ac553'),
('7c949da7cec54249b30779b33b883a5c','2018秋季新款韩范高腰阔腿裤女侧边斜条纹宽松显瘦休闲直筒长裤潮','2ce98f36af7545c38f2a5a8c7999f50b.jpg',149,'b0a1877ba5f84cd89a25e507fb7612d8','f2e2cc48e99445fda19a3f189fc12c02.jpg','2018-09-19 18:24:46','cedc50a5125c4987affc1d238a6520c2'),
('7eb591e0c0ab44cd890186edae5f36ef','UCC-满99减50','5a2234649c3145aa86ae0783a0cca28e.jpg',1180.3,'29feb3d0ad4d4b86aabcaa80e435e29b','22ef085e38624966a58b4369912ef8fb.jpg','2018-09-19 18:18:51','cf25d79aac824e82a83a424b7d339df8'),
('81df4c6850284c0ba35cdd568c6ec9c3','身体沐浴会场','9838c2afa876421688f06f6a0ca97d10.jpg',40,'2f95f6b82bf54b0bb065b3ac1463c6a8','28a0eb6908ae404dac2a0f9cb62ef49b.jpg','2018-09-19 16:30:32','f8e8239af9064160a67219fdc89ac553'),
('855d967653cc495ea0f0d90dd5e9183d','进口休闲零食-满99减50','dea68012a73f42bea7e29df8070ad718.jpg',66,'29feb3d0ad4d4b86aabcaa80e435e29b','e8a9a0b3d7224e65b4f7ab57c8536ac5.jpg','2018-09-19 18:56:47','cf25d79aac824e82a83a424b7d339df8'),
('89c96d4be598473b94f892d47cd24395','全套4本 米小圈上学记第一辑 小学一年级课外书注音版 二年级必读小学生课外阅读书籍 儿童读物7-10岁故事书 6-12周岁拼音老师推荐','4dbbc45a201940b79ab22e3fc76e382d.jpg',54,'758afbf0a4d1474e8b53043c6697a9f7','7fc06869ed354a398a0e6ee6d19ea954.jpg','2018-09-19 19:02:14','af19a7af18a24be0b9d4685cd6bcf0b2'),
('8d0860d8aaea486b9448067f73963cf9','正版包邮 HTML5权威指南 弗里曼 html5+css3 从入门到精通  网页源码 web应用开发 ','7bd2902c8d8e4fbab36af9db946d866b.jpg',87.6,'758afbf0a4d1474e8b53043c6697a9f7','3a8fdc4931b749c0b304594689474039.jpg','2018-09-19 19:01:09','e4f4a6cc046741268edc0279007a50e7'),
('9178e14b48aa45768310a1fab555b8ec','国产零食会场','271eb978bd7a4752a8e7690d97e79fb3.jpg',23,'0eba44a141d54156b0f0c16475c084ac','80fab5a3d2f44c3f977cfad7edd76376.jpg','2018-09-19 16:09:01','e44ca1c151c147b5a46ac021c11b46f7'),
('91a523ec1d1142b2ab8d9f2e85ffc183',' 曹文轩系列全套7册','391222bebc314db2abf7961de208a15f.jpg',78,'758afbf0a4d1474e8b53043c6697a9f7','7a5bd1e8e5c64e5b83395f4f1792fe56.jpg','2018-09-19 19:01:57','e4f4a6cc046741268edc0279007a50e7'),
('97e154ff07d84e3f981a4463f9a437ae','孕妇坚果-满99减50','bf114890a96c4d6f938e44cf84f2b20c.jpg',78,'29feb3d0ad4d4b86aabcaa80e435e29b','ecd219921ba041ca86e8bb752dbb2998.jpg','2018-09-19 18:56:32','cf25d79aac824e82a83a424b7d339df8'),
('9bcc7d3fc6684907a63c7c79cefb0958','时尚套装2018秋季新款长袖针织衫毛衣套装女洋气百搭牛仔裤两件套','b05ecdd220794885bb868d9d6f6353d4.jpg',239,'b0a1877ba5f84cd89a25e507fb7612d8','b846a379125c4963adf355259d51ea0c.jpg','2018-09-19 18:26:40','cedc50a5125c4987affc1d238a6520c2'),
('9de787791f0f44fa9b6b344368f14c17','朗读者董卿正版书籍全套(1-3辑)全3册现当代文学随笔中国诗词大会见字如面平凡的世界畅销书籍排行榜正版','797395b3a4424b408688e7131dbbe0d2.jpg',88,'758afbf0a4d1474e8b53043c6697a9f7','afa8d1788f3b48c28d521891184a4300.jpg','2018-09-19 18:59:59','e4f4a6cc046741268edc0279007a50e7'),
('a629e027fe2f4c13b47443b2bb572229','日本正品代购 资生堂心机2014年新款 润彩唇膏/口红 保湿滋润持久','b9a6dc309b7e4deb976c4c38f2ed05ea.jpg',198,'8409f4cacd18457cb153f8fe8741d122','5f61d24ee6914832a5d5b1a8b70c689b.jpg','2018-09-19 18:20:20','f35d8e8d487e45509f99da6e7b071f40	'),
('aa73228fadae485bb08cf4a13500144f','Vue.js实战尤雨溪作序 马骥 站长大漠 在线回','ca147325142d4382ab00d92f6dc2c5d8.jpg',99,'758afbf0a4d1474e8b53043c6697a9f7','52ebfdcbd1c9455488a51189f2dc82cc.jpg','2018-09-19 19:02:45','e4f4a6cc046741268edc0279007a50e7'),
('c88d602b4910414f990fc9c3e96d0cdf','全5册 城南旧事正版包邮 林海音 小学生版五六年级 呼兰河传 萧红著 骆驼祥子老舍初中必读原著阅读的课外书 繁星春水冰心课外书籍','923bad32f6814f57a335eae28d4d9b77.jpg',39.9,'758afbf0a4d1474e8b53043c6697a9f7','73f5d6d3e07047708dc808260d8af56b.jpg','2018-09-19 19:00:21','e4f4a6cc046741268edc0279007a50e7'),
('c94ff2d3d35c4a1bb349fbcde8bc38a6','早秋卫衣少女2018新款chic慵懒bf风韩版宽松长袖怪味春秋装薄上衣','9fa2a9457927425d8029d817b225534b.jpg',258,'b0a1877ba5f84cd89a25e507fb7612d8','6a544a0f1b3e421894f9cd32435b2fbe.jpg','2018-09-19 18:26:06','cedc50a5125c4987affc1d238a6520c2'),
('ceb78f5e4543496294d93c5b8774fcb3','复古名媛秋装新款女装2018波点长袖高腰裙子修身拼接中长款连衣裙','1a9b2fae788844fdb1453ee7c3018ab3.jpg',298,'b0a1877ba5f84cd89a25e507fb7612d8','1946f6235ea745dfb6bf731933f091a9.jpg','2018-09-19 18:26:26','cedc50a5125c4987affc1d238a6520c2'),
('d3740e27ffef46c3a59735f6b1430893','早秋装卫衣女2018新款chic上衣慵懒春原宿风长袖韩版宽松松垮垮薄','418c5342674a4c84bb1022db8e88a9ca.jpg',870,'b0a1877ba5f84cd89a25e507fb7612d8','54c638aa160c4bc785449d93ae897dbf.jpg','2018-09-19 18:15:25','cedc50a5125c4987affc1d238a6520c2'),
('d3efb3d891904e528abb214d9ab5bcd0','【OSL高端定制】600070双面尼','3af6f28baef64ef0a0f0ed7393346963.jpg',580,'b0a1877ba5f84cd89a25e507fb7612d8','dbdc5532f5ca4813806ddb01203b4d0a.jpg','2018-09-19 18:15:01','cedc50a5125c4987affc1d238a6520c2'),
('d9dc8479ba3a423f9dab8d719a1c5ae0','七年级7册包邮 西游记原著正版朝花夕拾鲁迅初中生呐喊猎人笔记湘行散记镜花缘白洋淀纪事世界经典名著青少学版初一课外阅读书籍','e74e39d02f014c2abfdbdfec08360d2a.jpg',55.8,'758afbf0a4d1474e8b53043c6697a9f7','0ae758867ab44529ba68add7ce4b85a5.jpg','2018-09-19 18:16:05','e4f4a6cc046741268edc0279007a50e7'),
('dd31d79d9b2f4524be351934e6adb7f3','龙应台人生三书：目送+亲爱的安德烈+孩子你慢慢来 全3册经典套装 龙应台的书籍 畅销','2c76927bbdce47fbbc19e48812561984.jpg',59.8,'758afbf0a4d1474e8b53043c6697a9f7','f374288be4be4dd9a9275bedffac5da0.jpg','2018-09-19 18:59:35','e4f4a6cc046741268edc0279007a50e7'),
('ec9f427ef75b429fb2839218a75858cc','爱唯他奶粉','3a1ec6caaa1742b48e7f36c10cac99ad.jpg',999,'2f95f6b82bf54b0bb065b3ac1463c6a8','0cba9212ead148589f1986d94ee8018f.jpg','2018-09-20 15:43:07','f8e8239af9064160a67219fdc89ac553'),
('fa4be2eb9d284df788ea60aeb6ef9e77','早秋卫衣少女2018新款chic慵懒bf风韩版宽松长袖怪味春秋装薄上衣','5b765e0a18b8493897a5ed42d91b4bd3.jpg',880,'b0a1877ba5f84cd89a25e507fb7612d8','252e8daee2bd44098dba149250a7eb6f.jpg','2018-09-19 18:25:44','cedc50a5125c4987affc1d238a6520c2');

/*Table structure for table `s_product_type` */

DROP TABLE IF EXISTS `s_product_type`;

CREATE TABLE `s_product_type` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `product_type_name` varchar(50) NOT NULL COMMENT '分类名称',
  `product_type_desc` varchar(500) DEFAULT NULL COMMENT '分类描述',
  `product_type_icon` varchar(100) NOT NULL COMMENT '分类图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_product_type` */

insert  into `s_product_type`(`id`,`product_type_name`,`product_type_desc`,`product_type_icon`) values 
('0eba44a141d54156b0f0c16475c084ac','国产食品','国产食品','icon-headlines_fill'),
('29feb3d0ad4d4b86aabcaa80e435e29b','全球进口','全球进口','icon-brush_fill'),
('2f95f6b82bf54b0bb065b3ac1463c6a8','家居用品','家居用品','icon-redpacket'),
('758afbf0a4d1474e8b53043c6697a9f7','图书学习','图书学习','icon-createtask_fill'),
('8409f4cacd18457cb153f8fe8741d122','护肤美妆','护肤美妆','icon-group_fill'),
('8d629c06a6744d379dd8efdf9a7bf7bb','电子产品','电子产品','icon-like'),
('a4a2b8a2d6b7433dab3ffc2f6db0c4ab','医药保健','医药保健','icon-systemprompt_fill'),
('b0a1877ba5f84cd89a25e507fb7612d8','服装服饰','服装服饰','icon-text'),
('e65e64d29529482eaf8bede18c9699d3','儿童玩具','儿童玩具','icon-empty_fill');

/*Table structure for table `s_receiving_address` */

DROP TABLE IF EXISTS `s_receiving_address`;

CREATE TABLE `s_receiving_address` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `receiving_address` varchar(64) NOT NULL COMMENT '收货地址',
  `receiving_person` varchar(64) NOT NULL COMMENT '收件人',
  `mobile_phone` bigint(20) NOT NULL COMMENT '手机号',
  `user_id` varchar(64) NOT NULL COMMENT '账号',
  `is_default` int(1) NOT NULL COMMENT '默认收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_receiving_address` */

insert  into `s_receiving_address`(`id`,`receiving_address`,`receiving_person`,`mobile_phone`,`user_id`,`is_default`) values 
('801565444dda4520b9424e3d81baa3db','湖北省武汉市洪山区曙光星城B区5栋','华浩',17688561711,'432ab81f72694caba2ae6a4418779175',-1),
('bb61c2e0a7a74a6ea631cc9f4466a9af','河北省秦皇岛市昌黎县ssssss','sssssssss',13888888888,'e888e30e355445a8a55f97f0320ed7a2',1);

/*Table structure for table `s_search_history` */

DROP TABLE IF EXISTS `s_search_history`;

CREATE TABLE `s_search_history` (
  `id` varchar(64) NOT NULL COMMENT 'PK',
  `search_words` varchar(100) NOT NULL COMMENT '搜索词',
  `num` int(11) NOT NULL COMMENT '次数',
  `search_time` datetime NOT NULL COMMENT '搜索时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_search_history` */

insert  into `s_search_history`(`id`,`search_words`,`num`,`search_time`) values 
('0fb71bb180e74306800e8d3a410c0314','华为',3,'2018-09-21 10:19:33'),
('10b9e34b96614143917ec5cb86809e54','牛奶',4,'2018-09-21 10:19:08'),
('189efd31a40b4d2888d7e8f9c2caccac','f',1,'2018-10-11 09:39:59'),
('26c3b0fada3b400db837d7d4700acf01','洗衣粉',4,'2018-10-11 09:43:31'),
('449aa4873e284afa8a81384144725248','车载手机架',4,'2018-09-21 10:20:04'),
('4d681b0b89f84deaa81024aa44249665','图书',27,'2018-09-21 10:17:02'),
('51607181080c4326a47aa55e0e62589c','旗袍',1,'2018-09-26 09:23:21'),
('67ae96e5438a4da0b36c5343a72b0863','空调',3,'2018-09-21 10:17:57'),
('6aee5b8350b94c389d1ed69469d2a17b','d',2,'2018-10-11 09:00:17'),
('7e8a4c8c35624af5989719dc1f9cecd3','苹果',3,'2018-10-11 09:42:44'),
('87dbcc86efb442a8b906a370501420b7','洗衣机',6,'2018-09-21 10:18:02'),
('9153109e15a64957930d865acda1bc21','s',1,'2018-10-11 09:40:09'),
('9527ecac49554c98bbbaabcc4fa40912','java',25,'2018-09-21 10:35:14'),
('9eb6dfeb1eff4c51897d25b4313cc9ed','电脑',1,'2018-09-21 10:19:23'),
('bb875a19ef81470296183b3660e9d897','洗发水',4,'2018-09-21 10:17:37'),
('c0e04159a64c4688a78442e2355eb4a6','小米',1,'2018-09-21 10:19:28'),
('ecb3f4a0e5064d68b1ec2a1110ca206e','进口',3,'2018-09-26 09:23:15'),
('ecc9f97e56cd44f69a6e44cb732e52c0','维达',30,'2018-09-21 10:17:22');

/*Table structure for table `s_shop_cart` */

DROP TABLE IF EXISTS `s_shop_cart`;

CREATE TABLE `s_shop_cart` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `cart_id` varchar(64) NOT NULL COMMENT '购物车id',
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_shop_cart` */

insert  into `s_shop_cart`(`id`,`cart_id`,`user_id`) values 
('8d4bab7e52554bb7b81a14a77c3b4031','8bc05083d997465086c4cbaaee85c1b2','e888e30e355445a8a55f97f0320ed7a2'),
('d87b3656890045ee996690bd1d005e28','c6011a8eabdc49aea3911ce38dd071e1','432ab81f72694caba2ae6a4418779175');

/*Table structure for table `s_shop_cart_product` */

DROP TABLE IF EXISTS `s_shop_cart_product`;

CREATE TABLE `s_shop_cart_product` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `shop_cart_id` varchar(64) NOT NULL COMMENT '购物车id',
  `product_id` varchar(64) NOT NULL COMMENT '商品id',
  `product_num` int(6) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_shop_cart_product` */

/*Table structure for table `s_user` */

DROP TABLE IF EXISTS `s_user`;

CREATE TABLE `s_user` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `type` int(1) NOT NULL COMMENT '用户类型：0、后台用户；1、前台用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_user` */

insert  into `s_user`(`id`,`username`,`password`,`type`) values 
('0','admin','37b28671d6e175480d6d94e30564a2b9',0),
('01dd7f60ca4d4de281860229657c79f9','zzz','f3abb86bd34cf4d52698f14c0da1dc60',0),
('0874b0ef4bc842a98378e57891fc8aaf','huahao','e10adc3949ba59abbe56e057f20f883e',0),
('432ab81f72694caba2ae6a4418779175','huangjinjin','202cb962ac59075b964b07152d234b70',1),
('794248ad767c48408ceaff6ea12a8d06','wudi','e10adc3949ba59abbe56e057f20f883e',0),
('a0ca9ba65d644d858e3886a4f428b447','lisi','e10adc3949ba59abbe56e057f20f883e',1),
('a433b84c42134643901c3466f69dd7a8','aaa','202cb962ac59075b964b07152d234b70',1),
('ac144d77b7274e1099b0017facfb49fa','zhangsan','e10adc3949ba59abbe56e057f20f883e',1),
('e888e30e355445a8a55f97f0320ed7a2','wudi','e10adc3949ba59abbe56e057f20f883e',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
