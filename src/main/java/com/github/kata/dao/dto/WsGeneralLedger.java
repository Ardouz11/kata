package com.github.kata.dao.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class WsGeneralLedger {
  private Double balance;
  private Integer category;
  private String collectif;
  private String comment;
  private Double credit;
  private String currency;
  private Double currencyAmount;
  private String date;
  private Double debit;
  private String description;
  private String header;
  private String internalID;
  private Long journalIndex;
  private String lastModifyDate;
  private String letter;
  private String letterID;
  private String letteredInBetween;
  private String name;
  private String number;
  private Integer paymentMethod;
  private String paymentMethodDescription;
  private String periodEnd;
  private String periodStart;
  private Integer piece;
  private String qtyUnit;
  private Double quantity;
  private String ref;
  private String term;
  private String voucherID;
  private String voucherRef;

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

  public String getCollectif() {
    return collectif;
  }

  public void setCollectif(String collectif) {
    this.collectif = collectif;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Double getCredit() {
    return credit;
  }

  public void setCredit(Double credit) {
    this.credit = credit;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Double getCurrencyAmount() {
    return currencyAmount;
  }

  public void setCurrencyAmount(Double currencyAmount) {
    this.currencyAmount = currencyAmount;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Double getDebit() {
    return debit;
  }

  public void setDebit(Double debit) {
    this.debit = debit;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getInternalID() {
    return internalID;
  }

  public void setInternalID(String internalID) {
    this.internalID = internalID;
  }

  public Long getJournalIndex() {
    return journalIndex;
  }

  public void setJournalIndex(Long journalIndex) {
    this.journalIndex = journalIndex;
  }

  public String getLastModifyDate() {
    return lastModifyDate;
  }

  public void setLastModifyDate(String lastModifyDate) {
    this.lastModifyDate = lastModifyDate;
  }

  public String getLetter() {
    return letter;
  }

  public void setLetter(String letter) {
    this.letter = letter;
  }

  public String getLetterID() {
    return letterID;
  }

  public void setLetterID(String letterID) {
    this.letterID = letterID;
  }

  public String getLetteredInBetween() {
    return letteredInBetween;
  }

  public void setLetteredInBetween(String letteredInBetween) {
    this.letteredInBetween = letteredInBetween;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

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

  public String getPeriodEnd() {
    return periodEnd;
  }

  public void setPeriodEnd(String periodEnd) {
    this.periodEnd = periodEnd;
  }

  public String getPeriodStart() {
    return periodStart;
  }

  public void setPeriodStart(String periodStart) {
    this.periodStart = periodStart;
  }

  public Integer getPiece() {
    return piece;
  }

  public void setPiece(Integer piece) {
    this.piece = piece;
  }

  public String getQtyUnit() {
    return qtyUnit;
  }

  public void setQtyUnit(String qtyUnit) {
    this.qtyUnit = qtyUnit;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getVoucherID() {
    return voucherID;
  }

  public void setVoucherID(String voucherID) {
    this.voucherID = voucherID;
  }

  public String getVoucherRef() {
    return voucherRef;
  }

  public void setVoucherRef(String voucherRef) {
    this.voucherRef = voucherRef;
  }
}
