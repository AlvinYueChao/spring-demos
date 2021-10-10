package org.example.alvin.springexamples.annotation.aop.proxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
ģ�� JDK ��̬������д newProxyInstance() ִ�й���
 */
public class MyProxy {

  private final static String PATH = "D:\\WorkSpace\\Java\\Gradle\\spring-demos\\spring-base\\src\\main\\java\\org\\example\\alvin\\springexamples\\annotation\\aop\\proxy";

  private final static Logger logger = LogManager.getLogger(MyProxy.class);

  public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, MyInvocationHandler h) {
    // 1. ͨ��ƴ���ַ�������ʽƴ�ճ�һ�������� $Proxy0
    String javaStr = getJavaStr(interfaces);
    // 2. ��ƴ�ճ��Ĵ�������������ʽд�뵽�����е� $Proxy0.java �ļ���
    createFile(javaStr);
    // 3. ʹ������ʱ���뷽������ $Proxy0.java ����� $Proxy0.class
    compiler();
    // 4. ʹ���Զ����������������õ� $Proxy0.class ���ص� JVM �ڴ���
    // 5. ʵ�����ڴ��е� $Proxy0.class��Ȼ���ʵ������
    MyClassLoader myClassLoader = new MyClassLoader(PATH);
    try {
      Class<?> $Proxy0 = myClassLoader.findClass("$Proxy0");
      Constructor<?> constructor = $Proxy0.getConstructor(MyInvocationHandler.class);
      return constructor.newInstance(h);
    } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
      logger.warn("Caught exception when trying to get the instance for the class", e);
    }

    return null;
  }

  private static void createFile(String javaStr) {
    File file = new File(PATH + "\\$Proxy0.java");
    try (FileWriter fw = new FileWriter(file)) {
      fw.write(javaStr);
      fw.flush();
    } catch (IOException e) {
      logger.warn("Caught exception when trying to write to the java file: {}", file, e);
    }
  }

  private static String getJavaStr(Class<?>[] interfaces) {
    Method[] methods = interfaces[0].getMethods();
    return "package org.example.alvin.springexamples.annotation.aop.proxy;" + System.lineSeparator()
        + "import java.lang.reflect.Method;" + System.lineSeparator()
        + "public class $Proxy0 implements " + interfaces[0].getName() + "{" + System.lineSeparator()
        + "MyInvocationHandler h;" + System.lineSeparator()
        + "public $Proxy0(MyInvocationHandler h) {" + System.lineSeparator()
        + "this.h = h;" + System.lineSeparator() + "}"
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
