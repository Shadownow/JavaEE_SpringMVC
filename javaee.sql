
CREATE TABLE `school`.`s_homework` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `homework_title` VARCHAR(20) NULL,
  `homework_content` TEXT NULL,
  `add_time` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));


DROP TABLE if exists `school`.`s_student`;
CREATE TABLE `school`.`s_student` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_name` VARCHAR(20) NOT NULL,
  `student_id` BIGINT NOT NULL,
  `add_time` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));


DROP TABLE if exists `school`.`s_student_homework`;
CREATE TABLE `school`.`s_student_homework` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT NOT NULL,
  `homework_id` BIGINT NOT NULL,
  `answer` VARCHAR(45) NOT NULL,
  `answer_time` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));
