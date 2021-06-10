-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2021 a las 03:19:00
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
(20, 255, 255, 255, 35, 33, 11);

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
(2, 'ver2'),
(3, 'ver4'),
(4, 'ver6'),
(5, 'ver7'),
(6, 'ver9'),
(7, 'ver10'),
(8, 'ver11'),
(9, 'qwer'),
(10, 'ver12'),
(11, 'frigo');

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
(1, 1, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(2, 1, 210, 420, 60, 60, 3, 6, 'b', 9, 11, 48),
(3, 1, 420, 210, 60, 60, 6, 3, 'c', 9, 11, 48),
(4, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(5, 1, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(6, 1, 210, 420, 60, 60, 3, 6, 'b', 9, 11, 48),
(7, 1, 420, 210, 60, 60, 6, 3, 'c', 9, 11, 48),
(8, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(9, 1, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(10, 1, 210, 420, 60, 60, 3, 6, 'b', 9, 11, 48),
(11, 1, 420, 210, 60, 60, 6, 3, 'c', 9, 11, 48),
(12, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(13, 1, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(14, 1, 210, 420, 60, 60, 3, 6, 'b', 9, 11, 48),
(15, 1, 420, 210, 60, 60, 6, 3, 'c', 9, 11, 48),
(16, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(17, 1, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(18, 1, 420, 210, 60, 60, 6, 3, 'b', 9, 11, 48),
(19, 1, 210, 420, 60, 60, 3, 6, 'c', 9, 11, 48),
(20, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(21, 1, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(22, 1, 420, 210, 60, 60, 6, 3, 'b', 9, 11, 48),
(23, 1, 210, 420, 60, 60, 3, 6, 'c', 9, 11, 48),
(24, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(25, 1, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(26, 1, 420, 210, 60, 60, 6, 3, 'b', 9, 11, 48),
(27, 1, 210, 420, 60, 60, 3, 6, 'c', 9, 11, 48),
(28, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(29, 1, 210, 210, 60, 60, 3, 3, 'a', 9, 11, 48),
(30, 1, 210, 420, 60, 60, 3, 6, 'b', 9, 11, 48),
(31, 1, 420, 210, 60, 60, 6, 3, 'c', 9, 11, 48),
(32, 1, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48),
(33, 11, 210, 140, 60, 60, 3, 2, 'a', 9, 11, 48),
(34, 11, 420, 210, 60, 60, 6, 3, 'b', 9, 11, 48),
(35, 11, 210, 420, 60, 60, 3, 6, 'c', 9, 11, 48),
(36, 11, 420, 420, 60, 60, 6, 6, 'd', 9, 11, 48);

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
  MODIFY `id_enlace` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `grafo_compet`
--
ALTER TABLE `grafo_compet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `nodo_compet`
--
ALTER TABLE `nodo_compet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

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
