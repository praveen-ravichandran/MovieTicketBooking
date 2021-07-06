
-- --------------------------------------------------------

--
-- Table structure for table `theatre_hall_path`
--

DROP TABLE IF EXISTS `theatre_hall_path`;
CREATE TABLE `theatre_hall_path` (
  `hall_path_id` int(11) NOT NULL,
  `theatre_hall_id` int(11) NOT NULL,
  `break_at_col` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
