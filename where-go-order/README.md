# where-go-order

- 将自身服务注册的eureka server 中心
- 获取server中的email服务，利用Feign内置(Ribbon)负载均衡 调用email服务，并且开启hystrix消息熔断

