CREATE TABLE reading_types(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT uc_reading_types__name UNIQUE(name)
);

ALTER TABLE readings ADD reading_type_id BIGINT NOT NULL AFTER value;