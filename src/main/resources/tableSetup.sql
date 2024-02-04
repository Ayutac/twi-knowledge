BEGIN;
CREATE TYPE landmass_ocean_type AS ENUM (
  'Moon',
  'Continent',
  'Half continent',
  'Archipelago',
  'Isle',
  'Ocean'
);
CREATE TYPE nation_type AS ENUM (
  'Empire',
  'Kingdom',
  'Oligarchy',
  'Republic',
  'Junta',
  'Walled city',
  'City state'
);
CREATE TYPE settlement_type AS ENUM (
  'City',
  'Town',
  'Village'
);
COMMIT;
BEGIN;
CREATE TABLE volume (
  id        SERIAL,
  name      TEXT      UNIQUE NOT NULL,
  wiki_link TEXT      UNIQUE NOT NULL,
  PRIMARY KEY(id)
);
CREATE TABLE book (
  id                SERIAL,
  name              TEXT      UNIQUE NOT NULL,
  volume_ord        INT, -- the ordinal number within the volume
  wiki_link         TEXT      UNIQUE NOT NULL,
  publication_link  TEXT,
  publication_date  BIGINT, -- days since epoch
  audible_link      TEXT,
  audible_date      BIGINT, -- days since epoch
  PRIMARY KEY(id)
);
CREATE TABLE chapter (
  id          SERIAL,
  name        TEXT      UNIQUE NOT NULL,
  volume_ord  INT, -- the ordinal number within the volume
  book_ord    INT, -- the ordinal number within the book
  release     BIGINT    NOT NULL, -- days since epoch
  words       INT       NOT NULL        CONSTRAINT positive_word_count CHECK(words > 0), -- word count of the chapter
  lettered    BOOLEAN   DEFAULT FALSE,
  interlude   BOOLEAN   DEFAULT FALSE,
  in_parts    BOOLEAN   DEFAULT FALSE,
  book_id     INT                       REFERENCES book(id),
  volume_id   INT                       REFERENCES volume(id),
  link        TEXT      NOT NULL,
  wiki_link   TEXT      NOT NULL,
  PRIMARY KEY(id)
);
CREATE VIEW book_with_volume AS
SELECT DISTINCT
  book.id,
  book.name,
  book.volume_ord,
  book.wiki_link,
  book.publication_link,
  book.publication_date,
  book.audible_link,
  book.audible_date,
  chapter.volume_id
