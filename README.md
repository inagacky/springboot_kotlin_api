# SpringBoot × Kotlinで作成したAPIのサンプルプログラム

#### 下記リポジトリの内容をKotlinで作成しました。(当リポジトリの方が機能が多いです。)
[springboot_api_example](https://github.com/inagacky/springboot_api_example)

## 認証について
認証にはJWT(JsonWebToken)を使用しています。  
`仮ユーザー作成API` 実行後、作成ユーザーで `ログインAPI` を叩き、  
返却されたトークンを使用することで他のAPIが実行可能になります。

## DB
DBはmysql8.0を使用しています。  
Dockerコンテナとして起動します。

#### DB定義書
[DB定義書](https://github.com/inagacky/springboot_kotlin_sample/blob/master/docs/db/database_design.md)

#### 起動方法
##### Dockerコンテナとして起動します。

##### ビルド方法　
`/bin/sh build_dev.sh` 

##### 実行方法
`/bin/sh run_dev.sh`

## API定義
・現状、下記のAPIを作成しています。
* ログインAPI
* 仮ユーザー作成API
* ユーザー更新API
* ユーザー情報取得API

エンドポイント等の説明は下記を参照してください。  
[API定義](https://github.com/inagacky/springboot_kotlin_sample/blob/master/docs/api/api_design.md)

## プログラム使用方法
#### 起動
```
./gradlew clean bootRun
```

#### 仮ユーザー作成API
※ トークンによる認証は不要
```
curl -v -X POST -d "{ \"email\" : \"sample@gmail.com\", \"password\" : \"password\"}" -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1.0/users/temporaries" | jq .
< HTTP/1.1 200
{
  "results": {
    "userId": 3,
    "email": "sample@gmail.com",
    "status": 1
  },
  "error": null,
  "status": 200
}
```

#### ログインAPI
※ トークンによる認証は不要
```
curl -v -X POST -d "{ \"loginId\" : \"sample@gmail.com\", \"password\" : \"password\"}" -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1.0/login" | jq .
< HTTP/1.1 200
< Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW1wbGVAZ21haWwuY29tIiwiZXhwIjoxNTU1MzM3ODM3fQ.suIcf4ZrmtMqKclTd9qujpDytwdNidX5cnXGxkU4Zyg
```

#### ユーザー更新API
※ トークンによる認証が必要
##### 認証成功時
```
curl -v -X PUT -d "{ \"firstName\" : \"TEST_FIRST_NAME\", \"lastName\" : \"TEST_LAST_NAME\", \"email\" : \"test@gmail.com\", \"password\" : \"sample\"}" -H "accept: application/json" -H "Content-Type: application/json" -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW1wbGVAZ21haWwuY29tIiwiZXhwIjoxNTU1MzM3ODM3fQ.suIcf4ZrmtMqKclTd9qujpDytwdNidX5cnXGxkU4Zyg" "http://localhost:8080/api/v1.0/users/3" | jq .
< HTTP/1.1 200
{
  "results": {
    "userId": 3,
    "firstName": "TEST_FIRST_NAME",
    "lastName": "TEST_LAST_NAME",
    "email": "test@gmail.com",
    "loginId": "test@gmail.com",
    "status": 5
  },
  "error": null,
  "status": 200
}
```
##### 認証失敗時
```
curl -v -X PUT -d "{ \"firstName\" : \"TEST_FIRST_NAME\", \"lastName\" : \"TEST_LAST_NAME\", \"email\" : \"test@gmail.com\", \"password\" : \"sample\"}" -H "accept: application/json" -H "Content-Type: application/json" -H "Authorization: Bearer sample" "http://localhost:8080/api/v1.0/users/3" | jq .
< HTTP/1.1 403
```


#### ユーザー取得API
※ トークンによる認証が必要

```
curl -X GET -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW1wbGVAZ21haWwuY29tIiwiZXhwIjoxNTU1MzM3ODM3fQ.suIcf4ZrmtMqKclTd9qujpDytwdNidX5cnXGxkU4Zyg" "http://localhost:8080/api/v1.0/users/3" | jq .
{
  "results": {
    "userId": 3,
    "firstName": "TEST_FIRST_NAME",
    "lastName": "TEST_LAST_NAME",
    "email": "test@gmail.com",
    "loginId": "test@gmail.com",
    "status": 5,
    "password": "$2a$10$oNzL.zmbmG52GglHXVhhRuy6mImwagNqPqglWjNfjxkUrATf2d2I."
  },
  "error": null,
  "status": 200
}
```

## 使用技術について
### 言語
`kotlin`
### フレームワーク
`SpringBoot`
### ビルドツール
`Gradle`
### マイグレーションツール
`flyway`
### JWTライブラリ
`com.auth0:java-jwt:3.8.0`

## 使用ポート
|ポート|用途|
|---|---|
|8080|APIアプリケーション|
|33006|Mysql(Dockerコンテナ)|