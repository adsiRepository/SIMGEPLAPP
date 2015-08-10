-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-08-2015 a las 14:53:10
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `simgeplapp`
--
CREATE DATABASE IF NOT EXISTS `simgeplapp` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `simgeplapp`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencias`
--

DROP TABLE IF EXISTS `incidencias`;
CREATE TABLE IF NOT EXISTS `incidencias` (
`registro` int(255) NOT NULL,
  `lectura` double(5,2) NOT NULL,
  `factor` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `incidencias`
--

INSERT INTO `incidencias` (`registro`, `lectura`, `factor`, `fecha`, `hora`) VALUES
(1, 114.45, 'presion', '2015-08-10', '09:10:14'),
(2, 101.42, 'nivel', '2015-08-10', '09:10:15'),
(3, 11.12, 'presion', '2015-08-10', '09:15:12'),
(4, 108.99, 'nivel', '2015-08-10', '09:16:53'),
(5, 107.27, 'presion', '2015-08-10', '09:16:54'),
(6, 98.90, 'presion', '2015-08-10', '09:17:31'),
(7, 95.63, 'nivel', '2015-08-10', '09:18:19'),
(8, 136.22, 'temperatura', '2015-08-10', '09:18:56'),
(9, 102.78, 'presion', '2015-08-10', '09:19:34'),
(10, 20.40, 'nivel', '2015-08-10', '09:19:36'),
(11, 138.56, 'temperatura', '2015-08-10', '09:27:33'),
(12, 0.25, 'temperatura', '2015-08-10', '09:30:14'),
(13, 98.81, 'presion', '2015-08-10', '09:30:33'),
(14, 18.64, 'presion', '2015-08-10', '09:30:54'),
(15, 93.61, 'nivel', '2015-08-10', '09:32:46'),
(16, 81.00, 'nivel', '2015-08-10', '09:33:10'),
(17, 82.43, 'nivel', '2015-08-10', '09:33:29'),
(18, 94.95, 'presion', '2015-08-10', '09:35:08'),
(19, 20.07, 'temperatura', '2015-08-10', '09:35:22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lecturas`
--

DROP TABLE IF EXISTS `lecturas`;
CREATE TABLE IF NOT EXISTS `lecturas` (
  `actualTemp` double(5,2) NOT NULL,
  `actualPresion` double(5,2) NOT NULL,
  `actualNivel` double(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `lecturas`
--

INSERT INTO `lecturas` (`actualTemp`, `actualPresion`, `actualNivel`) VALUES
(47.48, 75.30, 78.58);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `apes` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `tipo_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pass` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `rol` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `nick` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apes`, `tipo_id`, `telefono`, `email`, `pass`, `rol`, `nick`) VALUES
('1107057722', 'Miguel', 'González', 'CC', '3173567440', 'miguelon_91@misena.edu.co', '1234', 'Administrador', 'migue'),
('16405506', 'Matilda', 'Ortiz', 'CC', '', '', 'samanta', '', 'mati4042'),
('5556', 'Eriberto', 'Asprilla', 'CC', '', '', 'ooo', 'Administrador', 'memo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `incidencias`
--
ALTER TABLE `incidencias`
 ADD PRIMARY KEY (`registro`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `incidencias`
--
ALTER TABLE `incidencias`
MODIFY `registro` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
