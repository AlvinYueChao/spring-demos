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
  ������������޶���������ᱻʵ����������spring����
  MetadataReaderFactory ������������޶�����ȡ MetadataReader
  metadataReader.getAnnotationMetadata() �õ� AnnotationMetadata ����Ȼ���װ�� SourceClass ����
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

    // ���ܻ����̰߳�ȫ����
    private final List<Entry> list = new ArrayList<>();

    /*
    �ռ���Ҫʵ��������
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
