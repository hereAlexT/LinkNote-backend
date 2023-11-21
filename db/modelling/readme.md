#### QuickDBD script
This tool is used for data modeling
[QuickDBD Modelling Tool](https://www.quickdatabasediagrams.com/)
```
app_user
-
user_id PK BIGINT
email VARCHAR(255) UNIQUE 
pwd VARCHAR(255)  # password
dis_name VARCHAR(30) NULLABLE # display name
c_code VARCHAR(5) NULLABLE # country calling code
ph_num  VARCHAR(50) NULLABLE # phone number
reg_dt TIMESTAMP  # register date time
# region VARCHAR(50) # country_code



note
-
note_id PK BIGINT 
user_id BIGINT FK >- app_user.user_id
body VARCHAR(5000)
cre_dt TIMESTAMP  # created date time
last_edit_dt TIMESTAMP # last edit datetime
# location


role
-
role_id BIGINT PK
role_name VARCHAR(255) 


user_role
-
user_id BIGINT FK >- app_user.user_id
role_id BIGINT FK >- role.role_id
```
