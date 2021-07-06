
--
-- Indexes for dumped tables
--

--
-- Indexes for table `city_location`
--
ALTER TABLE `city_location`
  ADD PRIMARY KEY (`city_id`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movie_id`);

--
-- Indexes for table `movie_show`
--
ALTER TABLE `movie_show`
  ADD PRIMARY KEY (`show_id`),
  ADD KEY `fk_movie_show_movie_id_movie_movie_id` (`movie_id`),
  ADD KEY `fk_movie_show_theatre_hall_id_theatre_hall_hall_id` (`theatre_hall_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `fk_payment_ticket_booking_id_ticket_booking_booking_id` (`ticket_booking_id`);

--
-- Indexes for table `theatre`
--
ALTER TABLE `theatre`
  ADD PRIMARY KEY (`theatre_id`),
  ADD KEY `fk_theatre_city_id_city_location_city_id` (`city_id`);

--
-- Indexes for table `theatre_hall`
--
ALTER TABLE `theatre_hall`
  ADD PRIMARY KEY (`hall_id`),
  ADD KEY `fk_theatre_hall_hall_type_id_theatre_hall_type_hall_type_id` (`hall_type_id`),
  ADD KEY `fk_theatre_hall_theatre_id_theatre_theatre_id` (`theatre_id`);

--
-- Indexes for table `theatre_hall_class`
--
ALTER TABLE `theatre_hall_class`
  ADD PRIMARY KEY (`class_id`),
  ADD KEY `fk_theatre_hall_class_theatre_hall_id_theatre_hall_hall_id` (`theatre_hall_id`);

--
-- Indexes for table `theatre_hall_path`
--
ALTER TABLE `theatre_hall_path`
  ADD PRIMARY KEY (`hall_path_id`),
  ADD KEY `fk_theatre_hall_path_theatre_hall_id_theatre_hall_hall_id` (`theatre_hall_id`);

--
-- Indexes for table `theatre_hall_seat`
--
ALTER TABLE `theatre_hall_seat`
  ADD PRIMARY KEY (`seat_id`),
  ADD KEY `fk_theatre_hall_seat_hall_class_id_theatre_hall_class_class_id` (`hall_class_id`);

--
-- Indexes for table `theatre_hall_type`
--
ALTER TABLE `theatre_hall_type`
  ADD PRIMARY KEY (`hall_type_id`);

--
-- Indexes for table `ticket_booking`
--
ALTER TABLE `ticket_booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `fk_ticket_booking_movie_show_id_movie_show_show_id` (`movie_show_id`),
  ADD KEY `fk_ticket_booking_ticket_status_id_ticket_status_status_id` (`ticket_status_id`);

--
-- Indexes for table `ticket_booking_seat`
--
ALTER TABLE `ticket_booking_seat`
  ADD PRIMARY KEY (`booking_seat_id`),
  ADD KEY `fk_ticket_booking_seat_booking_id_ticket_booking_booking_id` (`booking_id`),
  ADD KEY `fk_ticket_booking_seat_seat_id_theatre_hall_seat_seat_id` (`seat_id`);

--
-- Indexes for table `ticket_status`
--
ALTER TABLE `ticket_status`
  ADD PRIMARY KEY (`status_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `city_location`
--
ALTER TABLE `city_location`
  MODIFY `city_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `movie_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `movie_show`
--
ALTER TABLE `movie_show`
  MODIFY `show_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `theatre`
--
ALTER TABLE `theatre`
  MODIFY `theatre_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `theatre_hall`
--
ALTER TABLE `theatre_hall`
  MODIFY `hall_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `theatre_hall_class`
--
ALTER TABLE `theatre_hall_class`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `theatre_hall_path`
--
ALTER TABLE `theatre_hall_path`
  MODIFY `hall_path_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `theatre_hall_seat`
--
ALTER TABLE `theatre_hall_seat`
  MODIFY `seat_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `theatre_hall_type`
--
ALTER TABLE `theatre_hall_type`
  MODIFY `hall_type_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ticket_booking`
--
ALTER TABLE `ticket_booking`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ticket_booking_seat`
--
ALTER TABLE `ticket_booking_seat`
  MODIFY `booking_seat_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ticket_status`
--
ALTER TABLE `ticket_status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `movie_show`
--
ALTER TABLE `movie_show`
  ADD CONSTRAINT `fk_movie_show_movie_id_movie_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
  ADD CONSTRAINT `fk_movie_show_theatre_hall_id_theatre_hall_hall_id` FOREIGN KEY (`theatre_hall_id`) REFERENCES `theatre_hall` (`hall_id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_payment_ticket_booking_id_ticket_booking_booking_id` FOREIGN KEY (`ticket_booking_id`) REFERENCES `ticket_booking` (`booking_id`);

--
-- Constraints for table `theatre`
--
ALTER TABLE `theatre`
  ADD CONSTRAINT `fk_theatre_city_id_city_location_city_id` FOREIGN KEY (`city_id`) REFERENCES `city_location` (`city_id`);

--
-- Constraints for table `theatre_hall`
--
ALTER TABLE `theatre_hall`
  ADD CONSTRAINT `fk_theatre_hall_hall_type_id_theatre_hall_type_hall_type_id` FOREIGN KEY (`hall_type_id`) REFERENCES `theatre_hall_type` (`hall_type_id`),
  ADD CONSTRAINT `fk_theatre_hall_theatre_id_theatre_theatre_id` FOREIGN KEY (`theatre_id`) REFERENCES `theatre` (`theatre_id`);

--
-- Constraints for table `theatre_hall_class`
--
ALTER TABLE `theatre_hall_class`
  ADD CONSTRAINT `fk_theatre_hall_class_theatre_hall_id_theatre_hall_hall_id` FOREIGN KEY (`theatre_hall_id`) REFERENCES `theatre_hall` (`hall_id`);

--
-- Constraints for table `theatre_hall_path`
--
ALTER TABLE `theatre_hall_path`
  ADD CONSTRAINT `fk_theatre_hall_path_theatre_hall_id_theatre_hall_hall_id` FOREIGN KEY (`theatre_hall_id`) REFERENCES `theatre_hall` (`hall_id`);

--
-- Constraints for table `theatre_hall_seat`
--
ALTER TABLE `theatre_hall_seat`
  ADD CONSTRAINT `fk_theatre_hall_seat_hall_class_id_theatre_hall_class_class_id` FOREIGN KEY (`hall_class_id`) REFERENCES `theatre_hall_class` (`class_id`);

--
-- Constraints for table `ticket_booking`
--
ALTER TABLE `ticket_booking`
  ADD CONSTRAINT `fk_ticket_booking_movie_show_id_movie_show_show_id` FOREIGN KEY (`movie_show_id`) REFERENCES `movie_show` (`show_id`),
  ADD CONSTRAINT `fk_ticket_booking_ticket_status_id_ticket_status_status_id` FOREIGN KEY (`ticket_status_id`) REFERENCES `ticket_status` (`status_id`);

--
-- Constraints for table `ticket_booking_seat`
--
ALTER TABLE `ticket_booking_seat`
  ADD CONSTRAINT `fk_ticket_booking_seat_booking_id_ticket_booking_booking_id` FOREIGN KEY (`booking_id`) REFERENCES `ticket_booking` (`booking_id`),
  ADD CONSTRAINT `fk_ticket_booking_seat_seat_id_theatre_hall_seat_seat_id` FOREIGN KEY (`seat_id`) REFERENCES `theatre_hall_seat` (`seat_id`);
