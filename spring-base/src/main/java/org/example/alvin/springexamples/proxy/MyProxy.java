package org.example.alvin.springexamples.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
模仿 JDK 动态代理，手写 newProxyInstance() 执行过程
 */
public class MyProxy {

  private final Logger logger = LogManager.getLogger(MyProxy.class);

  // 1. 通过拼凑字符串的形式拼凑出一个代理类 $Proxy0

  // 2. 将拼凑出的代理类以流的形式写入到磁盘中的 $Proxy0.java 文件中

  // 3. 使用运行时编译方法，将 $Proxy0.java 编译成 $Proxy0.class

  // 4. 使用自定义类加载器将编译好的 $Proxy0.class 加载到 JVM 内存中

  // 5. 实例化内存中的 $Proxy0.class，然后返回
}
