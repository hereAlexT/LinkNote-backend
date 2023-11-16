CREATE TABLE "app_user"
(
    "user_id"  BIGINT       NOT NULL,
    "email"    VARCHAR(255) NOT NULL,
    -- password
    "pwd"      VARCHAR(255) NOT NULL,
    -- display name
    "dis_name" VARCHAR(30)  NOT NULL,
    -- country calling code
    "c_code"   VARCHAR(5)   NULL,
    -- phone number
    "ph_num"   VARCHAR(50)  NULL,
    -- register date time
    "reg_dt"   TIMESTAMP    NOT NULL,
    CONSTRAINT "pk_app_user" PRIMARY KEY (
                                          "user_id"
        ),
    CONSTRAINT "uc_app_user_email" UNIQUE (
                                           "email"
        )
);

-- region VARCHAR(50)
CREATE TABLE "note"
(
    "note_id"      BIGINT        NOT NULL,
    "user_id"      BIGINT        NOT NULL,
    "body"         VARCHAR(5000) NOT NULL,
    -- created date time
    "cre_dt"       TIMESTAMP     NOT NULL,
    -- last edit datetime
    "last_edit_dt" TIMESTAMP     NOT NULL,
    CONSTRAINT "pk_note" PRIMARY KEY (
                                      "note_id"
        )
);

-- location
CREATE TABLE "role"
(
    "role_id"   BIGINT       NOT NULL,
    "role_name" VARCHAR(255) NOT NULL,
    CONSTRAINT "pk_role" PRIMARY KEY (
                                      "role_id"
        )
);

CREATE TABLE "user_role"
(
    "user_id" BIGINT NOT NULL,
    "role_id" BIGINT NOT NULL
);

ALTER TABLE "note"
    ADD CONSTRAINT "fk_note_user_id" FOREIGN KEY ("user_id")
        REFERENCES "app_user" ("user_id");

ALTER TABLE "user_role"
    ADD CONSTRAINT "fk_user_role_user_id" FOREIGN KEY ("user_id")
        REFERENCES "app_user" ("user_id");

ALTER TABLE "user_role"
    ADD CONSTRAINT "fk_user_role_role_id" FOREIGN KEY ("role_id")
        REFERENCES "role" ("role_id");

INSERT INTO "role" ("role_id", "role_name") VALUES (1, 'ROLE_USER_ADMIN');
INSERT INTO "role" ("role_id", "role_name") VALUES (2, 'ROLE_USER_UNREGISTERED');
INSERT INTO "role" ("role_id", "role_name") VALUES (3, 'ROLE_USER_REGISTERED');
INSERT INTO "role" ("role_id", "role_name") VALUES (4, 'ROLE_USER_PAID');


-- Password before encrypted: @Password1
INSERT INTO "app_user" ("user_id", "email", "pwd", "dis_name", "reg_dt")
VALUES (671167820206080, 'link@link.link', '$2a$10$6hTOIMFt1NsXgxqdA5B9OeFM2orNNiFOq8VgAs1AjWp17gz7I8o2e', 'defaultLinkUserName', CURRENT_TIMESTAMP);

-- Password before encrypted: @Password2
INSERT INTO "app_user" ("user_id", "email", "pwd", "dis_name", "reg_dt")
VALUES (736556142956544, 'link2@link.link', '$2a$10$xyKhqSyTUG5cdcqrGNGjJeUhZjjswymvJ6.amuamOmNtSFhMMa/lq', 'defaultLinkUserName2', CURRENT_TIMESTAMP);

INSERT INTO "user_role" ("user_id", "role_id")
VALUES (1, 3);

INSERT INTO "user_role" ("user_id", "role_id")
VALUES (2, 3);