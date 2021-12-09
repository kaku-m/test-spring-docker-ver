# Kotlin + Spring Boot で開発中のAPI

## 起動
```
git clone git@github.com:kaku-m/test-spring-docker-ver.git
cd test-spring-docker-ver
docker-compose up --build
```

## テスト
```
◆ページ一覧の取得（最初は空であること）
curl http://localhost:8080/api/page/list
◆データ登録（親ページID無し）
curl -X POST http://localhost:8080/api/page/create -d title=title1
◆データ登録（親ページIDあり）
curl -X POST http://localhost:8080/api/page/create -d parentPageId=1 -d title=title2
curl -X POST http://localhost:8080/api/page/create -d parentPageId=1 -d title=title3
curl -X POST http://localhost:8080/api/page/create -d parentPageId=3 -d title=title4
◆ページ一覧の取得（4件登録されていること）
curl http://localhost:8080/api/page/list
◆特定ページの直近の子ページを取得
curl http://localhost:8080/api/page/1/children
◆特定ページの直近の親ページを取得
curl http://localhost:8080/api/page/4/parent
◆特定ページの祖先ページを取得
curl http://localhost:8080/api/page/4/ancestors
◆特定ページの更新
curl -X PUT http://localhost:8080/api/page/2/update -d title=title2_update -d content=content2_update
◆特定ページの取得（更新されていること）
curl http://localhost:8080/api/page/2
◆特定ページとその子ページを全て削除
curl -X DELETE http://localhost:8080/api/page/1/delete
◆ページ一覧の取得（全て削除され空であること）
curl http://localhost:8080/api/page/list
```

## 終了
--volumesを付けるとDBクリア
```
docker-compose down --volumes
```
