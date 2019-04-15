# DB定義書

## users ユーザー情報
|カラム名|型|NOT NULL|PK|FK|和名|
|---|---|---|---|---|---|
|user_id|int(11)|○|○|-|ユーザーID|
|first_name|varchar(255)|-|-|-|名前|
|last_name|varchar(255)|-|-|-|苗字|
|email|varchar(255)|○|-|-|メールアドレス|
|login_id|varchar(255)|○|-|-|ログインID|
|password|varchar(255)|○|-|-|パスワード|
|status|tinyint(4)|○|-|-|顧客ステータス|
|created_at|datetime|○|-|-|作成日時|
|updated_at|datetime|○|-|-|更新日時|