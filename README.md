# Kotlin + Spring Boot で開発中のAPI

## 起動
```
git clone git@github.com:kaku-m/test-spring-docker-ver.git
cd test-spring-docker-ver
docker-compose up --build
```

## テスト
```
// 一覧取得（最初は空であること）
curl http://localhost:8080/api/page/list
// データ登録（親ページID無し）
curl -X POST http://localhost:8080/api/page/create -d pageTitle=title1
// データ登録（親ページIDあり）
curl -X POST http://localhost:8080/api/page/create -d parentPageId=1 -d pageTitle=title2
// 一覧取得（2件登録されていること）
curl http://localhost:8080/api/page/list
// 特定のページ更新
curl -X PUT http://localhost:8080/api/page/update -d pageId=2 -d pageTitle=title2_update -d content=content2_update
// 特定のページ取得（更新されていること）
curl http://localhost:8080/api/page/2
// 特定のページとその子ページを全て削除
curl -X DELETE http://localhost:8080/api/page/1/delete
// 一覧取得（全て削除され空であること）
curl http://localhost:8080/api/page/list
```
