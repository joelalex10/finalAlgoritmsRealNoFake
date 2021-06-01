-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-04-2021 a las 04:09:32
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
-- Base de datos: `grafo`
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
  `text_direction` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `enlace`
--

INSERT INTO `enlace` (`id_grafo`, `x1`, `y1`, `x2`, `y2`, `atributo`, `thickness`, `nodo_inicio`, `nodo_fin`, `ciclo`, `color_red`, `color_green`, `color_blue`, `grafo_id`, `text_direction`) VALUES
(12, 669, 175, 421, 101, 2, 1, 11, 10, 0, 0, 255, 0, 4, -20),
(13, 421, 101, 669, 175, 3, 1, 10, 11, 0, 0, 255, 255, 4, 20),
(14, 421, 101, 103, 81, 15, 1, 10, 9, 0, 0, 255, 0, 4, -20),
(15, 103, 81, 421, 101, 11, 1, 9, 10, 0, 0, 255, 255, 4, 20),
(16, 421, 101, 421, 101, 24, 1, 10, 10, 1, 255, 200, 0, 4, 20),
(17, 103, 81, 103, 81, 12, 1, 9, 9, 1, 255, 200, 0, 4, 20),
(18, 669, 175, 669, 175, 3, 1, 11, 11, 1, 255, 200, 0, 4, 20),
(19, 711, 396, 608, 181, 2, 1, 14, 13, 0, 0, 255, 0, 5, -20),
(20, 608, 181, 711, 396, 3, 1, 13, 14, 0, 0, 255, 255, 5, 20),
(21, 608, 181, 130, 73, 15, 1, 13, 12, 0, 0, 255, 0, 5, -20),
(22, 130, 73, 608, 181, 11, 1, 12, 13, 0, 0, 255, 255, 5, 20),
(23, 608, 181, 421, 101, 24, 1, 13, 13, 1, 255, 200, 0, 5, 20),
(24, 130, 73, 103, 81, 12, 1, 12, 12, 1, 255, 200, 0, 5, 20),
(25, 711, 396, 608, 181, 3, 1, 14, 14, 1, 255, 200, 0, 5, 20),
(26, 277, 218, 608, 181, 3, 1, 15, 13, 0, 0, 255, 0, 5, -20),
(27, 608, 181, 277, 218, 20, 1, 13, 15, 0, 0, 255, 255, 5, 20),
(28, 277, 218, 277, 218, 1, 1, 15, 15, 1, 255, 200, 0, 5, 20),
(29, 277, 218, 130, 73, 16, 1, 15, 12, 0, 0, 255, 0, 5, -20),
(30, 130, 73, 277, 218, 64, 1, 12, 15, 0, 0, 255, 255, 5, 20),
(31, 130, 73, 103, 81, 12, 1, 16, 16, 1, 255, 200, 0, 6, 20),
(32, 351, 302, 648, 186, 3, 1, 18, 17, 0, 0, 255, 0, 6, -20),
(33, 648, 186, 130, 73, 1, 1, 17, 16, 0, 0, 255, 0, 6, -20),
(34, 130, 73, 351, 302, 2, 1, 16, 18, 0, 0, 255, 0, 6, -20),
(35, 648, 186, 648, 186, 1, 1, 17, 17, 1, 255, 200, 0, 6, 20),
(36, 130, 73, 103, 81, 12, 1, 19, 19, 1, 255, 200, 0, 7, 20),
(37, 322, 281, 648, 186, 12, 1, 21, 20, 0, 0, 255, 0, 7, -20),
(38, 648, 186, 130, 73, 1, 1, 20, 19, 0, 0, 255, 0, 7, -20),
(39, 130, 73, 322, 281, 2, 1, 19, 21, 0, 0, 255, 0, 7, -20),
(40, 648, 186, 648, 186, 1, 1, 20, 20, 1, 255, 200, 0, 7, 20),
(41, 705, 228, 442, 116, 2, 1, 24, 23, 0, 0, 255, 0, 8, -20),
(42, 442, 116, 705, 228, 3, 1, 23, 24, 0, 0, 255, 255, 8, 20),
(43, 442, 116, 495, 128, 3, 1, 23, 23, 1, 255, 200, 0, 8, 20),
(44, 442, 116, 215, 240, 2, 1, 23, 22, 0, 0, 255, 0, 8, -20),
(50, 503, 369, 757, 426, 3, 1, 31, 30, 0, 0, 255, 0, 9, -20),
(51, 757, 426, 503, 369, 2, 1, 30, 31, 0, 0, 255, 255, 9, 20),
(52, 503, 369, 215, 240, 5, 1, 31, 29, 0, 0, 255, 0, 9, -20),
(53, 475, 58, 299, 164, 20, 1, 33, 32, 0, 0, 255, 0, 10, -20),
(54, 299, 164, 475, 58, 25, 1, 32, 33, 0, 0, 255, 255, 10, 20),
(55, 299, 164, 53, 72, 102, 1, 32, 34, 0, 0, 255, 0, 10, -20),
(56, 53, 72, 299, 164, 204, 1, 34, 32, 0, 0, 255, 255, 10, 20),
(57, 475, 58, 751, 120, 10, 1, 33, 35, 0, 0, 255, 0, 10, -20),
(58, 751, 120, 475, 58, 6, 1, 35, 33, 0, 0, 255, 255, 10, 20),
(59, 669, 319, 475, 58, 29, 1, 36, 33, 0, 0, 255, 0, 10, -20),
(60, 475, 58, 669, 319, 13, 1, 33, 36, 0, 0, 255, 255, 10, 20),
(61, 669, 319, 299, 164, 10, 1, 36, 32, 0, 0, 255, 0, 10, -20),
(62, 299, 164, 669, 319, 54, 1, 32, 36, 0, 0, 255, 255, 10, 20),
(63, 481, 124, 748, 212, 3, 1, 38, 39, 0, 0, 255, 0, 11, -20),
(64, 748, 212, 481, 124, 12, 1, 39, 38, 0, 0, 255, 255, 11, 20),
(65, 481, 124, 220, 214, 3, 1, 38, 37, 0, 0, 255, 0, 11, -20),
(66, 481, 124, 481, 124, 5, 1, 38, 38, 1, 255, 200, 0, 11, 20),
(67, 484, 327, 680, 169, 2, 1, 42, 41, 0, 0, 255, 0, 12, -20),
(68, 680, 169, 215, 194, 3, 1, 41, 40, 0, 0, 255, 0, 12, -20),
(69, 215, 194, 680, 169, 4, 1, 40, 41, 0, 0, 255, 255, 12, 20),
(70, 215, 194, 484, 327, 5, 1, 40, 42, 0, 0, 255, 0, 12, -20),
(71, 484, 327, 484, 327, 6, 1, 42, 42, 1, 255, 200, 0, 12, 20),
(72, 357, 260, 439, 117, 2, 1, 44, 45, 0, 0, 255, 0, 13, -20),
(73, 439, 117, 357, 260, 3, 1, 45, 44, 0, 0, 255, 255, 13, 20);

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
(4, 'prueba Bolivia'),
(5, 'prueba 2 Bolivia'),
(6, 'Bolivia 4'),
(7, 'Prueba 5'),
(8, 'Interciudades'),
(9, 'Interdepartamental'),
(10, 'Sudamerica 1'),
(11, 'ABC'),
(12, 'Letras ABC'),
(13, 'Repeticion');

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
  `color_blue` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `nodo`
