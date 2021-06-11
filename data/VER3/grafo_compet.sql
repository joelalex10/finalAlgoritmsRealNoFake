-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-06-2021 a las 16:51:31
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `grafo_compet`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enlace_compet`
--

CREATE TABLE `enlace_compet` (
  `id_enlace` int(11) NOT NULL,
  `red` int(11) DEFAULT NULL,
  `green` int(11) DEFAULT NULL,
  `blue` int(11) DEFAULT NULL,
  `id_nodo_1` int(11) DEFAULT NULL,
  `id_nodo_2` int(11) DEFAULT NULL,
  `id_grafo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `enlace_compet`
--

INSERT INTO `enlace_compet` (`id_enlace`, `red`, `green`, `blue`, `id_nodo_1`, `id_nodo_2`, `id_grafo`) VALUES
(13, 255, 255, 255, 29, 31, 1),
(14, 255, 255, 255, 31, 32, 1),
(15, 255, 255, 255, 32, 30, 1),
(16, 255, 255, 255, 30, 29, 1),
(17, 255, 255, 255, 33, 34, 11),
(18, 255, 255, 255, 34, 36, 11),
(19, 255, 255, 255, 36, 35, 11),
(20, 255, 255, 255, 35, 33, 11),
(21, 255, 255, 255, 37, 38, 12),
(22, 255, 255, 255, 38, 40, 12),
(23, 255, 255, 255, 40, 39, 12),
(24, 255, 255, 255, 39, 37, 12),
(25, 255, 255, 255, 41, 42, 13),
(26, 255, 255, 255, 42, 43, 13),
(27, 255, 255, 255, 43, 44, 13),
(28, 255, 255, 255, 44, 41, 13),
(29, 255, 255, 255, 45, 46, 14),
(30, 255, 255, 255, 46, 48, 14),
(31, 255, 255, 255, 48, 47, 14),
(32, 255, 255, 255, 47, 45, 14),
(33, 255, 255, 255, 49, 50, 15),
(34, 255, 255, 255, 50, 51, 15),
(35, 255, 255, 255, 51, 52, 15),
(36, 255, 255, 255, 52, 49, 15),
(37, 255, 255, 255, 53, 54, 16),
(38, 255, 255, 255, 54, 55, 16),
(39, 255, 255, 255, 55, 56, 16),
(40, 255, 255, 255, 56, 53, 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grafo_compet`
--

CREATE TABLE `grafo_compet` (
  `id` int(11) NOT NULL,
  `NOMBRE` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grafo_compet`
--

INSERT INTO `grafo_compet` (`id`, `NOMBRE`) VALUES
(1, 'ver1'),
(11, 'frigo'),
(12, 'archivo 1'),
(13, 'compet 2'),
(14, 'prueba3'),
(15, 'prueba4'),
(16, 'poligono');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nodo_compet`
--

CREATE TABLE `nodo_compet` (
  `id` int(11) NOT NULL,
  `id_grafo` int(11) DEFAULT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `a` int(11) DEFAULT NULL,
  `b` int(11) DEFAULT NULL,
  `xx` double DEFAULT NULL,
  `yy` double DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `red` int(11) DEFAULT NULL,
  `green` int(11) DEFAULT NULL,
  `blue` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nodo_compet`
--

INSERT INTO `nodo_compet` (`id`, `id_grafo`, `x`, `y`, `a`, `b`, `xx`, `yy`, `nombre`, `red`, `green`, `blue`) VALUES
(29, 1, 210, 140, 60, 60, 3, 2, 'a', 9, 11, 48),
(30, 1, 210, 420, 60, 60, 3, 6, 'b', 9, 11, 48),
(31, 1, 420, 210, 60, 60, 6, 3, 'c', 9, 11, 48),
(32, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(33, 11, 210, 70, 60, 60, 3, 1, 'a', 9, 11, 48),
(34, 11, 420, 210, 60, 60, 6, 3, 'b', 9, 11, 48),
(35, 11, 210, 420, 60, 60, 3, 6, 'c', 9, 11, 48),
(36, 11, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(37, 12, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(38, 12, 420, 210, 60, 60, 6, 3, 'b', 9, 11, 48),
(39, 12, 210, 420, 60, 60, 3, 6, 'c', 9, 11, 48),
(40, 12, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(41, 13, 70, 280, 60, 60, 1, 4, 'a', 9, 11, 48),
(42, 13, 140, 350, 60, 60, 2, 5, 'b', 9, 11, 48),
(43, 13, 210, 490, 60, 60, 3, 7, 'c', 9, 11, 48),
(44, 13, 350, 140, 60, 60, 5, 2, 'd', 9, 11, 48),
(45, 14, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(46, 14, 420, 490, 60, 60, 6, 7, 'b', 9, 11, 48),
(47, 14, 70, 350, 60, 60, 1, 5, 'c', 9, 11, 48),
(48, 14, 210, 560, 60, 60, 3, 8, 'd', 9, 11, 48),
(49, 15, 70, 70, 60, 60, 1, 1, 'a', 9, 11, 48),
(50, 15, 210, 70, 60, 60, 3, 1, 'b', 9, 11, 48),
(51, 15, 280, 350, 60, 60, 4, 5, 'c', 9, 11, 48),
(52, 15, 140, 280, 60, 60, 2, 4, 'd', 9, 11, 48),
(53, 16, 70, 70, 60, 60, 1, 1, 'a', 9, 11, 48),
(54, 16, 140, 420, 60, 60, 2, 6, 'b', 9, 11, 48),
(55, 16, 280, 490, 60, 60, 4, 7, 'c', 9, 11, 48),
(56, 16, 350, 140, 60, 60, 5, 2, 'd', 9, 11, 48);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `enlace_compet`
--
ALTER TABLE `enlace_compet`
  ADD PRIMARY KEY (`id_enlace`),
  ADD KEY `id_nodo_1` (`id_nodo_1`),
  ADD KEY `id_nodo_2` (`id_nodo_2`),
  ADD KEY `id_grafo` (`id_grafo`);

--
-- Indices de la tabla `grafo_compet`
--
ALTER TABLE `grafo_compet`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `nodo_compet`
--
ALTER TABLE `nodo_compet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_grafo` (`id_grafo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `enlace_compet`
--
ALTER TABLE `enlace_compet`
  MODIFY `id_enlace` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `grafo_compet`
--
ALTER TABLE `grafo_compet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `nodo_compet`
--
ALTER TABLE `nodo_compet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `enlace_compet`
--
ALTER TABLE `enlace_compet`
  ADD CONSTRAINT `enlace_compet_ibfk_1` FOREIGN KEY (`id_nodo_1`) REFERENCES `nodo_compet` (`id`),
  ADD CONSTRAINT `enlace_compet_ibfk_2` FOREIGN KEY (`id_nodo_2`) REFERENCES `nodo_compet` (`id`),
  ADD CONSTRAINT `enlace_compet_ibfk_3` FOREIGN KEY (`id_grafo`) REFERENCES `grafo_compet` (`id`);

--
-- Filtros para la tabla `nodo_compet`
--
ALTER TABLE `nodo_compet`
  ADD CONSTRAINT `nodo_compet_ibfk_1` FOREIGN KEY (`id_grafo`) REFERENCES `grafo_compet` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
