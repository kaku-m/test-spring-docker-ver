# Kotlin + Spring Boot で開発中のAPI

## 起動
```
git clone git@github.com:kaku-m/test-spring-docker-ver.git
cd test-spring-docker-ver
docker-compose up --build
```

## テスト
```
◆ページの一覧取得（最初は空であること）
curl http://localhost:8080/api/pages
◆データ登録（親ページID無し）
curl -X POST http://localhost:8080/api/pages/create -d title=title1
◆データ登録（親ページIDあり）
curl -X POST http://localhost:8080/api/pages/create -d parentPageId=1 -d title=title2
curl -X POST http://localhost:8080/api/pages/create -d parentPageId=1 -d title=title3
curl -X POST http://localhost:8080/api/pages/create -d parentPageId=3 -d title=title4
◆ページの一覧取得（4件登録されていること）
curl http://localhost:8080/api/pages
◆特定ページの直近の子ページを取得（id2とid3を取得すること）
curl http://localhost:8080/api/pages/1/children
◆特定ページの直近の親ページを取得（id3を取得すること）
curl http://localhost:8080/api/pages/4/parent
◆特定ページの祖先ページを取得（id1とid3を取得すること）
curl http://localhost:8080/api/pages/4/ancestors
◆特定ページの更新
curl -X PUT http://localhost:8080/api/pages/2/update -d title=title2_update -d content=content2_update
◆特定ページの取得（更新されていること）
curl http://localhost:8080/api/pages/2
◆特定ページとその子ページを移動
curl -X PUT http://localhost:8080/api/pages/3/move -d parentPageId=2
◆ページの一覧取得（id3とid4がid2の下に移動していること）
curl http://localhost:8080/api/pages

◆画像情報の保存
curl -X POST http://localhost:8080/api/pages/1/images/save -d name=name1 -d path=path1
curl -X POST http://localhost:8080/api/pages/1/images/save -d name=name2 -d path=path2
◆画像情報の取得（2件登録されていること）
curl http://localhost:8080/api/pages/1/images

◆特定ページとその子ページを削除
curl -X DELETE http://localhost:8080/api/pages/2/delete
◆ページの一覧取得（id1のみ取得すること）
curl http://localhost:8080/api/pages
```

## 終了
--volumesを付けるとDBクリア
```
docker-compose down --volumes
```
