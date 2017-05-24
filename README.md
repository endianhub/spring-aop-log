# Spring AOP日志拦截

本项目主要是对Controller的拦截，并且对Service出现异常时进行拦截，从而记录在数据库中


### 难点
**1.拦截Controller注意事项**

想要拦截Controller时要把AOP放到spring-mvc.xml中，否则在拦截不了，本人就在此花了好些时间

**2.aop:aspectj-autoproxy**

```xml
<aop:aspectj-autoproxy proxy-target-class="true" />
```

proxy-target-class：属性值决定是基于接口的还是基于类的代理被创建。
 如果proxy-target-class 属性值被设置为true，那么基于类的代理将起作用（这时需要cglib库）。
 如果proxy-target-class属值被设置为false或者这个属性被省略，那么标准的JDK基于接口的代理 


**3.order**

```xml
<aop:aspect order="2" />
```

spring AOP事务与Afterthrowing冲突是要设置"order"参数，当在执行事务时遇到错误（exception）就会将插入的数据进行回滚，如果想在事务执行遇到exception回滚以后再去控制它去进入afterthowing就要用到"order"参数。
"order"参数，这个参数是用来控制aop通知的优先级，值越小，优先级越高。
