
-- --------------------------------------------------------

--
-- Table structure for table `theatre_hall_seat`
--

DROP TABLE IF EXISTS `theatre_hall_seat`;
CREATE TABLE `theatre_hall_seat` (
  `seat_id` int(11) NOT NULL,
  `row_num` int(11) NOT NULL,
  `col_num` int(11) NOT NULL,
  `seat_row_code` varchar(25) NOT NULL,
  `seat_col_code` varchar(25) NOT NULL,
  `hall_class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
