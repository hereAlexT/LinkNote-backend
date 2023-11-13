#### QuickDBD script
[QuickDBD Modelling Tool] (https://www.quickdatabasediagrams.com/)
```
appuser
-
user_id PK BIGINT
username VARCHAR(255) UNIQUE 
password VARCHAR(255) 
f_name VARCHAR(30) NULLABLE
l_name VARCHAR(30) NULLABLE
email VARCHAR(100) # email
c_code VARCHAR(5) NULLABLE # country dailing code
ph_num  VARCHAR(50) NULLABLE # phone number
reg_dt TIMESTAMP  # register date time
region VARCHAR(50) 



note
-
note_id PK BIGINT 
user_id BIGINT FK >- appuser.user_id
body VARCHAR(5000)
cre_dt TIMESTAMP  # created date time
last_edit_dt TIMESTAMP # last edit datetime

```
#### SQL

```sql
-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.


CREATE TABLE "appuser" (
                           "user_id" BIGINT   NOT NULL,
                           "username" VARCHAR(255)   NOT NULL,
                           "password" VARCHAR(255)   NOT NULL,
                           "f_name" VARCHAR(30)   NULL,
                           "l_name" VARCHAR(30)   NULL,
    -- email
                           "email" VARCHAR(100)   NOT NULL,
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
                        "note_id" CHAR(16)   NOT NULL,
                        "user_id" CHAR(16)   NOT NULL,
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






```