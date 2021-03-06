### spring cloud 内容整理

#### Netflix OSS组件集成（Eureka，Hystrix，Zuul，Archaius等）。
- 1.Eureka，服务注册和发现，它提供了一个服务注册中心、服务发现的客户端，还有一个方便的查看所有注册的服务的界面。
	所有的服务使用Eureka的服务发现客户端来将自己注册到Eureka的服务器上。
- 2.Zuul，网关，所有的客户端请求通过这个网关访问后台的服务。他可以使用一定的路由配置来判断某一个URL由哪个服务来处理。并从Eureka获取注册的服务来转发请求。
- 3.Ribbon，即负载均衡，Zuul网关将一个请求发送给某一个服务的应用的时候，如果一个服务启动了多个实例，就会通过Ribbon来通过一定的负载均衡策略来发送给某一个服务实例。
- 4.Feign，服务客户端，服务之间如果需要相互访问，可以使用RestTemplate，也可以使用Feign客户端访问。它默认会使用Ribbon来实现负载均衡。
- 5.Hystrix，监控和断路器。我们只需要在服务接口上添加Hystrix标签，就可以实现对这个接口的监控和断路器功能。
- 6.Hystrix Dashboard，监控面板，他提供了一个界面，可以监控各个服务上的服务调用所消耗的时间等。
- 7.Turbine，监控聚合，使用Hystrix监控，我们需要打开每一个服务实例的监控信息来查看。而Turbine可以帮助我们把所有的服务实例的监控信息聚合到一个地方统一查看。
- 8. gateway 比Zuul更加强大，协议转换，路由转发， 流量聚合，对流量进行监控，日志输出，作为整个系统的前端工程，对流量进行控制，有限流的作用，作为系统的前端边界，外部流量只能通过网关才能访问系统，可以在网关层做权限的判断可以在网关层做缓存
- 9. zipkin链路追踪
- 10. Bus, 用于将服务和服务实例与分布式消息传递链接在一起的事件总线 用于在群集中传播状态更改（例如，配置更改事件）


#### 注册中心 Consul, Eurkea

CAP原则又称CAP定理，指的是在一个分布式系统中，一致性（Consistency）、可用性（Availability）、分区容错性（Partition tolerance）
- 一致性（C）：在分布式系统中的所有数据备份，在同一时刻是否同样的值。（等同于所有节点访问同一份最新的数据副本）
- 可用性（A）：在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。（对数据更新具备高可用性）
- 分区容忍性（P）：系统中任意信息的丢失或失败不会影响系统的继续运作

##### Eureka,Consul,ZooKeeper 对比
Eureka ap
在Eureka平台中，如果某台服务器宕机，Eureka不会有类似于ZooKeeper的选举leader的过程；客户端请求会自动切换 到新的Eureka节点；当宕机的服务器重新恢复后，Eureka会再次将其纳入到服务器集群管理之中；而对于它来说，所有要做的无非是同步一些新的服务 注册信息而已。所以，再也不用担心有“掉队”的服务器恢复以后，会从Eureka服务器集群中剔除出去的风险了。Eureka甚至被设计用来应付范围更广 的网络分割故障，并实现“0”宕机维护需求。当网络分割故障发生时，每个Eureka节点，会持续的对外提供服务（注：ZooKeeper不会）：接收新 的服务注册同时将它们提供给下游
Consul cp
ZooKeeper是个 CP的，即任何时刻对ZooKeeper的访问请求能得到一致的数据结果，同时系统对网络分割具备容错性；但是它不能保证每次服务请求的可用性（注：也就 是在极端环境下，ZooKeeper可能会丢弃一些请求，消费者程序需要重新请求才能获得结果）

### TCP 三次握手 https://www.jianshu.com/p/f876f19112a2

SYN:连接请求
ACK：确认号
seq:发送端的初值序列号
ack:表示期望收到的对方下一个报文段的第一个数据字节序号，也表示x为止的字符都以收到

整个过程如下：
客户端发送SYN=1，ACK = 0，seq =x(x 是随机生成的一个 int 数值)给服务端，请求建立连接，进入SYN-SEND状态
服务端接收到之后，发送 SYN=1，ACK=1，seq=y,ack=x+1；进入SYN-RECIVED状态
客户端接收到之后，发送ACK,seq = x+1,ack=y+1;确认收到信息，进入连接状态establish
服务端收到报文，进入连接状态establish

第一次握手
	建立连接。客户端发送连接请求报文段，将SYN位置为1，Sequence Number为x；（x 是随机生成的一个 int 数值）然后，客户端进入SYN_SEND状态，等待服务器的确认；
	
	192.0.0.1 [SYN=1 ACK=0]	=322029973 win=64249 Len=8 NS=1460 Ws=256 SACK_PERM=1

第二次握手
	服务器收到SYN报文段。服务器收到客户端的SYN报文段，需要对这个SYN报文段进行确认，
	设置Acknowledgment Number为x+1(Sequence Number+1)；同时，自己自己还要发送SYN请求信息，
	将SYN位置为1，Sequence Number为 y （y 是随机生存的一个 int 数值）；
	服务器端将上述所有信息放到一个报文段（即SYN+ACK报文段）中，一并发送给客户端，此时服务器进入SYN_RECV状态；

192.0.0.2 [SYN=1 ACK=1]Seq=2179020842 Ack=3220829974 win=14140 Len=8 NsS=1412 SACK_PERM=1 Ws=512
第三次握手
	客户端收到服务器的SYN+ACK报文段。然后将Acknowledgment Number设置为y+1，
	向服务器发送ACK报文段，这个报文段发送完毕以后，客户端和服务器端都进入ESTABLISHED状态，完成TCP三次握手。
192.0.0.1 [ACK=1]seq=3220029974 Ack=2179020843 win=6634 Len=8

BeanFactoryPostProcessor是在spring容器加载了bean的定义文件之后，在bean实例化之前执行的。
接口方法的入参是ConfigurrableListableBeanFactory，使用该参数，可以获取到相关bean的定义信息


BeanPostProcessor，可以在spring容器实例化bean之后，在执行bean的初始化方法前后，添加一些自己的处理逻辑。这里说的初始化方法，指的是下面两种：
1）bean实现了InitializingBean接口，对应的方法为afterPropertiesSet

2）在bean定义的时候，通过init-method设置的方法

注意：BeanPostProcessor是在spring容器加载了bean的定义文件并且实例化bean之后执行的。BeanPostProcessor的执行顺序是在BeanFactoryPostProcessor之后。
https://blog.csdn.net/caihaijiang/article/details/35552859


osr预编译