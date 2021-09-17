package org.example.alvin.springexamples.annotation.deferredimport;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class DeferredImportSelectorDemo implements DeferredImportSelector {

  private final Logger logger = LogManager.getLogger(DeferredImportSelectorDemo.class);

  /*
  返回类的完整限定名，该类会被实例化并交给spring管理
  MetadataReaderFactory 根据类的完整限定名获取 MetadataReader
  metadataReader.getAnnotationMetadata() 拿到 AnnotationMetadata 对象，然后包装成 SourceClass 对象
   */
  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    logger.info("====== DeferredImportSelectorDemo.selectImports ======");
    return new String[]{SelectImportBean.class.getName()};
  }

  @Override
  public Class<? extends Group> getImportGroup() {
    logger.info("====== DeferredImportSelectorDemo.getImportGroup ======");
    return DeferredImportSelectorGroupDemo.class;
  }

  private static class DeferredImportSelectorGroupDemo implements DeferredImportSelector.Group {

    private final Logger logger = LogManager.getLogger(DeferredImportSelectorGroupDemo.class);

    // 可能会有线程安全问题
    private final List<Entry> list = new ArrayList<>();

    /*
    收集需要实例化的类
     */
    @Override
    public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
      logger.info("====== DeferredImportSelectorGroupDemo.process ======");
      String[] selectImports = selector.selectImports(metadata);
      for (String selectImport : selectImports) {
        list.add(new Entry(metadata, selectImport));
      }
    }

    @Override
    public Iterable<Entry> selectImports() {
      logger.info("====== DeferredImportSelectorGroupDemo.selectImports ======");
      return list;
    }
  }
}
