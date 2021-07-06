
-- --------------------------------------------------------

--
-- Table structure for table `theatre_hall_type`
--

DROP TABLE IF EXISTS `theatre_hall_type`;
CREATE TABLE `theatre_hall_type` (
  `hall_type_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
