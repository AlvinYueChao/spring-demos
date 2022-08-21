package org.example.alvin.springexamples.annotation.aop.proxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyClassLoader extends ClassLoader {

  private final Logger logger = LogManager.getLogger(MyClassLoader.class);

  private final File dir;

  public MyClassLoader(String path) {
    this.dir = new File(path);
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    File classFile = new File(this.dir, "\\" + name + ".class");
    if (classFile.exists()) {
      try {
        FileInputStream inputStream = new FileInputStream(classFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
          byteArrayOutputStream.write(buffer, 0, length);
        }
        // 将 class 文件加载进 JVM 内存中后，返回反射对象
        return defineClass("org.example.alvin.springexamples.annotation.aop.proxy." + name, byteArrayOutputStream.toByteArray(), 0,
            byteArrayOutputStream.size());
      } catch (FileNotFoundException e) {
        logger.warn("Cannot find the path: {}", classFile, e);
      } catch (IOException e) {
        logger.warn("Caught exception when reading the class file into the buffer", e);
      }
    }

    return super.findClass(name);
  }
}
