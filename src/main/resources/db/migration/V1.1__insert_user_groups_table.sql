INSERT INTO user_groups
(
	id,
	user_group_name
)
VALUES(
	gen_random_uuid ()::VARCHAR,
	'Student LV1'
);
INSERT INTO user_groups
(
	id,
	user_group_name
)
VALUES(
	gen_random_uuid ()::VARCHAR,
	'Student LV2'
);