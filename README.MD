# 基于SpringCloud的抽奖系统
## 介绍  
&#160;&#160;本项目是一个基于SpringCloud的抽奖系统，登录采用的是单点登录。

## 需求场景:

假设有一个抽奖的业务场景，用户在某个时间可以参与抽奖，比如一共有100个奖，奖品是某个礼物,然后参与抽奖的用户可能有很多，而且是一瞬间参与进来。

## 模块介绍   
|       模块名        |   模块介绍   | 端口 |    启动顺序    |
| :-----------------: | :----------: | :--: | :------------: |
|  xns-server-eureka  |   服务注册   | 7001  |       １       |
| xns-service-lottery |   抽奖服务   | 8002  |       4        |
|  xns-service-redis  |  redis服务   | 8301 |       ２       |
|     xns-sso-api     |   公用接口   |   无  | clean->install |
| xns-service-sample  | 测试例子模块 |  8003 |       5        |
|   xns-sso-server    | 中央认证服务 |  8001 |       3        |

## 技术
使用SpringCloud+SpringBoot进行开发，基于Redis实现抽奖逻辑，抗住所有请求，使最后修改数据库的请求和奖品数量相等，以及使用RabbitMQ异步减库存等，来减少数据库的压力，并使用Hystrix进行限流。

## 学到了
- 对SpringCloud框架有了进一步的认识,学会了使用Jmeter进行接口并发测试。