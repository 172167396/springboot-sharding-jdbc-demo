CREATE DATABASE `db_payment0` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

use db_payment0;

DROP TABLE IF EXISTS `tb_pay0`;
CREATE TABLE `tb_pay0` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_pay1`;
CREATE TABLE `tb_pay1` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_pay2`;
CREATE TABLE `tb_pay2` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_pay3`;
CREATE TABLE `tb_pay3` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `tb_pay4`;
CREATE TABLE `tb_pay4` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE DATABASE `db_payment1` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

use db_payment1;

DROP TABLE IF EXISTS `tb_pay0`;
CREATE TABLE `tb_pay0` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_pay1`;
CREATE TABLE `tb_pay1` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_pay2`;
CREATE TABLE `tb_pay2` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_pay3`;
CREATE TABLE `tb_pay3` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `tb_pay4`;
CREATE TABLE `tb_pay4` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;