
-- --------------------------------------------------------

--
-- Table structure for table `ticket_status`
--

DROP TABLE IF EXISTS `ticket_status`;
CREATE TABLE `ticket_status` (
  `status_id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `description` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
