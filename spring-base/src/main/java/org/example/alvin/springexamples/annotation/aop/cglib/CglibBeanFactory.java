package org.example.alvin.springexamples.annotation.aop.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class CglibBeanFactory {

  public static Object getInstance() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(UserServiceImpl.class);
    CglibCallbackFilter callbackFilter = new CglibCallbackFilter();
    enhancer.setCallbackFilter(callbackFilter);

    Callback callBack0 = new DoSomethingInterceptor0();
    Callback callBack1 = new DoSomethingInterceptor1();
    Callback callBack2 = new DoSomethingInterceptor2();
    Callback noOp = NoOp.INSTANCE;
    Callback fixedValueCallback = new FixedValueInterceptor();
    Callback[] callbacks = {callBack0, callBack1, callBack2, noOp, fixedValueCallback};
    enhancer.setCallbacks(callbacks);
    return enhancer.create();
  }
}
