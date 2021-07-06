
-- --------------------------------------------------------

--
-- Table structure for table `movie_show`
--

DROP TABLE IF EXISTS `movie_show`;
CREATE TABLE `movie_show` (
  `show_id` int(11) NOT NULL,
  `theatre_hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `show_date` date NOT NULL,
  `show_start_time` time NOT NULL,
  `show_end_time` time NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
