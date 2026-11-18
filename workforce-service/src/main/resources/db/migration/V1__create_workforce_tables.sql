-- 1. Employee
CREATE TABLE employee (
                          id BIGSERIAL PRIMARY KEY,
                          business_id BIGINT NOT NULL,
                          full_name VARCHAR(100) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          employment_type VARCHAR(20) NOT NULL
                              CHECK (employment_type IN ('CASUAL', 'PART_TIME', 'FULL_TIME')),
                          date_of_birth DATE,
                          start_date DATE NOT NULL,
                          base_hourly_rate NUMERIC(10, 2) NOT NULL
                              CHECK (base_hourly_rate > 0),
                          max_weekly_hours INTEGER NOT NULL
                              CHECK (max_weekly_hours BETWEEN 1 AND 60),
                          active BOOLEAN NOT NULL DEFAULT TRUE,
                          created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 2. Skill
CREATE TABLE skill (
                       id BIGSERIAL PRIMARY KEY,
                       code VARCHAR(50) NOT NULL UNIQUE,
                       name VARCHAR(100) NOT NULL
);

-- 3. Employee Skill
CREATE TABLE employee_skill (
                                employee_id BIGINT NOT NULL REFERENCES employee(id),
                                skill_id BIGINT NOT NULL REFERENCES skill(id),
                                level INTEGER NOT NULL CHECK (level BETWEEN 1 AND 3),

                                PRIMARY KEY (employee_id, skill_id)
);

-- 4. Availability
CREATE TABLE availability (
                              id BIGSERIAL PRIMARY KEY,
                              employee_id BIGINT NOT NULL REFERENCES employee(id),
                              day_of_week INTEGER NOT NULL
                                  CHECK (day_of_week BETWEEN 1 AND 7),
                              start_time TIME NOT NULL,
                              end_time TIME NOT NULL,
                              preference VARCHAR(20) NOT NULL
                                  CHECK (preference IN ('AVAILABLE', 'PREFERRED')),

                              CHECK (end_time > start_time)
);

-- 5. Shift Requirement
CREATE TABLE shift_requirement (
                                   id BIGSERIAL PRIMARY KEY,
                                   business_id BIGINT NOT NULL,
                                   day_of_week INTEGER NOT NULL
                                       CHECK (day_of_week BETWEEN 1 AND 7),
                                   start_time TIME NOT NULL,
                                   end_time TIME NOT NULL,

                                   CHECK (end_time > start_time)
);

-- 6. Shift Requirement Role
CREATE TABLE shift_requirement_role (
                                        id BIGSERIAL PRIMARY KEY,
                                        shift_requirement_id BIGINT NOT NULL
                                            REFERENCES shift_requirement(id),
                                        skill_id BIGINT NOT NULL REFERENCES skill(id),
                                        headcount INTEGER NOT NULL CHECK (headcount > 0),
                                        min_level INTEGER NOT NULL CHECK (min_level BETWEEN 1 AND 3)
);