CREATE TABLE roster_job (
                            id BIGSERIAL PRIMARY KEY,
                            business_id BIGINT NOT NULL,
                            requested_by BIGINT NOT NULL,
                            week_start DATE NOT NULL,
                            status VARCHAR(20) NOT NULL
                                CHECK (status IN ('PENDING', 'RUNNING', 'DONE', 'FAILED')),
                            solver VARCHAR(20) NOT NULL
                                CHECK (solver IN ('GREEDY', 'TIMEFOLD')),
                            input_snapshot JSONB,
                            error_message TEXT,
                            created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            started_at TIMESTAMPTZ,
                            finished_at TIMESTAMPTZ
);

CREATE TABLE roster (
                        id BIGSERIAL PRIMARY KEY,
                        job_id BIGINT NOT NULL UNIQUE REFERENCES roster_job(id),
                        business_id BIGINT NOT NULL,
                        week_start DATE NOT NULL,
                        status VARCHAR(20) NOT NULL
                            CHECK (status IN ('DRAFT', 'PUBLISHED')),
                        total_cost NUMERIC(12, 2) NOT NULL DEFAULT 0,
                        total_hours NUMERIC(10, 2) NOT NULL DEFAULT 0,
                        unfilled_count INTEGER NOT NULL DEFAULT 0,
                        preference_score NUMERIC(10, 2),
                        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roster_slot (
                             id BIGSERIAL PRIMARY KEY,
                             roster_id BIGINT NOT NULL REFERENCES roster(id),
                             shift_date DATE NOT NULL,
                             start_time TIME NOT NULL,
                             end_time TIME NOT NULL,
                             skill_id BIGINT NOT NULL,
                             employee_id BIGINT,
                             rate_snapshot NUMERIC(10, 2),
                             cost NUMERIC(12, 2),
                             manually_edited BOOLEAN NOT NULL DEFAULT FALSE,
                             CHECK (end_time > start_time)
);

CREATE TABLE outbox_event (
                              id BIGSERIAL PRIMARY KEY,
                              aggregate_id BIGINT NOT NULL,
                              event_type VARCHAR(100) NOT NULL,
                              payload JSONB NOT NULL,
                              created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              published_at TIMESTAMPTZ
);