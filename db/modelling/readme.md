#### QuickDBD script
[QuickDBD Modelling Tool] (https://www.quickdatabasediagrams.com/)
```
User
-
user_id PK CHAR(16) 
username VARCHAR(255) UNIQUE 
f_name VARCHAR(30) 
l_name VARCHAR(30) 
password VARCHAR(255)
email VARCHAR(100) # email
c_code VARCHAR(5) NULLABLE # country dailing code
ph_num  VARCHAR(50) NULLABLE # phone number
reg_dt TIMESTAMP  # register date time
region VARCHAR(50) 



Note
-
note_id PK CHAR(16) 
user_id CHAR(16) FK >- User.user_id
body VARCHAR(5000)
cre_dt TIMESTAMP  # created date time
last_edit_dt TIMESTAMP # last edit datetime

```
#### SQL

```sql
CREATE TABLE "User" (
    "user_id" CHAR(16)   NOT NULL,
    "username" VARCHAR(255)   NOT NULL,
    "f_name" VARCHAR(30)   NOT NULL,
    "l_name" VARCHAR(30)   NOT NULL,
    "password" VARCHAR(255)   NOT NULL,
    -- email
    "email" VARCHAR(100)   NOT NULL,
    -- country dailing code
    "c_code" VARCHAR(5)   NULL,
    -- phone number
    "ph_num" VARCHAR(50)   NULL,
    -- register date time
    "reg_dt" TIMESTAMP   NOT NULL,
    "region" VARCHAR(50)   NOT NULL,
    CONSTRAINT "pk_User" PRIMARY KEY (
        "user_id"
     ),
    CONSTRAINT "uc_User_username" UNIQUE (
        "username"
    )
);

CREATE TABLE "Note" (
    "note_id" CHAR(16)   NOT NULL,
    "user_id" CHAR(16)   NOT NULL,
    "body" VARCHAR(5000)   NOT NULL,
    -- created date time
    "cre_dt" TIMESTAMP   NOT NULL,
    -- last edit datetime
    "last_edit_dt" TIMESTAMP   NOT NULL,
    CONSTRAINT "pk_Note" PRIMARY KEY (
        "note_id"
     )
);

ALTER TABLE "Note" ADD CONSTRAINT "fk_Note_user_id" FOREIGN KEY("user_id")
REFERENCES "User" ("user_id");


```