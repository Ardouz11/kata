package com.github.kata.dao.model;

import jakarta.persistence.*;

@Embeddable
public class LetterInfo {
  private String letter;
  private String letterID;
  private String letteredInBetween;

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
}
