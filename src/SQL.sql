USE warsztaty_2;
CREATE TABLE  solution(
    id INT,
    created DATETIME,
    updated DATETIME,
    description TEXT,
    excersise_id INT,
    users_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (excersise_id) REFERENCES excercise(id)
);

CREATE TABLE excercise(
    id INT,
    title VARCHAR(255),
    description TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE user_group(
    id INT ,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE  excercise;

CREATE TABLE users(
    id INT AUTO_INCREMENT,
    userName VARCHAR(255),
    email VARCHAR(255) UNIQUE ,
    password VARCHAR(245),
    user_group_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY(user_group_id) REFERENCES user_group(id)
);