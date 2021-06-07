CREATE TABLE `meetingrooms` (
  `id` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_hungarian_ci,
  `width` int(11) UNSIGNED NOT NULL,
  `length` int(11) UNSIGNED NOT NULL
);