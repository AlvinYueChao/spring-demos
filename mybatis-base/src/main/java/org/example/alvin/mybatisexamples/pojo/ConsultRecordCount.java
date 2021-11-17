package org.example.alvin.mybatisexamples.pojo;

import org.apache.ibatis.type.Alias;

@Alias("ConsultRecordCount")
public class ConsultRecordCount {

  private String psptId;

  private Integer isproduce;

  private Integer unproduce;

  public String getPsptId() {
    return psptId;
  }

  public void setPsptId(String psptId) {
    this.psptId = psptId;
  }

  public Integer getIsproduce() {
    return isproduce;
  }

  public void setIsproduce(Integer isproduce) {
    this.isproduce = isproduce;
  }

  public Integer getUnproduce() {
    return unproduce;
  }

  public void setUnproduce(Integer unproduce) {
    this.unproduce = unproduce;
  }
}
