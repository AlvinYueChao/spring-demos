package org.example.alvin.mybatisexamples.mapper;

import java.util.List;
import org.example.alvin.mybatisexamples.pojo.ConsultContractCardInfo;

public interface CommonMapper {

  List<ConsultContractCardInfo> queryContractByCardId();
}