FROM book
LEFT JOIN chapter ON book.id = chapter.book_id;
CREATE TABLE class (
  id        SERIAL,
  name      TEXT      UNIQUE NOT NULL,
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE mention_class (
  class_id    INT   REFERENCES class(id),
  chapter_id  INT   REFERENCES chapter(id),
  PRIMARY KEY(class_id, chapter_id)
);
CREATE TABLE class_upgrade (
  base_id     INT   NOT NULL REFERENCES class(id),
  upgrade_id  INT   NOT NULL REFERENCES class(id),
  CONSTRAINT different_ids CHECK (base_id != upgrade_id),
  PRIMARY KEY(base_id, upgrade_id)
);
CREATE TABLE skill (
  id        SERIAL,
  name      TEXT      UNIQUE NOT NULL,
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE mention_skill (
  skill_id    INT   REFERENCES skill(id),
  chapter_id  INT   REFERENCES chapter(id),
  PRIMARY KEY(skill_id, chapter_id)
);
CREATE TABLE skill_upgrade (
  base_id     INT   NOT NULL REFERENCES skill(id),
  upgrade_id  INT   NOT NULL REFERENCES skill(id),
  CONSTRAINT different_ids CHECK (base_id != upgrade_id),
  PRIMARY KEY(base_id, upgrade_id)
);
CREATE TABLE class_skill (
  class_id  INT   NOT NULL REFERENCES class(id),
  skill_id  INT   NOT NULL REFERENCES skill(id),
  PRIMARY KEY(class_id, skill_id)
);
CREATE TABLE world (
  id        SERIAL,
  name      TEXT      UNIQUE NOT NULL,
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE appearance_world (
  world_id    INT NOT NULL  REFERENCES world(id),
  chapter_id  INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(world_id, chapter_id)
);
CREATE TABLE mention_world (
  world_id    INT NOT NULL  REFERENCES world(id),
  chapter_id  INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(world_id, chapter_id)
);
CREATE TABLE landmass_ocean (
  id        SERIAL,
  name      TEXT                UNIQUE NOT NULL,
  type      landmass_ocean_type NOT NULL,
  world_id  INT                                   REFERENCES world(id),
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE appearance_landmass_ocean (
  landmass_ocean_id INT NOT NULL  REFERENCES landmass_ocean(id),
  chapter_id        INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(landmass_ocean_id, chapter_id)
);
CREATE TABLE mention_landmass_ocean (
  landmass_ocean_id INT NOT NULL  REFERENCES landmass_ocean(id),
  chapter_id        INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(landmass_ocean_id, chapter_id)
);
CREATE TABLE landmark (
  id                SERIAL,
  name              TEXT    UNIQUE NOT NULL,
  is_natural        BOOLEAN NOT NULL,
  landmass_ocean_id INT                       REFERENCES landmass_ocean(id),
  wiki_link TEXT,
  PRIMARY KEY(id)
);
--CREATE VIEW landmark_izril
CREATE TABLE appearance_landmark (
  landmark_id INT NOT NULL  REFERENCES landmark(id),
  chapter_id  INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(landmark_id, chapter_id)
);
CREATE TABLE mention_landmark (
  landmark_id INT NOT NULL  REFERENCES landmark(id),
  chapter_id  INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(landmark_id, chapter_id)
);
CREATE TABLE nation (
  id                SERIAL,
  name              TEXT        UNIQUE NOT NULL,
  type              nation_type NOT NULL,
  landmass_ocean_id INT                           REFERENCES landmass_ocean(id),
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE appearance_nation (
  nation_id   INT NOT NULL  REFERENCES nation(id),
  chapter_id  INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(nation_id, chapter_id)
);
CREATE TABLE mention_nation (
  nation_id   INT NOT NULL  REFERENCES nation(id),
  chapter_id  INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(nation_id, chapter_id)
);
CREATE TABLE settlement (
  id        SERIAL,
  name      TEXT            UNIQUE NOT NULL,
  type      settlement_type NOT NULL,
  nation_id INT                               REFERENCES nation(id),
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE appearance_settlement (
  settlement_id     INT NOT NULL  REFERENCES settlement(id),
  chapter_id        INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(settlement_id, chapter_id)
);
CREATE TABLE mention_settlement (
  settlement_id     INT NOT NULL  REFERENCES settlement(id),
  chapter_id        INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(settlement_id, chapter_id)
);
CREATE TABLE species (
  id                SERIAL,
  name              TEXT      UNIQUE NOT NULL,
  can_level         BOOLEAN   NOT NULL,
  wiki_link         TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE appearance_species (
  species_id    INT NOT NULL  REFERENCES species(id),
  chapter_id    INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(species_id, chapter_id)
);
CREATE TABLE mention_species (
  species_id    INT NOT NULL  REFERENCES species(id),
  chapter_id    INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(species_id, chapter_id)
);
CREATE TABLE rsk ( -- short for: RelationShip Kind
  id    SERIAL,
  name  TEXT    UNIQUE NOT NULL,
  PRIMARY KEY(id)
);
CREATE TABLE character (
  id        SERIAL,
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE appearance_character (
  character_id  INT NOT NULL  REFERENCES character(id),
  chapter_id    INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(character_id, chapter_id)
);
CREATE TABLE mention_character (
  character_id  INT NOT NULL  REFERENCES character(id),
  chapter_id    INT NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(character_id, chapter_id)
);
CREATE TABLE first_name (
  name          TEXT      NOT NULL,
  character_id  INT       NOT NULL  REFERENCES character(id),
  since         INT                 REFERENCES chapter(id),
  PRIMARY KEY(name, character_id, since)
);
CREATE TABLE middle_name (
  name          TEXT      NOT NULL,
  character_id  INT       NOT NULL  REFERENCES character(id),
  since         INT                 REFERENCES chapter(id),
  PRIMARY KEY(name, character_id, since)
);
CREATE TABLE last_name (
  name          TEXT      NOT NULL,
  character_id  INT       NOT NULL  REFERENCES character(id),
  since         INT                 REFERENCES chapter(id),
  PRIMARY KEY(name, character_id, since)
);
CREATE TABLE character_species (
  character_id  INT       NOT NULL  REFERENCES character(id),
  species_id    INT       NOT NULL  REFERENCES species(id),
  since         INT                 REFERENCES chapter(id),
  PRIMARY KEY(character_id, species_id, since)
);
CREATE TABLE character_age (
  age           INT  NOT NULL   CONSTRAINT non_negative_age CHECK(age >= 0),
  character_id  INT  NOT NULL   REFERENCES character(id),
  since         INT             REFERENCES chapter(id),
  PRIMARY KEY(age, character_id, since)
);
CREATE TABLE status (
  id    SERIAL,
  name  TEXT    UNIQUE NOT NULL,
  PRIMARY KEY(id)
);
CREATE TABLE character_status (
  id            SERIAL,
  status_id     INT     NOT NULL  REFERENCES status(id),
  character_id  INT     NOT NULL  REFERENCES character(id),
  since         INT     NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(id)
);
CREATE TABLE first_meeting_left (
  id            SERIAL,
  character1_id INT NOT NULL  REFERENCES character(id),
  character2_id INT NOT NULL  REFERENCES character(id),
  chapter_id    INT NOT NULL  REFERENCES chapter(id),
  CONSTRAINT different_ids CHECK (character1_id != character2_id),
  PRIMARY KEY(character1_id, character2_id)
);
CREATE VIEW first_meeting_right AS
  SELECT m.id AS id, m.character2_id AS character1_id, m.character1_id AS character2_id, m.chapter_id AS chapter_id
  FROM first_meeting_left m;
CREATE VIEW first_meeting AS
  SELECT * FROM first_meeting_left UNION SELECT * FROM first_meeting_right;
CREATE TABLE battle (
  id        SERIAL,
  name      TEXT    UNIQUE NOT NULL,
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE battle_character (
  battle_id     INT   NOT NULL  REFERENCES battle(id),
  character_id  INT   NOT NULL  REFERENCES character(id),
  PRIMARY KEY(battle_id, character_id)
);
CREATE TABLE battle_chapter (
  battle_id     INT   NOT NULL  REFERENCES battle(id),
  chapter_id    INT   NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(battle_id, chapter_id)
);
CREATE TABLE battle_status (
  battle_id     INT   NOT NULL  REFERENCES battle(id),
  status_id     INT   NOT NULL  REFERENCES character_status(id),
  PRIMARY KEY(battle_id, status_id)
);
CREATE TABLE war (
  id        SERIAL,
  name      TEXT    UNIQUE NOT NULL,
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE war_battle (
  war_id     INT   NOT NULL  REFERENCES war(id),
  battle_id  INT   NOT NULL  REFERENCES battle(id),
  PRIMARY KEY(war_id, battle_id)
);
CREATE TABLE solstice (
  id        SERIAL,
  name      TEXT    UNIQUE NOT NULL,
  wiki_link TEXT,
  PRIMARY KEY(id)
);
CREATE TABLE solstice_chapter (
  solstice_id   INT   NOT NULL  REFERENCES solstice(id),
  chapter_id    INT   NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(solstice_id, chapter_id)
);
CREATE TABLE innworld_arrival (
  id            SERIAL,
  character_id  INT     NOT NULL  REFERENCES character(id),
  chapter_id    INT     NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(id)
);
CREATE TABLE inn_arrival (
  id            SERIAL,
  character_id  INT     NOT NULL  REFERENCES character(id),
  chapter_id    INT     NOT NULL  REFERENCES chapter(id),
  PRIMARY KEY(id)
);
CREATE TABLE level_up (
  id            SERIAL,
  character_id  INT     NOT NULL  REFERENCES character(id),
  chapter_id    INT     NOT NULL  REFERENCES chapter(id),
  new_level     INT,
  class_id      INT               REFERENCES class(id),
  capstone      BOOLEAN NOT NULL,
  canceled      BOOLEAN NOT NULL  DEFAULT TRUE,
  PRIMARY KEY(id)
);
CREATE TABLE level_up_skill (
  level_up_id INT   NOT NULL  REFERENCES level_up(id),
  skill_id    INT   NOT NULL  REFERENCES skill(id),
  PRIMARY KEY(level_up_id, skill_id)
);
COMMIT;