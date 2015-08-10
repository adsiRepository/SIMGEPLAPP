-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-03-2015 a las 02:28:45
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `simgeplap_adsi38`
--
DROP DATABASE IF EXISTS `simgeplap_adsi38`;
CREATE DATABASE IF NOT EXISTS `simgeplap_adsi38` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `simgeplap_adsi38`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencias`
--

DROP TABLE IF EXISTS `incidencias`;
CREATE TABLE IF NOT EXISTS `incidencias` (
`registro` int(25) NOT NULL,
  `incd_codUser` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `lectura` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `factor` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `incidencias`
--

INSERT INTO `incidencias` (`registro`, `incd_codUser`, `lectura`, `factor`, `fecha`, `hora`) VALUES
(1, 'B1103', '145', 'Temperatura', '2014-11-10', '08:31:27'),
(2, 'A0725', '55', 'Nivel', '2014-12-17', '08:42:12'),
(3, 'A9529', '116', 'Temperatura', '2015-01-16', '08:52:23'),
(4, 'B1103', '34', 'Flujo', '2014-09-18', '08:54:28'),
(5, 'B1103', '79', 'Nivel', '2015-01-10', '19:26:56'),
(6, 'B1103', '110', 'Flujo', '2015-01-13', '08:13:00'),
(7, 'B1103', '71', 'Temperatura', '2015-02-15', '19:29:15'),
(8, 'B1103', '86', 'Nivel', '2015-02-10', '19:29:15'),
(9, 'B1103', '175', 'Temperatura', '2015-03-10', '19:29:15'),
(10, 'B1103', '38', 'Flujo', '2015-02-21', '19:29:15'),
(11, 'B1103', '76', 'Nivel', '2015-03-10', '19:29:15'),
(12, 'B1103', '122', 'Temperatura', '2015-04-08', '19:29:15'),
(13, 'B1103', '110', 'Flujo', '2015-03-23', '08:13:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `itinerario`
--

DROP TABLE IF EXISTS `itinerario`;
CREATE TABLE IF NOT EXISTS `itinerario` (
  `iti_codUser` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `nombreUser` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fechaok` date NOT NULL,
  `horaok` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `itinerario`
--

INSERT INTO `itinerario` (`iti_codUser`, `nombreUser`, `fechaok`, `horaok`) VALUES
('A2742', 'Nubia Lopez', '2015-03-11', '19:18:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `codUser` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `id` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `nombres` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nick_user` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `clave_ingreso` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(75) COLLATE utf8_unicode_ci NOT NULL,
  `rol` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`codUser`, `id`, `nombres`, `apellidos`, `nick_user`, `clave_ingreso`, `telefono`, `email`, `rol`) VALUES
('A0000', '000', 'Default', 'Default', 'default', 'b59c67bf196a4758191e42f76670ceba', '77777777', 'simgeplap.adsi38@misena.edu.co', 'Administrador'),
('A0725', '1059702732', 'Neverlin', 'Santana Hoyos', 'paisa', 'afbe0876324c282e444a76aa9a96e5f8', '77777777', 'nsantana2@misena.edu.co', 'Administrador'),
('A2742', '67027804', 'Nubia', 'Lopez', 'nube', '5b3c7f53764c94b35dd130b6b7b0f694', '3173579600', 'nalopez408@misena.edu.co', 'Aprendiz'),
('A9529', '119723', 'Alexander', 'Quembauer', 'franz', '81dc9bdb52d04dc20036dbd8313ed055', '3105355799', 'aqpalacios@misena.edu.co', 'Administrador'),
('B0716', '1143929038', 'Diana', 'Jurado Valencia', 'diana', '8c64561a58f125ea0c3447659229d566', '3157724129', 'djurado8@misena.edu.co', 'Control Tecnico'),
('B1103', '1107057722', 'Miguel', 'González', 'miguel', '4a7d1ed414474e4033ac29ccb8653d9b', '3183693535', 'miguelon_91@misena.edu.co', 'Administrador'),
('B7922', '1118288855', 'Carlos', 'Torres', 'carlos', '4ddf78273835a05e6e87edd282d1cd51', '3104895498', 'catorres558@misena.edu.co', 'Control Tecnico');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `incidencias`
--
ALTER TABLE `incidencias`
 ADD PRIMARY KEY (`registro`), ADD KEY `fk_incidencias_usuarios_idx` (`incd_codUser`);

--
-- Indices de la tabla `itinerario`
--
ALTER TABLE `itinerario`
 ADD PRIMARY KEY (`iti_codUser`), ADD KEY `fk_itinerario_usuarios1_idx` (`iti_codUser`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`codUser`), ADD UNIQUE KEY `codUser_UNIQUE` (`codUser`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `incidencias`
--
ALTER TABLE `incidencias`
MODIFY `registro` int(25) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `incidencias`
--
ALTER TABLE `incidencias`
ADD CONSTRAINT `fk_incidencias_usuarios` FOREIGN KEY (`incd_codUser`) REFERENCES `usuarios` (`codUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `itinerario`
--
ALTER TABLE `itinerario`
ADD CONSTRAINT `fk_itinerario_usuarios1` FOREIGN KEY (`iti_codUser`) REFERENCES `usuarios` (`codUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
