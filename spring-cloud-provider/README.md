# Chapter09 Spring Cloud Gateway 构建微服务网关
## 过滤器工厂
### 1. AddRequestParameter
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
            - AddRequestParameter=key_name,key_value

server:
  port: 8220
```
### executing result:
```text
21:50:02.732 [reactor-http-nio-3] DEBUG r.n.h.c.HttpClientConnect - [2e5eb7af-1, L:/192.168.1.4:50557 - R:www.phei.com.cn/218.249.32.140:80] Handler is being applied: {uri=http://www.phei.com.cn/hello?key_name=key_value, method=GET}
```
### 2.AddRequestHeader
