-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema info_team09_schema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema info_team09_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `info_team09_schema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `info_team09_schema` ;

-- -----------------------------------------------------
-- Table `info_team09_schema`.`discipline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team09_schema`.`discipline` (
  `name` VARCHAR(45) NOT NULL,
  `flag` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team09_schema`.`athlete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team09_schema`.`athlete` (
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `birthday` DATE NOT NULL,
  `gender` ENUM('H', 'F', 'A') NOT NULL,
  `nationality` VARCHAR(45) NOT NULL,
  `discipline_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`first_name`, `last_name`),
  INDEX `fk_athlete_discipline1_idx` (`discipline_name` ASC) VISIBLE,
  CONSTRAINT `fk_athlete_discipline1`
    FOREIGN KEY (`discipline_name`)
    REFERENCES `info_team09_schema`.`discipline` (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team09_schema`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team09_schema`.`site` (
  `idSite` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `category` ENUM('stade', 'salle_de_spectacle', 'lieu_public', 'centre_aquatique') NOT NULL,
  PRIMARY KEY (`idSite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team09_schema`.`session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team09_schema`.`session` (
  `code` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  `discipline` VARCHAR(45) NOT NULL,
  `idSite` INT NOT NULL,
  `description` LONGTEXT NULL,
  `type` ENUM('qualifications', 'medailles') NOT NULL,
  `category` ENUM('H', 'F', 'M') NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `discipline_idx` (`discipline` ASC) VISIBLE,
  INDEX `idSite_idx` (`idSite` ASC) VISIBLE,
  CONSTRAINT `discipline`
    FOREIGN KEY (`discipline`)
    REFERENCES `info_team09_schema`.`discipline` (`name`),
  CONSTRAINT `idSite`
    FOREIGN KEY (`idSite`)
    REFERENCES `info_team09_schema`.`site` (`idSite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `info_team09_schema`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team09_schema`.`user` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'gesC', 'gesS') NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
