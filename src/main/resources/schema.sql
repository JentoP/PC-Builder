DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities
(
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);
create unique index ix_auth_username on authorities (username,authority);