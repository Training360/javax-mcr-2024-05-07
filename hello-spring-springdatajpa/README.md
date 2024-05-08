
```shell
docker run -d -e POSTGRES_DB=studenttracking -e POSTGRES_USER=studenttracking  -e POSTGRES_PASSWORD=studenttracking  -p 5432:5432  --name studenttracking-postgres postgres
```