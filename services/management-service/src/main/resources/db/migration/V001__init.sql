CREATE TABLE farm
  (
     id               VARCHAR(255) NOT NULL,
     created_at       TIMESTAMP,
     created_by       VARCHAR(255),
     last_modified_at TIMESTAMP,
     last_modified_by VARCHAR(255),
     version          INT4 NOT NULL,
     name             VARCHAR(255),
     PRIMARY KEY (id)
  );

CREATE TABLE field
  (
     id                       VARCHAR(255) NOT NULL,
     created_at               TIMESTAMP,
     created_by               VARCHAR(255),
     last_modified_at         TIMESTAMP,
     last_modified_by         VARCHAR(255),
     version                  INT4 NOT NULL,
     farm_id                  VARCHAR(255),
     season                   VARCHAR(255),
     cropType                 VARCHAR(255),
     plantingArea             VARCHAR(255),
     expectedProduct          VARCHAR(255),
     actualHarvestedProduct   VARCHAR(255),
     PRIMARY KEY (id)
  );

ALTER TABLE field
  ADD CONSTRAINT fklfwrwv0ju748ee2097lyfq0w6 FOREIGN KEY (farm_id) REFERENCES
  farm;