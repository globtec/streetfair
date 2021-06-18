-- -----------------------------------------------------
-- Schema streetfair
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `streetfair` DEFAULT CHARACTER SET utf8 ;
USE `streetfair` ;

-- -----------------------------------------------------
-- Table `streetfair`.`fair`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streetfair`.`fair` ;

CREATE TABLE IF NOT EXISTS `streetfair`.`fair` (
  `id` VARCHAR(8) NULL,
  `lat` VARCHAR(10) NULL,
  `lon` VARCHAR(10) NULL,
  `setcems` VARCHAR(15) NULL,
  `weighting_area` VARCHAR(13) NULL,
  `district_code` VARCHAR(9) NULL,
  `district` VARCHAR(18) NULL,
  `subprefecture_code` CHAR(2) NULL,
  `subprefecture` VARCHAR(25) NULL,
  `region5` VARCHAR(6) NULL,
  `region8` VARCHAR(7) NULL,
  `name` VARCHAR(30) NULL,
  `registry` VARCHAR(6) NOT NULL,
  `address` VARCHAR(34) NULL,
  `number` VARCHAR(15) NULL,
  `neighborhood` VARCHAR(20) NULL,
  `landmark` VARCHAR(45) NULL,
  PRIMARY KEY (`registry`))
ENGINE = InnoDB;
