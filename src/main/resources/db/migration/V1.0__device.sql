DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `identifier` varchar(30) NOT NULL,
  `deliver_at` varchar(30) NOT NULL,
  PRIMARY KEY (`identifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;