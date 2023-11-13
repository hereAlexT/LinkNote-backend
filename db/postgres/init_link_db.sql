-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.


CREATE TABLE "appuser" (
                           "user_id" BIGINT   NOT NULL,
                           "username" VARCHAR(255)   NOT NULL,
                           "password" VARCHAR(255)   NOT NULL,
                           "f_name" VARCHAR(30)   NULL,
                           "l_name" VARCHAR(30)   NULL,
    -- email
                           "email" VARCHAR(100)   NULL,
    -- country dailing code
                           "c_code" VARCHAR(5)   NULL,
    -- phone number
                           "ph_num" VARCHAR(50)   NULL,
    -- register date time
                           "reg_dt" TIMESTAMP   NULL,
                           "region" VARCHAR(50)   NULL,
                           CONSTRAINT "pk_appuser" PRIMARY KEY (
                                                                "user_id"
                               ),
                           CONSTRAINT "uc_appuser_username" UNIQUE (
                                                                    "username"
                               )
);

CREATE TABLE "note" (
                        "note_id" BIGINT   NOT NULL,
                        "user_id" BIGINT   NOT NULL,
                        "body" VARCHAR(5000)   NOT NULL,
    -- created date time
                        "cre_dt" TIMESTAMP   NULL,
    -- last edit datetime
                        "last_edit_dt" TIMESTAMP   NULL,
                        CONSTRAINT "pk_note" PRIMARY KEY (
                                                          "note_id"
                            )
);

ALTER TABLE "note" ADD CONSTRAINT "fk_note_user_id" FOREIGN KEY("user_id")
    REFERENCES "appuser" ("user_id");

