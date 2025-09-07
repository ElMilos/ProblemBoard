CREATE TABLE public.users (
  id            SERIAL PRIMARY KEY,
  username      VARCHAR(64) UNIQUE NOT NULL,
  email         VARCHAR(120) UNIQUE NOT NULL,
  password_hash VARCHAR(200) NOT NULL,
  role          VARCHAR(32) NOT NULL DEFAULT 'USER',
  created_at    TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE public.projects (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(120) NOT NULL,
  description TEXT,
  archived    BOOLEAN NOT NULL DEFAULT FALSE,
  created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

DO $$ BEGIN
  CREATE TYPE issue_status AS ENUM ('OPEN','IN_PROGRESS','RESOLVED');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;
DO $$ BEGIN
  CREATE TYPE issue_priority AS ENUM ('LOW','MEDIUM','HIGH');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

CREATE TABLE public.issues (
  id          SERIAL PRIMARY KEY,
  project_id  INT NOT NULL REFERENCES projects(id),
  title       VARCHAR(200) NOT NULL,
  description TEXT,
  status      issue_status NOT NULL DEFAULT 'OPEN',
  priority    issue_priority NOT NULL DEFAULT 'MEDIUM',
  assignee_id INT REFERENCES users(id),
  created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE public.comments (
  id         SERIAL PRIMARY KEY,
  issue_id   INT NOT NULL REFERENCES issues(id) ON DELETE CASCADE,
  author_id  INT NOT NULL REFERENCES users(id),
  text       TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_issues_project ON issues(project_id);
CREATE INDEX idx_issues_status  ON issues(status);
