BEGIN;
INSERT INTO status (name) VALUES
  ('Alive'),
  ('Deceased'),
  ('Active'),
  ('Unknown'),
  ('Soul consumed'),
  ('Soul unravelled');
INSERT INTO rsk (name) VALUES
  ('Nemesis'),
  ('Enemy'),
  ('Rival'),
  ('Jerk'),
  ('Acquaintance'),
  ('Friend'),
  ('Close friend'),
  ('Partner'),
  ('Parent'),
  ('Child'),
  ('Step parent'),
  ('Step child'),
  ('Sibling'),
  ('Family'),
  ('Tribe member');
COMMIT;