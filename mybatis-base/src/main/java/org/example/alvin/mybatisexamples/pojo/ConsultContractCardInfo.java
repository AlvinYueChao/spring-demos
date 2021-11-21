package org.example.alvin.mybatisexamples.pojo;

import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("ConsultContractCardInfo")
public class ConsultContractCardInfo {

  private Integer contractId;

  private String contractCode;

  private String activeTime;

  private Integer state;

  private List<ConsultIdCardInfo> infos;

  public Integer getContractId() {
    return contractId;
  }

  public void setContractId(Integer contractId) {
    this.contractId = contractId;
  }

  public String getContractCode() {
    return contractCode;
  }

  public void setContractCode(String contractCode) {
    this.contractCode = contractCode;
  }

  public String getActiveTime() {
    return activeTime;
  }

  public void setActiveTime(String activeTime) {
    this.activeTime = activeTime;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public List<ConsultIdCardInfo> getInfos() {
    return infos;
  }

  public void setInfos(List<ConsultIdCardInfo> infos) {
    this.infos = infos;
  }

  @Override
  public String toString() {
    return String.format("ConsultContractCardInfo{contractId=%s, contractCode=%s, activeTime=%s, state=%s, infos=%s}", this.contractId, this.contractCode,
        this.activeTime, this.state, this.infos.toString());
  }
}
