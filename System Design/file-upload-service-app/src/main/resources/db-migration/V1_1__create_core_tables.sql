CREATE TABLE permission_group (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE item (
  id SERIAL PRIMARY KEY,
  type VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  permission_group_id INT,
  parent_id INT,
  FOREIGN KEY (permission_group_id) REFERENCES permission_group(id),
  FOREIGN KEY (parent_id) REFERENCES item(id)

);

CREATE TABLE file_data (
  id SERIAL PRIMARY KEY,
  binary_data BYTEA NOT NULL,
  item_id INT NOT NULL,
  FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE permission (
  id SERIAL PRIMARY KEY,
  user_email VARCHAR(255) NOT NULL,
  permission_level VARCHAR(255) NOT NULL,
  group_id INT,
  FOREIGN KEY (group_id) REFERENCES permission_group(id)
);