-- users
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,     /* ID */
  `first_name` varchar(255) DEFAULT NULL,        /* 名 */
  `last_name` varchar(255) DEFAULT NULL,         /* 姓 */
  `email` varchar(255) NOT NULL,                 /* メールアドレス */
  `login_id` varchar(255) NOT NULL,              /* ログインID */
  `password` varchar(255) NOT NULL,              /* パスワード */
  `status` tinyint(4) NOT NULL,                  /* ステータス */
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;