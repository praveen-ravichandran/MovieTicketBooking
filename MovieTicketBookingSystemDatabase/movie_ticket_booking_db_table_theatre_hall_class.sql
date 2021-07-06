
-- --------------------------------------------------------

--
-- Table structure for table `theatre_hall_class`
--

DROP TABLE IF EXISTS `theatre_hall_class`;
CREATE TABLE `theatre_hall_class` (
  `class_id` int(11) NOT NULL,
  `theatre_hall_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  `class_type` varchar(50) NOT NULL,
  `class_order` int(11) NOT NULL,
  `base_price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
