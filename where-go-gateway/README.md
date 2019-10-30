# where-go-gateway
- 项目的API网关，在网关做用户访问请求校验

解决参考：https://segmentfault.com/a/1190000016227780
#### 问题
- gateway 不能添加start-web maven 
-  在gateway 中添加跨域请求，会和在user_service中的跨域请求冲突，以后都是通过APi网关访问，故移除服务中的跨域
-  getaway，path("/hello").uri("http://localhost:20211"),浏览器访问http://localhost:port/hello,
        真实访问的是http://localhost:20211/hello
- 是否与服务注册于发现组件进行结合，通过 serviceId 转发到具体的服务实例。默认为 false，
     设为 true 便开启通过服务中心的自动根据 serviceId 创建路由的功能。
     ```java spring.cloud.gateway.discovery.locator.enabled=true```
     