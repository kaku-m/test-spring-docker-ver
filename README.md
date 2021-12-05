# test-spring

```
git clone git@github.com:kaku-m/test-spring.git
cd test-spring
docker-compose up --build
curl http://localhost:8080/demo/add -d name=first -d password=test123
curl http://localhost:8080/demo/add -d name=second -d password=test234
curl http://localhost:8080/demo/all
```

test