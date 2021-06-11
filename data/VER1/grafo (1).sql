-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2021 a las 03:42:48
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
  `text_direction` int(11) DEFAULT NULL,
  `nroActividadNodoInicio` int(11) NOT NULL,
  `nroActividadNodoFin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `enlace`
--

INSERT INTO `enlace` (`id_grafo`, `x1`, `y1`, `x2`, `y2`, `atributo`, `thickness`, `nodo_inicio`, `nodo_fin`, `ciclo`, `color_red`, `color_green`, `color_blue`, `grafo_id`, `text_direction`, `nroActividadNodoInicio`, `nroActividadNodoFin`) VALUES
(132, 41, 59, 290, 55, 6, 1, 104, 105, 0, 0, 255, 0, 27, -20, 0, 1),
(133, 41, 59, 187, 159, 7, 1, 104, 106, 0, 0, 255, 0, 27, -20, 0, 2),
(134, 290, 55, 529, 76, 1, 1, 105, 107, 0, 0, 255, 0, 27, -20, 1, 3),
(135, 187, 159, 529, 76, 8, 1, 106, 107, 0, 0, 255, 0, 27, -20, 2, 3),
(136, 529, 76, 320, 221, 6, 1, 107, 108, 0, 0, 255, 0, 27, -20, 3, 4),
(137, 529, 76, 698, 174, 9, 1, 107, 109, 0, 0, 255, 0, 27, -20, 3, 5),
(138, 698, 174, 722, 315, 2, 1, 109, 110, 0, 0, 255, 0, 27, -20, 5, 6),
(139, 722, 315, 474, 382, 5, 1, 110, 111, 0, 0, 255, 0, 27, -20, 6, 7),
(140, 320, 221, 722, 315, 3, 1, 108, 110, 0, 0, 255, 0, 27, -20, 4, 6);

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
(27, 'prueba johnson 1');

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
  `nro_actividad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `nodo`
--

INSERT INTO `nodo` (`id_nodo`, `x`, `y`, `nombre`, `grafo_id_grafo`, `color_red`, `color_green`, `color_blue`, `nro_actividad`) VALUES
(104, 41, 59, 'a', 27, 9, 11, 48, 0),
(105, 290, 55, 'b', 27, 9, 11, 48, 1),
(106, 187, 159, 'c', 27, 9, 11, 48, 2),
(107, 529, 76, 'd', 27, 9, 11, 48, 3),
(108, 320, 221, 'e', 27, 9, 11, 48, 4),
(109, 698, 174, 'f', 27, 9, 11, 48, 5),
(110, 722, 315, 'g', 27, 9, 11, 48, 6),
(111, 474, 382, 'h', 27, 9, 11, 48, 7);

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
  MODIFY `id_grafo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;

--
-- AUTO_INCREMENT de la tabla `grafo`
--
ALTER TABLE `grafo`
  MODIFY `id_grafo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `nodo`
--
ALTER TABLE `nodo`
  MODIFY `id_nodo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

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
