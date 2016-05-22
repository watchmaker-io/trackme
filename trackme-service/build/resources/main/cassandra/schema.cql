CREATE TABLE trackme.phone_location (
	user_id uuid,
	time timestamp,
	longitude double,
	latitude double,
	altitude double,
	speed double,
	accuracy text,
	PRIMARY KEY ((user_id), time)
) WITH CLUSTERING ORDER BY (time DESC);