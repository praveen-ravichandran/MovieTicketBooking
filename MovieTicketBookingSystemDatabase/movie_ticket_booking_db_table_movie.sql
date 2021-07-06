
-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `movie_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `certification` varchar(10) NOT NULL,
  `poster_url` varchar(256) NOT NULL,
  `trailer_url` varchar(256) NOT NULL,
  `run_time_mins` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
