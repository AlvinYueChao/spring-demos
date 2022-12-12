# Chapter09 Spring Cloud Gateway 构建微服务网关
## 过滤器工厂
### 模式：PRE
#### 1. AddRequestParameter. 
```yaml
spring:
  application:
    name: Gateway
  cloud:
    gateway:
      routes:
        - id: AddRequestParameter_Route
          uri: http://www.phei.com.cn
          predicates:
            - Method=GET
          filters:
            - AddRequestParameter=key_name,key_value

server:
  port: 8220
```
#### execution:
#### request:
```http request
GET http://localhost:8220/hello
```
#### result:
```text
21:50:02.732 [reactor-http-nio-3] DEBUG r.n.h.c.HttpClientConnect - [2e5eb7af-1, L:/192.168.1.4:50557 - R:www.phei.com.cn/218.249.32.140:80] Handler is being applied: {uri=http://www.phei.com.cn/hello?key_name=key_value, method=GET}
```
#### 2.AddRequestHeader
```yaml
spring:
  application:
    name: Gateway
  cloud:
    gateway:
      routes:
        - id: AddRequestHeader_Route
          uri: http://www.phei.com.cn
          predicates:
            - Method=GET
          filters:
            - AddRequestHeader=key_name,key_value

server:
  port: 8220
```
#### execution:
#### request:
```http request
GET http://localhost:8220/hello
```
#### result:
```text
14:18:45.437 [reactor-http-nio-3] DEBUG o.s.c.g.h.RoutePredicateHandlerMapping - Mapping [Exchange: GET http://localhost:8220/hello] to Route{id='AddRequestHeader_Route', uri=http://www.phei.com.cn:80, order=0, predicate=Methods: [GET], gatewayFilters=[[[AddRequestHeader key_name = 'key_value'], order = 1]], metadata={}}
```
//TODO

## 路由容错
```yaml
spring:
  application:
    name: Fault-Tolerance Routing
  cloud:
    gateway:
      routes:
        - id: hello Route
          uri: http://www.phei.com.cn
          predicates:
            - Path=/hello
        - id: Fault-Tolerance Routing
          uri: forward:/notfound
          predicates:
            - Path=/**

server:
  port: 8220
```
### execution
#### request
```http request
###
GET http://localhost:8220/hello

###
GET http://localhost:8220/helloworld
```
#### result
```text
15:28:23.464 [reactor-http-nio-2] DEBUG o.s.c.g.h.RoutePredicateHandlerMapping - Route matched: hello Route
15:28:23.464 [reactor-http-nio-2] DEBUG o.s.c.g.h.RoutePredicateHandlerMapping - Mapping [Exchange: GET http://localhost:8220/hello] to Route{id='hello Route', uri=http://www.phei.com.cn:80, order=0, predicate=Paths: [/hello], match trailing slash: true, gatewayFilters=[], metadata={}}
15:28:23.464 [reactor-http-nio-2] DEBUG o.s.c.g.h.RoutePredicateHandlerMapping - [a056db3d-1] Mapped to org.springframework.cloud.gateway.handler.FilteringWebHandler@380c8077

15:28:41.814 [reactor-http-nio-3] DEBUG o.s.w.s.a.HttpWebHandlerAdapter - [825fb6ef-2] HTTP GET "/helloworld"
15:28:41.815 [reactor-http-nio-3] DEBUG o.s.c.g.h.RoutePredicateHandlerMapping - Route matched: Fault-Tolerance Routing
15:28:41.815 [reactor-http-nio-3] DEBUG o.s.c.g.h.RoutePredicateHandlerMapping - Mapping [Exchange: GET http://localhost:8220/helloworld] to Route{id='Fault-Tolerance Routing', uri=forward:/notfound, order=0, predicate=Paths: [/**], match trailing slash: true, gatewayFilters=[], metadata={}}
```
## Gateway Limit
```yaml
spring:
  application:
    name: Gateway Limiter
  redis:
    database: 1
    host: 192.168.20.10
  cloud:
    consul:
      discovery:
        register: false
      host: 192.168.20.10
      port: 8500
    gateway:
      discovery:
        locator:
          enabled: true
      routes:
        - id: test-ip
          uri: lb://service-provider
          predicates:
            - Path=/hello/**
          filters:
            - name: RequestRateLimiter
              args:
                redis-rate-limiter:
                  replenishRate: 1
                  burstCapacity: 2
                key-resolver: "#{@ipKeyResolver}"

server:
  port: 8221
```

### execution
#### request
```http request
GET http://localhost:8221/hello
```
#### result
```text
21:45:18.858 [lettuce-nioEventLoop-5-1] DEBUG o.s.c.g.f.r.RedisRateLimiter - response: Response{allowed=false, headers={X-RateLimit-Remaining=0, X-RateLimit-Requested-Tokens=1, X-RateLimit-Burst-Capacity=2, X-RateLimit-Replenish-Rate=1}, tokensRemaining=-1}
21:45:18.858 [lettuce-nioEventLoop-5-1] DEBUG o.s.w.s.a.HttpWebHandlerAdapter - [dcf226d2-17] Completed 429 TOO_MANY_REQUESTS
```