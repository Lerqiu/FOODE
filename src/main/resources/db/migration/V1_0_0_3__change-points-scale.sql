ALTER TABLE user_detail ADD COLUMN new_points decimal(20, 0);
UPDATE user_detail SET new_points=points;
ALTER TABLE user_detail DROP COLUMN points;
RENAME COLUMN user_detail.new_points TO points;

ALTER TABLE offer ADD COLUMN new_price decimal(20, 0);
UPDATE offer SET new_price=price;
ALTER TABLE offer DROP COLUMN price;
RENAME COLUMN offer.new_price TO price;