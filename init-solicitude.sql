CREATE DATABASE IF NOT EXISTS `bankservice-solicitude`;

GRANT ALL PRIVILEGES ON `bankservice-solicitude`.* TO 'arina'@'%';

FLUSH PRIVILEGES;


USE `bankservice-solicitude`;

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

-- Volcando estructura para tabla bankservice-solicitude.client_documents
CREATE TABLE IF NOT EXISTS `client_documents` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_id` bigint DEFAULT NULL,
  `documento` longblob,
  `estado` bit(1) DEFAULT NULL,
  `fecha_carga` date DEFAULT NULL,
  `ruta_documento` varchar(255) DEFAULT NULL,
  `document_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKg2ddcw8rwbomtc27ovodpkkcc` (`client_id`,`document_id`),
  KEY `FK5buouoi886x6brnkqu7s4n8r5` (`document_id`),
  CONSTRAINT `FK5buouoi886x6brnkqu7s4n8r5` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-solicitude.client_documents: ~0 rows (aproximadamente)

-- Volcando estructura para tabla bankservice-solicitude.documents
CREATE TABLE IF NOT EXISTS `documents` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `minimun_requirements` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-solicitude.documents: ~11 rows (aproximadamente)
INSERT IGNORE INTO `documents` (`id`, `minimun_requirements`, `title`) VALUES
	(1, b'0', 'Comprobante de ingresos'),
	(2, b'0', 'Certificado de avaluo'),
	(3, b'0', 'Historial crediticio'),
	(4, b'0', 'Escritura de la primera vivienda'),
	(5, b'0', 'Estado financiero del negocio'),
	(6, b'0', 'Plan de negocios'),
	(7, b'0', 'Presupuesto de la remodelacion'),
	(8, b'1', 'Comprobante de ingresos de 2 años'),
	(9, b'1', 'Certificado Dicom'),
	(10, b'1', 'Certificado de Cuenta Ahorro'),
	(11, b'1', 'Contrato laboral');

-- Volcando estructura para tabla bankservice-solicitude.requirements
CREATE TABLE IF NOT EXISTS `requirements` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `interest_rate` decimal(19,2) DEFAULT NULL,
  `max_amount` decimal(19,2) DEFAULT NULL,
  `max_months` int NOT NULL,
  `type_loan_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbl5a8lko0e1ret0wb0ptgsdyp` (`type_loan_id`),
  CONSTRAINT `FKbl5a8lko0e1ret0wb0ptgsdyp` FOREIGN KEY (`type_loan_id`) REFERENCES `typeloan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-solicitude.requirements: ~0 rows (aproximadamente)
INSERT IGNORE INTO `requirements` (`id`, `interest_rate`, `max_amount`, `max_months`, `type_loan_id`) VALUES
	(1, 0.05, 0.80, 360, 1),
	(2, 0.06, 0.70, 240, 2),
	(3, 0.07, 0.60, 300, 3),
	(4, 0.06, 0.50, 180, 4);

-- Volcando estructura para tabla bankservice-solicitude.requirement_documents
CREATE TABLE IF NOT EXISTS `requirement_documents` (
  `requirement_id` bigint NOT NULL,
  `document_id` bigint NOT NULL,
  KEY `FKljqnk3yrgrnvk7eeq961s8h5t` (`document_id`),
  KEY `FKsl7gqogu2h0rx5m6m9ihym4q1` (`requirement_id`),
  CONSTRAINT `FKljqnk3yrgrnvk7eeq961s8h5t` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`),
  CONSTRAINT `FKsl7gqogu2h0rx5m6m9ihym4q1` FOREIGN KEY (`requirement_id`) REFERENCES `requirements` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-solicitude.requirement_documents: ~0 rows (aproximadamente)
INSERT IGNORE INTO `requirement_documents` (`requirement_id`, `document_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(2, 1),
	(2, 2),
	(2, 3),
	(2, 4),
	(3, 1),
	(3, 2),
	(3, 5),
	(3, 6),
	(4, 1),
	(4, 2),
	(4, 7);

-- Volcando estructura para tabla bankservice-solicitude.solicitudes
CREATE TABLE IF NOT EXISTS `solicitudes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `calculated_amount` float NOT NULL,
  `client_id` bigint DEFAULT NULL,
  `date` date DEFAULT NULL,
  `deadline` int NOT NULL,
  `executive_id` bigint DEFAULT NULL,
  `interest_rate` double NOT NULL,
  `max_amount` float NOT NULL,
  `max_months` float NOT NULL,
  `state` int NOT NULL,
  `loan_type_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg0kqs11tpar2xnybrl5dx6krw` (`loan_type_id`),
  CONSTRAINT `FKg0kqs11tpar2xnybrl5dx6krw` FOREIGN KEY (`loan_type_id`) REFERENCES `typeloan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-solicitude.solicitudes: ~0 rows (aproximadamente)

-- Volcando estructura para tabla bankservice-solicitude.typeloan
CREATE TABLE IF NOT EXISTS `typeloan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bankservice-solicitude.typeloan: ~0 rows (aproximadamente)
INSERT IGNORE INTO `typeloan` (`id`, `type`) VALUES
	(1, 'Primera vivienda'),
	(2, 'Segunda vivienda'),
	(3, 'Propiedades comerciales'),
	(4, 'Remodelacion');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
