
DROP TABLE IF EXISTS simulation;
 
CREATE TABLE simulation (
  simultation_id INT AUTO_INCREMENT  PRIMARY KEY,
  simulation_name VARCHAR(250) NOT NULL,
  base_url VARCHAR(1000) NOT NULL,
  scenario_name VARCHAR(1000) NOT NULL,
  concurrent_users INT,
  conncurrent_user_duration INT,
  ramp_concurrent_users_start VARCHAR(250),
  ramp_concurrent_users_end	VARCHAR(250),
  ramp_up_duration	INT
);


DROP TABLE IF EXISTS execution_steps;
 
CREATE TABLE execution_steps (
  step_id INT AUTO_INCREMENT  PRIMARY KEY,
  execution_name VARCHAR(250) NOT NULL,
  request_type VARCHAR(250) NOT NULL,
  route VARCHAR(250) NOT NULL,
  pause INT,
  simultation_id INT 
);

ALTER TABLE execution_steps
ADD FOREIGN KEY (simultation_id) 
REFERENCES simulation(simultation_id);

DROP TABLE IF EXISTS header_param;
 
CREATE TABLE header_param (
  header_param_id INT AUTO_INCREMENT  PRIMARY KEY,
  header_key VARCHAR(250) NOT NULL,
  header_value VARCHAR(250) NOT NULL,
  step_id INT
);

ALTER TABLE header_param
ADD FOREIGN KEY (step_id) 
REFERENCES execution_steps(step_id);

CREATE TABLE query_param (
  query_param_id INT AUTO_INCREMENT  PRIMARY KEY,
  param_key VARCHAR(250) NOT NULL,
  param_value VARCHAR(250) NOT NULL,
  step_id INT
);

ALTER TABLE query_param
ADD FOREIGN KEY (step_id) 
REFERENCES execution_steps(step_id);
