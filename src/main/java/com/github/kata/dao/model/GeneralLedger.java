package com.github.kata.dao.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "general_ledger")
public class GeneralLedger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String internalID;
    private Double balance;
    private Integer category;
    private String collectif;
    private String comment;
    private Double credit;
    private String currency;
    private Double currencyAmount;

    private LocalDate date;
    private Double debit;
    private String description;
    private String header;
    private Long journalIndex;
    private LocalDate lastModifyDate;

    @Embedded
    private LetterInfo letterInfo;

    @Embedded
    private PaymentInfo paymentInfo;

    private String name;
    private String number;
    private String periodEnd;
    private String periodStart;
    private Integer piece;
    private String qtyUnit;
    private Double quantity;
    private String ref;
    private String term;
    private String voucherID;
    private String voucherRef;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getInternalID() {
        return internalID;
    }

    public void setInternalID(String internalID) {
        this.internalID = internalID;
    }

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

    public String getVoucherRef() {
        return voucherRef;
    }

    public void setVoucherRef(String voucherRef) {
        this.voucherRef = voucherRef;
    }

    public String getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public LetterInfo getLetterInfo() {
        return letterInfo;
    }

    public void setLetterInfo(LetterInfo letterInfo) {
        this.letterInfo = letterInfo;
    }

    public Long getJournalIndex() {
        return journalIndex;
    }

    public void setJournalIndex(Long journalIndex) {
        this.journalIndex = journalIndex;
    }

    public LocalDate getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(LocalDate lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(Double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}