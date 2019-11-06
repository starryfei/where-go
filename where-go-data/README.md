# where-go-data
### 展示页面数据的服务
使用PageHelper进行数据分页


### 信息发布和通知关注者实现思路
 被关注者发送文章，会推送到所有关注者的缓存中，用户登录自动拉取该文章
 对每个用户都维护一块缓存。当用户发布微博的时候，相应的后台程序可以先查询一下他的关注着，
然后将这条微博插入到所有关注着的缓存中。（当然这个缓存会按时间线排序，也会有一定的容量大小限制等，这些细节也不多做赘述。）
这样当用户上线逛微博的时候，那么TA就可以直接从缓存中读取，读取的性能有了质的飞升。


### 问题
发现使用FeignClient调用其他服务的时候一直走熔断器的逻辑，而事实上服务端已经被调用,只是没有结果返回
```java
@FeignClient(value = "WHERE-GO-USER", fallback = ShareExpreienceServiceImpl.class )
public interface ShareExperienceService {
    @GetMapping("followerList")
    List<Object> getFollowerList();

}
```
抛出的异常是
```java
com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList` out of START_OBJECT token
```
刚开始以为是传递的对象没有添加无参的构造器，因为我使用的是@Builder模式，但是添加无参构造器还是抛这个异常，仔细分析了一下，是ArrayList的问题，
发现服务返回的是json格式的数据，不能直接使用List进行转化，将返回参数改为String类型即可,在去将json转化为需要的对象即可
```java
 @GetMapping("followerList")
    String getFollowerList();
```

