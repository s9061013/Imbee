DROP TABLE IF EXISTS `fcm_job`;

CREATE TABLE `fcm_job` (
  `identifier` varchar(50) NOT NULL,
  `deliver_at` varchar(50) NOT NULL,
  PRIMARY KEY (`identifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;