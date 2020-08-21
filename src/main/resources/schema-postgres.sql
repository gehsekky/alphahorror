DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS character_class;
DROP TABLE IF EXISTS action;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS game;

CREATE TABLE character_class
(
    character_class_id uuid NOT NULL,
    strength integer NOT NULL,
    constitution integer NOT NULL,
    dexterity integer NOT NULL,
    intelligence integer NOT NULL,
    wisdom integer NOT NULL,
    charisma integer NOT NULL,
    name character varying(256) NOT NULL,
    CONSTRAINT character_class_pkey PRIMARY KEY (character_class_id)
);

CREATE TABLE game
(
    game_id uuid NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    CONSTRAINT game_pkey PRIMARY KEY (game_id)
);

CREATE TABLE player
(
    player_id uuid NOT NULL,
    game_id uuid NOT NULL,
    name character varying(1024) NOT NULL,
    hit_points integer NOT NULL,
    spell_points integer NOT NULL,
    character_class_id uuid NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    CONSTRAINT player_pkey PRIMARY KEY (player_id),
    CONSTRAINT fk_player_character_class_id FOREIGN KEY (character_class_id)
        REFERENCES character_class (character_class_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT fk_player_game_id FOREIGN KEY (game_id)
        REFERENCES game (game_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE room
(
    room_id uuid NOT NULL,
    game_id uuid NOT NULL,
    coordinate_x integer NOT NULL,
    coordinate_y integer NOT NULL,
    description text NOT NULL,
    is_cleared boolean NOT NULL DEFAULT false,
    is_current boolean NOT NULL DEFAULT false,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    CONSTRAINT room_pkey PRIMARY KEY (room_id),
    CONSTRAINT fk_game_game_id FOREIGN KEY (game_id)
        REFERENCES game (game_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE action
(
    action_id uuid NOT NULL,
    action_type character varying(256)  NOT NULL,
    action_value jsonb NOT NULL,
    room_id uuid NOT NULL,
    game_id uuid NOT NULL,
    resolution jsonb,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    CONSTRAINT action_pkey PRIMARY KEY (action_id),
    CONSTRAINT fk_action_room_id FOREIGN KEY (room_id)
        REFERENCES room (room_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);