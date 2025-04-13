CREATE TABLE `a_order_0` (
                             `id` bigint NOT NULL,
                             `count` int DEFAULT NULL,
                             `total_price` decimal(10,2) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='a订单';

CREATE TABLE `a_order_1` (
                             `id` bigint NOT NULL,
                             `count` int DEFAULT NULL,
                             `total_price` decimal(10,2) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='a订单';

CREATE TABLE `a_order_111` (
                               `id` int NOT NULL,
                               `count` int DEFAULT NULL,
                               `total_price` decimal(10,2) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='a订单';

CREATE TABLE `b_order` (
                           `id` int NOT NULL,
                           `count` int DEFAULT NULL,
                           `total_price` decimal(10,2) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='b订单';

CREATE TABLE `c_order` (
                           `id` int NOT NULL,
                           `count` int DEFAULT NULL,
                           `total_price` decimal(10,2) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='c订单';

CREATE TABLE `order_1` (
                           `id` bigint NOT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_0` (
                           `id` bigint NOT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `path` (
                        `id` int NOT NULL,
                        `name` varchar(45) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role` (
                        `id` int NOT NULL,
                        `name` varchar(45) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role2path` (
                             `role_id` bigint DEFAULT NULL,
                             `path_id` bigint DEFAULT NULL,
                             `id` int NOT NULL AUTO_INCREMENT,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
                        `id` int DEFAULT NULL,
                        `name` varchar(45) DEFAULT NULL,
                        `age` int DEFAULT NULL,
                        `password` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user2role` (
                             `user_id` int DEFAULT NULL,
                             `role_id` int DEFAULT NULL,
                             `id` int NOT NULL AUTO_INCREMENT,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
