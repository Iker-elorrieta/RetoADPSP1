-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-01-2021 a las 11:12:35
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `retofinal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos`
--

CREATE TABLE `datos` (
  `CodEst` int(6) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` date NOT NULL,
  `COmgm3` double DEFAULT NULL,
  `CO8hmgm3` double DEFAULT NULL,
  `NOgm3` double DEFAULT NULL,
  `NO2` double DEFAULT NULL,
  `NO2ICA` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `NOXgm3` double DEFAULT NULL,
  `PM10` double DEFAULT NULL,
  `PM10ICA` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `PM25` double DEFAULT NULL,
  `PM25ICA` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `SO2` double DEFAULT NULL,
  `SO2ICA` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `ICAEstacion` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacios`
--

CREATE TABLE `espacios` (
  `CodEspacio` int(6) NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_unicode_520_ci NOT NULL,
  `Descripcion` text COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `Tipo` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estaciones`
--

CREATE TABLE `estaciones` (
  `CodEst` int(4) NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `Provincia` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `Municipio` varchar(50) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `Direccion` varchar(100) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `Latitud` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `Longitud` varchar(30) COLLATE utf8_unicode_520_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashes`
--

CREATE TABLE `hashes` (
  `CodHash` int(6) NOT NULL,
  `Url` varchar(500) COLLATE utf8_unicode_520_ci NOT NULL,
  `Hash` varchar(1000) COLLATE utf8_unicode_520_ci NOT NULL,
  `NombreJSON` varchar(50) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipios`
--

CREATE TABLE `municipios` (
  `CodMuniAuto` int(6) NOT NULL,
  `CodMuni` int(6) NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_unicode_520_ci NOT NULL,
  `Descripcion` text COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `CodProv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincias`
--

CREATE TABLE `provincias` (
  `CodProv` int(4) NOT NULL,
  `Nombre` varchar(100) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicaciones`
--

CREATE TABLE `ubicaciones` (
  `CodEspacio` int(11) NOT NULL,
  `CodMuni` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `CodUsu` int(4) NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_unicode_520_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `datos`
--
ALTER TABLE `datos`
  ADD PRIMARY KEY (`Fecha`,`Hora`,`CodEst`) USING BTREE,
  ADD KEY `CodEst` (`CodEst`);

--
-- Indices de la tabla `espacios`
--
ALTER TABLE `espacios`
  ADD PRIMARY KEY (`CodEspacio`);

--
-- Indices de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  ADD PRIMARY KEY (`CodEst`);

--
-- Indices de la tabla `hashes`
--
ALTER TABLE `hashes`
  ADD PRIMARY KEY (`CodHash`);

--
-- Indices de la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD PRIMARY KEY (`CodMuniAuto`),
  ADD KEY `CodMuni` (`CodMuni`),
  ADD KEY `CodProv` (`CodProv`);

--
-- Indices de la tabla `provincias`
--
ALTER TABLE `provincias`
  ADD PRIMARY KEY (`CodProv`);

--
-- Indices de la tabla `ubicaciones`
--
ALTER TABLE `ubicaciones`
  ADD KEY `CodEspacio` (`CodEspacio`),
  ADD KEY `CodMuni` (`CodMuni`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`CodUsu`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `municipios`
--
ALTER TABLE `municipios`
  MODIFY `CodMuniAuto` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=252;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `CodUsu` int(4) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `datos`
--
ALTER TABLE `datos`
  ADD CONSTRAINT `datos_ibfk_1` FOREIGN KEY (`CodEst`) REFERENCES `estaciones` (`CodEst`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD CONSTRAINT `municipios_ibfk_2` FOREIGN KEY (`CodProv`) REFERENCES `provincias` (`CodProv`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `ubicaciones`
--
ALTER TABLE `ubicaciones`
  ADD CONSTRAINT `ubicaciones_ibfk_1` FOREIGN KEY (`CodEspacio`) REFERENCES `espacios` (`CodEspacio`) ON UPDATE CASCADE,
  ADD CONSTRAINT `ubicaciones_ibfk_2` FOREIGN KEY (`CodMuni`) REFERENCES `municipios` (`CodMuni`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;