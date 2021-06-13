-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2021 a las 18:49:21
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
-- Estructura de tabla para la tabla `arboles`
--

CREATE TABLE `arboles` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `data_arbol` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `arboles`
--

INSERT INTO `arboles` (`id`, `nombre`, `data_arbol`) VALUES
(1, 'PRUEBA 1', '8,9,6,9,8'),
(2, 'prueba 2', '19,9,14,7,17,13,4,5'),
(3, 'toyo pardo', '8,4,7,21,90,1'),
(4, 'frigo', '19,9,14,7,17,13,4,5'),
(5, 'prueba 222', '19,9,14,7,17,13,4,5,2,1,14,10,9'),
(6, 'random 1', '72,30,58,91,40,95,55,79,62,30,72,40,13,52,45,23,18,51,90,81,9,47,56,73,52,53,94,10,82,8,71,56,100,19,12,67,38,7,58,24,98,37,33,6,88,45,29,77,63,34');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compet`
--

CREATE TABLE `compet` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `compet_text` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `compet`
--

INSERT INTO `compet` (`id`, `nombre`, `compet_text`) VALUES
(1, 'prueba1', 'a,3,2;b,6,3;c,3,6;d,6,6'),
(2, 'prueba2', 'a,210,140;b,420,210;c,210,420;d,420,420'),
(3, 'prueba3', 'a,210,140;b,420,210;c,210,420;d,60,60'),
(4, 'ver3', 'a,210,140;b,420,210;c,210,420;d,420,420'),
(5, 'ver4', 'a,60,60;b,60,60;c,60,60;d,60,60'),
(6, 'VER5', '0,0,0;0,0,0;0,0,0;0,0,0'),
(7, 'VER6', ''),
(8, 'VER8', ''),
(9, 'ver10', 'a,3,2;b,6,3;c,3,6;d,6,6'),
(10, 'ver11', 'a,6,1;b,3,2;c,9,2;d,4,4;e,4,8;f,5,12'),
(11, NULL, ''),
(12, 'HOLA', 'A,3,2;B,6,3;C,3,6;D,6,6');

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
(140, 320, 221, 722, 315, 3, 1, 108, 110, 0, 0, 255, 0, 27, -20, 4, 6),
(152, 213, 191, 480, 200, 2, 1, 122, 123, 0, 255, 200, 0, 30, -20, 0, 1),
(153, 480, 200, 737, 159, 3, 1, 123, 124, 0, 255, 200, 0, 30, -20, 1, 2),
(154, 211, 56, 463, 159, 4, 1, 125, 126, 0, 0, 255, 0, 31, -20, 0, 1),
(155, 463, 159, 226, 216, 3, 1, 126, 127, 0, 0, 255, 0, 31, -20, 1, 2),
(156, 226, 216, 390, 308, 6, 1, 127, 128, 0, 0, 255, 0, 31, -20, 2, 3),
(157, 390, 308, 617, 274, 7, 1, 128, 129, 0, 0, 255, 0, 31, -20, 3, 4),
(158, 617, 274, 593, 394, 2, 1, 129, 130, 0, 0, 255, 0, 31, -20, 4, 5);

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
(27, 'prueba johnson 1'),
(30, 'Johnson'),
(31, 'examen 1');

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
(111, 474, 382, 'h', 27, 9, 11, 48, 7),
(122, 213, 191, 'A', 30, 0, 255, 0, 0),
(123, 480, 200, 'B', 30, 0, 255, 0, 1),
(124, 737, 159, 'C', 30, 0, 255, 0, 2),
(125, 211, 56, 'a', 31, 9, 11, 48, 0),
(126, 463, 159, 'b', 31, 9, 11, 48, 1),
(127, 226, 216, 'c', 31, 9, 11, 48, 2),
(128, 390, 308, 'd', 31, 9, 11, 48, 3),
(129, 617, 274, 'e', 31, 9, 11, 48, 4),
(130, 593, 394, 'f', 31, 9, 11, 48, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sorts`
--

