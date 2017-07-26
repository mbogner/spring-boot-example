CREATE TABLE readings (
  id    BIGINT AUTO_INCREMENT,
  value DECIMAL(19, 2),
  CONSTRAINT pk_readings PRIMARY KEY (id)
);