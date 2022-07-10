package dubbo.consumer.anno.merge;

import com.alibaba.dubbo.rpc.cluster.Merger;


public class StringMerger implements Merger<String> {

  @Override
  public String merge(String... items) {
    if (items == null || items.length == 0) {
      return "there is no items to be merged";
    }
    return String.join("@", items);
  }
}