CREATE TABLE `sorts` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `sortText` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sorts`
--

INSERT INTO `sorts` (`id`, `nombre`, `sortText`) VALUES
(1, 'prueba 1', '88,14,96,2,30,95,6,40,9,8,90,71,83,59,56,75,26,42,5,5,90,18,89,41,75,50,16,77,7,69,22,18,18,52,33,98,24,59,72,8,77,83,44,2,66,100,52,74,95,54,4,100,60,65,41,3,32,55,90,74,31,88,85,46,76,10,9,43,98,88,9,48,52,62,13,70,65,5,24,71,19,30,92,100,26,98,98,58,19,68,24,91,35,83,94,14,21,27,98,37,'),
(2, 'prueba 2', '3,4,4,4,3,4,4,4,4,'),
(3, 'prueba 3', '127,56,114,109,198,136,72,223,15,289,237,101,143,42,92,126,257,119,34,32,245,286,90,25,44,71,156,172,16,196,41,83,114,164,68,244,119,190,106,104,149,87,244,209,46,222,7,192,204,55,295,223,30,228,162,129,288,70,188,248,142,62,91,92,80,227,117,145,80,98,153,288,210,266,44,286,244,122,209,163,74,241,7,68,270,239,228,14,50,51,'),
(4, 'prueba4', '60,23,107,104,62,383,259,325,98,80,288,169,343,141,186,226,21,208,100,151,90,219,219,194,333,267,297,5,345,133,102,94,62,133,136,213,16,108,399,66,176,313,382,246,233,395,125,99,289,396,276,84,349,178,335,259,119,255,127,212,272,262,315,223,121,332,229,383,36,332,205,73,233,154,19,356,358,355,82,395,132,357,296,300,73,273,377,253,95,13,292,280,348,213,230,57,126,299,'),
(5, 'random 1', '484,112,545,539,946,728,693,196,267,621,525,174,624,79,294,244,829,385,500,520,952,644,563,758,968,520,946,594,161,94,436,306,422,601,701,974,259,7,469,190,272,998,562,738,94,283,869,153,319,684,793,263,728,963,57,415,201,953,857,407,66,29,213,680,297,752,983,825,855,975,575,999,708,545,413,404,526,288,421,605,838,921,421,89,463,40,964,107,362,389,478,601,679,280,666,666,626,19,119,809,'),
(6, 'isocret', '743,173,782,391,837,351,648,736,938,504,261,38,429,539,152,897,627,85,472,116,144,531,658,944,383,799,807,253,621,111,403,578,413,745,172,104,412,39,145,309,94,400,939,182,712,214,360,191,262,80,135,405,696,173,680,13,119,689,160,718,727,665,817,1000,722,150,280,451,234,289,477,339,453,957,693,295,102,292,987,871,892,942,715,78,811,150,686,34,74,32,611,677,738,474,182,688,941,538,978,786,220,329,113,491,595,558,803,337,417,64,76,969,555,155,478,791,122,195,689,377,133,559,496,722,849,24,111,91,758,698,623,219,46,895,367,44,712,545,667,105,134,427,414,543,660,221,221,107,815,962,734,346,937,875,967,316,852,237,836,926,554,737,497,635,80,887,80,331,213,789,568,86,579,360,563,955,938,83,407,99,248,313,169,254,130,581,458,260,15,550,785,121,936,70,580,62,575,227,468,481,545,476,729,587,476,582,13,436,584,677,527,336,680,754,130,762,754,681,308,452,169,227,375,866,531,620,520,151,110,973,304,353,325,117,608,105,646,526,621,791,642,816,692,44,405,276,339,483,61,277,711,870,137,897,103,119,977,344,854,36,240,765,261,741,635,847,435,898,46,506,366,486,684,260,499,507,10,286,104,772,381,440,886,813,309,430,869,148,253,893,513,346,474,382,574,855,651,990,11,919,');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arboles`
--
ALTER TABLE `arboles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `compet`
--
ALTER TABLE `compet`
  ADD PRIMARY KEY (`id`);

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
-- Indices de la tabla `sorts`
--
ALTER TABLE `sorts`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arboles`
--
ALTER TABLE `arboles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `compet`
--
ALTER TABLE `compet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `enlace`
--
ALTER TABLE `enlace`
  MODIFY `id_grafo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=196;

--
-- AUTO_INCREMENT de la tabla `grafo`
--
ALTER TABLE `grafo`
  MODIFY `id_grafo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `nodo`
--
ALTER TABLE `nodo`
  MODIFY `id_nodo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- AUTO_INCREMENT de la tabla `sorts`
--
ALTER TABLE `sorts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
