package com.github.kata.dao.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
  @XmlElement(name = "wsGeneralLedger")
  private List<WsGeneralLedger> ledgers;

  public List<WsGeneralLedger> getLedgers() {
    return ledgers;
  }

  public void setLedgers(List<WsGeneralLedger> ledgers) {
    this.ledgers = ledgers;
  }
}
