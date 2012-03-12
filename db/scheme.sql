-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 11, 2012 at 07:30 PM
-- Server version: 5.5.21
-- PHP Version: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `calendar`
--
CREATE DATABASE `calendar` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `calendar`;

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE IF NOT EXISTS `appointment` (
  `appointment_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `room_id` int(11) NOT NULL,
  `meeting_leader_id` int(11) NOT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `fk_appointment_room` (`room_id`),
  KEY `fk_appointment_user1` (`meeting_leader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `appointment_has_attending`
--

CREATE TABLE IF NOT EXISTS `appointment_has_attending` (
  `appointment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`appointment_id`,`user_id`),
  KEY `fk_appointment_has_user_user1` (`user_id`),
  KEY `fk_appointment_has_user_appointment1` (`appointment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `seats` int(11) NOT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` int(10) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_has_callup`
--

CREATE TABLE IF NOT EXISTS `user_has_callup` (
  `appointment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `has_declined` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`,`user_id`),
  KEY `fk_appointment_has_user1_user1` (`user_id`),
  KEY `fk_appointment_has_user1_appointment1` (`appointment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk_appointment_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appointment_user1` FOREIGN KEY (`meeting_leader_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `appointment_has_attending`
--
ALTER TABLE `appointment_has_attending`
  ADD CONSTRAINT `fk_appointment_has_user_appointment1` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`appointment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appointment_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_has_callup`
--
ALTER TABLE `user_has_callup`
  ADD CONSTRAINT `fk_appointment_has_user1_appointment1` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`appointment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appointment_has_user1_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
