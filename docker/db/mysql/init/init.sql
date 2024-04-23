CREATE TABLE member (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberId` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `memberPassword` varchar(255) NOT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`)
);

insert into member (memberId, memberPassword, type) values ('admin', '1234', '1');

CREATE TABLE item (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemName` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `price` int NOT NULL,
  `type` varchar(2) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
);

insert into item (itemName, price, type) values
('마그네슘_1', 3000, 'm')
,('마그네슘_2', 5000, 'm')
,('마그네슘_3', 7000, 'm')
,('마그네슘_4', 9000, 'm')
,('마그네슘_5', 11000, 'm')
,('오메가3_1', 3000, 'o')
,('오메가3_2', 5000, 'o')
,('오메가3_3', 7000, 'o')
,('오메가3_4', 9000, 'o')
,('오메가3_5', 11000, 'o')
,('유산균_1', 3000, 'l')
,('유산균_2', 5000, 'l')
,('유산균_3', 7000, 'l')
,('유산균_4', 9000, 'l')
,('유산균_5', 11000, 'l')
,('비타민C_1', 3000, 'vc')
,('비타민C_2', 5000, 'vc')
,('비타민C_3', 7000, 'vc')
,('비타민C_4', 9000, 'vc')
,('비타민C_5', 11000, 'vc')
,('비타민B_1', 3000, 'vb')
,('비타민B_2', 5000, 'vb')
,('비타민B_3', 7000, 'vb')
,('비타민B_4', 9000, 'vb')
,('비타민B_5', 11000, 'vb')
,('비타민D_1', 3000, 'vd')
,('비타민D_2', 5000, 'vd')
,('비타민D_3', 7000, 'vd')
,('비타민D_4', 9000, 'vd')
,('비타민D_5', 11000, 'vd');

CREATE TABLE memberItem (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberId` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `itemName` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `itemPrice` int NOT NULL,
  `itemQuantity` int NOT NULL,
  PRIMARY KEY (`id`)
);