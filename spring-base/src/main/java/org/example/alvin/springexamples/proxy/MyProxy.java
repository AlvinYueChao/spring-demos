package org.example.alvin.springexamples.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
ģ�� JDK ��̬������д newProxyInstance() ִ�й���
 */
public class MyProxy {

  private final Logger logger = LogManager.getLogger(MyProxy.class);

  // 1. ͨ��ƴ���ַ�������ʽƴ�ճ�һ�������� $Proxy0

  // 2. ��ƴ�ճ��Ĵ�������������ʽд�뵽�����е� $Proxy0.java �ļ���

  // 3. ʹ������ʱ���뷽������ $Proxy0.java ����� $Proxy0.class

  // 4. ʹ���Զ����������������õ� $Proxy0.class ���ص� JVM �ڴ���

  // 5. ʵ�����ڴ��е� $Proxy0.class��Ȼ�󷵻�
}
