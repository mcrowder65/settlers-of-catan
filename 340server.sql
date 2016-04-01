

CREATE TABLE `commands` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `data` varchar(255) DEFAULT '',
  `game_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `commands to game` (`game_id`),
  CONSTRAINT `commands to game` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `game membership` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `game_id` int(11) unsigned NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `playerIndex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `join to game` (`game_id`),
  KEY `join to user` (`user_id`),
  CONSTRAINT `join to game` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `join to user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `games` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `data` longtext,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(255) NOT NULL DEFAULT '',
  `pass` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