--

INSERT INTO `nodo` (`id_nodo`, `x`, `y`, `nombre`, `grafo_id_grafo`, `color_red`, `color_green`, `color_blue`) VALUES
(9, 103, 81, 'LA PAZ', 4, 9, 11, 48),
(10, 421, 101, 'SANTA CRUZ', 4, 9, 11, 48),
(11, 669, 175, 'ORURO', 4, 9, 11, 48),
(12, 130, 73, 'LA PAZ', 5, 9, 11, 48),
(13, 608, 181, 'SANTA CRUZ', 5, 9, 11, 48),
(14, 711, 396, 'ORURO', 5, 9, 11, 48),
(15, 277, 218, 'SUCRE', 5, 9, 11, 48),
(16, 130, 73, 'LA PAZ', 6, 9, 11, 48),
(17, 648, 186, 'PANDO', 6, 9, 11, 48),
(18, 351, 302, 'COCHABAMBA', 6, 9, 11, 48),
(19, 130, 73, 'LA PAZ', 7, 9, 11, 48),
(20, 648, 186, 'PANDO', 7, 9, 11, 48),
(21, 322, 281, 'COCHABAMBA', 7, 9, 11, 48),
(22, 215, 240, 'LA PAZ', 8, 9, 11, 48),
(23, 442, 116, 'MONTERO', 8, 9, 11, 48),
(24, 705, 228, 'COCHABAMBA', 8, 9, 11, 48),
(29, 215, 240, 'LA PAZ', 9, 9, 11, 48),
(30, 757, 426, 'COCHABAMBA', 9, 9, 11, 48),
(31, 503, 369, 'ORURO', 9, 9, 11, 48),
(32, 299, 164, 'ARGENTINA', 10, 9, 11, 48),
(33, 475, 58, 'BOLIVIA', 10, 9, 11, 48),
(34, 53, 72, 'BRASIL', 10, 9, 11, 48),
(35, 751, 120, 'ECUADOR', 10, 9, 11, 48),
(36, 669, 319, 'CHILE', 10, 9, 11, 48),
(37, 220, 214, 'ORURO', 11, 9, 11, 48),
(38, 481, 124, 'SANTA CRUZ', 11, 9, 11, 48),
(39, 748, 212, 'COCHABAMBA', 11, 9, 11, 48),
(40, 215, 194, 'A', 12, 9, 11, 48),
(41, 680, 169, 'Y', 12, 9, 11, 48),
(42, 484, 327, 'C', 12, 9, 11, 48),
(43, 70, 378, 'B', 12, 9, 11, 48),
(44, 357, 260, 'T', 13, 9, 11, 48),
(45, 439, 117, 'P', 13, 9, 11, 48);

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
  MODIFY `id_grafo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT de la tabla `grafo`
--
ALTER TABLE `grafo`
  MODIFY `id_grafo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `nodo`
--
ALTER TABLE `nodo`
  MODIFY `id_nodo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

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
