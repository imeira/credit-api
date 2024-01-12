

CREATE TABLE `Person` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
  `indicator` varchar(255) NOT NULL
  `indicatorType` varchar(255) NOT NULL
  `valueMin` double NULL
  `valueMax` double(255) NULL
);

CREATE TABLE `credit` (
  `id` bigint(20) NOT NULL,
  `status` varchar(255) NOT NULL,
  `Person_id` bigint(20) NOT NULL
);

--
-- Indexes for table `Person`
--
ALTER TABLE `Person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `credit`
--
ALTER TABLE `credit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `Person`
--
ALTER TABLE `Person`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `credit`
--
ALTER TABLE `credit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


--
-- Constraints for table `credit`
--
ALTER TABLE `credit`
  ADD CONSTRAINT `Person_fk` FOREIGN KEY (`Person_id`) REFERENCES `Person` (`id`);
