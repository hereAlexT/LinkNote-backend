#### run
Run dev env will start:
- Postgres
- Redis
```bash
docker-compose -p cachenote -f docker-compose.dev.yml up
```

#### Naming Method
Request -> XxRequest <br />
View Object -> xxVo - Object from backend to frontend
Mongo Entity -> XxDoc 
Data Trabsfer Object -> XxDto

All table and filed name in database should be lower case.

#### Start Docker
```docker-compose -p cachenote -f docker-compose.dev.yml up 
```

#### Naming
- All database column name keep lower keys or use underscore to connect.
