
-- --------------------------------------------------------

--
-- Table structure for table `theatre`
--

DROP TABLE IF EXISTS `theatre`;
CREATE TABLE `theatre` (
  `theatre_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `address_line_1` varchar(256) NOT NULL,
  `address_line_2` varchar(256) NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `city_id` int(11) NOT NULL,
  `zipcode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
