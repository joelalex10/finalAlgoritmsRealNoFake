-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-06-2021 a las 16:51:27
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
-- Base de datos: `grafo_asignacion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enlace`
--

CREATE TABLE `enlace` (
  `id_grafo` int(11) NOT NULL,
  `x1` int(11) NOT NULL,
  `y1` int(11) NOT NULL,
  `x2` int(11) NOT NULL,
  `y2` int(11) NOT NULL,
  `atributo` int(11) NOT NULL,
  `thickness` int(11) NOT NULL,
  `nodo_inicio` int(11) NOT NULL,
  `nodo_fin` int(11) NOT NULL,
  `ciclo` tinyint(1) NOT NULL,
  `color_red` int(11) NOT NULL,
  `color_green` int(11) NOT NULL,
  `color_blue` int(11) NOT NULL,
  `grafo_id` int(11) NOT NULL,
  `text_direction` int(11) DEFAULT NULL,
  `nroActividadNodoInicio` int(11) NOT NULL,
  `nroActividadNodoFin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `enlace`
--

INSERT INTO `enlace` (`id_grafo`, `x1`, `y1`, `x2`, `y2`, `atributo`, `thickness`, `nodo_inicio`, `nodo_fin`, `ciclo`, `color_red`, `color_green`, `color_blue`, `grafo_id`, `text_direction`, `nroActividadNodoInicio`, `nroActividadNodoFin`) VALUES
(172, 119, 88, 623, 209, 85, 0, 202, 206, 0, 255, 255, 255, 47, 0, 0, 1),
(173, 119, 88, 613, 97, 55, 0, 202, 205, 0, 255, 255, 255, 47, 0, 0, 0),
(174, 119, 88, 654, 351, 68, 0, 202, 207, 0, 255, 255, 255, 47, 0, 0, 2),
(175, 135, 208, 613, 97, 71, 0, 203, 205, 0, 255, 255, 255, 47, 0, 1, 0),
(176, 135, 208, 623, 209, 69, 0, 203, 206, 0, 255, 255, 255, 47, 0, 1, 1),
(177, 135, 208, 654, 351, 72, 0, 203, 207, 0, 255, 255, 255, 47, 0, 1, 2),
(178, 171, 361, 613, 97, 82, 0, 204, 205, 0, 255, 255, 255, 47, 0, 2, 0),
(179, 171, 361, 623, 209, 52, 0, 204, 206, 0, 255, 255, 255, 47, 0, 2, 1),
(180, 171, 361, 654, 351, 72, 0, 204, 207, 0, 255, 255, 255, 47, 0, 2, 2),
(181, 134, 100, 672, 78, 3, 0, 208, 211, 0, 255, 255, 255, 48, 0, 0, 0),
(182, 134, 100, 704, 178, 4, 0, 208, 212, 0, 255, 255, 255, 48, 0, 0, 1),
(183, 134, 100, 711, 278, 6, 0, 208, 213, 0, 255, 255, 255, 48, 0, 0, 2),
(184, 134, 100, 717, 386, 6, 0, 208, 214, 0, 255, 255, 255, 48, 0, 0, 3),
(185, 152, 205, 672, 78, 6, 0, 209, 211, 0, 255, 255, 255, 48, 0, 1, 0),
(186, 152, 205, 704, 178, 3, 0, 209, 212, 0, 255, 255, 255, 48, 0, 1, 1),
(187, 152, 205, 711, 278, 4, 0, 209, 213, 0, 255, 255, 255, 48, 0, 1, 2),
(188, 152, 205, 717, 386, 7, 0, 209, 214, 0, 255, 255, 255, 48, 0, 1, 3),
(189, 161, 347, 672, 78, 1, 0, 210, 211, 0, 255, 255, 255, 48, 0, 2, 0),
(190, 161, 347, 704, 178, 5, 0, 210, 212, 0, 255, 255, 255, 48, 0, 2, 1),
(191, 161, 347, 711, 278, 3, 0, 210, 213, 0, 255, 255, 255, 48, 0, 2, 2),
(192, 161, 347, 717, 386, 2, 0, 210, 214, 0, 255, 255, 255, 48, 0, 2, 3),
(193, 134, 100, 672, 78, 3, 0, 215, 218, 0, 255, 255, 255, 49, 0, 0, 0),
(194, 134, 100, 704, 178, 4, 0, 215, 219, 0, 255, 255, 255, 49, 0, 0, 1),
(195, 134, 100, 711, 278, 6, 0, 215, 220, 0, 255, 255, 255, 49, 0, 0, 2),
(196, 134, 100, 717, 386, 6, 0, 215, 221, 0, 255, 255, 255, 49, 0, 0, 3),
(197, 152, 205, 672, 78, 6, 0, 216, 218, 0, 255, 255, 255, 49, 0, 1, 0),
(198, 152, 205, 704, 178, 3, 0, 216, 219, 0, 255, 255, 255, 49, 0, 1, 1),
(199, 152, 205, 711, 278, 4, 0, 216, 220, 0, 255, 255, 255, 49, 0, 1, 2),
(200, 152, 205, 717, 386, 7, 0, 216, 221, 0, 255, 255, 255, 49, 0, 1, 3),
(201, 161, 347, 672, 78, 1, 0, 217, 218, 0, 255, 255, 255, 49, 0, 2, 0),
(202, 161, 347, 704, 178, 5, 0, 217, 219, 0, 255, 255, 255, 49, 0, 2, 1),
(203, 161, 347, 711, 278, 3, 0, 217, 220, 0, 255, 255, 255, 49, 0, 2, 2),
(204, 161, 347, 717, 386, 2, 0, 217, 221, 0, 255, 255, 255, 49, 0, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grafo`
--

CREATE TABLE `grafo` (
  `id_grafo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `grafo`
--

INSERT INTO `grafo` (`id_grafo`, `nombre`) VALUES
(47, 'asignacion puestos'),
(48, 'kramer 1'),
(49, 'p');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nodo`
--

CREATE TABLE `nodo` (
  `id_nodo` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `grafo_id_grafo` int(11) NOT NULL,
  `color_red` int(11) NOT NULL,
  `color_green` int(11) NOT NULL,
  `color_blue` int(11) NOT NULL,
  `nro_actividad` int(11) NOT NULL,
  `asignacion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `nodo`
--

INSERT INTO `nodo` (`id_nodo`, `x`, `y`, `nombre`, `grafo_id_grafo`, `color_red`, `color_green`, `color_blue`, `nro_actividad`, `asignacion`) VALUES
(202, 119, 88, 'a', 47, 9, 11, 48, 0, 1),
(203, 135, 208, 'b', 47, 9, 11, 48, 1, 1),
(204, 171, 361, 'c', 47, 9, 11, 48, 2, 1),
(205, 613, 97, 'x', 47, 156, 12, 12, 0, 2),
(206, 623, 209, 'y', 47, 156, 12, 12, 1, 2),
(207, 654, 351, 'z', 47, 156, 12, 12, 2, 2),
(208, 134, 100, 'a', 48, 9, 11, 48, 0, 1),
(209, 152, 205, 'b', 48, 9, 11, 48, 1, 1),
(210, 161, 347, 'c', 48, 9, 11, 48, 2, 1),
(211, 672, 78, '1', 48, 156, 12, 12, 0, 2),
(212, 704, 178, '2', 48, 156, 12, 12, 1, 2),
(213, 711, 278, '3', 48, 156, 12, 12, 2, 2),
(214, 717, 386, '4', 48, 156, 12, 12, 3, 2),
(215, 134, 100, 'a', 49, 9, 11, 48, 0, 1),
(216, 152, 205, 'b', 49, 9, 11, 48, 1, 1),
(217, 161, 347, 'c', 49, 9, 11, 48, 2, 1),
(218, 672, 78, '1', 49, 156, 12, 12, 0, 2),
(219, 704, 178, '2', 49, 156, 12, 12, 1, 2),
(220, 711, 278, '3', 49, 156, 12, 12, 2, 2),
(221, 717, 386, '4', 49, 156, 12, 12, 3, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `enlace`
--
ALTER TABLE `enlace`
  ADD PRIMARY KEY (`id_grafo`),
  ADD KEY `nodo_inicio` (`nodo_inicio`),
  ADD KEY `nodo_fin` (`nodo_fin`),
  ADD KEY `grafo_id` (`grafo_id`);

--
-- Indices de la tabla `grafo`
--
ALTER TABLE `grafo`
  ADD PRIMARY KEY (`id_grafo`);

--
-- Indices de la tabla `nodo`
--
ALTER TABLE `nodo`
  ADD PRIMARY KEY (`id_nodo`),
  ADD KEY `grafo_id_grafo` (`grafo_id_grafo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `enlace`
--
ALTER TABLE `enlace`
  MODIFY `id_grafo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=221;

--
-- AUTO_INCREMENT de la tabla `grafo`
--
ALTER TABLE `grafo`
  MODIFY `id_grafo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `nodo`
--
ALTER TABLE `nodo`
  MODIFY `id_nodo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=230;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `enlace`
--
ALTER TABLE `enlace`
  ADD CONSTRAINT `enlace_ibfk_1` FOREIGN KEY (`nodo_inicio`) REFERENCES `nodo` (`id_nodo`),
  ADD CONSTRAINT `enlace_ibfk_2` FOREIGN KEY (`nodo_fin`) REFERENCES `nodo` (`id_nodo`),
  ADD CONSTRAINT `enlace_ibfk_3` FOREIGN KEY (`grafo_id`) REFERENCES `grafo` (`id_grafo`);

--
-- Filtros para la tabla `nodo`
--
ALTER TABLE `nodo`
  ADD CONSTRAINT `nodo_ibfk_1` FOREIGN KEY (`grafo_id_grafo`) REFERENCES `grafo` (`id_grafo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
