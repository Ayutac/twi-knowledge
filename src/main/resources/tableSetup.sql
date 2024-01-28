BEGIN;
CREATE TYPE SHORTNAME AS RANGE (subtype = VARCHAR(31));
CREATE TYPE LONGNAME AS RANGE (subtype = VARCHAR(63));
CREATE TYPE LINK AS RANGE (subtype = TEXT);
COMMIT;
BEGIN;
CREATE TABLE volume (
  id        SERIAL,
  name      SHORTNAME UNIQUE NOT NULL,
  wiki_link LINK      UNIQUE NOT NULL,
  PRIMARY KEY(id)
);
CREATE TABLE book (
  id            SERIAL,
  name          LONGNAME  UNIQUE NOT NULL,
  -- chapter will know which volume a book belongs to (?)
  --volume_id     INT                         REFERENCES volume(id),
  volume_ord    INT, -- the ordinal number within the volume
  wiki_link     LINK      UNIQUE NOT NULL,
  sell_link     LINK      UNIQUE NOT NULL,
  audible_link  LINK      UNIQUE,
  PRIMARY KEY(id)
);
CREATE TABLE chapter (
  id          SERIAL,
  name        LONGNAME  UNIQUE NOT NULL,
  volume_ord  INT, -- the ordinal number within the volume
  book_ord    INT, -- the ordinal number within the book
  release     INT       NOT NULL, -- days since epoch
  words       INT       NOT NULL,
  lettered    BOOLEAN   DEFAULT FALSE,
  interlude   BOOLEAN   DEFAULT FALSE,
  in_parts    BOOLEAN   DEFAULT FALSE,
  book_id     INT                       REFERENCES book(id),
  volume_id   INT       NOT NULL        REFERENCES volume(id),
  link        LINK      NOT NULL,
  wiki_link   LINK      NOT NULL,
  PRIMARY KEY(id)
);
CREATE TABLE class (
  id        SERIAL,
  name      LONGNAME  UNIQUE NOT NULL,
  since     INT                         REFERENCES chapter(id),
  PRIMARY KEY(id)
);
CREATE TABLE class_upgrade (
  base_id     INT   NOT NULL REFERENCES class(id),
  upgrade_id  INT   NOT NULL REFERENCES class(id),
  PRIMARY KEY(base_id, upgrade_id)
);
CREATE TABLE skill (
  id        SERIAL,
  name      LONGNAME  UNIQUE NOT NULL,
  since     INT                         REFERENCES chapter(id),
  PRIMARY KEY(id)
);
CREATE TABLE skill_upgrade (
  base_id     INT   NOT NULL REFERENCES skill(id),
  upgrade_id  INT   NOT NULL REFERENCES skill(id),
  PRIMARY KEY(base_id, upgrade_id)
);
CREATE TABLE class_skill (
  class_id  INT   NOT NULL REFERENCES class(id),
  skill_id  INT   NOT NULL REFERENCES skill(id),
  PRIMARY KEY(class_id, skill_id)
);
CREATE TABLE race (
  id                SERIAL,
  name              SHORTNAME UNIQUE NOT NULL,
  first_chapter_id  INT       REFERENCES chapter(id),
  wiki_link         LINK,
  PRIMARY KEY(id)
);
CREATE TABLE character (
  id        SERIAL,
  name_id   SHORTNAME UNIQUE NOT NULL,
  wiki_link LINK,
  PRIMARY KEY(id)
);
CREATE TABLE appearances (
  char_id     INT NOT NULL  REFERENCES character(id),
  chapter_id  INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(char_id, chapter_id)
);
CREATE TABLE first_name (
  name      LONGNAME  NOT NULL,
  char_id   INT       NOT NULL  REFERENCES character(id),
  since     INT                 REFERENCES chapter(id),
  PRIMARY KEY (name, char_id, since)
);
CREATE TABLE middle_name (
  name      LONGNAME  NOT NULL,
  char_id   INT       NOT NULL  REFERENCES character(id),
  since     INT                 REFERENCES chapter(id),
  PRIMARY KEY (name, char_id, since)
);
CREATE TABLE last_name (
  name      LONGNAME  NOT NULL,
  char_id   INT       NOT NULL  REFERENCES character(id),
  since     INT                 REFERENCES chapter(id),
  PRIMARY KEY (name, char_id, since)
);
CREATE TABLE char_race (
  char_id   INT       NOT NULL  REFERENCES character(id),
  race_id   INT       NOT NULL  REFERENCES race(id),
  since     INT                 REFERENCES chapter(id),
  PRIMARY KEY (char_id, race_id, since)
);
CREATE TABLE char_age (
  age       SMALLINT  NOT NULL,
  char_id   INT       NOT NULL  REFERENCES character(id),
  since     INT                 REFERENCES chapter(id),
  PRIMARY KEY (age, char_id, since)
);
COMMIT;