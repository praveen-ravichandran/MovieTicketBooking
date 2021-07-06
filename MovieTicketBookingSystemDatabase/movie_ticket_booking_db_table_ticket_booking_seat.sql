
-- --------------------------------------------------------

--
-- Table structure for table `ticket_booking_seat`
--

DROP TABLE IF EXISTS `ticket_booking_seat`;
CREATE TABLE `ticket_booking_seat` (
  `booking_seat_id` int(11) NOT NULL,
  `booking_id` int(11) NOT NULL,
  `seat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
