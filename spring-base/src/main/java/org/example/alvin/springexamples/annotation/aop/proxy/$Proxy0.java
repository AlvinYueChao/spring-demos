package org.example.alvin.springexamples.annotation.aop.proxy;
import java.lang.reflect.Method;
public class $Proxy0 implements org.example.alvin.springexamples.annotation.aop.proxy.People{
MyInvocationHandler h;
public $Proxy0(MyInvocationHandler h) {
this.h = h;
}public void findMM() throws Throwable {
Method md = org.example.alvin.springexamples.annotation.aop.proxy.People.class.getMethod("findMM", new Class[]{});
this.h.invoke(this, md, null);
}
}