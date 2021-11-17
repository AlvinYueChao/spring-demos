package org.example.alvin.mybatisexamples.mapper;

import java.util.List;
import java.util.Map;
import org.example.alvin.mybatisexamples.pojo.ConsultContractCardInfo;

public interface CommonMapper {

  List<ConsultContractCardInfo> queryContractByCardId();

  List<Map<?, ?>> queryUserByPsptId(Map<?, ?> params);


}
