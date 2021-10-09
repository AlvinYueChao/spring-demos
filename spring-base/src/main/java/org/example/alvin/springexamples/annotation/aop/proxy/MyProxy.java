package org.example.alvin.springexamples.annotation.aop.proxy;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
模仿 JDK 动态代理，手写 newProxyInstance() 执行过程
 */
public class MyProxy {

  private final static String PATH = "";

  private final static Logger logger = LogManager.getLogger(MyProxy.class);

  protected static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, MyInvocationHandler h) {
    // 1. 通过拼凑字符串的形式拼凑出一个代理类 $Proxy0
    String javaStr = getJavaStr(interfaces);
    // 2. 将拼凑出的代理类以流的形式写入到磁盘中的 $Proxy0.java 文件中
    createFile(javaStr);
    // 3. 使用运行时编译方法，将 $Proxy0.java 编译成 $Proxy0.class
    compiler();
    // 4. 使用自定义类加载器将编译好的 $Proxy0.class 加载到 JVM 内存中
    // 5. 实例化内存中的 $Proxy0.class，然后把实例返回

    return null;
  }

  private static void createFile(String javaStr) {

  }

  private static String getJavaStr(Class<?>[] interfaces) {
    Method[] methods = interfaces[0].getMethods();
    return "package org.example.alvin.springexamples.annotation.aop.proxy;" + System.lineSeparator()
        + "import java.lang.reflect.Method;" + System.lineSeparator()
        + "public class $Proxy0 implements " + interfaces[0].getName() + "{" + System.lineSeparator()
        + "MyInvocationHandler h;" + System.lineSeparator()
        + "public $Proxy0(MyInvocationHandler h) {" + System.lineSeparator()
        + "this.h = h" + System.lineSeparator() + "}"
        + getMethodStr(methods, interfaces[0]) + System.lineSeparator() + "}";
  }

  private static String getMethodStr(Method[] methods, Class<?> interfaceInstance) {
    StringBuilder proxyMethod = new StringBuilder();

    for (Method method : methods) {
      proxyMethod.append("public void ").append(method.getName()).append("() throws Throwable {").append(System.lineSeparator()).append("Method md = ")
          .append(interfaceInstance.getName()).append(".class.getMethod(\"").append(method.getName()).append("\", new Class[]{});")
          .append(System.lineSeparator()).append("this.h.invoke(this, md, null);").append(System.lineSeparator()).append("}");
    }
    return proxyMethod.toString();
  }

  private static void compiler() {
    JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
    try (StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, StandardCharsets.UTF_8)) {
      Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(PATH + "\\$Proxy0.java");
      CompilationTask task = systemJavaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
      task.call();
    } catch (IOException e) {
      logger.warn("Caught exception when compiling the java file", e);
    }
  }
}
