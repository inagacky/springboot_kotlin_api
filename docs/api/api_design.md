# API定義


## 仮ユーザー作成API

### エンドポイント  
・ http://localhost:8080/api/v1.0/users/temporaries
### メソッド
・POST
### 用途
・ 仮ユーザーの作成
### 認証
・ 不要
### リクエストパラメータ

| 項目 | 名称 | 型 | 必須 | 例 | 備考 |  
|:---|:---|:---|:---|:---|:---|  
|email| メールアドレス| String | ○ | sample@gmail.com | ログインIDとなる。 |
|password| パスワード | String | ○ | - | - |

```
{
  "email": "sample@gmail.com",
  "password": "sample"
}
```
### レスポンス

```
{
    "results": {
        "userId": 1,
        "email": "sample@gmail.com",
        "status": 1
    },
    "error": null,
    "status": 200
}
```

---

## ログインAPI

### エンドポイント  
・ http://localhost:8080/api/v1.0/login
### メソッド
・POST
### 用途
・ ログインを行い、認証トークンの取得。(仮ユーザー作成APIで作成したユーザーでログイン)
### 認証
・ 不要
### リクエストパラメータ

| 項目 | 名称 | 型 | 必須 | 例 | 備考 |  
|:---|:---|:---|:---|:---|:---|  
|loginId| ログインID| String | ○ | sample@gmail.com | メールアドレスと同一 |
|password| パスワード | String | ○ | - | - |

```
{
  "loginId": "sample@gmail.com",
  "password": "sample"
}
```
### レスポンス
レスポンスヘッダとして返却される。
```
authorization →Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW1wbGVAZ21haWwuY29tIiwiZXhwIjoxNTU1MzM3ODM3fQ.suIcf4ZrmtMqKclTd9qujpDytwdNidX5cnXGxkU4Zyg
```

---

## ユーザー更新API

### エンドポイント  
・ http://localhost:8080/api/v1.0/users/1
### メソッド
・PUT
### 用途
・ ユーザーの更新
### 認証
・ 必要
### リクエストパラメータ

| 項目 | 名称 | 型 | 必須 | 例 | 備考 |  
|:---|:---|:---|:---|:---|:---|  
|userId| ユーザーID| Int | ○ | - | URLに含む |
|firstName| 苗字| String | ○ | - | - |
|lastName| 名前 | String | ○ | - | - |
|email| メールアドレス | String | ○ | - | - |
|password| パスワード | String | ○ | - | - |
```
{
  "firstName": "TEST_FIRST_NAME",
  "lastName": "TEST_LAST_NAME",
  "email": "sample@gmail.com",
  "password": "password"
}
```
### レスポンス

```
{
    "results": {
        "userId": 1,
        "firstName": "TEST_FIRST_NAME",
        "lastName": "TEST_LAST_NAME",
        "email": "sample@gmail.com",
        "loginId": "sample@gmail.com",
        "status": 5
    },
    "error": null,
    "status": 200
}
```

---

## ユーザー取得API

### エンドポイント  
・ http://localhost:8080/api/v1.0/users/1
### メソッド
・GET
### 用途
・ ユーザーの取得
### 認証
・ 必要
### リクエストパラメータ

| 項目 | 名称 | 型 | 必須 | 例 | 備考 |  
|:---|:---|:---|:---|:---|:---|  
|userId| ユーザーID| Int | ○ | - | URLに含む |

### レスポンス

```
{
    "results": {
        "userId": 1,
        "firstName": "TEST_FIRST_NAME",
        "lastName": "TEST_LAST_NAME",
        "email": "sample@gmail.com",
        "loginId": "sample@gmail.com",
        "status": 5
    },
    "error": null,
    "status": 200
}
```