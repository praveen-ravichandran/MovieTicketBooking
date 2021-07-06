
-- --------------------------------------------------------

--
-- Table structure for table `city_location`
--

DROP TABLE IF EXISTS `city_location`;
CREATE TABLE `city_location` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
