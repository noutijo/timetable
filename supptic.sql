-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 28, 2019 at 12:18 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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

CREATE TABLE `categorie` (
  `code_categorie` char(5) NOT NULL,
  `taux_tp` int(11) NOT NULL,
  `taux_td` int(11) NOT NULL,
  `taux_cm` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

CREATE TABLE `classe` (
  `code_classe` char(5) NOT NULL,
  `nom_classe` char(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cours`
--

CREATE TABLE `cours` (
  `code_cours` char(5) NOT NULL,
  `nom_cours` char(30) NOT NULL,
  `nature_cours` char(30) NOT NULL,
  `code_salle` char(5) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dates`
--

CREATE TABLE `dates` (
  `periodes` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dipenser`
--

CREATE TABLE `dipenser` (
  `code_cours` char(20) NOT NULL,
  `periodes` date NOT NULL,
  `code_plage` char(5) NOT NULL,
  `id_enseignant` char(5) NOT NULL,
  `code_salle` char(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `effectuer`
--

CREATE TABLE `effectuer` (
  `id_tp` char(5) NOT NULL,
  `code_laboratoire` char(5) NOT NULL,
  `id_enseignant` char(30) NOT NULL,
  `periodes` date NOT NULL,
  `id_groupe` char(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `enseignant`
--

CREATE TABLE `enseignant` (
  `id_enseignant` char(5) NOT NULL,
  `nom_enseignant` char(30) NOT NULL,
  `prenom_enseignant` char(30) NOT NULL,
  `add_enseignant` char(30) NOT NULL,
  `email_enseignant` char(30) NOT NULL,
  `telephone_enseignant` int(11) NOT NULL,
  `statut_enseignant` char(30) NOT NULL,
  `code_categorie` char(5) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enseignant`
--

INSERT INTO `enseignant` (`id_enseignant`, `nom_enseignant`, `prenom_enseignant`, `add_enseignant`, `email_enseignant`, `telephone_enseignant`, `statut_enseignant`, `code_categorie`) VALUES
('JUU', 'Ngoufack', 'Ursem', 'EmanaII', 'ursemngoufack@gmail.com', 695825524, 'Cuba', 'Chili'),
('EN011', 'Noumo', 'oreo', 'Canada', 'j@hhd', 6522155, 'Cuba', 'Inde'),
('EN004', 'Noumodong Tindjong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Chili', 'Chili'),
('EN5', 'Noumo', 'oreo', 'Canada', 'j@hhd', 6522155, 'Cuba', 'Inde'),
('JUT', 'Ngoufack', 'Ursem', 'EmanaII', 'ursemngoufack@gmail.com', 695825524, 'Cuba', 'Chili'),
('LEN7', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Cuba', 'Togo'),
('LEN6', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Cuba', 'Togo'),
('LEN5', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Cuba', 'Togo'),
('LEN4', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Cuba', 'Togo'),
('JAJ', 'BETSEM A ABEDIANG JU§', 'Edouard Junior', 'Terminux', 'betsemedouard@gmail.com', 689854711, 'Cuba', 'Chili'),
('JILY', 'Betsem', 'Edouard', 'Terminux', 'betsemedouard@gmail.com', 689854711, 'Cuba', 'Chili'),
('JUL', 'Betsem', 'Edouard', 'Terminux', 'betsemedouard@gmail.com', 689854711, 'Cuba', 'Chili'),
('JUH', 'Betsem', 'Edouard', 'Terminux', 'betsemedouard@gmail.com', 689854711, 'Cuba', 'Chili'),
('JUNI', 'Ngoufack', 'Ursem', 'Emana', 'ursemngoufack@gmail.com', 695825524, 'Cuba', 'Chili'),
('JUN', 'Ngoufack', 'Ursem', 'Emana', 'ursemngoufack@gmail.com', 695825524, 'Cuba', 'Chili'),
('LEN', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 651922571, 'Cuba', 'Togo'),
('JILE', 'BETSEM A ABEDIANG', 'Edouard', 'Terminux', 'betsemedouard@gmail.com', 689854711, 'Cuba', 'Chili'),
('LEN3', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 651922571, 'Cuba', 'Togo'),
('LEN8', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Cuba', 'Togo'),
('LEN9', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Cuba', 'Togo'),
('LE10', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Cuba', 'Togo'),
('LE11', 'Noumodong', 'Junior-Oréol', 'Canada', 'oreolnoumodong@gmail.com', 690079333, 'Cuba', 'Togo'),
('riz', 'zambou Zambou', 'Ditriech', 'Yaoundé', 'zam@gmail.com', 655527847, 'Inde', 'Mali'),
('ri1', 'zambou Zambou', 'Ditriech', 'Yaoundé', 'zam@gmail.com', 655527847, 'Inde', 'Mali'),
('ri2', 'zambou Zambou', 'Ditriech', 'Yaoundé', 'zam@gmail.com', 655527847, 'Inde', 'Mali'),
('yon', 'd', 'd', 'l', 'l', 444, 'Irak', 'Togo'),
('yoo', 'd', 'd', 'l', 'l', 444, 'Irak', 'Togo'),
('BRIN', 'Bill', 'Gates', 'Califonia', 'cali@gmail.com', 690079333, 'Laos', 'Chili'),
('BRID', 'Bill', 'Gates', 'Califonia', 'cali@gmail.com', 690079333, 'Laos', 'Chili'),
('BRIT', 'Bill', 'Gates', 'Califonia', 'cali@gmail.com', 690079333, 'Laos', 'Chili'),
('XOn', 'Xoximan', 'poo', 'oo', 'ooo', 14, 'Cuba', 'Chili'),
('XOit', 'Xoximan', 'poo', 'oo', 'ooo', 14, 'Cuba', 'Chili'),
('XOip', 'Xoximan', 'poo', 'oo', 'ooo@lklkll', 14, 'Cuba', 'Chili'),
('Love', 'Noumdong', 'Junior', 'Yaoundé', 'oerolnoumodong@gmail.com', 690079333, 'Cuba', 'Inde'),
('LION', 'Noumdong', 'Junior', 'Yaoundé', 'oerolnoumodong@gmail.com', 690079333, 'Cuba', 'Inde'),
('LOU', 'Noumdong', 'Junior', 'Yaoundé', 'oerolnoumodong@gmail.com', 690079333, 'Cuba', 'Inde');

-- --------------------------------------------------------

--
-- Table structure for table `groupe`
--

CREATE TABLE `groupe` (
  `id_groupe` char(5) NOT NULL,
  `nom_groupe` char(20) NOT NULL,
  `code_classe` char(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `labo_tp`
--

CREATE TABLE `labo_tp` (
  `code_laboratoire` char(5) NOT NULL,
  `libelle_laboratoire` char(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `plage`
--

CREATE TABLE `plage` (
  `code_plage` char(5) NOT NULL,
  `libelle_plage` char(10) NOT NULL,
  `heure_debut` date NOT NULL,
  `heure_fin` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `salle`
--

CREATE TABLE `salle` (
  `code_salle` char(5) NOT NULL,
  `nom_salle` char(15) NOT NULL,
  `code_classe` char(5) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tp`
--

CREATE TABLE `tp` (
  `id_tp` char(5) NOT NULL,
  `nom_tp` char(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`code_categorie`);

--
-- Indexes for table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`code_classe`);

--
-- Indexes for table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`code_cours`),
  ADD KEY `code_salle` (`code_salle`);

--
-- Indexes for table `dates`
--
ALTER TABLE `dates`
  ADD PRIMARY KEY (`periodes`);

--
-- Indexes for table `dipenser`
--
ALTER TABLE `dipenser`
  ADD PRIMARY KEY (`code_cours`,`periodes`,`code_plage`,`id_enseignant`,`code_salle`),
  ADD KEY `code_salle` (`code_salle`),
  ADD KEY `periodes` (`periodes`),
  ADD KEY `code_plage` (`code_plage`),
  ADD KEY `id_enseignant` (`id_enseignant`);

--
-- Indexes for table `effectuer`
--
ALTER TABLE `effectuer`
  ADD PRIMARY KEY (`id_tp`,`code_laboratoire`,`id_enseignant`,`periodes`,`id_groupe`),
  ADD KEY `code_laboratoire` (`code_laboratoire`),
  ADD KEY `id_enseignant` (`id_enseignant`),
  ADD KEY `periodes` (`periodes`),
  ADD KEY `id_groupe` (`id_groupe`);

--
-- Indexes for table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`id_enseignant`),
  ADD KEY `code_categorie` (`code_categorie`);

--
-- Indexes for table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`id_groupe`),
  ADD KEY `code_classe` (`code_classe`);

--
-- Indexes for table `labo_tp`
--
ALTER TABLE `labo_tp`
  ADD PRIMARY KEY (`code_laboratoire`);

--
-- Indexes for table `plage`
--
ALTER TABLE `plage`
  ADD PRIMARY KEY (`code_plage`);

--
-- Indexes for table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`code_salle`),
  ADD KEY `code_classe` (`code_classe`);

--
-- Indexes for table `tp`
--
ALTER TABLE `tp`
  ADD PRIMARY KEY (`id_tp`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
