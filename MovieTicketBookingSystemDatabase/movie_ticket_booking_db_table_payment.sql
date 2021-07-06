
-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `ticket_booking_id` int(11) NOT NULL,
  `mode` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `action_time` datetime NOT NULL,
  `amount` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
