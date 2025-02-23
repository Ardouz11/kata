package com.github.kata.dao.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WCFRestIRFGeneralLegerResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class WsGeneralLedgerListWrapper {

  @XmlElement(name = "response")
  private Response response;

  @XmlElement(name = "data")
  private Data data;

  public Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}
