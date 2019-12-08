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

ALTER TABLE users ADD COLUMN user_group_id INT;
ALTER TABLE  users ADD FOREIGN KEY (user_group_id) REFERENCES user_group(id);