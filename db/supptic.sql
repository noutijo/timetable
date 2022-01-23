-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 22, 2022 at 11:46 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `supptic`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `code_categorie` char(5) NOT NULL,
  `taux_tp` int(11) NOT NULL,
  `taux_td` int(11) NOT NULL,
  `taux_cm` int(11) NOT NULL,
  PRIMARY KEY (`code_categorie`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `code_classe` char(5) NOT NULL,
  `nom_classe` char(15) NOT NULL,
  PRIMARY KEY (`code_classe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `code_cours` char(5) NOT NULL,
  `nom_cours` char(30) NOT NULL,
  `nature_cours` char(30) NOT NULL,
  `code_salle` char(5) DEFAULT NULL,
  PRIMARY KEY (`code_cours`),
  KEY `code_salle` (`code_salle`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dates`
--

DROP TABLE IF EXISTS `dates`;
CREATE TABLE IF NOT EXISTS `dates` (
  `periodes` date NOT NULL,
  PRIMARY KEY (`periodes`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dipenser`
--

DROP TABLE IF EXISTS `dipenser`;
CREATE TABLE IF NOT EXISTS `dipenser` (
  `code_cours` char(20) NOT NULL,
  `periodes` date NOT NULL,
  `code_plage` char(5) NOT NULL,
  `id_enseignant` char(5) NOT NULL,
  `code_salle` char(10) NOT NULL,
  PRIMARY KEY (`code_cours`,`periodes`,`code_plage`,`id_enseignant`,`code_salle`),
  KEY `code_salle` (`code_salle`),
  KEY `periodes` (`periodes`),
  KEY `code_plage` (`code_plage`),
  KEY `id_enseignant` (`id_enseignant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `effectuer`
--

DROP TABLE IF EXISTS `effectuer`;
CREATE TABLE IF NOT EXISTS `effectuer` (
  `id_tp` char(5) NOT NULL,
  `code_laboratoire` char(5) NOT NULL,
  `id_enseignant` char(30) NOT NULL,
  `periodes` date NOT NULL,
  `id_groupe` char(5) NOT NULL,
  PRIMARY KEY (`id_tp`,`code_laboratoire`,`id_enseignant`,`periodes`,`id_groupe`),
  KEY `code_laboratoire` (`code_laboratoire`),
  KEY `id_enseignant` (`id_enseignant`),
  KEY `periodes` (`periodes`),
  KEY `id_groupe` (`id_groupe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `id_enseignant` char(5) NOT NULL,
  `nom_enseignant` char(30) NOT NULL,
  `prenom_enseignant` char(30) NOT NULL,
  `add_enseignant` char(30) NOT NULL,
  `email_enseignant` char(30) NOT NULL,
  `telephone_enseignant` int(11) NOT NULL,
  `statut_enseignant` char(30) NOT NULL,
  PRIMARY KEY (`id_enseignant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enseignant`
--

INSERT INTO `enseignant` (`id_enseignant`, `nom_enseignant`, `prenom_enseignant`, `add_enseignant`, `email_enseignant`, `telephone_enseignant`, `statut_enseignant`) VALUES
('JUU', 'Ngoufack Wolfi', 'Ursem W', 'EmanaII', 'ursemngoufack@gmail.com', 690000000, 'Vacataire'),
('15', 'Kemkeu', 'Pacome', 'Melin', 'pacome@gmail.com', 690000000, 'Vacataire'),
('LEN6', 'Noumodong', 'Junior-Oréol', 'Canadas', 'oreolnoumodong@gmail.com', 690000000, 'Vacataire'),
('LEN5', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Permanent'),
('JUH', 'Betsem', 'Edouard', 'Terminux', 'betsemedouard@gmail.com', 690000000, 'Vacataire'),
('11', 'Kameni', 'Alex', 'Ngoa-Ekele', 'kams@gmail.com', 690000000, 'Vacataire'),
('7', 'Etogo', 'Onana', 'Yaoundé,Essomba', 'etogoonana@gmail.com', 690000000, 'Vacataire');

-- --------------------------------------------------------

--
-- Table structure for table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `id_groupe` char(5) NOT NULL,
  `nom_groupe` char(20) NOT NULL,
  `code_classe` char(20) DEFAULT NULL,
  PRIMARY KEY (`id_groupe`),
  KEY `code_classe` (`code_classe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `identifiant`
--

DROP TABLE IF EXISTS `identifiant`;
CREATE TABLE IF NOT EXISTS `identifiant` (
  `autoIncri` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `identifiant`
--

INSERT INTO `identifiant` (`autoIncri`) VALUES
(17);

-- --------------------------------------------------------

--
-- Table structure for table `labo_tp`
--

DROP TABLE IF EXISTS `labo_tp`;
CREATE TABLE IF NOT EXISTS `labo_tp` (
  `code_laboratoire` char(5) NOT NULL,
  `libelle_laboratoire` char(30) NOT NULL,
  PRIMARY KEY (`code_laboratoire`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `plage`
--

DROP TABLE IF EXISTS `plage`;
CREATE TABLE IF NOT EXISTS `plage` (
  `code_plage` char(5) NOT NULL,
  `libelle_plage` char(10) NOT NULL,
  `heure_debut` date NOT NULL,
  `heure_fin` date NOT NULL,
  PRIMARY KEY (`code_plage`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `code_salle` char(5) NOT NULL,
  `nom_salle` char(15) NOT NULL,
  `code_classe` char(5) DEFAULT NULL,
  PRIMARY KEY (`code_salle`),
  KEY `code_classe` (`code_classe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tp`
--

DROP TABLE IF EXISTS `tp`;
CREATE TABLE IF NOT EXISTS `tp` (
  `id_tp` char(5) NOT NULL,
  `nom_tp` char(30) NOT NULL,
  PRIMARY KEY (`id_tp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
