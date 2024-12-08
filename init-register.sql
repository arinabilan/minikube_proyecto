CREATE DATABASE IF NOT EXISTS `bankservice-register`;

GRANT ALL PRIVILEGES ON `bankservice-register`.* TO 'arina'@'%';

FLUSH PRIVILEGES;


USE `bankservice-register`;

-- --------------------------------------------------------
-- Host:                         192.168.1.92
-- Versión del servidor:         8.0.39 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla bankservice-register.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rut` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-register.clients: ~0 rows (aproximadamente)

-- Volcando estructura para tabla bankservice-register.client_dates
CREATE TABLE IF NOT EXISTS `client_dates` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` int NOT NULL,
  `dicom` bit(1) DEFAULT NULL,
  `initial_contract` int NOT NULL,
  `media_salary` int NOT NULL,
  `month_salary` int NOT NULL,
  `monthly_debt` int NOT NULL,
  `type` int NOT NULL,
  `client_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtlqxm5aawi0u3ti33x7ufu8jx` (`client_id`),
  CONSTRAINT `FKtlqxm5aawi0u3ti33x7ufu8jx` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-register.client_dates: ~0 rows (aproximadamente)

-- Volcando estructura para tabla bankservice-register.savings_capacity
CREATE TABLE IF NOT EXISTS `savings_capacity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `balance` int NOT NULL,
  `deposits` int NOT NULL,
  `recent_withdrawals` int NOT NULL,
  `withdrawal` bit(1) DEFAULT NULL,
  `withdrawals` int NOT NULL,
  `years_savings` int NOT NULL,
  `client_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoehuwml3ahetj8o2hvif6sf0n` (`client_id`),
  CONSTRAINT `FKoehuwml3ahetj8o2hvif6sf0n` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-register.savings_capacity: ~0 rows (aproximadamente)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
