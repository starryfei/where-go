# where-go-user
- where-go 用户服务，获取用户信息，登陆，注册等服务

- 登陆方式初步使用redis 存储token，client请求在请求头中加入"authStr"字段， 可以判断是否登陆进去

- todo
    使用JWTtoken方式登陆

---

### 开发中遇到的问题
- 在使用LoginInterceptor中,userService bean 注册不进去
```java
@Autowired
UserService userService;
```

需要在WebConfig 中以bean的方式注册进去，不能使用 new LoginInterceptor();方式注册，使用如下方式可以获取
```java
 @Autowired
 LoginInterceptor loginInterceptor;
registry.addInterceptor(loginInterceptor).addPathPatterns("/*/**");
```


