package com.github.kata.dao.model;

import jakarta.persistence.*;

@Embeddable
public class PaymentInfo {
  private Integer paymentMethod;
  private String paymentMethodDescription;

  public Integer getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(Integer paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getPaymentMethodDescription() {
    return paymentMethodDescription;
  }

  public void setPaymentMethodDescription(String paymentMethodDescription) {
    this.paymentMethodDescription = paymentMethodDescription;
  }
}
