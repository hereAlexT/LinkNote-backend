CREATE TABLE IF NOT EXISTS "app_user"
(
    "user_id"  BIGINT       NOT NULL,
    "username" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "f_name"   VARCHAR(30)  NULL,
    "l_name"   VARCHAR(30)  NULL,
    -- email
    "email"    VARCHAR(100) NULL,
    -- country dailing code
    "c_code"   VARCHAR(5)   NULL,
    -- phone number
    "ph_num"   VARCHAR(50)  NULL,
    -- register date time
    "reg_dt"   TIMESTAMP    NULL,
    "role"     VARCHAR(15)  NULL,
    "region"   VARCHAR(50)  NULL,
    CONSTRAINT "pk_User" PRIMARY KEY (
                                      "user_id"
        ),
    CONSTRAINT "uc_User_username" UNIQUE (
                                          "username"
        )
);

CREATE TABLE IF NOT EXISTS "note"
(
    "note_id"      BIGINT        NOT NULL,
    "user_id"      BIGINT        NOT NULL,
    "body"         VARCHAR(5000) NOT NULL,
    -- created date time
    "cre_dt"       TIMESTAMP     NULL,
    -- last edit datetime
    "last_edit_dt" TIMESTAMP     NULL,s
    CONSTRAINT "pk_Note" PRIMARY KEY (
                                      "note_id"
        )
);

ALTER TABLE "Note"
    ADD CONSTRAINT "fk_Note_user_id" FOREIGN KEY ("user_id")
        REFERENCES "User" ("user_id");