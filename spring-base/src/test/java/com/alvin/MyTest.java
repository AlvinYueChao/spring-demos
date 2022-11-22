package com.alvin;

import com.alvin.service.OrderService;
import com.alvin.service.UserService;
import com.spring.MyApplicationContext;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Slf4j
class MyTest {

  @Test
  void test1() {
    MyApplicationContext applicationContext = new MyApplicationContext(AppConfig.class);
    log.info("{}", applicationContext.getBean("userService"));
    log.info("{}", applicationContext.getBean("userService"));
    log.info("{}", applicationContext.getBean("orderService"));
  }

  @Test
  void test2() {
    MyApplicationContext applicationContext = new MyApplicationContext(AppConfig.class);
    UserService userService = (UserService) applicationContext.getBean("userService");
    userService.test1();
  }

  @Test
  void test3() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = (UserService) applicationContext.getBean("userService");
    userService.test1();
    userService.test1();
    userService.test1();
  }

  @Test
  void test4() {
    // 由@Bean注入的带有@Configuration注解的类，不会作为配置类(spring-5.3.22及以下)
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderService orderService = (OrderService) applicationContext.getBean("orderService");
    orderService.test();
  }

  @Test
  void test5() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = applicationContext.getBean(UserService.class);
    log.info("{}", userService);
  }

  @Test
  void test6() {
    UserService target = new UserService();
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(UserService.class);
    enhancer.setCallbacks(new Callback[]{
        (MethodInterceptor) (o, method, objects, methodProxy) -> {
          log.info("before...");
          /*
          o: userService的代理对象 com.alvin.service.UserService@26dcd8c0
          method: public void com.alvin.service.UserService.test()
          objects: test()方法入参
          methodProxy: org.springframework.cglib.proxy.MethodProxy@1a5f7e7c
          CGLib实现原理：UserServiceProxy extends UserService {}
           */
//          Object result = methodProxy.invoke(target, objects);
          // 和71行代码效果相同
//          Object result = method.invoke(target, objects);
          // java.lang.StackOverflowError，cpu占用率100%
//          Object result = method.invoke(o, objects);
          // 和71行代码效果相同
          Object result = methodProxy.invokeSuper(o, objects);
          log.info("after...");
          return result;
        }
    });
    UserService proxyInstance = (UserService) enhancer.create();
    proxyInstance.test();
  }

  @Test
  void test7() {
    UserService target = new UserService();
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(target);
    proxyFactory.addAdvice((AfterReturningAdvice) (returnValue, method, args, target1) -> {
      // TODO
    });
    proxyFactory.addAdvice((MethodBeforeAdvice) (method, args, target12) -> {
      // TODO
    });
    proxyFactory.addAdvice((MethodBeforeAdvice) (method, args, target13) -> {
      // TODO
    });
    proxyFactory.addAdvice(new ThrowsAdvice() {
      // TODO
      public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        // TODO
      }
    });
    proxyFactory.addAdvice((org.aopalliance.intercept.MethodInterceptor) invocation -> {
      // TODO
      log.info("方法执行前。。。");
      Object result = invocation.proceed();
      log.info("方法执行后");
      return result;
    });
    UserService proxy = (UserService) proxyFactory.getProxy();
    proxy.test();
  }

  @Test
  void test8() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = applicationContext.getBean(UserService.class);
    userService.test();
  }

  @Test
  void test9() {
    AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
    webApplicationContext.scan("com.alvin");
    webApplicationContext.refresh();
    webApplicationContext.start();
  }
}
