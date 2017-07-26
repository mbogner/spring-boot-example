CREATE TABLE reading_types (
  id   BIGINT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_reading_types PRIMARY KEY (id),
  CONSTRAINT uc_reading_types__name UNIQUE (name)
);

ALTER TABLE readings
  ADD reading_type_id BIGINT NOT NULL AFTER value;