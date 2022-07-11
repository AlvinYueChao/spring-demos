package dubbo.consumer.anno.merge;

import com.alibaba.dubbo.rpc.cluster.Merger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringMerger implements Merger<String> {

  @Override
  public String merge(String... items) {
    if (items == null || items.length == 0) {
      return "there is no items to be merged";
    }
    log.info("merge multiple string items into ones");
    return String.join("@", items);
  }
}
