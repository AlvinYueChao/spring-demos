package org.example.alvin.mybatisexamples.mapper;

import java.util.List;
import java.util.Map;
import org.example.alvin.mybatisexamples.pojo.ConsultContractCardInfo;
import org.example.alvin.mybatisexamples.pojo.ConsultRecordCount;

public interface CommonMapper {

  List<ConsultContractCardInfo> queryContractByCardId();

  List<Map<?, ?>> queryUserByPsptId(Map<?, ?> params);

  List<ConsultRecordCount> queryRecordCount(Map<?, ?> params);

  List<ConsultContractCardInfo> getTop4Contracts(Map<?, ?> params);

  List<ConsultContractCardInfo> getTop4ContractsLazy(Map<?, ?> params);
}
