
-- --------------------------------------------------------

--
-- Table structure for table `theatre_hall`
--

DROP TABLE IF EXISTS `theatre_hall`;
CREATE TABLE `theatre_hall` (
  `hall_id` int(11) NOT NULL,
  `theatre_id` int(11) NOT NULL,
  `hall_name` varchar(100) NOT NULL,
  `hall_code` varchar(50) NOT NULL,
  `capacity_rows` int(11) NOT NULL,
  `capacity_cols` int(11) NOT NULL,
  `hall_type_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
