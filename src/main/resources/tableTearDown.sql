BEGIN;
DROP TABLE war_battle;
DROP TABLE war;
DROP TABLE battle_status;
DROP TABLE battle_chapter;
DROP TABLE battle_character;
DROP TABLE battle;
DROP VIEW first_meeting;
DROP VIEW first_meeting_right;
DROP TABLE first_meeting_left;
DROP TABLE character_status;
DROP TABLE status;
DROP TABLE character_age;
DROP TABLE character_species;
DROP TABLE last_name;
DROP TABLE middle_name;
DROP TABLE first_name;
DROP TABLE mention_character;
DROP TABLE appearance_character;
DROP TABLE character;
DROP TABLE rsk;
DROP TABLE mention_species;
DROP TABLE appearance_species;
DROP TABLE species;
DROP TABLE mention_settlement;
DROP TABLE appearance_settlement;
DROP TABLE settlement;
DROP TABLE mention_nation;
DROP TABLE appearance_nation;
DROP TABLE nation;
DROP TABLE mention_landmark;
DROP TABLE appearance_landmark;
DROP TABLE landmark;
DROP TABLE mention_landmass_ocean;
DROP TABLE appearance_landmass_ocean;
DROP TABLE landmass_ocean;
DROP TABLE mention_world;
DROP TABLE appearance_world;
DROP TABLE world;
DROP TABLE class_skill;
DROP TABLE skill_upgrade;
DROP TABLE skill;
DROP TABLE class_upgrade;
DROP TABLE class;
DROP VIEW book_with_volume;
DROP TABLE chapter;
DROP TABLE book;
DROP TABLE volume;
COMMIT;
BEGIN;
DROP TYPE settlement_type;
DROP TYPE nation_type;
DROP TYPE landmass_ocean_type;
COMMIT;