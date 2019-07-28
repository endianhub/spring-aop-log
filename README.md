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

<br>
<br>



**<aop:aspect>与<aop:advisor>的区别**

<aop:aspect>：定义切面（切面包括通知和切点） <br>
<aop:advisor>：定义通知器（通知器跟切面一样，也包括通知和切点） <br>

<aop:aspect>定义切面时，只需要定义一般的bean就行，而定义< aop:advisor>中引用的通知时，通知必须实现Advice接口。


**<aop:config proxy-target-class="true" /> **

proxy-target-class="true" 表示强制使用 CGLIB 技术来实现AOP，因为CGLIB是生成子类也就是代理类来实现的，所以proxy-target-class，表示是否代理目标类。<aop:config /> 就会由spring来选择，spring优先使用JDK动态代理来实现AOP。


**<aop:aspectj-autoproxy proxy-target-class="true" />**

proxy-target-class属性值决定是基于接口的还是基于类的代理被创建。 如果proxy-target-class 属性值被设置为true，那么基于类的代理将起作用（这时需要cglib库）。 如果proxy-target-class属值被设置为false或者这个属性被省略，那么标准的JDK基于接口的代理


**<aop:config expose-proxy="true">**

expose-proxy。为是否暴露当前代理对象为ThreadLocal模式。


<br>
<br>

补充一下知识点：

@ApiOperation和@ApiParam注解功用

@ApiOperation和@ApiParam为添加的API相关注解，个参数说明如下：  <br>
> @ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”）；其他参数可参考源码； <br>
> @ApiParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述”） <br>

springfox-swagger2、springfox-swagger-ui