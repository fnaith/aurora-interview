# SmartbeeExam1

This repository is for [極光 後端Java測驗](https://hackmd.io/@C6sPzZr2T32Do-HNSf_5tQ/H1QOyCZAH).

Online Demo is on : [Heroku](https://aurora-interview.herokuapp.com/index.html)

```
使用者角色清單權限
後台帳號管理，需要建立帳號，每個帳號會有不同角色，每個角色擁有不同的清單(menu)

需求說明
1.需要由角色查出所有的帳號
2.需由帳號查出所擁有的清單
3.需使用Spring boot 做為系統架構
4.請使用JPA或MyBatis或Hibernate做為資料存取
5.請把完成的作業上傳至自己的github帳號

需求驗收
1.請由Unit Test, 及Integration Test,提供驗證結果
2.需提供帳號，角色，清單查詢相關詢結果
3.請提供Db Sql script(及er-Model)
4.需在程式架構中，使用設計模式，並註解出所使用的設計模式
```

## 驗收

### 後台帳號管理，需要建立帳號
註冊畫面
![image](/doc/register.PNG)

### 每個帳號會有不同角色
manager : 是manager以及user角色
user : 是user角色
![image](/doc/diff_role.PNG)

### 每個角色擁有不同的清單
manager的 : 清單
![image](/doc/manager_menu.PNG)
user的 : 清單
![image](/doc/user_menu.PNG)


### 需要由角色查出所有的帳號
manager能查詢使用者列表
![image](/doc/user_list_ok.PNG)
user的無法查詢使用者列表
![image](/doc/user_menu.PNG)


### 需使用Spring boot 做為系統架構
使用spring boot starter
![image](/doc/spring_boot.PNG)

### 請使用JPA或MyBatis或Hibernate做為資料存取
MenuRepository以及UserRepository使用jpa
![image](/doc/jpa.PNG)

### 請把完成的作業上傳至自己的github帳號
[Github](https://github.com/fnaith/aurora-interview)

### 請由Unit Test, 及Integration Test,提供驗證結果
SpringBootTest測試結果
![image](/doc/test.PNG)

### 需提供帳號，角色，清單查詢相關詢結果

#### 帳號 : 密碼
manager@gmail.com : manager
user@gmail.com : user

#### 帳號 : 角色
manager@gmail.com : manager / user
user@gmail.com : user

#### 清單查詢相關詢結果
manager的 : 清單
![image](/doc/manager_menu.PNG)
user的 : 清單
![image](/doc/user_menu.PNG)

### 請提供Db Sql script(及er-Model)
schema-h2.sql用於建表。data-h2.sql用於初始化資料。
![image](/doc/sql.PNG)

### 需在程式架構中，使用設計模式，並註解出所使用的設計模式
單體模式
![image](/doc/singleton.PNG)
