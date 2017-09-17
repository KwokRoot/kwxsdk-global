# 微信公众平台接口调用中控服务器：
---

```
1、实现了定时主动刷新 Access_token 及 JsApi_Ticket。
2、实现了统一获取和刷新 Access_token 及 JsApi_Ticket。
3、对外提供强制刷新 Access_token 、JsApi_Ticket 的接口地址及获取 Access_token 、JsApi_Ticket 的接口地址。
```
---

```
1、获取 Access_token 链接地址：http://localhost:8080/kwxsdk-global/getAccessToken 。
2、获取 JsApi_Ticket 链接地址：http://localhost:8080/kwxsdk-global/getJsApiTicket 。
3、强制刷新 Token 链接地址：http://localhost:8080/kwxsdk-global/updateToken 。
```
参数：randomStr=""&signature="" 。

验证：signature = sha1(randomStr + AppConfig.token) 。
