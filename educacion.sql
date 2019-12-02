-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2019 a las 19:09:17
-- Versión del servidor: 5.7.17
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `educacion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `talumnos`
--

CREATE TABLE `talumnos` (
  `codigoalumno` int(4) UNSIGNED NOT NULL,
  `nombrealumno` varchar(15) COLLATE latin1_general_ci NOT NULL,
  `apellidosalumno` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `dnialumno` varchar(14) COLLATE latin1_general_ci NOT NULL,
  `direccionalumno` varchar(60) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci COMMENT='Tabla\r\nde Alumnos';

--
-- Volcado de datos para la tabla `talumnos`
--

INSERT INTO `talumnos` (`codigoalumno`, `nombrealumno`, `apellidosalumno`, `dnialumno`, `direccionalumno`) VALUES
(1, 'José', 'Martínez Plaza', '084579856L', 'Calle del Pinto, 34'),
(2, 'María', 'Coz Martín', '6958452K', 'Calle Ambrosia, 2 - 3oA'),
(3, 'Luis', 'Fernandez Martínez', '96854225H', 'Paseo de la Alameda, 56'),
(4, 'Fernando', 'sanchez', '449', 'c/del hierro'),
(7, 'Paco', 'Rodriguez', '8888888B', 'C/Jamon'),
(8, 'Javier', 'Garcia', '2222222C', 'C/Grecia'),
(9, 'Pedro Manuel', 'cubo', '111111A', 'c/viento'),
(10, 'Marta', 'Lopez', '2222222B', 'C/ Cea Bermudez 2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `talumnosdetalles`
--

CREATE TABLE `talumnosdetalles` (
  `codigoalumnodetalle` int(11) NOT NULL,
  `codigoalumnodetalleenlace` int(11) DEFAULT NULL,
  `moduloalumnodetalle` varchar(30) DEFAULT NULL,
  `evaluacionalumnodetalle` int(11) DEFAULT NULL,
  `notamoduloalumnodetalle` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `talumnosdetalles`
--

INSERT INTO `talumnosdetalles` (`codigoalumnodetalle`, `codigoalumnodetalleenlace`, `moduloalumnodetalle`, `evaluacionalumnodetalle`, `notamoduloalumnodetalle`) VALUES
(1, 9, 'Marcas', 2, 7),
(15, 2, 'BBDD', 1, 10),
(4, 9, 'BBDD', 3, 2),
(6, 8, 'Marcas', 1, 3),
(7, 8, 'BBDD', 2, 10),
(8, 1, 'Marcas', 1, 7),
(9, 2, 'Marcas', 2, 6),
(10, 2, 'Programacion', 2, 7),
(11, 1, 'Programacion', 2, 9),
(12, 7, 'Marcas', 2, 5),
(13, 7, 'Sistemas', 3, 5),
(14, 7, 'BBDD', 3, 6),
(16, 4, 'BBDD', 3, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `talumnosnotas`
--

CREATE TABLE `talumnosnotas` (
  `codigoalumnonota` int(4) NOT NULL,
  `codigoenlacealumnonota` int(4) DEFAULT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `DNI` varchar(20) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `moduloalumnonota` varchar(20) NOT NULL,
  `evaluacionalumnonota` tinyint(1) UNSIGNED NOT NULL,
  `notaalumno` tinyint(1) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `talumnosnotas`
--

INSERT INTO `talumnosnotas` (`codigoalumnonota`, `codigoenlacealumnonota`, `Nombre`, `Apellido`, `DNI`, `Direccion`, `moduloalumnonota`, `evaluacionalumnonota`, `notaalumno`) VALUES
(1, 9, 'Pedro Manuel', 'cubo', '111111A', 'c/viento', 'Marcas', 2, 7),
(2, 2, 'María', 'Coz Martín', '6958452K', 'Calle Ambrosia, 2 - 3oA', 'BBDD', 1, 10),
(3, 9, 'Pedro Manuel', 'cubo', '111111A', 'c/viento', 'BBDD', 3, 2),
(4, 8, 'Javier', 'Garcia', '2222222C', 'C/Grecia', 'Marcas', 1, 3),
(5, 8, 'Javier', 'Garcia', '2222222C', 'C/Grecia', 'BBDD', 2, 10),
(6, 1, 'José', 'Martínez Plaza', '084579856L', 'Calle del Pinto, 34', 'Marcas', 1, 7),
(7, 2, 'María', 'Coz Martín', '6958452K', 'Calle Ambrosia, 2 - 3oA', 'Marcas', 2, 6),
(8, 2, 'María', 'Coz Martín', '6958452K', 'Calle Ambrosia, 2 - 3oA', 'Programacion', 2, 7),
(9, 1, 'José', 'Martínez Plaza', '084579856L', 'Calle del Pinto, 34', 'Programacion', 2, 9),
(10, 7, 'Paco', 'Rodriguez', '8888888B', 'C/Jamon', 'Marcas', 2, 5),
(11, 7, 'Paco', 'Rodriguez', '8888888B', 'C/Jamon', 'Sistemas', 3, 5),
(12, 7, 'Paco', 'Rodriguez', '8888888B', 'C/Jamon', 'BBDD', 3, 6),
(13, 4, 'Fernando', 'sanchez', '449', 'c/del hierro', 'BBDD', 3, 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `talumnos`
--
ALTER TABLE `talumnos`
  ADD PRIMARY KEY (`codigoalumno`),
  ADD UNIQUE KEY `dnialumno` (`dnialumno`);

--
-- Indices de la tabla `talumnosdetalles`
--
ALTER TABLE `talumnosdetalles`
  ADD PRIMARY KEY (`codigoalumnodetalle`),
  ADD UNIQUE KEY `nuevoindice` (`codigoalumnodetalleenlace`,`moduloalumnodetalle`,`evaluacionalumnodetalle`) USING BTREE;

--
-- Indices de la tabla `talumnosnotas`
--
ALTER TABLE `talumnosnotas`
  ADD PRIMARY KEY (`codigoalumnonota`),
  ADD UNIQUE KEY `indice` (`moduloalumnonota`,`evaluacionalumnonota`,`notaalumno`),
  ADD KEY `PK_foranea` (`codigoenlacealumnonota`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `talumnos`
--
ALTER TABLE `talumnos`
  MODIFY `codigoalumno` int(4) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `talumnosdetalles`
--
ALTER TABLE `talumnosdetalles`
  MODIFY `codigoalumnodetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT de la tabla `talumnosnotas`
--
ALTER TABLE `talumnosnotas`
  MODIFY `codigoalumnonota` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
