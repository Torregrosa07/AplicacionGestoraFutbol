-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-04-2025 a las 20:27:27
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `kickoff`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id_equipo` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `anio_fundacion` varchar(10) DEFAULT NULL,
  `localidad` varchar(100) DEFAULT NULL,
  `entrenador` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id_equipo`, `nombre`, `anio_fundacion`, `localidad`, `entrenador`) VALUES
(2, 'Prueba FC', '2000', 'Villanueva de La Jara', 'Santiago Torregrosa'),
(3, 'Real Prueba CF', '1920', 'Ciudad de Prueba', 'Juan Pruebas'),
(6, 'Torregrosa FC', '4000', 'Villanueva de La Jara', 'Luis Enrique'),
(10, 'Real Madrid', NULL, 'Madrid', 'Carlo Ancelotti'),
(11, 'Atlético de Madrid', NULL, 'Madrid', 'Diego Simeone'),
(14, 'Team wasaaaaaaaaaaaaaaaa', '2000', 'aqui', 'Don pollo'),
(15, 'Aaaaa', '2022', 'aaaaa', 'aaaaa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadisticas`
--

CREATE TABLE `estadisticas` (
  `id_estadistica` int(11) NOT NULL,
  `id_partido` int(11) DEFAULT NULL,
  `id_jugador` int(11) DEFAULT NULL,
  `goles` int(11) DEFAULT NULL,
  `asistencias` int(11) DEFAULT NULL,
  `tarjetas_amarillas` int(11) DEFAULT NULL,
  `tarjetas_rojas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `id_jugador` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `posicion` enum('PORTERO','DEFENSA','MEDIOCENTRO','DELANTERO') DEFAULT NULL,
  `dorsal` varchar(10) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `nacionalidad` varchar(50) DEFAULT NULL,
  `sexo` enum('FEMENINO','MASCULINO','NO_DEFINIDO') DEFAULT NULL,
  `id_equipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`id_jugador`, `nombre`, `apellidos`, `posicion`, `dorsal`, `edad`, `nacionalidad`, `sexo`, `id_equipo`) VALUES
(1, 'Santiago Andres', 'Torregrosa Alvarado', 'DELANTERO', '100', 17, 'Colombiano', 'MASCULINO', 2),
(32, 'Thomaaaaaas', 'Quiroga Martinez', 'DEFENSA', '19', 18, NULL, 'MASCULINO', 14),
(35, 'waaaaaasaaa', 'aaaa', 'DEFENSA', '22', 11, NULL, 'NO_DEFINIDO', 3),
(36, 'Rapha', 'kaka', 'DEFENSA', '11', 22, NULL, 'MASCULINO', 6),
(38, 'pruba', 'jugador', 'DELANTERO', '99', 99, NULL, 'MASCULINO', 10),
(45, 'awsadasd', 'dfssdgs', 'DEFENSA', '19', 18, NULL, 'MASCULINO', 14),
(46, 'sdgsegsdgsdgr', 'ssdgsdgsdgrr', 'MEDIOCENTRO', '11', 22, NULL, 'MASCULINO', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `id_partido` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `id_equipo_local` int(11) DEFAULT NULL,
  `id_equipo_visitante` int(11) DEFAULT NULL,
  `goles_local` int(11) DEFAULT NULL,
  `goles_visitante` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`id_partido`, `fecha`, `hora`, `id_equipo_local`, `id_equipo_visitante`, `goles_local`, `goles_visitante`) VALUES
(3, '2025-04-08', '13:00:00', 3, NULL, NULL, NULL),
(6, '2025-04-18', '15:00:00', 6, 10, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `torneo`
--

CREATE TABLE `torneo` (
  `id_torneo` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `tipo` enum('LIGA','ELIMINACION_DIRECTA','GRUPOS_Y_CLASIFICACION') DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id_equipo`);

--
-- Indices de la tabla `estadisticas`
--
ALTER TABLE `estadisticas`
  ADD PRIMARY KEY (`id_estadistica`),
  ADD KEY `id_partido` (`id_partido`),
  ADD KEY `id_jugador` (`id_jugador`);

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`id_jugador`),
  ADD UNIQUE KEY `nombre_apellidos_unique` (`nombre`,`apellidos`),
  ADD KEY `id_equipo` (`id_equipo`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`id_partido`),
  ADD UNIQUE KEY `unique_match` (`fecha`,`hora`,`id_equipo_local`,`id_equipo_visitante`),
  ADD KEY `id_equipo_local` (`id_equipo_local`),
  ADD KEY `partido_ibfk_2` (`id_equipo_visitante`);

--
-- Indices de la tabla `torneo`
--
ALTER TABLE `torneo`
  ADD PRIMARY KEY (`id_torneo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id_equipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `estadisticas`
--
ALTER TABLE `estadisticas`
  MODIFY `id_estadistica` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `jugador`
--
ALTER TABLE `jugador`
  MODIFY `id_jugador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `id_partido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `torneo`
--
ALTER TABLE `torneo`
  MODIFY `id_torneo` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `estadisticas`
--
ALTER TABLE `estadisticas`
  ADD CONSTRAINT `estadisticas_ibfk_1` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id_partido`),
  ADD CONSTRAINT `estadisticas_ibfk_2` FOREIGN KEY (`id_jugador`) REFERENCES `jugador` (`id_jugador`);

--
-- Filtros para la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD CONSTRAINT `jugador_ibfk_1` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`);

--
-- Filtros para la tabla `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `partido_ibfk_1` FOREIGN KEY (`id_equipo_local`) REFERENCES `equipo` (`id_equipo`),
  ADD CONSTRAINT `partido_ibfk_2` FOREIGN KEY (`id_equipo_visitante`) REFERENCES `equipo` (`id_equipo`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
